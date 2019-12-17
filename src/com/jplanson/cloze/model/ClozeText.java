package com.jplanson.cloze.model;

import java.util.ArrayList;
import java.util.List;

public class ClozeText 
{
	public Integer id;
	public ArrayList<ClozeComponent> clozeComponents;
	public String sampleText;
	public String translation;
	
	public ClozeText(Integer id, String sampleText, String translation)
	{
		this.id = id; 
		this.sampleText = sampleText;
		this.translation = translation;
		
		clozeComponents = new ArrayList<ClozeComponent>();
		
		// TODO: Abstract this function on a per-language basis so we can decide word or symbol level separation
		for (int i = 0; i < sampleText.length(); i++)
		{
			String c = sampleText.substring(i, i+1);
			clozeComponents.add(new ClozeComponent(c));
		}
	}
	
	public ClozeText(Integer id, String sampleText, String translation, List<ClozeQuestion> clozeQuestions)
	{
		this.id = id;
		this.sampleText = sampleText;
		this.translation = translation;
	}
	
	@Override
	public String toString()
	{
		String simpleSampleText = sampleText;
		if (sampleText.length() > 20)
		{
			simpleSampleText = sampleText.substring(0, 20) + "...";
		}
		String simpleTranslation = translation;
		if (translation.length() > 20)
		{
			simpleTranslation = translation.substring(0, 20) + "...";
		}
		return "Set ID : " + id + "   |   " + simpleSampleText + "   |   " + simpleTranslation;
	}
}
