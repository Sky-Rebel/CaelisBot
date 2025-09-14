package com.caelis.core.msg;

import org.json.JSONObject;

public class RecordMessage extends Message
{
	public static final String msgType = "record";

	private String file;

	private String name;

	private String curl;

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

	public String getCurl()
	{
		return curl;
	}

	public void setCurl(String curl)
	{
		this.curl = curl;
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
		rootObject.put("data", dataObject);
		return rootObject;
	}
}