package com.jplanson.cloze.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Model 
{
	public ClozeText createClozeText = null;
	
	public HashMap<Integer, ClozeText> masterClozeTexts;
	public HashMap<Integer, ArrayList<ClozeQuestion>> masterClozeQuestions = new HashMap<Integer, ArrayList<ClozeQuestion>>();
	
	public TestState testState = new TestState();
}
