package com.jplanson.cloze.controller;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Collections;

import com.jplanson.cloze.model.TestQuestion;
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
		// Create ClozeQuestionInstances
		model.testState.testQuestions = getTestQuestions();
		if (model.testState.testQuestions.size() == 0)
		{
			model.testState.testQuestions = null;
			return;
		}
		
		// Randomize
		Collections.shuffle(model.testState.testQuestions);
		
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
			ClozeQuestion clozeQuestion = model.masterClozeQuestions.get(key);
			testQuestions.add(clozeQuestion.toTestQuestion());
		}
		
		return testQuestions;
	}
	
	public static void clearTest(ClozeGeneratorGUI gui)
	{
		gui.btnTestAdvance.setText("Answer");
		gui.lblTestQuestion.setText("");
		gui.lblTestTranslation.setText("");
		gui.lblTestAnswer.setText("");
	}
}
