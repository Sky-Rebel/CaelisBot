package com.caelis.bot.event.msg.group;

import com.caelis.bot.event.msg.BotMessageEvent;

public class BotGroupMessageEvent extends BotMessageEvent
{
	private String messageSubType;

	public String getMessageSubType()
	{
		return messageSubType;
	}

	public void setMessageSubType(String messageSubType)
	{
		this.messageSubType = messageSubType;
	}
}
