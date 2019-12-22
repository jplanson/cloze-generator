package com.jplanson.cloze.controller;

import java.util.ArrayList;
import java.util.List;

import com.jplanson.cloze.dao.DbClozeQuestionDAO;
import com.jplanson.cloze.dao.ClozeTextDAO;
import com.jplanson.cloze.model.ClozeComponent;
import com.jplanson.cloze.model.DbClozeQuestion;
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
		
		List<DbClozeQuestion> dbClozeQuestions = new ArrayList<DbClozeQuestion>();
		
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
			while (i < model.createClozeText.clozeComponents.size() && ccState == model.createClozeText.clozeComponents.get(i).getValue())
			{
				i++;
			}
			
			end = i;
			
			DbClozeQuestion dbClozeQuestion = new DbClozeQuestion(null, null, start, end);
			dbClozeQuestions.add(dbClozeQuestion);
		}
		
		try 
		{
			int clozeTextId = new ClozeTextDAO().insertClozeText(model.createClozeText);
			
			DbClozeQuestionDAO dbClozeQuestionDAO = new DbClozeQuestionDAO();
			for (i = 0; i < dbClozeQuestions.size(); i++)
			{
				DbClozeQuestion dcq = dbClozeQuestions.get(i);
				dcq.clozeTextId = clozeTextId;
				dbClozeQuestionDAO.insertClozeQuestion(dcq);
			}
		} 
		catch (Exception e) 
		{
			//TODO: Print error to screen
			e.printStackTrace();
		}
		
		// Clear form after a successful addition
		gui.inputSampleText.setText("");
		gui.inputTranslation.setText("");
		gui.panelProcessing.removeAll();
		gui.panelProcessing.revalidate();
		gui.panelProcessing.repaint();
		gui.pack();
		model.createClozeText = null;
		
		// Refresh home page list
		UpdateClozeQuestionListController ucsl = new UpdateClozeQuestionListController(model, gui);
		ucsl.process();
	}
}
