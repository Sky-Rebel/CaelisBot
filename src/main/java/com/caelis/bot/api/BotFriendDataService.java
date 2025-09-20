package com.caelis.bot.api;

import com.caelis.bot.net.BotNapcatClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BotFriendDataService
{
	public class FriendInfo
	{
		public int age;

		public int birthdayDay;

		public int birthdayMonth;

		public int birthdayYear;

		public int categoryId;

		public String email;

		public int level;

		public String nickname;

		public String phoneNum;

		public String remark;

		public String sex;

		public long uin;
	}

	public static List<BotFriendDataService.FriendInfo> getFriendInfoList()
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("get_friend_list");
		String requestBody = String.valueOf(new JSONObject().put("no_cache", false));
		JSONObject jsonObject = botNapcatClient.send(requestBody);
		ArrayList<BotFriendDataService.FriendInfo> friendInfoList = new ArrayList<>();
		JSONArray dataArray = jsonObject.getJSONArray("data");
		BotFriendDataService BotFriendDataService = new BotFriendDataService();
		for (int i = 0 ; i < dataArray.length() ; i++)
		{
			BotFriendDataService.FriendInfo friendInfo = BotFriendDataService.new FriendInfo();
			JSONObject friendInfoObject = dataArray.getJSONObject(i);
			friendInfo.age = friendInfoObject.getInt("age");
			friendInfo.birthdayDay = friendInfoObject.getInt("birthday_day");
			friendInfo.birthdayMonth = friendInfoObject.getInt("birthday_month");
			friendInfo.birthdayYear = friendInfoObject.getInt("birthday_year");
			friendInfo.categoryId = friendInfoObject.getInt("category_id");
			friendInfo.email = friendInfoObject.getString("email");
			friendInfo.level = friendInfoObject.getInt("level");
			friendInfo.nickname = friendInfoObject.getString("nickname");
			friendInfo.phoneNum = friendInfoObject.getString("phone_num");
			friendInfo.remark = friendInfoObject.getString("remark");
			friendInfo.sex = friendInfoObject.getString("sex");
			friendInfo.uin = friendInfoObject.getLong("user_id");
			friendInfoList.add(friendInfo);
		}
		return friendInfoList;
	}

	public static List<Long> getFriendUinList()
	{
		List<BotFriendDataService.FriendInfo> friendInfoList = getFriendInfoList();
		List<Long> friendUinList = new ArrayList<>();
		friendInfoList.forEach(friendInfo ->
		{
			friendUinList.add(friendInfo.uin);
		});
		return friendUinList;
	}

	public static List<String> getFriendNicknameList()
	{
		List<BotFriendDataService.FriendInfo> friendInfoList = getFriendInfoList();
		List<String> friendNicknameList = new ArrayList<>();
		friendInfoList.forEach(friendInfo ->
		{
			friendNicknameList.add(friendInfo.nickname);
		});
		return friendNicknameList;
	}

	public static List<String> getFriendPhoneNumList()
	{
		List<BotFriendDataService.FriendInfo> friendInfoList = getFriendInfoList();
		List<String> friendPhoneNumList = new ArrayList<>();
		friendInfoList.forEach(friendInfo ->
		{
			friendPhoneNumList.add(friendInfo.phoneNum);
		});
		return friendPhoneNumList;
	}

	public static List<Integer> getFriendAgeList()
	{
		List<BotFriendDataService.FriendInfo> friendInfoList = getFriendInfoList();
		List<Integer> friendAgeList = new ArrayList<>();
		friendInfoList.forEach(friendInfo ->
		{
			friendAgeList.add(friendInfo.age);
		});
		return friendAgeList;
	}

	public static List<String> getFriendRemarkList()
	{
		List<BotFriendDataService.FriendInfo> friendInfoList = getFriendInfoList();
		List<String> friendRemarkList = new ArrayList<>();
		friendInfoList.forEach(friendInfo ->
		{
			friendRemarkList.add(friendInfo.remark);
		});
		return friendRemarkList;
	}

	public static LinkedHashMap<String, Long> getFriendNicknameAndIdList()
	{
		List<BotFriendDataService.FriendInfo> friendInfoList = getFriendInfoList();
		LinkedHashMap<String, Long> friendNicknameAndIdList = new LinkedHashMap<>();
		friendInfoList.forEach(friendInfo ->
		{
			friendNicknameAndIdList.put(friendInfo.nickname, friendInfo.uin);
		});
		return friendNicknameAndIdList;
	}

	public static int getFriendCount()
	{
		return getFriendUinList().size();
	}

	public static boolean isFriend(long userId)
	{
		return getFriendUinList().contains(userId);
	}

	public static void deleteFriend(long userId)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/delete_friend");
		botNapcatClient.send(new JSONObject().put("user_id", userId).toString());
	}
}