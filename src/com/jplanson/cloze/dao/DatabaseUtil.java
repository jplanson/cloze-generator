package com.jplanson.cloze.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil 
{
	public static final String databaseFileName = "cloze.db";
	public static final String databaseUri = "jdbc:sqlite:" + databaseFileName;
	
	static Connection conn = null;
	
	protected static Connection connect() throws Exception
	{
		if (conn != null) { return conn; }
		
		try
		{
			conn = DriverManager.getConnection(databaseUri);
		}
		catch (SQLException e)
		{
			throw new Exception("Failed to establish database connection.");
		}

		return conn;
	}
	
	public static void initializeDatabase() throws Exception
	{
		Connection c = connect();
		
		// Initialize tables
		String createSampleTexts = "CREATE TABLE IF NOT EXISTS clozeTexts (\n" + 
				"id integer PRIMARY KEY,\n" + 
				"sampleText TEXT NOT NULL,\n" +
				"translation TEXT NOT NULL,\n" +
				"createTime INTEGER NOT NULL\n" + 
				");";
		Statement sqlCreateSampleTexts = c.createStatement();
		sqlCreateSampleTexts.executeUpdate(createSampleTexts);
		
		String createQuestions = "CREATE TABLE IF NOT EXISTS clozeQuestions (\n" + 
				"id INTEGER PRIMARY KEY,\n" + 
				"clozeTextId INTEGER NOT NULL,\n" + 
				"start INTEGER NOT NULL,\n" + 
				"end INTEGER NOT NULL,\n" + 
				"createTime INTEGER NOT NULL,\n" +
				"FOREIGN KEY(clozeTextId) REFERENCES clozeTexts(id)\n" +
				");";
		Statement sqlCreateQuestions = c.createStatement();
		sqlCreateQuestions.executeUpdate(createQuestions);
	}
}
