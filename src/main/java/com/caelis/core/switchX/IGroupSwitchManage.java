package com.caelis.core.switchX;

public interface IGroupSwitchManage
{
	boolean getSwitch(long groupId);

	void setSwitch(long groupId, boolean switchX);
}