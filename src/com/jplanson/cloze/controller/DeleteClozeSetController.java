package com.jplanson.cloze.controller;

import com.jplanson.cloze.dao.ClozeTextDAO;
import com.jplanson.cloze.model.ClozeText;
import com.jplanson.cloze.model.Model;
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
			// TODO: Print error somewhere and return
			System.out.println("Cloze text is Null!");
			return;
		}
		
		try 
		{
			new ClozeTextDAO().deleteClozeText(clozeText.id);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		UpdateClozeQuestionListController ucsl = new UpdateClozeQuestionListController(model, gui);
		ucsl.process();
	}
}
