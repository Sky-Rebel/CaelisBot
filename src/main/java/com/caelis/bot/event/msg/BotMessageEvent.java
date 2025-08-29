package com.caelis.bot.event.msg;

import com.caelis.bot.event.BotClientPostEvent;

public class BotMessageEvent extends BotClientPostEvent
{
	private String messageType;

	public String getMessageType()
	{
		return messageType;
	}

	public void setMessageType(String messageType)
	{
		this.messageType = messageType;
	}
}