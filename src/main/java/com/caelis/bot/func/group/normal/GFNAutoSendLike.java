package com.caelis.bot.func.group.normal;

import com.caelis.bot.api.BotMessageSendService;
import com.caelis.bot.api.BotSendLikeService;
import com.caelis.bot.event.msg.group.normal.BotGroupNormalMessageEvent;
import com.caelis.bot.event.msg.group.normal.IBotGroupNormalMessageEventHandler;

public class GFNAutoSendLike implements IBotGroupNormalMessageEventHandler
{
	@Override
	public void handleGroupNormalMessageEvent(BotGroupNormalMessageEvent botGroupNormalMessageEvent)
	{
		if (botGroupNormalMessageEvent.getRawMessage().equals("赞我"))
		{
			BotSendLikeService.sendLike(botGroupNormalMessageEvent.getUserId(), 10);
			BotMessageSendService.sendGroupTextMsg(botGroupNormalMessageEvent.getGroupId(), "既已完成点赞 —— CaelisBot");
		}
	}
}