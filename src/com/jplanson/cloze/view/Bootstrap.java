package com.jplanson.cloze.view;

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
			new CG_ErrorMessage(e.getMessage());
			System.exit(1);
		}

		// Display GUI
		new ClozeGeneratorGUI();
	}
}
