package com.jplanson.cloze.model;

import java.util.HashMap;

public class Model 
{
	public ClozeText createClozeText = null;
	public ClozeText editClozeText = null;
	
	// Maps cloze text IDs to cloze text objects
	public HashMap<Integer, ClozeText> masterClozeTexts = new HashMap<Integer, ClozeText>();
	
	// Maps cloze question IDs to cloze question instances
	public HashMap<Integer, ClozeQuestion> masterClozeQuestions = new HashMap<Integer, ClozeQuestion>();
	
	// Maps index of strings in the JList to their corresponding cloze question instance
	public HashMap<Integer, Integer> listIndexToClozeQuestionId = new HashMap<Integer, Integer>();
	
	public TestState testState = new TestState();
}
