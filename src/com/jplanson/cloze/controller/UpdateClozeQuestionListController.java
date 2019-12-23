package com.jplanson.cloze.controller;

import java.util.List;

import javax.swing.DefaultListModel;

import com.jplanson.cloze.dao.DbClozeQuestionDAO;
import com.jplanson.cloze.dao.ClozeTextDAO;
import com.jplanson.cloze.model.DbClozeQuestion;
import com.jplanson.cloze.model.ClozeQuestion;
import com.jplanson.cloze.model.ClozeText;
import com.jplanson.cloze.model.Model;
import com.jplanson.cloze.view.CG_ErrorMessage;
import com.jplanson.cloze.view.ClozeGeneratorGUI;

public class UpdateClozeQuestionListController 
{
	Model model;
	ClozeGeneratorGUI gui;
	
	public UpdateClozeQuestionListController(Model model, ClozeGeneratorGUI gui)
	{
		this.model = model;
		this.gui = gui;
	}
	
	public void process()
	{
		try 
		{
			// Clear previous state 
			model.masterClozeTexts.clear();
			model.masterClozeQuestions.clear();
			model.listIndexToClozeQuestionId.clear();
			
			// Update model.masterClozeTexts
			List<ClozeText> clozeTexts = new ClozeTextDAO().getAllClozeTexts();
			for (ClozeText clozeText : clozeTexts)
			{
				model.masterClozeTexts.put(clozeText.id, clozeText);
			}
			
			List<DbClozeQuestion> dbClozeQuestions = new DbClozeQuestionDAO().getAllClozeQuestions();
			DefaultListModel<ClozeQuestion> listModelClozeQuestion = new DefaultListModel<ClozeQuestion>();
			int i = 0;
			for (DbClozeQuestion dbClozeQuestion : dbClozeQuestions)
			{
				ClozeQuestion clozeQuestion = new ClozeQuestion(model.masterClozeTexts.get(dbClozeQuestion.clozeTextId), dbClozeQuestion);
				model.masterClozeQuestions.put(dbClozeQuestion.id, clozeQuestion);
				listModelClozeQuestion.addElement(clozeQuestion);
				model.listIndexToClozeQuestionId.put(i, clozeQuestion.dbQuestion.id);
				i++;
			}
			
			gui.listClozeSet.setModel(listModelClozeQuestion);
		} 
		catch (Exception e) 
		{
			new CG_ErrorMessage("Error occurred while updating cloze question list: " + e.getMessage());
		}
	}
}
