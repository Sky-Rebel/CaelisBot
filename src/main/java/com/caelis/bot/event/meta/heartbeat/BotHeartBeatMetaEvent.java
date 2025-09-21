package com.caelis.bot.event.meta.heartbeat;

import com.caelis.bot.event.meta.BotMetaEvent;

public class BotHeartBeatMetaEvent extends BotMetaEvent
{
	private long interval;

	private BotStatus botStatus;

	public BotStatus getBotStatus()
	{
		return botStatus;
	}

	public void setBotStatus(BotStatus botStatus)
	{
		this.botStatus = botStatus;
	}

	public static class BotStatus
	{
		private boolean online;

		private boolean good;

		public boolean isOnline()
		{
			return online;
		}

		public void setOnline(boolean online)
		{
			this.online = online;
		}

		public boolean isGood()
		{
			return good;
		}

		public void setGood(boolean good)
		{
			this.good = good;
		}
	}

	public long getInterval()
	{
		return interval;
	}

	public void setInterval(long interval)
	{
		this.interval = interval;
	}
}
