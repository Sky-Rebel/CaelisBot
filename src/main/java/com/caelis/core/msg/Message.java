package com.caelis.core.msg;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public abstract class Message
{
	public abstract String getMsgType();

	public abstract JSONObject toJSONObject();

	public static JSONArray getMessageListJSONArray(List<? extends Message> messageList)
	{
		JSONArray jsonArray = new JSONArray();
		messageList.forEach(message -> jsonArray.put(message.toJSONObject()));
		return jsonArray;
	}
}