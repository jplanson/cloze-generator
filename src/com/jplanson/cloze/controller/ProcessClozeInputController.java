package com.jplanson.cloze.controller;

import com.jplanson.cloze.model.ClozeText;
import com.jplanson.cloze.model.Model;
import com.jplanson.cloze.view.CG_ErrorMessage;
import com.jplanson.cloze.view.ClozeGeneratorGUI;

public class ProcessClozeInputController
{
	public Model model;
	public ClozeGeneratorGUI gui;
	
	public ProcessClozeInputController(Model model, ClozeGeneratorGUI gui)
	{
		this.model = model;
		this.gui = gui;
	}
	
	public void process()
	{
		// Get text inputs and make sure they contain valid strings 
		String sampleText = gui.inputSampleText.getText().trim();
		String translationText = gui.inputTranslation.getText().trim();
		
		if (sampleText.isEmpty() || translationText.isEmpty())
		{
			new CG_ErrorMessage("Sample text and translation cannot be empty.");
			return;
		}
		
		// Clear previous text in the processing pane
		gui.panelProcessing.removeAll();
		
		// Create cloze labels and add them to the processing pane
		model.createClozeText = new ClozeText(null, sampleText, translationText);
		for (int i = 0; i < model.createClozeText.clozeComponents.size(); i++)
		{				
			gui.panelProcessing.add(model.createClozeText.clozeComponents.get(i));
		}
		
		// Update processing panel layout manager to dynamically draw labels
		gui.panelProcessing.revalidate();
		gui.panelProcessing.repaint();
		gui.pack();
	}
}
