package com.caelis.bot.event.msg.group.normal;

import com.caelis.bot.event.msg.group.BotGroupMessageEvent;
import org.json.JSONArray;

public class BotNormalGroupMessageEvent extends BotGroupMessageEvent
{
	private String rawMessage;

	private String groupName;

	private long groupId;

	private long userId;

	private long realId;

	private JSONArray message;

	private long messageSeq;

	private long messageId;

	private int font;

	private String messageFormat;

	private Sender sender;

	public class Sender
	{
		private String role;

		private long userId;

		private String nickname;

		private String card;

		public String getRole()
		{
			return role;
		}

		public void setRole(String role)
		{
			this.role = role;
		}

		public long getUserId()
		{
			return userId;
		}

		public void setUserId(long userId)
		{
			this.userId = userId;
		}

		public String getNickname()
		{
			return nickname;
		}

		public void setNickname(String nickname)
		{
			this.nickname = nickname;
		}

		public String getCard()
		{
			return card;
		}

		public void setCard(String card)
		{
			this.card = card;
		}

		@Override
		public String toString()
		{
			return "Sender{" + "role='" + role + '\'' + ", userId=" + userId + ", nickname='" + nickname + '\'' + ", card='" + card + '\'' + '}';
		}
	}

	public String getRawMessage()
	{
		return rawMessage;
	}

	public void setRawMessage(String rawMessage)
	{
		this.rawMessage = rawMessage;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	public long getGroupId()
	{
		return groupId;
	}

	public void setGroupId(long groupId)
	{
		this.groupId = groupId;
	}

	public long getUserId()
	{
		return userId;
	}

	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	public long getRealId()
	{
		return realId;
	}

	public void setRealId(long realId)
	{
		this.realId = realId;
	}

	public JSONArray getMessage()
	{
		return message;
	}

	public void setMessage(JSONArray message)
	{
		this.message = message;
	}

	public long getMessageSeq()
	{
		return messageSeq;
	}

	public void setMessageSeq(long messageSeq)
	{
		this.messageSeq = messageSeq;
	}

	public long getMessageId()
	{
		return messageId;
	}

	public void setMessageId(long messageId)
	{
		this.messageId = messageId;
	}

	public int getFont()
	{
		return font;
	}

	public void setFont(int font)
	{
		this.font = font;
	}

	public String getMessageFormat()
	{
		return messageFormat;
	}

	public void setMessageFormat(String messageFormat)
	{
		this.messageFormat = messageFormat;
	}

	public Sender getSender()
	{
		return sender;
	}

	public void setSender(Sender sender)
	{
		this.sender = sender;
	}

	@Override
	public String toString()
	{
		return "BotGroupNormalMessageEvent{" + "rawMessage='" + rawMessage + '\'' + ", groupName='" + groupName + '\'' + ", groupId=" + groupId + ", userId=" + userId + ", realId=" + realId + ", message=" + message + ", messageSeq=" + messageSeq + ", messageId=" + messageId + ", font=" + font + ", messageFormat='" + messageFormat + '\'' + ", sender=" + sender + '}';
	}
}