package com.caelis.core.msg;

import org.json.JSONObject;

public class AtMessage extends Message
{
	private static final String msgType = "at";

	private String qq;

	public String getQQ()
	{
		return qq;
	}

	public void setQQ(String qq)
	{
		this.qq = qq;
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
		dataObject.put("qq", getQQ());
		rootObject.put("data", dataObject);
		return rootObject;
	}
}