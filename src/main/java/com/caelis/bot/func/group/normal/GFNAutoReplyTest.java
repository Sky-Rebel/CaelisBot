package com.caelis.bot.func.group.normal;

import com.caelis.bot.api.BotMessageManageService;
import com.caelis.bot.api.BotProfileService;
import com.caelis.bot.event.msg.group.normal.BotGroupNormalMessageEvent;
import com.caelis.bot.event.msg.group.normal.IBotGroupNormalMessageEventHandler;
import com.caelis.core.permission.BotPermissionManage;

import java.util.List;

import static com.caelis.core.msg.ATMessage.getAtList;

public class GFNAutoReplyTest implements IBotGroupNormalMessageEventHandler
{
	@Override
	public void handleGroupNormalMessageEvent(BotGroupNormalMessageEvent botGroupNormalMessageEvent)
	{
		final String command = botGroupNormalMessageEvent.getRawMessage();
		final String[] commands = botGroupNormalMessageEvent.getRawMessage().split(" ");
		final long userId = botGroupNormalMessageEvent.getUserId();
		final long groupId = botGroupNormalMessageEvent.getGroupId();
		final String groupName = botGroupNormalMessageEvent.getGroupName();
		final long messageId = botGroupNormalMessageEvent.getMessageId();
		final long senderId = botGroupNormalMessageEvent.getSender().getUserId();
		final String senderNickName = botGroupNormalMessageEvent.getSender().getNickname();
		final String senderRole = botGroupNormalMessageEvent.getSender().getRole();
		final String senderCard = botGroupNormalMessageEvent.getSender().getCard();
		final boolean isOwner = senderRole.equals("owner");
		final boolean isAdmin = senderRole.equals("admin");
		final List<Long> atList = getAtList(botGroupNormalMessageEvent.getMessage());

		if (botGroupNormalMessageEvent.getRawMessage().equals("test"))
		{
			BotMessageManageService.sendGroupTextMsg(botGroupNormalMessageEvent.getGroupId(), "GF: Auto Reply Test - Success! " + "[" + (System.currentTimeMillis() - botGroupNormalMessageEvent.getTime()) + "]");
		}

		if (command.equals("机器设置"))
		{
			StringBuilder reply = new StringBuilder();
			reply.append("设置机器头像").append("\n");
			reply.append("设置机器昵称").append("\n");
			reply.append("设置机器个签").append("\n");
			reply.append("设置机器性别").append("\n");
			BotMessageManageService.sendGroupTextMsg(groupId, reply.toString());
		}
		
		if (command.equals("我的账号")) BotMessageManageService.sendGroupTextMsg(groupId, "你的账号 -> " + userId);

		if (command.startsWith("设置机器头像") && BotPermissionManage.isZRPermission(userId))
		{
			BotProfileService.setBotAvatar(commands[1]);
			BotMessageManageService.sendGroupTextMsg(groupId, "设置成功！");
		}

		if (command.startsWith("设置机器性别") && BotPermissionManage.isZRPermission(userId))
		{
			BotProfileService.setBotSex(commands[1]);
			BotMessageManageService.sendGroupTextMsg(groupId, "设置成功！");
		}

		if (command.startsWith("设置机器个签") && BotPermissionManage.isZRPermission(userId))
		{
			BotProfileService.setBotPersonaNote(commands[1]);
			BotMessageManageService.sendGroupTextMsg(groupId, "设置成功！");
		}

		if (command.startsWith("设置机器机型") && BotPermissionManage.isZRPermission(userId))
		{
			BotProfileService.setBotModelShow(commands[1], commands[2]);
			BotMessageManageService.sendGroupTextMsg(groupId, "设置成功！");
		}

		if (command.startsWith("设置机器昵称") && BotPermissionManage.isZRPermission(userId))
		{
			BotProfileService.setBotNickName(commands[1]);
			BotMessageManageService.sendGroupTextMsg(groupId, "设置成功！");
		}
	}
}