package com.caelisbot.util.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpPostRequest
{
	private URL url;

	private String requestBody;

	public HttpPostRequest(HttpRequestInfo httpRequestInfo)
	{
		this.url = httpRequestInfo.getUrl();
		this.requestBody = httpRequestInfo.getRequestBody();
	}

	public HttpResponseInfo sendRequest()
	{
		HttpResponseInfo httpResponseInfo = new HttpResponseInfo();
		try
		{
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setConnectTimeout(25000);
			httpURLConnection.setReadTimeout(25000);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			PrintStream printStream = new PrintStream(outputStream);
			printStream.print(requestBody);
			printStream.close();
			InputStream inputStream = httpURLConnection.getInputStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			httpResponseInfo.setResponseStr(new String(bufferedInputStream.readAllBytes(), StandardCharsets.UTF_8));
			bufferedInputStream.close();
			return httpResponseInfo;
		}
		catch (IOException e)
		{
			return httpResponseInfo;
		}
	}

	public static String sendRequest(String address, String requestBody)
	{
		try
		{
			HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(address).openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setConnectTimeout(25000);
			httpURLConnection.setReadTimeout(25000);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			PrintStream printStream = new PrintStream(outputStream);
			printStream.print(requestBody);
			printStream.close();
			InputStream inputStream = httpURLConnection.getInputStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			String response =  new String(bufferedInputStream.readAllBytes(), StandardCharsets.UTF_8);
			bufferedInputStream.close();
			return response;
		}
		catch (IOException e)
		{
			return "HTTP POST EXCEPTION";
		}
	}
}
