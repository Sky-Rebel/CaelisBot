package com.caelis.core.msg;

import org.json.JSONObject;

public class TextMessage extends Message
{
	private static final String msgType = "text";

	private String text;

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	@Override
	public String getMsgType()
	{
		return msgType;
	}

	@Override
	public JSONObject toJSONObject()
	{
		JSONObject rootObject = new JSONObject();
		rootObject.put("type", getMsgType());
		JSONObject dataObject = new JSONObject();
		dataObject.put("text", getText());
		rootObject.put("data", dataObject);
		return rootObject;
	}
}
