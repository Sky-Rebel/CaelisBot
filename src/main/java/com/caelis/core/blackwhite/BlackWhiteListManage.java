package com.caelis.core.blackwhite;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class BlackWhiteListManage
{
	private static final String DATA_FILE_NAME = "black_white_list.json";

	private static final Path DATA_FILE_PATH = Path.of(DATA_FILE_NAME);

	static
	{
		if (Files.notExists(DATA_FILE_PATH))
		{
			try
			{
				Files.createFile(DATA_FILE_PATH);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("black_list", new JSONArray());
				jsonObject.put("white_list", new JSONArray());
				Files.writeString(DATA_FILE_PATH, jsonObject.toString(4));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private JSONObject getDataFileContent()
	{
		try
		{
			return new JSONObject(Files.readString(DATA_FILE_PATH, StandardCharsets.UTF_8));
		}
		catch (IOException e)
		{
			return new JSONObject();
		}
	}

	public boolean isBlackList(long userId)
	{
		JSONObject jsonObject = getDataFileContent();
		JSONArray jsonArray = jsonObject.getJSONArray("black_list");
		return jsonArray.toList().contains(userId);
	}

	public boolean isWhiteList(long userId)
	{
		JSONObject jsonObject = getDataFileContent();
		JSONArray jsonArray = jsonObject.getJSONArray("white_list");
		return jsonArray.toList().contains(userId);
	}

	public boolean addBlackList(long userId)
	{
		return addUserToList(true, userId);
	}

	public boolean addWhiteList(long userId)
	{
		return addUserToList(false, userId);
	}

	public List<Long> getBlackList()
	{
		return getUserList(true);
	}

	public List<Long> getWhiteList()
	{
		return getUserList(false);
	}

	private List<Long> getUserList(boolean isBlackList)
	{
		JSONObject jsonObject = getDataFileContent();
		JSONArray jsonArray = jsonObject.getJSONArray(isBlackList? "black_list" : "white_list");
		return jsonArray.toList().stream().map(Number.class::cast).map(Number::longValue).collect(Collectors.toList());
	}

	private boolean addUserToList(boolean isBlackList, long userId)
	{
		if (isBlackList)
			if (isBlackList(userId))
				return false;
		else
			if (isWhiteList(userId))
				return false;
		JSONObject jsonObject = getDataFileContent();
		JSONArray jsonArray = jsonObject.getJSONArray(isBlackList ? "black_list" : "white_list");
		jsonArray.put(userId);
		return true;
	}

	public boolean removeBlackList(long userId)
	{
		return removeUserFromList(true, userId);
	}

	public boolean removeWhiteList(long userId)
	{
		return removeUserFromList(false, userId);
	}

	private boolean removeUserFromList(boolean isBlackList, long userId)
	{
		if (isBlackList)
			if (!isBlackList(userId))
				return false;
		else
			if (!isWhiteList(userId))
				return false;
		JSONObject jsonObject = getDataFileContent();
		JSONArray jsonArray = jsonObject.getJSONArray(isBlackList ? "black_list" : "white_list");
		for (int i = jsonArray.length() -1; i >= 0; i--)
		{
			if (jsonArray.getLong(i) == userId)
				jsonArray.remove(i);
		}
		return true;
	}
}