package com.caelis.bot.api;

import com.caelis.bot.net.BotHttpClient;
import org.json.JSONObject;

public class BotProfileService
{
	public static void setBotNickName(String nickName)
	{
		BotHttpClient botHttpClient = new BotHttpClient("/set_qq_profile");
		JSONObject rootObject = new JSONObject();
		rootObject.put("nickname", nickName);
		botHttpClient.send(rootObject.toString());
	}

	public static void setBotSex(String sex)
	{
		BotHttpClient botHttpClient = new BotHttpClient("/set_qq_profile");
		JSONObject rootObject = new JSONObject();
		rootObject.put("sex", sex);
		rootObject.put("nickname", "");
		botHttpClient.send(rootObject.toString());
	}

	public static void setBotPersonaNote(String personalNote)
	{
		BotHttpClient botHttpClient = new BotHttpClient("/set_qq_profile");
		JSONObject rootObject = new JSONObject();
		rootObject.put("personal_note", personalNote);
		rootObject.put("nickname", "");
		botHttpClient.send(rootObject.toString());
	}

	public static void setBotAvatar(String file)
	{
		BotHttpClient botHttpClient = new BotHttpClient("/set_qq_avatar");
		JSONObject rootObject = new JSONObject();
		rootObject.put("file", file);
		botHttpClient.send(rootObject.toString());
	}

	public static void setBotModelShow(String model, String modelShow)
	{
		BotHttpClient botHttpClient = new BotHttpClient("/_set_model_show");
		JSONObject rootObject = new JSONObject();
		rootObject.put("model", model);
		rootObject.put("model_show", modelShow);
		botHttpClient.send(rootObject.toString());
	}
}
