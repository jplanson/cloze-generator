package com.jplanson.cloze.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClozeGeneratorFrame extends JFrame 
{
	private static final long serialVersionUID = 8826062099639243348L;

	public ClozeGeneratorFrame()
	{
		this.setTitle("Cloze Generator v1.0");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(new Dimension(
				(int) (screenSize.getWidth() * 3)/4, 
				(int) (screenSize.getHeight() * 3)/4)
		);
		
		this.setContentPane(new JPanel());
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
