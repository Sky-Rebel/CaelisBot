package com.caelis.core.data;

public class BotSystemDataManage extends BotDataManage
{
	public BotSystemDataManage()
	{
		super();
		super.subJsonDataType = "system";
		super.subJsonData = super.jsonData.getJSONObject("system");
	}
}