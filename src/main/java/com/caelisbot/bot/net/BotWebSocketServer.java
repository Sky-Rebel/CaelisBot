package com.caelisbot.bot.net;

import com.caelisbot.bot.event.handler.BotClientEventHandler;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;

import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;

public class BotWebSocketServer extends WebSocketServer
{
	private final CountDownLatch countDownLatch;

	public BotWebSocketServer(InetSocketAddress inetSocketAddress, CountDownLatch countDownLatch)
	{
		super(inetSocketAddress);
		this.countDownLatch = countDownLatch;
		countDownLatch.countDown();
	}

	@Override
	public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake)
	{
		System.out.println("客户端连接开启 -> " + webSocket.getRemoteSocketAddress());
		countDownLatch.countDown();
	}

	@Override
	public void onClose(WebSocket webSocket, int i, String s, boolean b)
	{
		System.out.println("客户端连接关闭 -> " + webSocket.getRemoteSocketAddress());
	}

	@Override
	public void onMessage(WebSocket webSocket, String s)
	{
		JSONObject botClientData = new JSONObject(s);
		BotClientEventHandler.handleEvent(botClientData);
	}

	@Override
	public void onError(WebSocket webSocket, Exception e) {}

	@Override
	public void onStart() {}
}