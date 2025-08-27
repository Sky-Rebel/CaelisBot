package com.caelisbot.bot;

import com.caelisbot.bot.net.BotHttpClient;
import com.caelisbot.bot.net.BotNetworkConfig;
import com.caelisbot.bot.net.BotWebSocketServer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class BotEntry
{
	public static void main(String[] args)
	{
		try
		{
			CountDownLatch serverStartedLatch = new CountDownLatch(1);
			InetSocketAddress inetSocketAddress = new InetSocketAddress(BotNetworkConfig.WEB_SOCKET_SERVER_PORT);
			BotWebSocketServer botWebSocketServer = new BotWebSocketServer(inetSocketAddress, serverStartedLatch);
			botWebSocketServer.start();
			serverStartedLatch.await();
			System.out.println(000);
			BotHttpClient botHttpClient = new BotHttpClient("/send_group_msg");
			JSONObject json = new JSONObject();
			json.put("group_id", 634447585);
			JSONArray messages = new JSONArray();
			JSONObject message = new JSONObject();
			message.put("type", "text");
			JSONObject data = new JSONObject();
			data.put("text", "text");
			message.put("data", data);
			messages.put(message);
			json.put("message", messages);
			System.out.println(json.toString());
			JSONObject response = botHttpClient.send(json.toString());
			System.out.println(response.toString());
			Scanner scanner = new Scanner(System.in);
			while (scanner.nextLine().equals("exit")) System.exit(200);
		}
		catch (Throwable e)
		{
		}
	}
}