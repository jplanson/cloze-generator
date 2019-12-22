package com.jplanson.cloze.controller;

import com.jplanson.cloze.dao.DbClozeQuestionDAO;
import com.jplanson.cloze.model.ClozeQuestion;
import com.jplanson.cloze.model.Model;
import com.jplanson.cloze.view.ClozeGeneratorGUI;

public class DeleteClozeQuestionController 
{
	public Model model;
	public ClozeGeneratorGUI gui;
	
	public DeleteClozeQuestionController(Model model, ClozeGeneratorGUI gui)
	{
		this.model = model;
		this.gui = gui;
	}
	
	public void process(int clozeQuestionIndex)
	{
		ClozeQuestion clozeQuestion = model.masterClozeQuestions.get(model.listIndexToClozeQuestionId.get(clozeQuestionIndex));
		if (clozeQuestion == null)
		{
			// TODO: Print error somewhere and return
			System.out.println("Cloze question is Null!");
			return;
		}
		
		try 
		{
			new DbClozeQuestionDAO().deleteById(clozeQuestion.dbQuestion.id);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		UpdateClozeQuestionListController ucsl = new UpdateClozeQuestionListController(model, gui);
		ucsl.process();
	}
}
