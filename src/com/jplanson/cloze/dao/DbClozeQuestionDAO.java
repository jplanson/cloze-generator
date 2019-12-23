package com.jplanson.cloze.dao;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jplanson.cloze.model.DbClozeQuestion;

public class DbClozeQuestionDAO 
{
	Connection conn;
	
	public DbClozeQuestionDAO()
	{
		try 
		{
			conn = DatabaseUtil.connect();
		} 
		catch (Exception e) 
		{
			conn = null;
		}
	}
	
	public int insertClozeQuestion(DbClozeQuestion cq) throws Exception
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("INSERT INTO clozeQuestions(clozeTextId, start, end, createTime) VALUES (?, ?, ?, strftime('%s', 'now'));");
			ps.setInt(1, cq.clozeTextId);
			ps.setInt(2, cq.start);
			ps.setInt(3, cq.end);
			ps.executeUpdate();
			
			ResultSet resultSet = ps.getGeneratedKeys();
			if (resultSet.next())
			{
				return resultSet.getInt(1); 
			}
			
			return -1;
		}
		catch (SQLException se)
		{
			throw new Exception("Failed to insert cloze question: " + se.getMessage());
		}
	}
	
	public List<DbClozeQuestion> getAllClozeQuestions() throws Exception
	{
		List<DbClozeQuestion> allClozeQuestions = new ArrayList<DbClozeQuestion>();
		try
		{
			Statement s = conn.createStatement();
			ResultSet resultSet = s.executeQuery("SELECT * FROM clozeQuestions;");
			
			while (resultSet.next())
			{
				DbClozeQuestion clozeText = generateClozeQuestion(resultSet);
				allClozeQuestions.add(clozeText);
			}
			
			return allClozeQuestions;
		}
		catch (Exception e)
		{
			throw new Exception("Failed to retrieve all cloze questions: " + e.getMessage());
		}
	}

	private DbClozeQuestion generateClozeQuestion(ResultSet resultSet) throws Exception
	{
		Integer id = resultSet.getInt("id");
		Integer clozeTextId = resultSet.getInt("clozeTextId");
		Integer start = resultSet.getInt("start");
		Integer end = resultSet.getInt("end");
		
		return new DbClozeQuestion(id, clozeTextId, start, end);
	}
	
	public void deleteByTextId(Integer id) throws Exception
	{
		Connection conn = DatabaseUtil.connect();
		
		try
		{
			PreparedStatement ps = conn.prepareStatement("DELETE FROM clozeQuestions WHERE clozeTextId = ?;");
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		catch (SQLException se)
		{
			throw new Exception("Unable to delete cloze questions: " + se.getMessage());
		}
	}

	public void deleteById(Integer id) throws Exception
	{
		Connection conn = DatabaseUtil.connect();
		
		try
		{
			PreparedStatement ps = conn.prepareStatement("DELETE FROM clozeQuestions WHERE id = ?;");
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		catch (SQLException se)
		{
			throw new Exception("Unable to delete cloze question: " + se.getMessage());
		}
	}

	public List<DbClozeQuestion> getClozeQuestionsByTextId(Integer id) throws Exception
	{
		List<DbClozeQuestion> allClozeQuestions = new ArrayList<DbClozeQuestion>();
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM clozeQuestions WHERE clozeTextId = ?;");
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next())
			{
				DbClozeQuestion clozeText = generateClozeQuestion(resultSet);
				allClozeQuestions.add(clozeText);
			}
			
			return allClozeQuestions;
		}
		catch (Exception e)
		{
			throw new Exception("Failed to retrieve cloze questions: " + e.getMessage());
		}
	}
}
