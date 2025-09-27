package com.caelis.bot.net;

public class BotNetworkConfig
{
	public static final String SCHEME = "http";

	public static final String HOST = "127.0.0.1";

	public static final int HTTP_CLIENT_PORT = 9452;

	public static final int HTTP_SERVER_PORT = 9572;

	public static final int WEB_SOCKET_SERVER_PORT = 9347;

	public static final String HTTP_CLIENT_ADDRESS = SCHEME + ":" + "//" + HOST + ":" + HTTP_CLIENT_PORT;
}