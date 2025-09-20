package com.caelis.bot.func.group.normal;

import com.caelis.bot.api.BotMessageManageService;
import com.caelis.bot.event.msg.group.normal.BotGroupNormalMessageEvent;
import com.caelis.bot.event.msg.group.normal.IBotGroupNormalMessageEventHandler;
import com.caelis.core.permission.BotPermissionManage;
import com.caelis.core.permission.BotPermissionType;

public class GFNPermissionSystem implements IBotGroupNormalMessageEventHandler
{
	@Override
	public void handleGroupNormalMessageEvent(BotGroupNormalMessageEvent botGroupNormalMessageEvent)
	{
		if
		(
			(
				botGroupNormalMessageEvent.getRawMessage().equals("设置超级管理") ||
				botGroupNormalMessageEvent.getRawMessage().equals("设置超管")
			) &&
			BotPermissionManage.isZRPermission(String.valueOf(botGroupNormalMessageEvent.getUserId())))
		{
			String userId = botGroupNormalMessageEvent.getRawMessage()
													  .split(" ", 2)[0];
			if (BotPermissionManage.isCGPermission(userId))
			{
				BotMessageManageService.sendGroupTextMsg(botGroupNormalMessageEvent.getGroupId(), "对方既已拥有超管及更高权限");
			}
			BotPermissionManage.addUserAsPermissionOwner(BotPermissionType.CG, userId);
			BotMessageManageService.sendGroupTextMsg(botGroupNormalMessageEvent.getGroupId(), "既已设置对方为超管权限");
		}

		if
		(
			(
				botGroupNormalMessageEvent.getRawMessage().equals("设置普通管理") ||
				botGroupNormalMessageEvent.getRawMessage().equals("设置普管")
			) &&
			BotPermissionManage.isZRPermission(String.valueOf(botGroupNormalMessageEvent.getUserId())))
		{
			String userId = botGroupNormalMessageEvent.getRawMessage().split(" ", 2)[0];
			if (BotPermissionManage.isPGPermission(userId))
			{
				BotMessageManageService.sendGroupTextMsg(botGroupNormalMessageEvent.getGroupId(), "对方既已拥有普管及更高权限");
			}
			BotPermissionManage.addUserAsPermissionOwner(BotPermissionType.PG, userId);
			BotMessageManageService.sendGroupTextMsg(botGroupNormalMessageEvent.getGroupId(), "既已设置对方为普管权限");
		}
	}
}