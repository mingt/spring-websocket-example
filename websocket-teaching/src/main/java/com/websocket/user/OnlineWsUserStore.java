package com.websocket.user;

import com.websocket.model.WsUser;
import java.util.List;

/**
 * 在线用户存储实现，所需接口.
 *
 * <p>Date: 2020/9/6 Ahming</p>
 */
public interface OnlineWsUserStore {

    /**
     * 检查是否为需要维护在线用户的频道.
     *
     * @param channelId 频道ID
     * @return 如果需要，返回 true ，否则 false
     */
    boolean verifyChannel(String channelId);

    /**
     * 返回指定频道的在线用户.
     *
     * @param channelId 频道ID
     * @return 在线用户列表 channel users
     */
    List<WsUser> getChannelUsers(String channelId);

    /**
     * 线程安全地在指定频道加入在线用户.
     *
     * <p>确保线程安全</p>
     *
     * @param channelId 频道ID
     * @param wsUser 用户信息
     * @return 加入后该频道的在线用户。注意某些实现目前可能返回空列表，未强制要求，因为可能用户列表过大，并不必要全部返回。
     */
    List<WsUser> addChannelUserThreadSafe(String channelId, WsUser wsUser);

    /**
     * 在指定频道移除某在线用户.
     *
     * @param channelId 频道ID
     * @param wsUser 用户信息
     * @return 移除后该频道的在线用户。注意某些实现目前可能返回空列表，未强制要求，因为可能用户列表过大，并不必要全部返回。
     */
    List<WsUser> removeChannelUser(String channelId, WsUser wsUser);

    /**
     * 在全部相关频道移除某在线用户.
     *
     * <p>只要 disconnect ，这个接口必须实现包含有实质从全部频道移除的操作。</p>
     *
     * @param wsUser 用户信息
     * @return 如果全部成功，返回 true ，其他返回 false
     */
    boolean removeUserFromAllChannel(WsUser wsUser);
}
