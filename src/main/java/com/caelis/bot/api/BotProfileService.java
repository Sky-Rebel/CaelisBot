package com.caelis.bot.api;

import com.caelis.bot.net.BotNapcatClient;
import org.json.JSONObject;

public class BotProfileService
{
	public static void setBotNickName(String nickName)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/set_qq_profile");
		JSONObject rootObject = new JSONObject();
		rootObject.put("nickname", nickName);
		botNapcatClient.send(rootObject.toString());
	}

	public static void setBotSex(String sex)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/set_qq_profile");
		JSONObject rootObject = new JSONObject();
		rootObject.put("sex", sex);
		rootObject.put("nickname", "");
		botNapcatClient.send(rootObject.toString());
	}

	public static void setBotPersonaNote(String personalNote)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/set_qq_profile");
		JSONObject rootObject = new JSONObject();
		rootObject.put("personal_note", personalNote);
		rootObject.put("nickname", "");
		botNapcatClient.send(rootObject.toString());
	}

	public static void setBotAvatar(String file)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/set_qq_avatar");
		JSONObject rootObject = new JSONObject();
		rootObject.put("file", file);
		botNapcatClient.send(rootObject.toString());
	}

	public static void setBotModelShow(String model, String modelShow)
	{
		BotNapcatClient botNapcatClient = new BotNapcatClient("/_set_model_show");
		JSONObject rootObject = new JSONObject();
		rootObject.put("model", model);
		rootObject.put("model_show", modelShow);
		botNapcatClient.send(rootObject.toString());
	}
}
