package com.caelis.bot.api;

import com.caelis.bot.net.BotNapcatClient;
import com.caelis.core.msg.Message;
import com.caelis.core.msg.TextMessage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BotMessageManageService
{
	public static void setEssenceMsg(long messageId)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/set_essence_msg");
		JSONObject rootObject = new JSONObject();
		rootObject.put("message_id", messageId);
		botNapcatClient.send(rootObject.toString());
	}

	public static void deleteEssenceMsg(long messageId)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/delete_essence_msg");
		JSONObject rootObject = new JSONObject();
		rootObject.put("message_id", messageId);
		botNapcatClient.send(rootObject.toString());
	}

	public static void deleteMsg(long messageId)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/delete_msg");
		JSONObject rootObject = new JSONObject();
		rootObject.put("message_id", messageId);
		botNapcatClient.send(rootObject.toString());
	}

	public static void sendGroupMsg(long groupId, JSONArray messages)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/send_group_msg");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupId);
		rootObject.put("message", messages);
		botNapcatClient.send(rootObject.toString());
	}

	public static void sendGroupMsg(long groupId, List<? extends Message> messages)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/send_group_msg");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupId);
		JSONArray message = Message.getMessageListJSONArray(messages);
		rootObject.put("message", message);
		botNapcatClient.send(rootObject.toString());
	}

	public static void sendGroupTextMsg(long groupID, String text)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/send_group_msg");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupID);
		TextMessage textMessage = new TextMessage();
		textMessage.setText(text);
		List<? super Message> messageList = new ArrayList<>();
		messageList.add(textMessage);
		JSONArray messages = Message.getMessageListJSONArray((List<? extends Message>) messageList);
		rootObject.put("message", messages);
		botNapcatClient.send(rootObject.toString());
	}

	public static void sendCaelisBotImage(long groupID)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/send_group_msg");
		JSONObject rootObject = new JSONObject();
		rootObject.put("group_id", groupID);
		JSONArray messageArray = new JSONArray();
		JSONObject messageObject = new JSONObject();
		messageObject.put("type", "image");
		JSONObject dateObject = new JSONObject();
		dateObject.put("file", "C:\\Users\\DELL\\CaelisBot\\assets\\caelisbot.png");
		messageObject.put("data", dateObject);
		messageArray.put(messageObject);
		rootObject.put("message", messageArray);
		botNapcatClient.send(rootObject.toString());
	}
}