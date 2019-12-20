package com.jplanson.cloze.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JMenuItem;

public class CG_MenuItem extends JMenuItem
{
	private static final long serialVersionUID = -2870027951676830642L;

	private static final Font font = new Font("Consolas", Font.PLAIN, 18);
	
	public CG_MenuItem(String name)
	{
		super(name);
		setFont(font);
		setOpaque(true);
		setForeground(Color.DARK_GRAY);
		setBackground(Color.WHITE);
	}
	
}
