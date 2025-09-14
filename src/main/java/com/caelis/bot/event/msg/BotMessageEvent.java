package com.caelis.bot.event.msg;

import com.caelis.bot.event.BotEvent;

public class BotMessageEvent extends BotEvent
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