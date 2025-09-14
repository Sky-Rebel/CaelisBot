package com.caelis.bot.func.group.normal;

import com.caelis.bot.api.BotMessageSendService;
import com.caelis.bot.event.msg.group.normal.BotGroupNormalMessageEvent;
import com.caelis.bot.event.msg.group.normal.IBotGroupNormalMessageEventHandler;
import com.caelis.core.permission.BotPermissionManage;
import com.caelis.core.switchX.impl.GroupSwitchManage;

public class GFNGroupSwitch implements IBotGroupNormalMessageEventHandler
{
	@Override
	public void handleGroupNormalMessageEvent(BotGroupNormalMessageEvent botGroupNormalMessageEvent)
	{
		String reply;

		if (botGroupNormalMessageEvent.getRawMessage().equals("开机"))
		{
			if (!BotPermissionManage.isPGPermission(botGroupNormalMessageEvent.getUserId()))
				reply = "权限不足！";
			else
			{
				GroupSwitchManage groupSwitchManage = new GroupSwitchManage();
				if (groupSwitchManage.getSwitch(botGroupNormalMessageEvent.getGroupId()))
					reply = "既已开机！";
				else
				{
					groupSwitchManage.setSwitch(botGroupNormalMessageEvent.getGroupId(), true);
					reply = "开机成功！";
				}
			}
			BotMessageSendService.sendGroupTextMsg(botGroupNormalMessageEvent.getGroupId(), reply);
		}

		if (botGroupNormalMessageEvent.getRawMessage().equals("关机"))
		{
			if (!BotPermissionManage.isPGPermission(botGroupNormalMessageEvent.getUserId()))
				reply = "权限不足！";
			else
			{
				GroupSwitchManage groupSwitchManage = new GroupSwitchManage();
				if (groupSwitchManage.getSwitch(botGroupNormalMessageEvent.getGroupId()))
				{
					groupSwitchManage.setSwitch(botGroupNormalMessageEvent.getGroupId(), false);

					reply = "关机成功！";
				}
				else
					reply = "既已关机！";
			}
			BotMessageSendService.sendGroupTextMsg(botGroupNormalMessageEvent.getGroupId(), reply);
		}
	}
}
