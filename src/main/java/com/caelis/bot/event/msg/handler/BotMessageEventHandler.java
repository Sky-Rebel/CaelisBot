package com.caelis.bot.event.msg.handler;

import com.caelis.bot.event.OneBotEvent;
import com.caelis.bot.event.msg.BotMessageEvent;
import com.caelis.bot.event.msg.group.handler.BotGroupMessageEventHandler;
import com.caelis.bot.event.msg.priv.handler.BotPrivateMsgEventHandler;
import org.json.JSONObject;

public class BotMessageEventHandler
{
	public static void handleEvent(OneBotEvent oneBotEvent, JSONObject clientPostJSONData)
	{
		BotMessageEvent botMessageEvent = new BotMessageEvent();
		botMessageEvent.setTime(oneBotEvent.getTime());
		botMessageEvent.setSelfId(oneBotEvent.getSelfId());
		botMessageEvent.setPostType(oneBotEvent.getPostType());
		botMessageEvent.setMessageType(clientPostJSONData.getString("message_type"));
		if (botMessageEvent.getMessageType().equals("group"))
			BotGroupMessageEventHandler.handleEvent(botMessageEvent, clientPostJSONData);
		else if (botMessageEvent.getMessageType().equals("private"))
			BotPrivateMsgEventHandler.handleEvent(botMessageEvent, clientPostJSONData);
		else
			System.out.println("ERROR -> 预期之外的未知消息类型的上报");
	}
}