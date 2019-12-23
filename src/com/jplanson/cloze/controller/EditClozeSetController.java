package com.jplanson.cloze.controller;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jplanson.cloze.model.ClozeQuestion;
import com.jplanson.cloze.model.DbClozeQuestion;
import com.jplanson.cloze.model.Model;
import com.jplanson.cloze.view.CG_ErrorMessage;
import com.jplanson.cloze.view.ClozeGeneratorGUI;

public class EditClozeSetController 
{
	Model model;
	ClozeGeneratorGUI gui;
	
	public EditClozeSetController(Model model, ClozeGeneratorGUI gui)
	{
		this.model = model;
		this.gui = gui;
	}
	
	public void process(Integer listSelectedIndex)
	{
		if (listSelectedIndex == null || listSelectedIndex < 0) 
		{ 
			new CG_ErrorMessage("Please select an item from the list");
			return; 
		}
		
		gui.panelEditProcessing.removeAll();
		
		model.editClozeText = model.masterClozeQuestions.get(model.listIndexToClozeQuestionId.get(listSelectedIndex)).parent;
		
		if (model.editClozeText == null) { return; }
		
		List<DbClozeQuestion> dbClozeQuestions = new ArrayList<DbClozeQuestion>();
		for (ClozeQuestion cq : model.masterClozeQuestions.values())
		{
			if (cq.parent.id == model.editClozeText.id)
			{
				dbClozeQuestions.add(cq.dbQuestion);
			}
		}
		
		for (int i = 0; i < model.editClozeText.clozeComponents.size(); i++)
		{				
			gui.panelEditProcessing.add(model.editClozeText.clozeComponents.get(i));
		}
		
		Collections.sort(dbClozeQuestions);
		
		for (int i = 0; i < dbClozeQuestions.size(); i++)
		{
			DbClozeQuestion dcq = dbClozeQuestions.get(i);		
			int val = 1;
			if (dcq.start > 0 && model.editClozeText.clozeComponents.get(dcq.start - 1).getValue() == 1)
			{
				val = 2;
			}
			for (int j = dcq.start; j < dcq.end; j++)
			{
				model.editClozeText.clozeComponents.get(j).update(val);
			}
		}
		
		CardLayout cl = (CardLayout) gui.panelContent.getLayout();
		cl.show(gui.panelContent, "editClozeSet");
	}
}
