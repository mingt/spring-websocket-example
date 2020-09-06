package com.websocket.model.chat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 聊天相关配置.
 *
 * @since 2020/8/28 Ahming
 */
public class ChatConfig {
    private ChatConfig() {}

    /**
     * 聊天频道列表.
     *
     * <p>频道 ID( 或 ID 前缀) => 频道名称。
     * 在需要区分系统的聊天频道时使用。在需要时，频道名称可扩展为频道信息（Room），例如名称，是否上线下线提醒，等等</p>
     */
    private static final Map<String, ChatRoom> CHAT_ROOM_MAP = new HashMap<>();

    static {
        ChatRoom room1 = new ChatRoom();
        room1.setName("聊天频道前缀");
        room1.setIfRemindOnOffline(true);
        CHAT_ROOM_MAP.put("/topic/messages", room1);
    }

    /**
     * 判断是否为聊天频道.
     *
     * @param channelId 频道ID
     * @return 为聊天频道时返回true，否则false
     */
    public static boolean isChatRoomChannel(String channelId) {
        if (channelId == null) {
            return false;
        }
        Set<String> keySet = CHAT_ROOM_MAP.keySet();
        if (keySet.contains(channelId)) {
            return true;
        }
        for (String one : keySet) {
            if (channelId.startsWith(one)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否聊天频道上下线提示.
     *
     * @param channelId 频道ID
     * @return 为聊天频道且开启提醒上下线时返回true，否则false
     */
    public static boolean ifChatRoomRemindOnOffline(String channelId) {
        if (channelId == null) {
            return false;
        }
        for (Iterator iterator = CHAT_ROOM_MAP.keySet().iterator(); iterator.hasNext(); ) {
            String key = (String)iterator.next();
            if (channelId.equals(key) || channelId.startsWith(key)) {
                ChatRoom room = CHAT_ROOM_MAP.get(key);

                return room != null && room.getIfRemindOnOffline() != null && room.getIfRemindOnOffline();
            }
        }
        return false;
    }
}
