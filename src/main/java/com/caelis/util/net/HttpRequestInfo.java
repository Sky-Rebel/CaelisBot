package com.caelis.util.net;

import java.net.URL;

public class HttpRequestInfo
{
	private URL url;

	private String requestBody;

	public URL getUrl()
	{
		return url;
	}

	public void setUrl(URL url)
	{
		this.url = url;
	}

	public String getRequestBody()
	{
		return requestBody;
	}

	public void setRequestBody(String requestBody)
	{
		this.requestBody = requestBody;
	}
}
