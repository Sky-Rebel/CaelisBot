package com.caelis.bot.api;

import com.caelis.bot.net.BotHttpClient;
import org.json.JSONObject;

public class BotSendLikeService
{
	public static void sendLike(long userId, int times)
	{
		BotHttpClient botHttpClient = new BotHttpClient("/send_like");
		JSONObject rootObject = new JSONObject();
		rootObject.put("user_id", userId);
		rootObject.put("times", times);
		botHttpClient.send(rootObject.toString());
	}
}
