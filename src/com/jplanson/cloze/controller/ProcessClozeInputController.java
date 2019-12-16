package com.jplanson.cloze.controller;

import java.awt.Font;

import javax.swing.JLabel;

import com.jplanson.cloze.model.Model;
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
		String sampleText = gui.inputSampleText.getText();
		Character[] sampleTextArr = new Character[sampleText.length()];
		
		gui.panelProcessing.removeAll();
		
		Font font = new Font("Dialog", Font.PLAIN, 36);
		for (int i = 0; i < sampleText.length(); i++)
		{
			sampleTextArr[i] = sampleText.charAt(i);
			JLabel clozeComponent = new JLabel(sampleTextArr[i].toString());
			clozeComponent.setFont(font);
			gui.panelProcessing.add(clozeComponent);
		}
		
		gui.panelProcessing.revalidate();
		gui.panelProcessing.repaint();
		gui.pack();
	}
}
