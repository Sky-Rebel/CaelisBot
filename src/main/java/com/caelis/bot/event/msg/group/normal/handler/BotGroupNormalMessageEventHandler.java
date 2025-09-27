package com.caelis.bot.event.msg.group.normal.handler;

import com.caelis.bot.event.msg.group.BotGroupMessageEvent;
import com.caelis.bot.event.msg.group.normal.BotNormalGroupMessageEvent;
import com.caelis.bot.event.msg.group.normal.IBotNormalGroupMessageEventHandler;
import com.caelis.bot.func.group.normal.GFNAutoReplyTest;
import com.caelis.bot.func.group.normal.GFNAutoSendLike;
import com.caelis.bot.func.group.normal.GFNSwitchGroup;
import com.caelis.bot.func.group.normal.GFNPermissionSystem;
import com.caelis.core.switchX.impl.GroupSwitchManage;
import org.json.JSONObject;

import java.util.Arrays;

public class BotGroupNormalMessageEventHandler
{
	public static void handlerEvent(BotGroupMessageEvent botGroupMessageEvent, JSONObject clientPostJSONData)
	{
		BotNormalGroupMessageEvent botNormalGroupMessageEvent = new BotNormalGroupMessageEvent();
		JSONObject senderJsonObject = clientPostJSONData.getJSONObject("sender");
		BotNormalGroupMessageEvent.Sender sender = botNormalGroupMessageEvent.new Sender();
		sender.setCard(senderJsonObject.getString("card"));
		sender.setRole(senderJsonObject.getString("role"));
		sender.setUserId(senderJsonObject.getLong("user_id"));
		sender.setNickname(senderJsonObject.getString("nickname"));
		botNormalGroupMessageEvent.setSender(sender);
		botNormalGroupMessageEvent.setTime(botGroupMessageEvent.getTime());
		botNormalGroupMessageEvent.setSelfId(botGroupMessageEvent.getSelfId());
		botNormalGroupMessageEvent.setPostType(botGroupMessageEvent.getPostType());
		botNormalGroupMessageEvent.setMessageType(botGroupMessageEvent.getMessageType());
		botNormalGroupMessageEvent.setMessageSubType(botGroupMessageEvent.getMessageSubType());
		botNormalGroupMessageEvent.setFont(clientPostJSONData.getInt("font"));
		botNormalGroupMessageEvent.setUserId(clientPostJSONData.getLong("user_id"));
		botNormalGroupMessageEvent.setGroupId(clientPostJSONData.getLong("group_id"));
		botNormalGroupMessageEvent.setMessageId(clientPostJSONData.getLong("message_id"));
		botNormalGroupMessageEvent.setMessage(clientPostJSONData.getJSONArray("message"));
		botNormalGroupMessageEvent.setGroupName(clientPostJSONData.getString("group_name"));
		botNormalGroupMessageEvent.setMessageSeq(clientPostJSONData.getLong("message_seq"));
		botNormalGroupMessageEvent.setRawMessage(clientPostJSONData.getString("raw_message"));
		botNormalGroupMessageEvent.setMessageFormat(clientPostJSONData.getString("message_format"));
		callEventHandler(botNormalGroupMessageEvent);
	}

	public static void callEventHandler(BotNormalGroupMessageEvent botNormalGroupMessageEvent)
	{
		GroupSwitchManage groupSwitchManage = new GroupSwitchManage();
		new GFNSwitchGroup().handleGroupNormalMessageEvent(botNormalGroupMessageEvent);
		if (groupSwitchManage.getSwitch(botNormalGroupMessageEvent.getGroupId()))
		{
			Arrays.stream(new IBotNormalGroupMessageEventHandler[]
			{
				  new GFNAutoSendLike(),
				  new GFNAutoReplyTest(),
				  new GFNPermissionSystem()
			}).forEach(handler -> handler.handleGroupNormalMessageEvent(botNormalGroupMessageEvent));
		}
	}
}