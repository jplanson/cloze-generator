package com.jplanson.cloze.model;

import java.util.Date;

public class ClozeQuestion 
{
	public Integer id;
	public Integer clozeTextId;
	public int start;
	public int end;
	public Date createTime;
	
	public ClozeQuestion(Integer id, Integer clozeTextId, int start, int end)
	{
		this.id = id;
		this.clozeTextId = clozeTextId;
		this.start = start;
		this.end = end;
	}
}
