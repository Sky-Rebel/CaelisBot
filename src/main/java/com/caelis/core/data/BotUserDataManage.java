package com.caelis.core.data;

import org.json.JSONObject;

public class BotUserDataManage extends BotDataManage
{
	public BotUserDataManage(String userID)
	{
		super();
		super.subJsonDataType = "user";
		super.userID = userID;
		super.subJsonData = super.jsonData.getJSONObject("user");
		boolean isExistUser = subJsonData.keySet().stream().anyMatch(user -> user.equals(userID));
		if (!isExistUser) subJsonData.put(userID, new JSONObject());
		super.userJsonData = subJsonData.getJSONObject(userID);
		super.rawSubJsonData = super.subJsonData;
		super.subJsonData = super.userJsonData;
	}
}