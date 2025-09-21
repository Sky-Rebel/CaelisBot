package com.caelis.bot.api;

import com.caelis.bot.net.BotNapcatClient;
import org.json.JSONObject;

public class BotGroupManageService
{
	public static void setGroupKick(long groupId, long userId, boolean rejectAddRequest)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/set_group_kick");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupId);
		rootObject.put("user_id", userId);
		rootObject.put("rejectAddRequest", rejectAddRequest);
		botNapcatClient.send(rootObject.toString());
	}

	public static void setGroupBan(long groupId, long userId, long duration)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/set_group_ban");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupId);
		rootObject.put("user_id", userId);
		rootObject.put("duration", duration);
		botNapcatClient.send(rootObject.toString());
	}

	public static void setGroupCard(long groupId, long userId, String card)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/set_group_card");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupId);
		rootObject.put("user_id", userId);
		rootObject.put("card", card);
		botNapcatClient.send(rootObject.toString());
	}

	public static void setGroupWholeBan(long groupId, boolean enable)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/set_group_whole_ban");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupId);
		rootObject.put("enable", enable);
		botNapcatClient.send(rootObject.toString());
	}

	public static void setGroupAdmin(long groupId, long userId, boolean enable)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/set_group_admin");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupId);
		rootObject.put("user_id", userId);
		rootObject.put("enable", enable);
		botNapcatClient.send(rootObject.toString());
	}

	public static void setGroupSpecialTitle(long groupId, long userId, String specialTitle)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/set_group_special_title");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupId);
		rootObject.put("user_id", userId);
		rootObject.put("specialTitle", specialTitle);
		botNapcatClient.send(rootObject.toString());
	}
}