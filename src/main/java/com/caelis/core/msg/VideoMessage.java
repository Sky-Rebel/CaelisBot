package com.caelis.core.msg;

import org.json.JSONObject;

public class VideoMessage extends Message
{
	public static final String msgType = "video";

	private String file;

	private String name;

	private String thumb;

	private String url;

	private String path;

	private String fileId;

	private String fileSize;

	private String fileUnique;

	public String getFile()
	{
		return file;
	}

	public void setFile(String file)
	{
		this.file = file;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getThumb()
	{
		return thumb;
	}

	public void setThumb(String thumb)
	{
		this.thumb = thumb;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getFileId()
	{
		return fileId;
	}

	public void setFileId(String fileId)
	{
		this.fileId = fileId;
	}

	public String getFileSize()
	{
		return fileSize;
	}

	public void setFileSize(String fileSize)
	{
		this.fileSize = fileSize;
	}

	public String getFileUnique()
	{
		return fileUnique;
	}

	public void setFileUnique(String fileUnique)
	{
		this.fileUnique = fileUnique;
	}

	@Override
	public String getMsgType()
	{
		return msgType;
	}

	@Override
	public JSONObject toJSONObject()
	{
		JSONObject rootObject = new JSONObject();
		rootObject.put("type", getMsgType());
		JSONObject dataObject = new JSONObject();
		dataObject.put("name", getName());
		dataObject.put("file", getFile());
		dataObject.put("thumb", getThumb());
		rootObject.put("data", dataObject);
		return rootObject;
	}
}