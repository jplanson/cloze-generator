package com.jplanson.cloze.controller;

import com.jplanson.cloze.dao.ClozeTextDAO;
import com.jplanson.cloze.model.ClozeText;
import com.jplanson.cloze.model.Model;
import com.jplanson.cloze.view.CG_ErrorMessage;
import com.jplanson.cloze.view.ClozeGeneratorGUI;

public class DeleteClozeSetController 
{
	public Model model;
	public ClozeGeneratorGUI gui;
	
	public DeleteClozeSetController(Model model, ClozeGeneratorGUI gui)
	{
		this.model = model;
		this.gui = gui;
	}
	
	public void process(int clozeQuestionIndex)
	{
		ClozeText clozeText = model.masterClozeQuestions.get(model.listIndexToClozeQuestionId.get(clozeQuestionIndex)).parent;
		if (clozeText == null)
		{
			new CG_ErrorMessage("Cloze text is null.");
		}
		
		try 
		{
			new ClozeTextDAO().deleteClozeText(clozeText.id);
		} 
		catch (Exception e) 
		{
			new CG_ErrorMessage("Error occurred while deleting cloze text: " + e.getMessage());
		}
		
		UpdateClozeQuestionListController ucsl = new UpdateClozeQuestionListController(model, gui);
		ucsl.process();
	}
}
