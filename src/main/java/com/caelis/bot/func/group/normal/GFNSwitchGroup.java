package com.caelis.bot.func.group.normal;

import com.caelis.bot.api.BotMessageManageService;
import com.caelis.bot.event.msg.group.normal.BotNormalGroupMessageEvent;
import com.caelis.bot.event.msg.group.normal.IBotNormalGroupMessageEventHandler;
import com.caelis.core.permission.BotPermissionManage;
import com.caelis.core.switchX.impl.GroupSwitchManage;

public class GFNSwitchGroup implements IBotNormalGroupMessageEventHandler
{
	@Override
	public void handleGroupNormalMessageEvent(BotNormalGroupMessageEvent botNormalGroupMessageEvent)
	{
		String reply;

		if (botNormalGroupMessageEvent.getRawMessage().equals("开机"))
		{
			if (!BotPermissionManage.isPGPermission(botNormalGroupMessageEvent.getUserId()))
				reply = "权限不足！";
			else
			{
				GroupSwitchManage groupSwitchManage = new GroupSwitchManage();
				if (groupSwitchManage.getSwitch(botNormalGroupMessageEvent.getGroupId()))
					reply = "既已开机！";
				else
				{
					groupSwitchManage.setSwitch(botNormalGroupMessageEvent.getGroupId(), true);
					reply = "开机成功！";
				}
			}
			BotMessageManageService.sendGroupTextMsg(botNormalGroupMessageEvent.getGroupId(), reply);
		}

		if (botNormalGroupMessageEvent.getRawMessage().equals("关机"))
		{
			if (!BotPermissionManage.isPGPermission(botNormalGroupMessageEvent.getUserId()))
				reply = "权限不足！";
			else
			{
				GroupSwitchManage groupSwitchManage = new GroupSwitchManage();
				if (groupSwitchManage.getSwitch(botNormalGroupMessageEvent.getGroupId()))
				{
					groupSwitchManage.setSwitch(botNormalGroupMessageEvent.getGroupId(), false);

					reply = "关机成功！";
				}
				else
					reply = "既已关机！";
			}
			BotMessageManageService.sendGroupTextMsg(botNormalGroupMessageEvent.getGroupId(), reply);
		}
	}
}
