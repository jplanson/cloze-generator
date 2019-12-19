package com.jplanson.cloze.controller;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Collections;

import com.jplanson.cloze.model.ClozeQuestionInstance;
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
		model.testState.clozeQuestionInstances = getTestQuestions();
		
		// Randomize
		Collections.shuffle(model.testState.clozeQuestionInstances);
		
		if (model.testState.clozeQuestionInstances.size() == 0)
		{
			model.testState.clozeQuestionInstances = null;
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
	
	public ArrayList<ClozeQuestionInstance> getTestQuestions()
	{
		ArrayList<ClozeQuestionInstance> clozeQuestionInstances = new ArrayList<ClozeQuestionInstance>();
		
		for (Integer key : model.masterClozeQuestions.keySet())
		{
			ArrayList<ClozeQuestion> clozeTextQuestions = model.masterClozeQuestions.get(key);
			ClozeText clozeText = model.masterClozeTexts.get(key);
			
			for (ClozeQuestion clozeQuestion : clozeTextQuestions)
			{
				ClozeQuestionInstance clozeQuestionInstance = toQuestionInstance(clozeQuestion, clozeText);
				clozeQuestionInstances.add(clozeQuestionInstance);
			}
		}
		
		return clozeQuestionInstances;
	}
	
	public ClozeQuestionInstance toQuestionInstance(ClozeQuestion clozeQuestion, ClozeText clozeText)
	{
		// TODO: Implement this function
		String question = clozeText.sampleText.substring(0, clozeQuestion.start) + "___" + clozeText.sampleText.substring(clozeQuestion.end);
		String answer = clozeText.sampleText.substring(clozeQuestion.start, clozeQuestion.end);
		
		return new ClozeQuestionInstance(question, clozeText.translation, answer);
	}
	
	public static void clearTest(ClozeGeneratorGUI gui)
	{
		gui.btnTestAdvance.setText("Answer");
		gui.lblTestQuestion.setText("");
		gui.lblTestTranslation.setText("");
		gui.lblTestAnswer.setText("");
	}
}
