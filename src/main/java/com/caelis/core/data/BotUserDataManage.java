package com.caelis.core.data;

import org.json.JSONObject;

public class BotUserDataManage extends BotDataManage
{
	public BotUserDataManage(String userId)
	{
		super();
		super.subJsonDataType = "user";
		super.userId = userId;
		super.subJsonData = super.jsonData.getJSONObject("user");
		boolean isExistUser = subJsonData.keySet().stream().anyMatch(user -> user.equals(userId));
		if (!isExistUser) subJsonData.put(userId, new JSONObject());
		super.userJsonData = subJsonData.getJSONObject(userId);
		super.rawSubJsonData = super.subJsonData;
		super.subJsonData = super.userJsonData;
	}
}