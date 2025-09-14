package com.caelis.core.msg;

import org.json.JSONObject;

public class ImageMessage extends Message
{
	private static final String msgType = "image";

	private String name;

	private String summary;

	private String file;

	private String subType;

	private String fileId;

	private String url;

	private String path;

	private String fileSize;

	private String fileUnique;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSummary()
	{
		return summary;
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	public String getFile()
	{
		return file;
	}

	public void setFile(String file)
	{
		this.file = file;
	}

	public String getSubType()
	{
		return subType;
	}

	public void setSubType(String subType)
	{
		this.subType = subType;
	}

	public String getFileId()
	{
		return fileId;
	}

	public void setFileId(String fileId)
	{
		this.fileId = fileId;
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
		rootObject.put("summary", getSummary());
		rootObject.put("file", getFile());
		rootObject.put("sub_type", getSubType());
		rootObject.put("data", dataObject);
		return rootObject;
	}
}