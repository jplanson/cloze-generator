package com.jplanson.cloze.controller;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Collections;

import com.jplanson.cloze.model.TestQuestion;
import com.jplanson.cloze.model.ClozeText;
import com.jplanson.cloze.model.Model;
import com.jplanson.cloze.model.ClozeQuestion;
import com.jplanson.cloze.view.ClozeGeneratorGUI;

public class StartTestController 
{
	public Model model;
	public ClozeGeneratorGUI gui;
	
	public StartTestController(Model model, ClozeGeneratorGUI gui)
	{
		this.model = model;
		this.gui = gui;
	}
	
	public void process()
	{
		// Create ClozeQuestionInstances and randomize
		model.testState.testQuestions = getTestQuestions();
		
		// Randomize
		Collections.shuffle(model.testState.testQuestions);
		
		if (model.testState.testQuestions.size() == 0)
		{
			model.testState.testQuestions = null;
			return;
		}
		
		// We have at least one question, so start
		
		clearTest(gui);
		model.testState.started = true;
		model.testState.questionNum = -1;
		
		DisplayQuestionController dq = new DisplayQuestionController(model, gui);
		dq.process(true);
		
		CardLayout testLayout = (CardLayout) gui.panelTest.getLayout();
		testLayout.show(gui.panelTest, "testPerform");
	}
	
	public ArrayList<TestQuestion> getTestQuestions()
	{
		ArrayList<TestQuestion> testQuestions = new ArrayList<TestQuestion>();
		
		for (Integer key : model.masterClozeQuestions.keySet())
		{
			ArrayList<ClozeQuestion> clozeTextQuestions = model.masterClozeQuestions.get(key);
			ClozeText clozeText = model.masterClozeTexts.get(key);
			
			for (ClozeQuestion clozeQuestion : clozeTextQuestions)
			{
				TestQuestion testQuestion = toTestQuestion(clozeQuestion, clozeText);
				testQuestions.add(testQuestion);
			}
		}
		
		return testQuestions;
	}
	
	public TestQuestion toTestQuestion(ClozeQuestion clozeQuestion, ClozeText clozeText)
	{
		String question = clozeText.sampleText.substring(0, clozeQuestion.start) + "___" + clozeText.sampleText.substring(clozeQuestion.end);
		String answer = clozeText.sampleText.substring(clozeQuestion.start, clozeQuestion.end);
		
		return new TestQuestion(question, clozeText.translation, answer);
	}
	
	public static void clearTest(ClozeGeneratorGUI gui)
	{
		gui.btnTestAdvance.setText("Answer");
		gui.lblTestQuestion.setText("");
		gui.lblTestTranslation.setText("");
		gui.lblTestAnswer.setText("");
	}
}
