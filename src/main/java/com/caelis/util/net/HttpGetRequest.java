package com.caelis.util.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpGetRequest
{
	private final URL url;

	public HttpGetRequest(String address)
	{
		this.url = createURLByAddress(address);
	}

	public HttpGetRequest(URL url)
	{
		this.url = url;
	}

	public HttpGetRequest(HttpRequestInfo httpRequestInfo)
	{
		this.url = httpRequestInfo.getUrl();
	}

	private URL createURLByAddress(String address)
	{
		URL url = null;
		try
		{
			url = new URL(address);
		}
		catch (MalformedURLException e)
		{
		}
		return url;
	}

	public HttpResponseInfo sendRequest()
	{
		HttpResponseInfo httpResponseInfo = new HttpResponseInfo();
		try
		{
			if (url == null) return httpResponseInfo;
			InputStream inputStream = url.openStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			httpResponseInfo.setResponseStr(new String(bufferedInputStream.readAllBytes(), StandardCharsets.UTF_8));
			return httpResponseInfo;
		}
		catch (IOException e)
		{
			return httpResponseInfo;
		}
	}

	public static String sendRequest(String address)
	{
		try
		{
			InputStream inputStream = new URL(address).openStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			return new String(bufferedInputStream.readAllBytes(), StandardCharsets.UTF_8);
		}
		catch (IOException e)
		{
			return "HTTP GET EXCEPTION";
		}
	}
}