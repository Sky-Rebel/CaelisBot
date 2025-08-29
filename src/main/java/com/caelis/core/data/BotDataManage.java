package com.caelis.core.data;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class BotDataManage implements AutoCloseable
{
	private static final String JSON_DATA_FILE_NAME = "bot_json_database.json";

	private static File file = new File(JSON_DATA_FILE_NAME);

	protected JSONObject jsonData;

	protected JSONObject subJsonData;

	protected JSONObject rawSubJsonData;

	protected String userID;

	protected JSONObject userJsonData;

	protected String subJsonDataType;

	public BotDataManage()
	{
		initialization();
	}

	private void initialization()
	{
		if (!file.exists())
		{
			try
			{
				file.createNewFile();
				PrintStream printStream = new PrintStream(file);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("user", new JSONObject());
				jsonObject.put("group", new JSONObject());
				jsonObject.put("system", new JSONObject());
				printStream.write(jsonObject.toString(4).getBytes(StandardCharsets.UTF_8));
				printStream.close();
			}
			catch (IOException ioException)
			{
				ioException.printStackTrace();
			}
		}
		this.jsonData = readJSONDataFromFile();
	}

	public void put(String key, String value)
	{
		subJsonData.put(key, value);
	}

	public void put(String key, int value)
	{
		subJsonData.put(key, value);
	}

	public void put(String key, long value)
	{
		subJsonData.put(key, value);
	}

	public void put(String key, boolean value)
	{
		subJsonData.put(key, value);
	}

	public void put(String key, float value)
	{
		subJsonData.put(key, value);
	}

	public void put(String key, double value)
	{
		subJsonData.put(key, value);
	}

	public String getString(String key)
	{
		return subJsonData.optString(key);
	}

	public int getInt(String key)
	{
		return subJsonData.optInt(key);
	}

	public long getLong(String key)
	{
		return subJsonData.optLong(key);
	}

	public boolean getBoolean(String key)
	{
		return subJsonData.optBoolean(key);
	}

	public float getFloat(String key)
	{
		return subJsonData.optFloat(key);
	}

	public double getDouble(String key)
	{
		return subJsonData.optDouble(key);
	}

	public String getString(String key, String def)
	{
		return subJsonData.optString(key, def);
	}

	public int getInt(String key, int def)
	{
		return subJsonData.optInt(key, def);
	}

	public long getLong(String key, long def)
	{
		return subJsonData.optLong(key, def);
	}

	public boolean getBoolean(String key, boolean def)
	{
		return subJsonData.optBoolean(key, def);
	}

	public float getFloat(String key, float def)
	{
		return subJsonData.optFloat(key, def);
	}

	public double getDouble(String key, double def)
	{
		return subJsonData.optDouble(key, def);
	}

	protected static JSONObject readJSONDataFromFile()
	{
		try
		(
			FileInputStream fileInputStream = new FileInputStream(file);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		)
		{
			String content = new String(bufferedInputStream.readAllBytes(), StandardCharsets.UTF_8);
			return new JSONObject(content);
		}
		catch (IOException ioException)
		{
			ioException.printStackTrace();
			return new JSONObject();
		}
	}

	protected static void writeJSONDataToFile(JSONObject jsonData)
	{
		try
		(
			PrintStream printStream = new PrintStream(file)
		)
		{
			printStream.write(jsonData.toString(4).getBytes(StandardCharsets.UTF_8));
		}
		catch (IOException ioException)
		{
			ioException.printStackTrace();
		}
	}

	@Override
	public void close()
	{
		if (subJsonDataType.equals("group"))
			jsonData.put("group", subJsonData);
		else if (subJsonDataType.equals("user"))
		{
			rawSubJsonData.put(userID, userJsonData);
			jsonData.put("user", rawSubJsonData);
		}
		else
			jsonData.put("system", subJsonData);
		writeJSONDataToFile(jsonData);
	}
}