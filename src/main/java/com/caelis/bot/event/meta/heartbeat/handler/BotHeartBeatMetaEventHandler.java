package com.caelis.bot.event.meta.heartbeat.handler;

import com.caelis.bot.api.BotMessageManageService;
import com.caelis.bot.event.meta.BotMetaEvent;
import com.caelis.bot.event.meta.heartbeat.BotHeartBeatMetaEvent;
import org.json.JSONObject;

public class BotHeartBeatMetaEventHandler
{
	public static void handleEvent(BotMetaEvent botMetaEvent, JSONObject botClientJSONData)
	{
		BotHeartBeatMetaEvent botHeartBeatMetaEvent = new BotHeartBeatMetaEvent();
		botHeartBeatMetaEvent.setTime(botMetaEvent.getTime());
		botHeartBeatMetaEvent.setSelfId(botMetaEvent.getSelfId());
		botHeartBeatMetaEvent.setPostType(botMetaEvent.getPostType());
		botHeartBeatMetaEvent.setInterval(botClientJSONData.getLong("interval"));
		BotHeartBeatMetaEvent.BotStatus botStatus = new BotHeartBeatMetaEvent.BotStatus();
		botStatus.setGood(botClientJSONData.getJSONObject("status").getBoolean("good"));
		botStatus.setOnline(botClientJSONData.getJSONObject("status").getBoolean("online"));
		botHeartBeatMetaEvent.setBotStatus(botStatus);

		if (!botHeartBeatMetaEvent.getBotStatus().isOnline())
		{
			System.out.println("Bot离线，退出程序");
			System.exit(0);
		}

		String postMsg = """
                         Bot WS Server - Heart Beat Event
                         
                         TIME -> %d
                         SELF ID -> %d
                         IS GOOD -> %b
                         INTERVAL -> %d
                         IS ONLINE -> %b
                         
                         Caelis Bot 2025 - Author 2056840996""".formatted(botHeartBeatMetaEvent.getTime(),
		botHeartBeatMetaEvent.getSelfId(),
		botHeartBeatMetaEvent.getBotStatus().isGood(),
		botHeartBeatMetaEvent.getInterval(),
		botHeartBeatMetaEvent.getBotStatus().isOnline());
		BotMessageManageService.sendGroupTextMsgToMainGroup(postMsg);
	}
}