package com.jplanson.cloze.model;

import java.util.Date;

public class DbClozeQuestion implements Comparable<DbClozeQuestion>
{
	public Integer id;
	public Integer clozeTextId;
	public int start;
	public int end;
	public Date createTime;
	
	public DbClozeQuestion(Integer id, Integer clozeTextId, int start, int end)
	{
		this.id = id;
		this.clozeTextId = clozeTextId;
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(DbClozeQuestion other) 
	{
		return this.id.compareTo(other.id);
	}
}
