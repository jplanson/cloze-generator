package com.jplanson.cloze.model;

public class ModularInteger 
{
	private int val;
	private int mod;
	
	public ModularInteger(int val, int mod)
	{
		this.val = val;
		this.mod = mod;
	}
	
	public void increment()
	{
		val++;
		val %= mod;
	}
	
	public int getValue()
	{
		return val;
	}
	
	public void setValue(int value)
	{
		val = value % mod;
	}
}
