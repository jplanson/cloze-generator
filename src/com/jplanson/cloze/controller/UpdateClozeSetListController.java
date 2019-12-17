package com.jplanson.cloze.controller;

import javax.swing.DefaultListModel;

import com.jplanson.cloze.dao.ClozeQuestionDAO;
import com.jplanson.cloze.dao.ClozeTextDAO;
import com.jplanson.cloze.model.ClozeText;
import com.jplanson.cloze.model.Model;
import com.jplanson.cloze.view.ClozeGeneratorGUI;

public class UpdateClozeSetListController 
{
	Model model;
	ClozeGeneratorGUI gui;
	
	public UpdateClozeSetListController(Model model, ClozeGeneratorGUI gui)
	{
		this.model = model;
		this.gui = gui;
	}
	
	public void process()
	{
		try 
		{
			model.masterClozeText = new ClozeTextDAO().getAllClozeTexts();
			model.masterClozeQuestions = new ClozeQuestionDAO().getAllClozeQuestions();
		
			DefaultListModel<ClozeText> listModelClozeText = new DefaultListModel<ClozeText>();
			for (ClozeText c : model.masterClozeText)
			{
				listModelClozeText.addElement(c);
			}
			gui.listClozeSet.setModel(listModelClozeText);
		} 
		catch (Exception e) 
		{
			// TODO: Handle this error properly
			e.printStackTrace();
		}
		
	}
}
