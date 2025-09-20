package com.caelis.bot.api;

import com.caelis.bot.net.BotNapcatClient;
import org.json.JSONObject;

public class BotSendLikeService
{
	public static void sendLike(long userId)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/send_like");
		JSONObject rootObject = new JSONObject();
		rootObject.put("user_id", userId);
		if (BotFriendDataService.isFriend(userId))
			rootObject.put("times", 10);
		else
			rootObject.put("times", 50);
		botNapcatClient.send(rootObject.toString());
	}

	public static void sendLike(long userId, int times)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/send_like");
		JSONObject rootObject = new JSONObject();
		rootObject.put("user_id", userId);
		rootObject.put("times", times);
		botNapcatClient.send(rootObject.toString());
	}

	public static void sendLikeAllFriend()
	{
		for (long friend : BotFriendDataService.getFriendUinList())
		{
			BotNapcatClient botNapcatClient = new BotNapcatClient("/send_like");
			JSONObject rootObject = new JSONObject();
			rootObject.put("user_id", friend);
			if (BotFriendDataService.isFriend(friend))
				rootObject.put("times", 10);
			else
				rootObject.put("times", 50);
			botNapcatClient.send(rootObject.toString());
		}
	}

	public static void sendLikeAllFriend(int times)
	{
		for (long friend : BotFriendDataService.getFriendUinList())
		{
			BotNapcatClient botNapcatClient = new BotNapcatClient("/send_like");
			JSONObject rootObject = new JSONObject();
			rootObject.put("user_id", friend);
			rootObject.put("times", times);
			botNapcatClient.send(rootObject.toString());
		}
	}
}