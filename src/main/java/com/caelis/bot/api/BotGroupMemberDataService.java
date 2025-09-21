package com.caelis.bot.api;

import com.caelis.bot.net.BotNapcatClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BotGroupMemberDataService
{
	public static class GroupMemberInfo
	{
		public long groupId;

		public long userId;

		public String nickname;

		public String card;

		public String sex;

		public int age;

		public String area;

		public String level;

		public int qqLevel;

		public long joinTime;

		public long lastSentTime;

		public long titleExpireTime;

		public boolean unfriendly;

		public boolean cardChangeable;

		public boolean isRobot;

		public long shutUpTimestamp;

		public String role;

		public String title;
	}

	public static List<BotGroupMemberDataService.GroupMemberInfo> getGroupMemberInfoList(long groupId)
	{
		String requestBody = String.valueOf(new JSONObject().put("group_id", groupId).put("no_cache", false));
		BotNapcatClient botNapcatClient = new BotNapcatClient("/get_group_member_list");
		JSONObject jsonObject = botNapcatClient.send(requestBody);
		ArrayList<BotGroupMemberDataService.GroupMemberInfo> groupMemberInfoList = new ArrayList<>();
		JSONArray dataArray = jsonObject.getJSONArray("data");
		for (int i = 0 ; i < dataArray.length() ; i++)
		{
			BotGroupMemberDataService.GroupMemberInfo groupMemberInfo = new GroupMemberInfo();
			JSONObject groupMemberInfoObject = dataArray.getJSONObject(i);
			groupMemberInfo.groupId = groupMemberInfoObject.getLong("group_id");
			groupMemberInfo.userId = groupMemberInfoObject.getLong("user_id");
			groupMemberInfo.nickname = groupMemberInfoObject.getString("nickname");
			groupMemberInfo.card = groupMemberInfoObject.getString("card");
			groupMemberInfo.sex = groupMemberInfoObject.getString("sex");
			groupMemberInfo.age = groupMemberInfoObject.getInt("age");
			groupMemberInfo.area = groupMemberInfoObject.getString("area");
			groupMemberInfo.level = groupMemberInfoObject.getString("level");
			groupMemberInfo.qqLevel = groupMemberInfoObject.getInt("qq_level");
			groupMemberInfo.joinTime = groupMemberInfoObject.getLong("join_time");
			groupMemberInfo.lastSentTime = groupMemberInfoObject.getLong("last_sent_time");
			groupMemberInfo.titleExpireTime = groupMemberInfoObject.getLong("title_expire_time");
			groupMemberInfo.unfriendly = groupMemberInfoObject.getBoolean("unfriendly");
			groupMemberInfo.cardChangeable = groupMemberInfoObject.getBoolean("card_changeable");
			groupMemberInfo.isRobot = groupMemberInfoObject.getBoolean("is_robot");
			groupMemberInfo.shutUpTimestamp = groupMemberInfoObject.getLong("shut_up_timestamp");
			groupMemberInfo.role = groupMemberInfoObject.getString("role");
			groupMemberInfo.title = groupMemberInfoObject.getString("title");;
			groupMemberInfoList.add(groupMemberInfo);
		}
		return groupMemberInfoList;
	}

	public static List<Long> getGroupMemberUinList(long groupId)
	{
		List<BotGroupMemberDataService.GroupMemberInfo> groupMemberInfoList = getGroupMemberInfoList(groupId);
		List<Long> groupMemberUinList = new ArrayList<>();
		groupMemberInfoList.forEach(groupMemberInfo ->
		{
			groupMemberUinList.add(groupMemberInfo.userId);
		});
		return groupMemberUinList;
	}

	public static long getGroupOwner(long groupId)
	{
		List<BotGroupMemberDataService.GroupMemberInfo> groupMemberInfoList = getGroupMemberInfoList(groupId);
		return groupMemberInfoList.stream()
								  .filter(groupMemberInfo -> groupMemberInfo.role.equals("owner"))
								  .findFirst()
								  .map(ownerInfo -> ownerInfo.userId)
								  .orElse(-1L);
	}

	public static List<Long> getGroupAdminList(long groupId)
	{
		List<BotGroupMemberDataService.GroupMemberInfo> groupMemberInfoList = getGroupMemberInfoList(groupId);
		return groupMemberInfoList.stream()
								  .filter(groupMemberInfo -> groupMemberInfo.role.equals("admin"))
								  .map(adminInfo -> adminInfo.userId).collect(Collectors.toList());
	}

	public static boolean isGroupOwner(long groupId, long userId)
	{
		return getGroupOwner(groupId) == userId;
	}

	public static boolean hasGroupAdminPermission(long groupId, long userId)
	{
		if (isGroupOwner(groupId, userId)) return true;
		return getGroupAdminList(groupId).contains(userId);
	}

	public static boolean isGroupMember(long groupId, long userId)
	{
		return getGroupMemberUinList(groupId).contains(userId);
	}
}