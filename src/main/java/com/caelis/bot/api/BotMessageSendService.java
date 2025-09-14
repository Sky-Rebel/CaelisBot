package com.caelis.bot.api;

import com.caelis.bot.net.BotHttpClient;
import com.caelis.core.msg.Message;
import com.caelis.core.msg.TextMessage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BotMessageSendService
{
	public static void sendGroupMsg(long groupId, JSONArray messages)
	{
		BotHttpClient botHttpClient = new BotHttpClient("/send_group_msg");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupId);
		rootObject.put("message", messages);
		botHttpClient.send(rootObject.toString());
	}

	public static void sendGroupMsg(long groupId, List<? extends Message> messages)
	{
		BotHttpClient botHttpClient = new BotHttpClient("/send_group_msg");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupId);
		JSONArray message = Message.getMessageListJSONArray(messages);
		rootObject.put("message", message);
		botHttpClient.send(rootObject.toString());
	}

	public static void sendGroupTextMsg(long groupID, String text)
	{
		BotHttpClient botHttpClient = new BotHttpClient("/send_group_msg");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupID);
		TextMessage textMessage = new TextMessage();
		textMessage.setText(text);
		List<? super Message> messageList = new ArrayList<>();
		messageList.add(textMessage);
		JSONArray messages = Message.getMessageListJSONArray((List<? extends Message>) messageList);
		rootObject.put("message", messages);
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