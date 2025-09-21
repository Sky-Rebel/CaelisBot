package com.caelis.bot.func.group.normal;

import com.caelis.bot.api.BotMessageManageService;
import com.caelis.bot.api.BotSendLikeService;
import com.caelis.bot.event.msg.group.normal.BotNormalGroupMessageEvent;
import com.caelis.bot.event.msg.group.normal.IBotNormalGroupMessageEventHandler;

public class GFNAutoSendLike implements IBotNormalGroupMessageEventHandler
{
	@Override
	public void handleGroupNormalMessageEvent(BotNormalGroupMessageEvent botNormalGroupMessageEvent)
	{
		if (botNormalGroupMessageEvent.getRawMessage().equals("赞我"))
		{
			BotSendLikeService.sendLike(botNormalGroupMessageEvent.getUserId(), 10);
			BotMessageManageService.sendGroupTextMsg(botNormalGroupMessageEvent.getGroupId(), "既已完成点赞 —— CaelisBot");
		}
	}
}