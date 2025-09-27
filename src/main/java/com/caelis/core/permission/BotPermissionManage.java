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

	public static BotPermissionType getUserPermissionType(long userID)
	{
		return getUserPermissionType(String.valueOf(userID));
	}

	public static BotPermissionType getUserPermissionType(String userID)
	{
		String permissionType;
		try (BotUserDataManage botUserDataManage = new BotUserDataManage(String.valueOf(userID)))
		{
			permissionType = botUserDataManage.getString("permission_type", BotPermissionType.MR.toString());
		}
		return switch (permissionType)
		{
			case "ZR" -> BotPermissionType.ZR;
			case "CQ" -> BotPermissionType.CG;
			case "PG" -> BotPermissionType.PG;
			default -> BotPermissionType.MR;
		};
	}

	public static boolean isZRPermission(long userID)
	{
		return isZRPermission(String.valueOf(userID));
	}

	public static boolean isCGPermission(long userID)
	{
		return isCGPermission(String.valueOf(userID));
	}

	public static boolean isPGPermission(long userID)
	{
		return isPGPermission(String.valueOf(userID));
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
		System.out.println(botPermissionType);
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