package com.caelis.core.msg;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ATMessage extends Message
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

	public static List<Long> getAtList(JSONArray jsonArray)
	{
		List<Long> atList = new ArrayList<>();
		jsonArray.forEach(obj ->
		{
			JSONObject jsonObject = (JSONObject) obj;
			if (jsonObject.getString("type").equals("at"))
			{
				String at = jsonObject.getString("qq");
				if (!at.equals("all"))
					atList.add(Long.valueOf(at));
			}
		});
		return atList;
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