package com.caelis.bot.event.meta.handler;

import com.caelis.bot.event.OneBotEvent;
import com.caelis.bot.event.meta.BotMetaEvent;
import com.caelis.bot.event.meta.heartbeat.handler.BotHeartBeatMetaEventHandler;
import org.json.JSONObject;

public class BotMetaEventHandler
{
	public static void handleEvent(OneBotEvent oneBotEvent, JSONObject botClientJSONData)
	{
		BotMetaEvent botMetaEvent = new BotMetaEvent();
		botMetaEvent.setTime(oneBotEvent.getTime());
		botMetaEvent.setSelfId(oneBotEvent.getSelfId());
		botMetaEvent.setPostType(oneBotEvent.getPostType());
		botMetaEvent.setMetaEventType(botClientJSONData.getString("meta_event_type"));
		if (botMetaEvent.getMetaEventType().equals("heartbeat"))
		{
			// BotHeartBeatMetaEventHandler.handleEvent(botMetaEvent, botClientJSONData);
		}
	}
}
