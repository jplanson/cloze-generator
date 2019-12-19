package com.jplanson.cloze.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jplanson.cloze.dao.DatabaseUtil;

public class Bootstrap 
{
	public static void main(String args[])
	{
		// Perform database initialization task
		try 
		{
			DatabaseUtil.initializeDatabase();
		}
		catch (Exception e)
		{
			// TODO: Display error message in a pane and close upon exit
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		try {
			BufferedImage image = ImageIO.read(new File("res/default_side.png"));
			BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics g = image2.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.dispose();
			int pixels[] = ((DataBufferInt) image2.getRaster().getDataBuffer()).getData();
			for (int i = 0; i < pixels.length; i++)
			{
				if ((pixels[i] & 0x282828) == 0x282828)
				{
					pixels[i] = 0x404040;
				}
			}
			ImageIO.write(image2, "png", new File("res/default_side_2.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// Display GUI
		ClozeGeneratorGUI frame = new ClozeGeneratorGUI();
	}
}
