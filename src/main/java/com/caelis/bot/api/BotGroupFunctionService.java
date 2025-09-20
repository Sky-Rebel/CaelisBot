package com.caelis.bot.api;

import com.caelis.bot.net.BotNapcatClient;
import org.json.JSONObject;

public class BotGroupFunctionService
{
	public static void setGroupLeave(long groupID)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/set_group_leave");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupID);
		botNapcatClient.send(rootObject.toString());
	}

	public static void setGroupName(long groupId, String groupName)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/set_group_name");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupId);
		rootObject.put("group_name", groupName);
		botNapcatClient.send(rootObject.toString());
	}

	public static void setGroupPortrait(long groupId, String file)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/set_group_portrait");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupId);
		rootObject.put("file", file);
		botNapcatClient.send(rootObject.toString());
	}

	public static void sendGroupNotice(long groupId, String content)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/_send_group_notice");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupId);
		rootObject.put("content", content);
		botNapcatClient.send(rootObject.toString());
	}
}
