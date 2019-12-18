package com.jplanson.cloze.controller;

import java.awt.CardLayout;

import com.jplanson.cloze.model.Model;
import com.jplanson.cloze.view.ClozeGeneratorGUI;

public class DisplayQuestionController 
{
	Model model;
	ClozeGeneratorGUI gui;
	
	public DisplayQuestionController(Model model, ClozeGeneratorGUI gui)
	{
		this.model = model;
		this.gui = gui;
	}
	
	public void process(boolean forward)
	{
		if (!model.testState.started) { return; }
		
		int i = forward ? 1 : -1;
	
		model.testState.questionNum += i;
		
		if (model.testState.questionNum == -1 || model.testState.questionNum == 0)
		{
			model.testState.questionNum = 0;
			gui.btnTestPrevious.setEnabled(false);
		}
		else if (model.testState.questionNum == model.testState.clozeQuestionInstances.size())
		{
			// Test is over, return to selection screen
			CardLayout cl = (CardLayout) gui.panelTest.getLayout();
			cl.show(gui.panelTest, "testSelect");
			return;
		}
		
		gui.lblTestQuestion.setText(model.testState.clozeQuestionInstances.get(model.testState.questionNum).question);
		gui.lblTestTranslation.setText(model.testState.clozeQuestionInstances.get(model.testState.questionNum).translation);
		gui.lblTestAnswer.setText("");
		
		gui.btnTestAdvance.setText("Answer");
	}
}
