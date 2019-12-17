package com.jplanson.cloze.controller;

import java.util.ArrayList;
import java.util.List;

import com.jplanson.cloze.dao.ClozeQuestionDAO;
import com.jplanson.cloze.dao.ClozeTextDAO;
import com.jplanson.cloze.model.ClozeComponent;
import com.jplanson.cloze.model.ClozeQuestion;
import com.jplanson.cloze.model.Model;
import com.jplanson.cloze.view.ClozeGeneratorGUI;

public class GenerateClozeQuestionsController 
{
	public Model model;
	public ClozeGeneratorGUI gui;
	
	public GenerateClozeQuestionsController(Model model, ClozeGeneratorGUI gui)
	{
		this.model = model;
		this.gui = gui;
	}
	
	public void process()
	{
		if (model.createClozeText == null)
		{
			return;
		}
		
		List<ClozeQuestion> clozeQuestions = new ArrayList<ClozeQuestion>();
		
		// Separate input into chunks based on state
		int i = 0;
		while (i < model.createClozeText.clozeComponents.size())
		{
			ClozeComponent cc = model.createClozeText.clozeComponents.get(i);
			int ccState = cc.getValue();
			
			if (ccState == 0) { i++; continue; }
			
			int start = i;
			int end = -1;
			
			// Iterate until a component with a different state is found
			while (ccState == model.createClozeText.clozeComponents.get(i).getValue())
			{
				i++;
			}
			
			end = i - 1;
			
			ClozeQuestion clozeQuestion = new ClozeQuestion(null, null, start, end);
			clozeQuestions.add(clozeQuestion);
		}
		
		try 
		{
			int clozeTextId = new ClozeTextDAO().insertClozeText(model.createClozeText);
			
			ClozeQuestionDAO clozeQuestionDAO = new ClozeQuestionDAO();
			for (i = 0; i < clozeQuestions.size(); i++)
			{
				ClozeQuestion cq = clozeQuestions.get(i);
				cq.clozeTextId = clozeTextId;
				clozeQuestionDAO.insertClozeQuestion(cq);
			}
		} 
		catch (Exception e) 
		{
			//TODO: Print error to screen
			e.printStackTrace();
		}
		
		UpdateClozeSetListController ucsl = new UpdateClozeSetListController(model, gui);
		ucsl.process();
	}
}
