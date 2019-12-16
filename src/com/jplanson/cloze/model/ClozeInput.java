package com.jplanson.cloze.model;

import java.util.ArrayList;

public class ClozeInput 
{
	public ArrayList<ClozeComponent> clozeComponents;
	public String translation;
	
	public ClozeInput(String sampleText, String translation)
	{
		clozeComponents = new ArrayList<ClozeComponent>();
		
		// TODO: Abstract this function on a per-language basis so we can decide word or symbol level separation
		for (int i = 0; i < sampleText.length(); i++)
		{
			String c = sampleText.substring(i, i+1);
			clozeComponents.add(new ClozeComponent(c));
		}
		
		this.translation = translation;
	}
}
