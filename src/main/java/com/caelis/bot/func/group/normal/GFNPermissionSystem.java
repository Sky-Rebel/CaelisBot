package com.caelis.bot.func.group.normal;

import com.caelis.bot.api.BotMessageManageService;
import com.caelis.bot.event.msg.group.normal.BotNormalGroupMessageEvent;
import com.caelis.bot.event.msg.group.normal.IBotNormalGroupMessageEventHandler;
import com.caelis.core.permission.BotPermissionManage;
import com.caelis.core.permission.BotPermissionType;

public class GFNPermissionSystem implements IBotNormalGroupMessageEventHandler
{
	@Override
	public void handleGroupNormalMessageEvent(BotNormalGroupMessageEvent botNormalGroupMessageEvent)
	{
		if
		(
			(
				botNormalGroupMessageEvent.getRawMessage().equals("设置超级管理") ||
				botNormalGroupMessageEvent.getRawMessage().equals("设置超管")
			) &&
			BotPermissionManage.isZRPermission(String.valueOf(botNormalGroupMessageEvent.getUserId())))
		{
			String userId = botNormalGroupMessageEvent.getRawMessage()
													  .split(" ", 2)[0];
			if (BotPermissionManage.isCGPermission(userId))
			{
				BotMessageManageService.sendGroupTextMsg(botNormalGroupMessageEvent.getGroupId(), "对方既已拥有超管及更高权限");
			}
			BotPermissionManage.addUserAsPermissionOwner(BotPermissionType.CG, userId);
			BotMessageManageService.sendGroupTextMsg(botNormalGroupMessageEvent.getGroupId(), "既已设置对方为超管权限");
		}

		if
		(
			(
				botNormalGroupMessageEvent.getRawMessage().equals("设置普通管理") ||
				botNormalGroupMessageEvent.getRawMessage().equals("设置普管")
			) &&
			BotPermissionManage.isZRPermission(String.valueOf(botNormalGroupMessageEvent.getUserId())))
		{
			String userId = botNormalGroupMessageEvent.getRawMessage().split(" ", 2)[0];
			if (BotPermissionManage.isPGPermission(userId))
			{
				BotMessageManageService.sendGroupTextMsg(botNormalGroupMessageEvent.getGroupId(), "对方既已拥有普管及更高权限");
			}
			BotPermissionManage.addUserAsPermissionOwner(BotPermissionType.PG, userId);
			BotMessageManageService.sendGroupTextMsg(botNormalGroupMessageEvent.getGroupId(), "既已设置对方为普管权限");
		}
	}
}