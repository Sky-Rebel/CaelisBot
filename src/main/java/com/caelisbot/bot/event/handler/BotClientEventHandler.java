package com.caelisbot.bot.event.handler;

import com.caelisbot.bot.event.BotClientEvent;
import com.caelisbot.bot.event.meta.handler.BotMetaEventHandler;
import com.caelisbot.bot.event.msg.handler.BotMessageEventHandler;
import com.caelisbot.bot.event.msg_sent.handler.BotSentMsgEventHandler;
import com.caelisbot.bot.event.notice.handler.BotNoticeEventHandler;
import com.caelisbot.bot.event.request.handler.BotRequestEventHandler;
import org.json.JSONObject;

public class BotClientEventHandler
{
	public static void handleEvent(JSONObject clientPostJSONData)
	{
		BotClientEvent botClientEvent = new BotClientEvent();
		botClientEvent.setTime(clientPostJSONData.getLong("time"));
		botClientEvent.setSelfID(clientPostJSONData.getLong("self_id"));
		botClientEvent.setPostType(clientPostJSONData.getString("post_type"));
		switch (botClientEvent.getPostType())
		{
			// 元事件提交
			case "meta" -> BotMetaEventHandler.handleEvent(botClientEvent, clientPostJSONData);
			// 通知事件提交
			case "notice" -> BotNoticeEventHandler.handleEvent(botClientEvent, clientPostJSONData);
			// 消息事件提交
			case "message" -> BotMessageEventHandler.handleEvent(botClientEvent, clientPostJSONData);
			// 请求事件提交
			case "request" -> BotRequestEventHandler.handleEvent(botClientEvent, clientPostJSONData);
			// 自发消息事件提交
			case "message_sent" -> BotSentMsgEventHandler.handleEvent(botClientEvent, clientPostJSONData);
		}
	}
}