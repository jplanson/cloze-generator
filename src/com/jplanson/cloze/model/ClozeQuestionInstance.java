package com.jplanson.cloze.model;

public class ClozeQuestionInstance 
{
	public String question;
	public String translation;
	public String answer;
	
	public ClozeQuestionInstance(String question, String translation, String answer)
	{
		this.question = question;
		this.translation = translation;
		this.answer = answer;
	}
}
