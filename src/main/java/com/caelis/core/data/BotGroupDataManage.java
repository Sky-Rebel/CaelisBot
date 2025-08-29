package com.caelis.core.data;

public class BotGroupDataManage extends BotDataManage
{
	public BotGroupDataManage()
	{
		super();
		super.subJsonDataType = "group";
		super.subJsonData = super.jsonData.getJSONObject("group");
	}
}