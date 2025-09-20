package com.caelis.bot;

import com.caelis.bot.api.BotMessageManageService;
import com.caelis.bot.net.BotNetworkConfig;
import com.caelis.bot.net.BotWebSocketServer;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class BotEntry
{
	public static final long BOT_CG_GROUP = 634447585;

	public static void main(String[] args)
	{
		BotMessageManageService.sendGroupTextMsg(BOT_CG_GROUP, "Caelis Bot 启动成功, 即将启动服务器");
		BotMessageManageService.sendCaelisBotImage(BOT_CG_GROUP);
		try
		{
			CountDownLatch serverStartedLatch = new CountDownLatch(1);
			InetSocketAddress inetSocketAddress = new InetSocketAddress(BotNetworkConfig.WEB_SOCKET_SERVER_PORT);
			BotWebSocketServer botWebSocketServer = new BotWebSocketServer(inetSocketAddress, serverStartedLatch);
			botWebSocketServer.start();
			serverStartedLatch.await();
			Scanner scanner = new Scanner(System.in);
			while (scanner.nextLine().equals("exit")) System.exit(200);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}
}