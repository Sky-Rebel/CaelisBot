package com.caelis.bot.api;

import com.caelis.bot.net.BotHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class BotMessageSendService
{
	public static void sendGroupTextMsg(long groupID, String text)
	{
		BotHttpClient botHttpClient = new BotHttpClient("/send_group_msg");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupID);
		JSONArray messageArray = new JSONArray();
		JSONObject messageObject = new JSONObject();
		messageObject.put("type", "text");
		JSONObject dateObject = new JSONObject();
		dateObject.put("text", text);
		messageObject.put("data", dateObject);
		messageArray.put(messageObject);
		rootObject.put("message", messageArray);
		botHttpClient.send(rootObject.toString());
	}

	public static void sendCaelisBotImage(long groupID)
	{
		BotHttpClient botHttpClient = new BotHttpClient("/send_group_msg");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupID);
		JSONArray messageArray = new JSONArray();
		JSONObject messageObject = new JSONObject();
		messageObject.put("type", "image");
		JSONObject dateObject = new JSONObject();
		dateObject.put("file", "C:\\Users\\DELL\\CaelisBot\\src\\main\\resources\\caelisbot.png");
		messageObject.put("data", dateObject);
		messageArray.put(messageObject);
		rootObject.put("message", messageArray);
		botHttpClient.send(rootObject.toString());
	}
}