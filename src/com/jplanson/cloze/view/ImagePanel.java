package com.jplanson.cloze.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel 
{
	private static final long serialVersionUID = -8030611784707339426L;

	private BufferedImage image;
	
	public ImagePanel(String fileName) 
	{
		try 
		{
			image = ImageIO.read(new File(fileName));
		} 
		catch (IOException e) 
		{
			image = null;
		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if (image != null)
		{	
			g.drawImage(image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH), 0, 0, this);
		}
	}
}
