package com.caelis.bot.event.msg.group.normal.handler;

import com.caelis.bot.api.BotMessageSendService;
import com.caelis.bot.event.msg.group.BotGroupMessageEvent;
import com.caelis.bot.event.msg.group.normal.BotGroupNormalMessageEvent;
import org.json.JSONObject;

public class BotGroupNormalMessageEventHandler
{
	public static void handlerEvent(BotGroupMessageEvent botGroupMessageEvent, JSONObject clientPostJSONData)
	{
		BotGroupNormalMessageEvent botGroupNormalMessageEvent = new BotGroupNormalMessageEvent();
		JSONObject senderJsonObject = clientPostJSONData.getJSONObject("sender");
		BotGroupNormalMessageEvent.Sender sender = botGroupNormalMessageEvent.new Sender();
		sender.setCard(senderJsonObject.getString("card"));
		sender.setRole(senderJsonObject.getString("role"));
		sender.setUserId(senderJsonObject.getLong("user_id"));
		sender.setNickname(senderJsonObject.getString("nickname"));
		botGroupNormalMessageEvent.setTime(botGroupMessageEvent.getTime());
		botGroupNormalMessageEvent.setSelfId(botGroupMessageEvent.getSelfId());
		botGroupNormalMessageEvent.setPostType(botGroupMessageEvent.getPostType());
		botGroupNormalMessageEvent.setMessageType(botGroupMessageEvent.getMessageType());
		botGroupNormalMessageEvent.setMessageSubType(botGroupMessageEvent.getMessageSubType());
		botGroupNormalMessageEvent.setFont(clientPostJSONData.getInt("font"));
		botGroupNormalMessageEvent.setUserId(clientPostJSONData.getLong("user_id"));
		botGroupNormalMessageEvent.setGroupId(clientPostJSONData.getLong("group_id"));
		botGroupNormalMessageEvent.setMessageId(clientPostJSONData.getLong("message_id"));
		botGroupNormalMessageEvent.setMessage(clientPostJSONData.getJSONArray("message"));
		botGroupNormalMessageEvent.setGroupName(clientPostJSONData.getString("group_name"));
		botGroupNormalMessageEvent.setMessageSeq(clientPostJSONData.getLong("message_seq"));
		botGroupNormalMessageEvent.setRawMessage(clientPostJSONData.getString("raw_message"));
		botGroupNormalMessageEvent.setMessageFormat(clientPostJSONData.getString("message_format"));
		if (botGroupNormalMessageEvent.getRawMessage().equals("test"))
			BotMessageSendService.sendGroupTextMsg(botGroupNormalMessageEvent.getGroupId(), "success");
	}
}
