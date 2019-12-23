package com.jplanson.cloze.controller;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;

import com.jplanson.cloze.dao.DbClozeQuestionDAO;
import com.jplanson.cloze.dao.ClozeTextDAO;
import com.jplanson.cloze.model.ClozeComponent;
import com.jplanson.cloze.model.ClozeText;
import com.jplanson.cloze.model.DbClozeQuestion;
import com.jplanson.cloze.model.Model;
import com.jplanson.cloze.view.CG_ErrorMessage;
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
	
	public void process(boolean create)
	{
		ClozeText clozeText = create ? model.createClozeText : model.editClozeText;
		
		if (clozeText == null)
		{
			return;
		}
		
		List<DbClozeQuestion> dbClozeQuestions = new ArrayList<DbClozeQuestion>();
		
		// Separate input into chunks based on state
		int i = 0;
		while (i < clozeText.clozeComponents.size())
		{
			ClozeComponent cc = clozeText.clozeComponents.get(i);
			int ccState = cc.getValue();
			
			if (ccState == 0) { i++; continue; }
			
			int start = i;
			int end = -1;
			
			// Iterate until a component with a different state is found
			while (i < clozeText.clozeComponents.size() && ccState == clozeText.clozeComponents.get(i).getValue())
			{
				i++;
			}
			
			end = i;
			
			DbClozeQuestion dbClozeQuestion = new DbClozeQuestion(null, null, start, end);
			dbClozeQuestions.add(dbClozeQuestion);
		}
		
		if (dbClozeQuestions.size() == 0) { return; }
		
		
		try 
		{
			DbClozeQuestionDAO dbClozeQuestionDAO = new DbClozeQuestionDAO();
			Integer clozeTextId = null;
			
			// If we are editing, delete the previous cloze questions
			if (!create)
			{
				dbClozeQuestionDAO.deleteByTextId(clozeText.id);
				clozeTextId = clozeText.id;
			}
			else 
			{
				clozeTextId = new ClozeTextDAO().insertClozeText(clozeText);
			}
			
			for (i = 0; i < dbClozeQuestions.size(); i++)
			{
				DbClozeQuestion dcq = dbClozeQuestions.get(i);
				dcq.clozeTextId = clozeTextId;
				dbClozeQuestionDAO.insertClozeQuestion(dcq);
			}
		} 
		catch (Exception e) 
		{
			new CG_ErrorMessage("Error occurred while inserting cloze question: " + e.getMessage());
		}
		
		if (create)
		{
			// Clear form after a successful addition
			gui.inputSampleText.setText("");
			gui.inputTranslation.setText("");
			gui.panelProcessing.removeAll();
			gui.panelProcessing.revalidate();
			gui.panelProcessing.repaint();
			model.createClozeText = null;
		}
		else
		{
			gui.panelEditProcessing.removeAll();
			gui.panelEditProcessing.revalidate();
			gui.panelEditProcessing.repaint();
			model.editClozeText = null;
			CardLayout cl = (CardLayout) gui.panelContent.getLayout();
			cl.show(gui.panelContent, "home");
		}
		
		gui.pack();
		
		// Refresh home page list
		UpdateClozeQuestionListController ucsl = new UpdateClozeQuestionListController(model, gui);
		ucsl.process();
	}
}
