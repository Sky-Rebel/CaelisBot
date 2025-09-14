package com.caelis.bot.event.handler;

import com.caelis.bot.event.BotEvent;
import com.caelis.bot.event.meta.handler.BotMetaEventHandler;
import com.caelis.bot.event.msg.handler.BotMessageEventHandler;
import com.caelis.bot.event.msg_sent.handler.BotSentMsgEventHandler;
import com.caelis.bot.event.notice.handler.BotNoticeEventHandler;
import com.caelis.bot.event.request.handler.BotRequestEventHandler;
import org.json.JSONObject;

public class BotEventHandler
{
	public static void handleEvent(JSONObject clientPostJSONData)
	{
		BotEvent botClientPostEvent = new BotEvent();
		botClientPostEvent.setTime(clientPostJSONData.getLong("time"));
		botClientPostEvent.setSelfId(clientPostJSONData.getLong("self_id"));
		botClientPostEvent.setPostType(clientPostJSONData.getString("post_type"));
		switch (botClientPostEvent.getPostType())
		{
			// 元事件提交
			case "meta" -> BotMetaEventHandler.handleEvent(botClientPostEvent, clientPostJSONData);
			// 通知事件提交
			case "notice" -> BotNoticeEventHandler.handleEvent(botClientPostEvent, clientPostJSONData);
			// 消息事件提交
			case "message" -> BotMessageEventHandler.handleEvent(botClientPostEvent, clientPostJSONData);
			// 请求事件提交
			case "request" -> BotRequestEventHandler.handleEvent(botClientPostEvent, clientPostJSONData);
			// 自发消息事件提交
			case "message_sent" -> BotSentMsgEventHandler.handleEvent(botClientPostEvent, clientPostJSONData);
		}
	}
}