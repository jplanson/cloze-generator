package com.jplanson.cloze.controller;

import com.jplanson.cloze.model.Model;
import com.jplanson.cloze.view.ClozeGeneratorGUI;

public class AnswerQuestionController 
{
	Model model;
	ClozeGeneratorGUI gui;
	
	public AnswerQuestionController(Model model, ClozeGeneratorGUI gui)
	{
		this.model = model;
		this.gui = gui;
	}
	
	public void process()
	{
		if (!model.testState.started) { return; }
		
		gui.btnTestPrevious.setEnabled(true);
		gui.lblTestAnswer.setText(model.testState.testQuestions.get(model.testState.questionNum).answer);
		gui.btnTestAdvance.setText("Next");
	}
}
