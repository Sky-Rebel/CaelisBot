package com.caelis.core.msg;

import org.json.JSONObject;

public class ReplyMessage extends Message
{
	private static final String msgType = "reply";

	private String id;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
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
		dataObject.put("id", getId());
		rootObject.put("data", dataObject);
		return rootObject;
	}
}
