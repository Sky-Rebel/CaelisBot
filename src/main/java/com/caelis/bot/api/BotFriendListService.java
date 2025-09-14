package com.caelis.bot.api;

import com.caelis.bot.net.BotHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BotFriendListService
{
	public static final String ENDPOINT = "get_friend_list";

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
		public long userId;
	}

	public static List<BotFriendListService.FriendInfo> getFriendInfoList()
	{
		BotHttpClient botHttpClient = new BotHttpClient(ENDPOINT);
		String requestBody = String.valueOf(new JSONObject().put("no_cache", false));
		JSONObject jsonObject = botHttpClient.send(requestBody);
		ArrayList<BotFriendListService.FriendInfo> friendInfoList = new ArrayList<>();
		JSONArray dataArray = jsonObject.getJSONArray("data");
		BotFriendListService BotFriendListService = new BotFriendListService();
		for (int i = 0 ; i < dataArray.length() ; i++)
		{
			BotFriendListService.FriendInfo friendInfo = BotFriendListService.new FriendInfo();
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
			friendInfo.userId = friendInfoObject.getLong("user_id");
			friendInfoList.add(friendInfo);
		}
		return friendInfoList;
	}

	public static List<Long> getFriendList()
	{
		BotHttpClient botHttpClient = new BotHttpClient(ENDPOINT);
		String requestBody = String.valueOf(new JSONObject().put("no_cache", false));
		JSONObject jsonObject = botHttpClient.send(requestBody);
		ArrayList<Long> friendUserIDList = new ArrayList<Long>();
		JSONArray dataArray = jsonObject.optJSONArray("data");
		for (int i = 0 ; i < dataArray.length() ; i++)
		{
			JSONObject friendInfoObject = dataArray.getJSONObject(i);
			friendUserIDList.add(friendInfoObject.getLong("user_id"));
		}
		return friendUserIDList;
	}

	public static int getFriendCount()
	{
		return getFriendList().size();
	}

	public static boolean isFriend(long userId)
	{
		return getFriendList().contains(userId);
	}
}
