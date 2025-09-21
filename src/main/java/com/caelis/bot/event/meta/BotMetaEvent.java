package com.caelis.bot.event.meta;

import com.caelis.bot.event.OneBotEvent;

public class BotMetaEvent extends OneBotEvent
{
	private String metaEventType;

	public String getMetaEventType()
	{
		return metaEventType;
	}

	public void setMetaEventType(String metaEventType)
	{
		this.metaEventType = metaEventType;
	}
}