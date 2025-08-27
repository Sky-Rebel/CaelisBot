package com.caelisbot.bot.event;

public class BotClientEvent
{
	private long time;

	private long selfID;

	private String postType;

	public long getTime()
	{
		return time;
	}

	public void setTime(long time)
	{
		this.time = time;
	}

	public long getSelfID()
	{
		return selfID;
	}

	public void setSelfID(long selfID)
	{
		this.selfID = selfID;
	}

	public String getPostType()
	{
		return postType;
	}

	public void setPostType(String postType)
	{
		this.postType = postType;
	}
}
