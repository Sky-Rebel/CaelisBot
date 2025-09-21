package com.caelis.bot.event.msg;

import com.caelis.bot.event.OneBotEvent;

public class BotMessageEvent extends OneBotEvent
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