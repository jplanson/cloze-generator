package com.jplanson.cloze.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;

import com.jplanson.cloze.dao.ClozeQuestionDAO;
import com.jplanson.cloze.dao.ClozeTextDAO;
import com.jplanson.cloze.model.ClozeQuestion;
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
			List<ClozeText> clozeTexts = new ClozeTextDAO().getAllClozeTexts();
			DefaultListModel<ClozeText> listModelClozeText = new DefaultListModel<ClozeText>();
			model.masterClozeTexts = new HashMap<Integer, ClozeText>();
			int i = 0;
			for (ClozeText clozeText : clozeTexts)
			{
				model.masterClozeTexts.put(clozeText.id, clozeText);
				model.masterClozeQuestions.put(clozeText.id, new ArrayList<ClozeQuestion>());
				listModelClozeText.addElement(clozeText);
				model.listIndexToClozeTextId.put(i, clozeText.id);
				i++;
			}
			List<ClozeQuestion> clozeQuestions = new ClozeQuestionDAO().getAllClozeQuestions();
			for (ClozeQuestion clozeQuestion : clozeQuestions)
			{
				model.masterClozeQuestions.get(clozeQuestion.clozeTextId).add(clozeQuestion);
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
