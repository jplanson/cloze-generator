package com.jplanson.cloze.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class CG_Button extends JButton
{
	private static final long serialVersionUID = -4498480962137127057L;
	private static final Font font = new Font("Consolas", Font.PLAIN, 20);
	private static BufferedImage main;
	private static BufferedImage rollover;
	public static Dimension dim = new Dimension(200, 50); 
	
	static
	{
		main = new BufferedImage((int) dim.getWidth(), (int) dim.getHeight(), BufferedImage.TYPE_INT_RGB);
		rollover = new BufferedImage((int) dim.getWidth(), (int) dim.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		Graphics g = rollover.createGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, rollover.getWidth(), rollover.getHeight());
		g.dispose();
		
		g = main.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, main.getWidth(), main.getHeight());
		g.dispose();
	}

	public CG_Button(String label)
	{
		super(label);
		
		setHorizontalTextPosition(SwingConstants.CENTER);
		setFont(font);
		setRolloverEnabled(true);
		setMargin(new Insets(0, 0, 0, 0));
		setBorder(new LineBorder(Color.BLACK, 1));
		setPreferredSize(dim);
		setIcon(new ImageIcon(main));
		setRolloverIcon(new ImageIcon(rollover));
	}
}
