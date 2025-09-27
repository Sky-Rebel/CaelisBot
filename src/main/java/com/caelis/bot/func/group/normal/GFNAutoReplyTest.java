package com.caelis.bot.func.group.normal;

import com.caelis.bot.BotEntry;
import com.caelis.bot.api.BotGroupManageService;
import com.caelis.bot.api.BotGroupMemberDataService;
import com.caelis.bot.api.BotMessageManageService;
import com.caelis.bot.api.BotProfileService;
import com.caelis.bot.event.msg.group.normal.BotNormalGroupMessageEvent;
import com.caelis.bot.event.msg.group.normal.IBotNormalGroupMessageEventHandler;
import com.caelis.core.permission.BotPermissionManage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.caelis.core.msg.ATMessage.getAtList;

public class GFNAutoReplyTest implements IBotNormalGroupMessageEventHandler
{
	@Override
	public void handleGroupNormalMessageEvent(BotNormalGroupMessageEvent botNormalGroupMessageEvent)
	{
		final String command = botNormalGroupMessageEvent.getRawMessage();
		final String[] commands = botNormalGroupMessageEvent.getRawMessage().split(" ");
		final long userId = botNormalGroupMessageEvent.getUserId();
		final long groupId = botNormalGroupMessageEvent.getGroupId();
		final String groupName = botNormalGroupMessageEvent.getGroupName();
		final long messageId = botNormalGroupMessageEvent.getMessageId();
		final long senderId = botNormalGroupMessageEvent.getSender().getUserId();
		final String senderNickName = botNormalGroupMessageEvent.getSender().getNickname();
		final String senderRole = botNormalGroupMessageEvent.getSender().getRole();
		final String senderCard = botNormalGroupMessageEvent.getSender().getCard();
		final boolean isOwner = senderRole.equals("owner");
		final boolean isAdmin = senderRole.equals("admin");
		final List<Long> atList = getAtList(botNormalGroupMessageEvent.getMessage());
		final long selfId = botNormalGroupMessageEvent.getSelfId();
		final boolean isGroupOwnerTheSelf = BotGroupMemberDataService.isGroupOwner(groupId, selfId);
		final boolean isGroupAdminTheSelf = BotGroupMemberDataService.hasGroupAdminPermission(groupId, selfId);

		if (botNormalGroupMessageEvent.getRawMessage().equals("test"))
		{
			BotMessageManageService.sendGroupTextMsg(botNormalGroupMessageEvent.getGroupId(), "GF: Auto Reply Test - Success! " + "[" + (System.currentTimeMillis() - botNormalGroupMessageEvent.getTime()) + "]");
		}

		if (command.equals("菜单"))
		{
			String reply = """
                           		✨功能菜单✨
                           --------------------
                           ✅基本群管
                           ❎娱乐功能
                           ❎签到系统
                           ❎留言系统
                           ❎银行系统
                           ❎定时系统
                           ❎问答系统
                           ❎刷屏检测
                           ❎发言统计
                           ❎禁词系统
                           ✅权限系统
                           ✅机器设置
                           ✅清屏系统
                           ✅免费使用
                           --------------------
                           ☎️: 2056840996
                           --------------------
                           ⏰: %s
                           --------------------
                           Tip: ✅既已实现 ❎计划实现"""
			.formatted(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS").format(new Date()));
			BotMessageManageService.sendGroupTextMsg(groupId, reply);
		}

		if (command.equals("清屏系统"))
		{
			String reply = """
                           普通清屏
                           """;
			BotMessageManageService.sendGroupTextMsg(groupId, reply);
		}

		if (command.equals("机器设置"))
		{
			String reply = """
                           设置机器头像
                           设置机器昵称
                           设置机器个签
                           设置机器性别
                           设置机器机型
                           """;
			BotMessageManageService.sendGroupTextMsg(groupId, reply);
		}

		if (command.equals("基本群管"))
		{
			String reply = """
                           -----基本群管-----
                           
                           全禁 全解
                           禁言 【账号】 【时间】
                           解禁 【账号】
                           改名 【账号】 【名字】
                           清名 【账号】
                           上管 【账号】
                           下管 【账号】
                           上衔 【账号】
                           下衔 【账号】
                           踢出 【账号】
                           踢黑 【账号】
                           
                           %s
                           %s
                           %s
                           Tip: 参数请以空格分割"""
			.formatted(
				"-".repeat(20),
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS").format(new Date()),
				"-".repeat(20)
			);
			BotMessageManageService.sendGroupTextMsg(groupId, reply);
		}

		if (command.equals("免费使用"))
		{
			String reply = """
                           本QQ机器人为公益作品，免费开放使用
                           您可以通过如下方式免费使用QQ机器人
                           1:添加主人：2056840996
                           2:加入主群：%d""".formatted(BotEntry.BOT_CG_GROUP);
			BotMessageManageService.sendGroupTextMsg(groupId, reply);
		}

		if (command.startsWith("普通清屏"))
		{
			int times = Integer.parseInt(commands[1]);
			String text = """
                          ⚠⚠⚠
                          ⌈			⌉
                              普
                          ⌊			⌋
                          ⌈			⌉
                              通
                          ⌊			⌋
                          ⌈			⌉
                              清
                          ⌊			⌋
                          ⌈			⌉
                              屏
                          ⌊			⌋
                          ⚠⚠⚠
                          ⌈			⌉
                              禁
                          ⌊			⌋
                          ⌈			⌉
                              止
                          ⌊			⌋
                          ⌈			⌉
                              发
                          ⌊			⌋
                          ⌈			⌉
                              言
                          ⌊			⌋
                          ⚠⚠⚠""";
			BotMessageManageService.sendGroupTextMsg(groupId, "普通清屏开始 -> %s 次".formatted(commands[1]));
			for (int i = 0; i < times; i++)
				BotMessageManageService.sendGroupTextMsg(groupId, text);
		}

		if (command.startsWith("禁言") && (BotPermissionManage.isPGPermission(userId) || isAdmin))
		{
			if (Long.parseLong(commands[1]) == selfId)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "自身难施己令，此举固不可为");
				return;
			}
			if (!isGroupAdminTheSelf)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "未有司管之职，弗为经管之举");
				return;
			}
			if (BotGroupMemberDataService.hasGroupAdminPermission(groupId, Long.parseLong(commands[1])) && !isGroupOwnerTheSelf)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "同列有司之位，难行辖制之权");
				return;
			}
			if (!BotGroupMemberDataService.isGroupMember(groupId, Long.parseLong(commands[1])))
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "非其域内之人，未可行其事也");
				return;
			}
			if (Long.parseLong(commands[2]) < 1)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "时短不合规制，此事不可施行");
				return;
			}
			if (Long.parseLong(commands[2]) > (60 * 24 *30))
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "时长过逾限度，此举不可为之");
				return;
			}
			BotGroupManageService.setGroupBan(groupId, Long.parseLong(commands[1]), Long.parseLong(commands[2]) * 60);
			BotMessageManageService.sendGroupTextMsg(groupId, "尝试禁止发言 -> %s -> %s 分钟".formatted(commands[1], commands[2]));
		}

		if (command.startsWith("解禁") && (BotPermissionManage.isPGPermission(userId) || isAdmin))
		{
			if (Long.parseLong(commands[1]) == selfId)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "自身难施己令，此举固不可为");
				return;
			}
			if (!isGroupAdminTheSelf)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "未有司管之职，弗为经管之举");
				return;
			}
			if (BotGroupMemberDataService.hasGroupAdminPermission(groupId, Long.parseLong(commands[1])) && !isGroupOwnerTheSelf)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "同列有司之位，难行辖制之权");
				return;
			}
			if (!BotGroupMemberDataService.isGroupMember(groupId, Long.parseLong(commands[1])))
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "非其域内之人，未可行其事也");
				return;
			}
			BotGroupManageService.setGroupBan(groupId, Long.parseLong(commands[1]), 0);
			BotMessageManageService.sendGroupTextMsg(groupId, "尝试解除禁言 -> %s".formatted(commands[1]));
		}

		if (command.startsWith("踢出") && BotPermissionManage.isPGPermission(userId))
		{
			if (Long.parseLong(commands[1]) == selfId)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "自身难施己令，此举固不可为");
				return;
			}
			if (!isGroupAdminTheSelf)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "未有司管之职，弗为经管之举");
				return;
			}
			if (BotGroupMemberDataService.hasGroupAdminPermission(groupId, Long.parseLong(commands[1])) && !isGroupOwnerTheSelf)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "同列有司之位，难行辖制之权");
				return;
			}
			if (!BotGroupMemberDataService.isGroupMember(groupId, Long.parseLong(commands[1])))
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "非其域内之人，未可行其事也");
				return;
			}
			BotGroupManageService.setGroupKick(groupId, Long.parseLong(commands[1]), false);
			BotMessageManageService.sendGroupTextMsg(groupId, "尝试移出群组 -> %s".formatted(commands[1]));
		}

		if (command.startsWith("踢黑") && BotPermissionManage.isPGPermission(userId))
		{
			if (Long.parseLong(commands[1]) == selfId)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "自身难施己令，此举固不可为");
				return;
			}
			if (!isGroupAdminTheSelf)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "未有司管之职，弗为经管之举");
				return;
			}
			if (BotGroupMemberDataService.hasGroupAdminPermission(groupId, Long.parseLong(commands[1])) && !isGroupOwnerTheSelf)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "同列有司之位，难行辖制之权");
				return;
			}
			if (!BotGroupMemberDataService.isGroupMember(groupId, Long.parseLong(commands[1])))
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "非其域内之人，未可行其事也");
				return;
			}
			BotGroupManageService.setGroupKick(groupId, Long.parseLong(commands[1]), true);
			BotMessageManageService.sendGroupTextMsg(groupId, "尝试移出群组并加入黑名单 -> %s".formatted(commands[1]));
		}

		if (command.startsWith("改名") && BotPermissionManage.isPGPermission(userId))
		{
			if (Long.parseLong(commands[1]) == selfId)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "自身难施己令，此举固不可为");
				return;
			}
			if (!isGroupAdminTheSelf)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "未有司管之职，弗为经管之举");
				return;
			}
			if (BotGroupMemberDataService.hasGroupAdminPermission(groupId, Long.parseLong(commands[1])) && !isGroupOwnerTheSelf)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "同列有司之位，难行辖制之权");
				return;
			}
			if (!BotGroupMemberDataService.isGroupMember(groupId, Long.parseLong(commands[1])))
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "非其域内之人，未可行其事也");
				return;
			}
			BotGroupManageService.setGroupCard(groupId, Long.parseLong(commands[1]), commands[2]);
			BotMessageManageService.sendGroupTextMsg(groupId, "尝试修改名片 -> %s -> %s".formatted(commands[1], commands[2]));
		}

		if (command.startsWith("清名") && BotPermissionManage.isPGPermission(userId))
		{
			if (Long.parseLong(commands[1]) == selfId)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "自身难施己令，此举固不可为");
				return;
			}
			if (!isGroupAdminTheSelf)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "未有司管之职，弗为经管之举");
				return;
			}
			if (BotGroupMemberDataService.hasGroupAdminPermission(groupId, Long.parseLong(commands[1])) && !isGroupOwnerTheSelf)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "同列有司之位，难行辖制之权");
				return;
			}
			if (!BotGroupMemberDataService.isGroupMember(groupId, Long.parseLong(commands[1])))
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "非其域内之人，未可行其事也");
				return;
			}
			BotGroupManageService.setGroupCard(groupId, Long.parseLong(commands[1]), "");
			BotMessageManageService.sendGroupTextMsg(groupId, "尝试恢复名片为QQ昵称 -> %s -> %s".formatted(commands[1], commands[2]));
		}

		if (command.equals("全禁") && BotPermissionManage.isPGPermission(userId))
		{
			if (isGroupAdminTheSelf)
			{
				BotGroupManageService.setGroupWholeBan(groupId, true);
				BotMessageManageService.sendGroupTextMsg(groupId, "尝试开启全体禁言 -> %s".formatted(groupId));
			}
			else BotMessageManageService.sendGroupTextMsg(groupId, "未有司管之职，弗为经管之举");
		}

		if (command.equals("全解") && BotPermissionManage.isPGPermission(userId))
		{
			if (isGroupAdminTheSelf)
			{
				BotGroupManageService.setGroupWholeBan(groupId, false);
				BotMessageManageService.sendGroupTextMsg(groupId, "尝试关闭全体禁言 -> %s".formatted(groupId));
			}
			else BotMessageManageService.sendGroupTextMsg(groupId, "未有司管之职，弗为经管之举");
		}

		if (command.startsWith("上管") && BotPermissionManage.isPGPermission(userId))
		{
			if (Long.parseLong(commands[1]) == selfId)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "自身难施己令，此举固不可为");
				return;
			}
			if (!isGroupOwnerTheSelf)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "未有司管之职，弗为经管之举");
				return;
			}
			if (!BotGroupMemberDataService.isGroupMember(groupId, Long.parseLong(commands[1])))
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "非其域内之人，未可行其事也");
				return;
			}
			BotGroupManageService.setGroupAdmin(groupId, Long.parseLong(commands[1]), true);
			BotMessageManageService.sendGroupTextMsg(groupId, "尝试设置管理员 -> %s".formatted(commands[1]));
		}

		if (command.startsWith("下管") && BotPermissionManage.isPGPermission(userId))
		{
			if (Long.parseLong(commands[1]) == selfId)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "自身难施己令，此举固不可为");
				return;
			}
			if (!isGroupOwnerTheSelf)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "未有司管之职，弗为经管之举");
				return;
			}
			if (!BotGroupMemberDataService.isGroupMember(groupId, Long.parseLong(commands[1])))
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "非其域内之人，未可行其事也");
				return;
			}
			BotGroupManageService.setGroupAdmin(groupId, Long.parseLong(commands[1]), false);
			BotMessageManageService.sendGroupTextMsg(groupId, "尝试取消管理员 -> %s".formatted(commands[1]));
		}

		if (command.startsWith("上衔") && BotPermissionManage.isPGPermission(userId))
		{
			if (Long.parseLong(commands[1]) == selfId)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "自身难施己令，此举固不可为");
				return;
			}
			if (!isGroupOwnerTheSelf)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "未有司管之职，弗为经管之举");
				return;
			}
			if (!BotGroupMemberDataService.isGroupMember(groupId, Long.parseLong(commands[1])))
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "非其域内之人，未可行其事也");
				return;
			}
			BotGroupManageService.setGroupSpecialTitle(groupId, Long.parseLong(commands[1]), commands[2]);
			BotMessageManageService.sendGroupTextMsg(groupId, "尝试设置专属头衔 -> %s -> %s".formatted(commands[1], commands[2]));
		}

		if (command.startsWith("下衔") && BotPermissionManage.isPGPermission(userId))
		{
			if (Long.parseLong(commands[1]) == selfId)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "自身难施己令，此举固不可为");
				return;
			}
			if (!isGroupOwnerTheSelf)
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "未有司管之职，弗为经管之举");
				return;
			}
			if (!BotGroupMemberDataService.isGroupMember(groupId, Long.parseLong(commands[1])))
			{
				BotMessageManageService.sendGroupTextMsg(groupId, "非其域内之人，未可行其事也");
				return;
			}
			BotGroupManageService.setGroupSpecialTitle(groupId, Long.parseLong(commands[1]), "");
			BotMessageManageService.sendGroupTextMsg(groupId, "尝试取消专属头衔 -> %s".formatted(commands[1]));
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