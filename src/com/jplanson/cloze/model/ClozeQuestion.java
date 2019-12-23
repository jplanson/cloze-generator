package com.jplanson.cloze.model;

public class ClozeQuestion
{
	public ClozeText parent;
	public DbClozeQuestion dbQuestion;
	
	public ClozeQuestion(ClozeText parent, DbClozeQuestion dbQuestion)
	{
		this.parent = parent;
		this.dbQuestion = dbQuestion;
	}
	
	public TestQuestion toTestQuestion()
	{
		String question = parent.sampleText.substring(0, dbQuestion.start) + "___" + parent.sampleText.substring(dbQuestion.end);
		String answer = parent.sampleText.substring(dbQuestion.start, dbQuestion.end);
		
		return new TestQuestion(question, parent.translation, answer);
	}
	
	@Override
	public String toString()
	{
		TestQuestion question = toTestQuestion();
		return "ID: " + dbQuestion.id + "  |  " + question.answer + "  | " + question.translation;
	}
}
