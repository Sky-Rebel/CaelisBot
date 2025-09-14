package com.caelis.core.data;

import org.json.JSONObject;

public class BotGroupDataManage extends BotDataManage
{
	public BotGroupDataManage(String groupId)
	{
		super();
		super.subJsonDataType = "group";
		super.groupId = groupId;
		super.subJsonData = super.jsonData.getJSONObject("group");
		boolean isExistGroup = subJsonData.keySet().stream().anyMatch(group -> group.equals(groupId));
		if (!isExistGroup) subJsonData.put(groupId, new JSONObject());
		super.groupJsonData = subJsonData.getJSONObject(groupId);
		super.rawSubJsonData = super.subJsonData;
		super.subJsonData = super.groupJsonData;
	}
}