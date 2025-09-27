package com.caelis.bot.net;

import com.caelis.bot.api.BotMessageManageService;
import com.caelis.bot.event.handler.OneBotEventHandler;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BotHttpNapcatServer
{
	public static void start()
	{
		new Thread(()->
		{
			try
			(
			 	ServerSocket serverSocket = new ServerSocket(BotNetworkConfig.HTTP_SERVER_PORT);
			 	ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()
			)
			{
				BotMessageManageService.sendGroupTextMsgToMainGroup("Http Server 启动成功！");
				while (!serverSocket.isClosed())
				{
					Socket socket = serverSocket.accept();
					executorService.submit(() ->
					{
						try
						{
							// BotMessageManageService.sendGroupTextMsgToMainGroup("New Http Request");
							OutputStream outputStream = socket.getOutputStream();
							PrintStream printStream = new PrintStream(outputStream);
							printStream.write("{}".getBytes());
							socket.shutdownOutput();
							InputStream inputStream = socket.getInputStream();
							BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
							String requestBody = new String(bufferedInputStream.readAllBytes(), StandardCharsets.UTF_8);
							String[] requestLines = requestBody.split("\n");
							StringBuilder jsonData = new StringBuilder();
							boolean isJson = false;
							for (String line : requestLines)
							{
								if (line.startsWith("{"))
									isJson = true;
								if (isJson)
									jsonData.append(line);
							}
							bufferedInputStream.close();
							JSONObject jsonObject = new JSONObject(jsonData.toString());
							OneBotEventHandler.handleEvent(jsonObject);
							System.out.println(jsonObject.toString(4));
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
					});
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}).start();
	}
}
