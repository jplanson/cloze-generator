package com.jplanson.cloze.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class ClozeComponent extends JLabel
{
	private static final long serialVersionUID = -8535067407590201236L;
	
	public static final Color color1 = new Color(0x0D3D56);
	public static final Color color2 = new Color(0x912617);
	
	private static final Font font = new Font("MS Gothic", Font.PLAIN, 36);
	private ModularInteger val;
	
	private static int dragColor = -1;
	
	public ClozeComponent(String text)
	{
		super(text);
		
		val = new ModularInteger(0, 3);
		setOpaque(true);
		setFont(font);
		
		addMouseListener(new MouseListener() 
		{
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				if (dragColor != -1)
				{
					val.setValue(dragColor);
					updateColor();
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) 
			{	
				if (dragColor == -1)
				{
					val.increment();
					dragColor = val.getValue();
					updateColor();
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) 
			{
				dragColor = -1;
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {}

		});

		updateColor();
	}
	
	public void update(int newVal)
	{
		val.setValue(newVal);
		updateColor();
	}
	
	public void increment()
	{
		val.increment();
		updateColor();
	}
	
	public void updateColor()
	{
		Color fg = null;
		Color bg = null;
		
		switch (val.getValue())
		{
		case 0: 
			fg = Color.BLACK;
			bg = Color.WHITE;
			break;
		case 1:
			fg = Color.WHITE;
			bg = color1;
			break;
			
		case 2:
			fg = Color.WHITE;
			bg = color2;
			break;
		}
		
		if (fg == null || bg == null)
		{
			return;
		}
		
		setForeground(fg);
		setBackground(bg);
	}
	
	public int getValue()
	{
		return val.getValue();
	}
}
