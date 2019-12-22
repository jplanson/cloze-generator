package com.jplanson.cloze.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CG_HeaderLabel extends JLabel 
{
	private static final long serialVersionUID = 7404825436369655006L;

	public CG_HeaderLabel(String label)
	{
		super(label);
		setFont(new Font("Consolas", Font.PLAIN, 24));
		setOpaque(true);
		setBackground(Color.WHITE);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.LEFT);
		setVerticalAlignment(SwingConstants.BOTTOM);
		setHorizontalAlignment(SwingConstants.LEFT);
	}
}
