package com.caelis.bot.net;

import com.caelis.util.net.HttpPostRequest;
import com.caelis.util.net.HttpRequestInfo;
import com.caelis.util.net.HttpResponseInfo;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class BotNapcatClient
{
	private URL url;

	public BotNapcatClient(String path)
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
		System.out.println(requestBody);
		HttpPostRequest httpPostRequest = new HttpPostRequest(httpRequestInfo);
		HttpResponseInfo httpResponseInfo = httpPostRequest.sendRequest();
		String requestStr = httpResponseInfo.getResponseStr();
		if (requestStr == null || requestStr.isEmpty()) return new JSONObject();
		JSONObject response = new JSONObject(httpResponseInfo.getResponseStr());
		System.out.println(response.toString(4));
		return response;
	}
}