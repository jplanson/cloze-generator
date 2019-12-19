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

		// Display GUI
		ClozeGeneratorGUI frame = new ClozeGeneratorGUI();
	}
}
