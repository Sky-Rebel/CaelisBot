package com.caelis.bot.event.msg.group.handler;

import com.caelis.bot.event.msg.BotMessageEvent;
import com.caelis.bot.event.msg.group.BotGroupMessageEvent;
import com.caelis.bot.event.msg.group.normal.handler.BotGroupNormalMessageEventHandler;
import org.json.JSONObject;

public class BotGroupMessageEventHandler
{
	public static void handleEvent(BotMessageEvent botMessageEvent, JSONObject clientPostJSONData)
	{
		BotGroupMessageEvent botGroupMessageEvent = new BotGroupMessageEvent();
		botGroupMessageEvent.setTime(botMessageEvent.getTime());
		botGroupMessageEvent.setPostType(botMessageEvent.getPostType());
		botGroupMessageEvent.setSelfId(botMessageEvent.getSelfId());
		botGroupMessageEvent.setMessageType(botMessageEvent.getMessageType());
		botGroupMessageEvent.setMessageSubType(clientPostJSONData.getString("sub_type"));
		if (botGroupMessageEvent.getMessageSubType().equals("normal"))
			BotGroupNormalMessageEventHandler.handlerEvent(botGroupMessageEvent, clientPostJSONData);
		else if (botGroupMessageEvent.getMessageSubType().equals("notice"))
			System.err.println("ERROR -> 未实现群组消息类型的上报");
		else
			System.err.println("ERROR -> 预期之外的未知群组消息类型的上报");
	}
}