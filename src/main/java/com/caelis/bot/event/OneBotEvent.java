package com.caelis.bot.event;

public class OneBotEvent
{
	private long time;

	private long selfId;

	private String postType;

	public long getTime()
	{
		return time;
	}

	public void setTime(long time)
	{
		this.time = time;
	}

	public long getSelfId()
	{
		return selfId;
	}

	public void setSelfId(long selfID)
	{
		this.selfId = selfID;
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