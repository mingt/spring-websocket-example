package com.websocket.user;

import com.websocket.model.WsUser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单实例上的内存 WS 在线用户实现.
 *
 * <p>Date: 2020/9/6 Ahming</p>
 */
public class MemoryOnlineWsUserStore implements OnlineWsUserStore {
    private static final Logger logger = LoggerFactory.getLogger(MemoryOnlineWsUserStore.class);

    /**
     * 用于维护在线用户的映射（Key:频道id，Value:该频道在线用户列表）.
     *
     * <p>(重要) 当前只是实现了单机（或说单个应用实例）中的在线用户列表。需要支持集群的话，要更多补充、扩展。</p>
     *
     */
    private static final Map<String, List<WsUser>> ONLINE_USERS_MAP = new ConcurrentHashMap<>();

    @Override
    public boolean verifyChannel(String channelId) {
        return ChatConfig.isChatRoomChannel(channelId);
    }

    @Override
    public List<WsUser> getChannelUsers(String channelId) {
        if (!verifyChannel(channelId)) {
            return Collections.emptyList();
        }

        List<WsUser> channelUsers = ONLINE_USERS_MAP.get(channelId);
        if (channelUsers == null) {
            return Collections.emptyList();
        }
        return channelUsers;
    }

    @Override
    public List<WsUser> addChannelUserThreadSafe(String channelId, WsUser wsUser) {
        if (!verifyChannel(channelId)) {
            return Collections.emptyList();
        }

        // ahming notes: 注意极端临界条件，活用 interface ConcurrentMap.putIfAbsent, replace 等方法保证数据一致
        List<WsUser> channelUsers;
        while (true) {
            channelUsers = ONLINE_USERS_MAP.get(channelId);
            // 下面是要区分是否为 null ,而不是数组 null 或非 null 但 size = 0
            if (channelUsers == null) { // wrong: (CollectionUtils.isEmpty(channelUsers)) {
                // 如果当前频道用户列表为空，则新创建一个频道用户列表，并将当前用户添加进去
                channelUsers = new ArrayList<>();
                channelUsers.add(wsUser);
                // 下面与 null 比较，要注意成功新增才返回 null ，如果已存在才非空，返回已存在的值
                if ((ONLINE_USERS_MAP.putIfAbsent(channelId, channelUsers)) == null) {
                    break;
                }
            } else {
                if (!channelUsers.contains(wsUser)) {
                    // 如果当前频道用户列表中不包含当前用户，则将其加入频道用户列表中去
                    List<WsUser> newChannelUsers = new ArrayList<>(channelUsers);
                    // newChannelUsers.addAll(channelUsers);
                    newChannelUsers.add(wsUser);
                    if (ONLINE_USERS_MAP.replace(channelId, channelUsers, newChannelUsers)) {
                        channelUsers = newChannelUsers;
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        return channelUsers;
    }

    @Override
    public List<WsUser> removeChannelUser(String channelId, WsUser wsUser) {
        if (!verifyChannel(channelId)) {
            return Collections.emptyList();
        }

        List<WsUser> channelUsers = ONLINE_USERS_MAP.get(channelId);
        if (CollectionUtils.isNotEmpty(channelUsers)) {
            // 如果当前频道用户列表中包含当前用户，则将其从频道用户列表中移除
            channelUsers.remove(wsUser);
        }

        return channelUsers;
    }

    @Override
    public boolean removeUserFromAllChannel(WsUser wsUser) {
        boolean isAllOk = true;

        // 断开WebSocket连接时，将从所有频道用户列表中将当前用户移除
        for (List<WsUser> userList : ONLINE_USERS_MAP.values()) {
            boolean removeOneResult = userList.remove(wsUser);
            isAllOk = isAllOk && removeOneResult; // if any a removeOneResult false, then isAllOk false
        }

        return isAllOk;
    }
}
