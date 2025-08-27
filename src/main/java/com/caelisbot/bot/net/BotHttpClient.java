package com.caelisbot.bot.net;

import com.caelisbot.util.net.HttpPostRequest;
import com.caelisbot.util.net.HttpRequestInfo;
import com.caelisbot.util.net.HttpResponseInfo;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class BotHttpClient
{
	private URL url;

	public BotHttpClient(String path)
	{
		try
		{
			this.url = new URL(BotNetworkConfig.HTTP_CLIENT_ADDRESS + path);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
	}

	public JSONObject send(String requestBody)
	{
		HttpRequestInfo httpRequestInfo = new HttpRequestInfo();
		httpRequestInfo.setUrl(this.url);
		httpRequestInfo.setRequestBody(requestBody);
		HttpPostRequest httpPostRequest = new HttpPostRequest(httpRequestInfo);
		HttpResponseInfo httpResponseInfo = httpPostRequest.sendRequest();
		return new JSONObject(httpResponseInfo.getResponseStr());
	}
}
