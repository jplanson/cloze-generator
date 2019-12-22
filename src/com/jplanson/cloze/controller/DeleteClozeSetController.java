package com.jplanson.cloze.controller;

import java.sql.Connection;

import com.jplanson.cloze.dao.ClozeTextDAO;
import com.jplanson.cloze.dao.DatabaseUtil;
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
	
	public void process(int clozeSetIndex)
	{
		ClozeText clozeText = model.masterClozeTexts.get(model.listIndexToClozeTextId.get(clozeSetIndex));
		if (clozeText == null)
		{
			// TODO: Print error somewhere and return
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
