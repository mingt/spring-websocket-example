package com.websocket.user;

import com.websocket.model.WsUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

/**
 * 基于 Redis 的 WS 在线用户实现.
 *
 * <p>
 * Date: 2020/9/6 Ahming
 * </p>
 */
public class RedisOnlineWsUserStore implements OnlineWsUserStore {
    private static final Logger logger = LoggerFactory.getLogger(RedisOnlineWsUserStore.class);

    /**
     * Redis 操作所用 RedisTemplate .
     */
    private RedisTemplate redisTemplate;

    // /**
    //  * Instantiates a new Redis online ws user store.
    //  */
    // public RedisOnlineWsUserStore() {
    // }

    /**
     * Instantiates a new Redis online ws user store.
     *
     * @param redisTemplate the redis template
     */
    public RedisOnlineWsUserStore(RedisTemplate redisTemplate) {
        if (redisTemplate == null) {
            throw new IllegalArgumentException("redisTemplate must not be null");
        }
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean verifyChannel(String channelId) {
        return ChatConfig.isChatRoomChannel(channelId);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<WsUser> getChannelUsers(String channelId) {
        if (!verifyChannel(channelId)) {
            return Collections.emptyList();
        }

        final String uidSetKey = String.format(OnlineWsUserConstants.USERS_UID_SET_KEY_PATTERN, channelId);

        // 先查询用户列表 Set
        Cursor<Object> cursor = redisTemplate.opsForSet().scan(uidSetKey,
            ScanOptions.NONE // TODO: 目前获取全部。 prod 生产环境应该自定义 count 设较小的值循环扫描
        );
        List<WsUser> result = new ArrayList<>();
        try {
            String userHashKey = null;
            while (cursor.hasNext()) {
                Integer uid = (Integer) cursor.next();
                // 找用户信息
                userHashKey = String.format(OnlineWsUserConstants.USER_HASH_KEY_PATTERN, channelId, uid);
                String name = (String) redisTemplate.opsForHash().get(userHashKey, "name");
                WsUser wsUser = new WsUser(null, uid, null, name, null);
                result.add(wsUser);
            }

        } catch (Exception ex) {
            logger.warn("Scan exception", ex);
            result = Collections.emptyList(); // 出错时清空
        } finally {
            try {
                cursor.close();
            } catch (IOException ex1) {
                logger.warn("Scan cursor close exception", ex1);
            }
        }

        return result;
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<WsUser> addChannelUserThreadSafe(String channelId, WsUser wsUser) {
        if (!verifyChannel(channelId)) {
            return Collections.emptyList();
        }

        // TODO: 注意极端临界条件，待重新评估并发情况，是否需要活用 putIfAbsent, replace 等方法保证数据一致

        // 使用 Hash 存储
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", wsUser.getId());
        userMap.put("name", wsUser.getName());
        // userMap.put("role", wsUser.getRole()); // 不能存null值
        final String userHashKey =
            String.format(OnlineWsUserConstants.USER_HASH_KEY_PATTERN, channelId, wsUser.getUid());
        redisTemplate.opsForHash().putAll(userHashKey, userMap);

        // 使用 Set 保存 uid 列表
        final String uidSetKey = String.format(OnlineWsUserConstants.USERS_UID_SET_KEY_PATTERN, channelId);
        redisTemplate.opsForSet().add(uidSetKey, wsUser.getUid());

        return new ArrayList<>(0);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<WsUser> removeChannelUser(String channelId, WsUser wsUser) {
        if (!verifyChannel(channelId)) {
            return Collections.emptyList();
        }

        // uid Set 移除
        final String uidSetKey = String.format(OnlineWsUserConstants.USERS_UID_SET_KEY_PATTERN, channelId);
        redisTemplate.opsForSet().remove(uidSetKey, wsUser.getUid());

        final String userHashKey =
            String.format(OnlineWsUserConstants.USER_HASH_KEY_PATTERN, channelId, wsUser.getUid());
        redisTemplate.opsForHash().delete(userHashKey, "id");
        redisTemplate.opsForHash().delete(userHashKey, "name");

        return new ArrayList<>(0);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public boolean removeUserFromAllChannel(WsUser wsUser) {
        logger.info("removeUserFromAllChannel");

        Set<String> keys = (Set<String>) redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> keysTmp = new HashSet<>();
            Cursor<byte[]> cursor = connection.scan(
                new ScanOptions.ScanOptionsBuilder().match(OnlineWsUserConstants.USERS_UID_SET_KEY_ALL_PATTERN)
                    .count(1000).build());
            while (cursor.hasNext()) {
                keysTmp.add(new String(cursor.next()));
            }
            return keysTmp;
        });

        for (String uidSetKey : keys) {
            redisTemplate.opsForSet().remove(uidSetKey, wsUser.getUid());

            final String channelId = uidSetKey.substring(OnlineWsUserConstants.USERS_UID_SET_KEY_PREFIX.length());
            final String userHashKey =
                String.format(OnlineWsUserConstants.USER_HASH_KEY_PATTERN, channelId, wsUser.getUid());
            redisTemplate.opsForHash().delete(userHashKey, "id");
            redisTemplate.opsForHash().delete(userHashKey, "name");
        }

        return true;
    }
}
