package com.caelis.core.switchX.impl;

import com.caelis.core.data.BotGroupDataManage;
import com.caelis.core.switchX.IGroupSwitchManage;

public class GroupSwitchManage implements IGroupSwitchManage
{
	@Override
	public boolean getSwitch(long groupId)
	{
		try (BotGroupDataManage botGroupDataManage = new BotGroupDataManage(String.valueOf(groupId)))
		{
			return botGroupDataManage.getBoolean("switch", false);
		}
	}

	@Override
	public void setSwitch(long groupId, boolean switchX)
	{
		try (BotGroupDataManage botGroupDataManage = new BotGroupDataManage(String.valueOf(groupId)))
		{
			botGroupDataManage.put("switch", switchX);
		}
	}
}
