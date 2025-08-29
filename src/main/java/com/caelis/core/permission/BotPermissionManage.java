package com.caelis.core.permission;

import com.caelis.core.data.BotUserDataManage;

public class BotPermissionManage
{
	public static void addUserAsPermissionOwner(BotPermissionType botPermissionType, String userID)
	{
		try (BotUserDataManage botUserDataManage = new BotUserDataManage(userID))
		{
			botUserDataManage.put("permission_type", botPermissionType.toString());
		}
	}

	public static BotPermissionType getUserPermissionType(String userID)
	{
		BotUserDataManage botUserDataManage = new BotUserDataManage(userID);
		String permissionType = botUserDataManage.getString("permission_type", BotPermissionType.MR.toString());
		switch (permissionType)
		{
			case "ZR" : return BotPermissionType.ZR;
			case "CQ" : return BotPermissionType.CG;
			case "PG" : return BotPermissionType.PG;
			default : return BotPermissionType.MR;
		}
	}

	public static boolean isZRPermission(String userID)
	{
		BotPermissionType botPermissionType = getUserPermissionType(userID);
		if (botPermissionType.equals(BotPermissionType.ZR)) return true;
		return false;
	}

	public static boolean isCGPermission(String userID)
	{
		BotPermissionType botPermissionType = getUserPermissionType(userID);
		if (botPermissionType.equals(BotPermissionType.CG) || isZRPermission(userID)) return true;
		return false;
	}

	public static boolean isPGPermission(String userID)
	{
		BotPermissionType botPermissionType = getUserPermissionType(userID);
		if (botPermissionType.equals(BotPermissionType.PG) || isZRPermission(userID) || isCGPermission(userID)) return true;
		return false;
	}
}