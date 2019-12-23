package com.jplanson.cloze.controller;

import com.jplanson.cloze.dao.ClozeTextDAO;
import com.jplanson.cloze.dao.DbClozeQuestionDAO;
import com.jplanson.cloze.model.ClozeQuestion;
import com.jplanson.cloze.model.Model;
import com.jplanson.cloze.view.CG_ErrorMessage;
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
			new CG_ErrorMessage("Cloze question is null.");
			return;
		}
		
		try 
		{
			new DbClozeQuestionDAO().deleteById(clozeQuestion.dbQuestion.id);
			
			int numClozeQuestions = new DbClozeQuestionDAO().getClozeQuestionsByTextId(clozeQuestion.parent.id).size();
			
			if (numClozeQuestions == 0)
			{
				new ClozeTextDAO().deleteClozeText(clozeQuestion.parent.id);
			}
		} 
		catch (Exception e) 
		{
			new CG_ErrorMessage("Error occurred while deleting cloze question: " + e.getMessage());
		}
		
		UpdateClozeQuestionListController ucsl = new UpdateClozeQuestionListController(model, gui);
		ucsl.process();
	}
}
