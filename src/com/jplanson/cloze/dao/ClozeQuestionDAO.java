package com.jplanson.cloze.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jplanson.cloze.model.ClozeQuestion;
import com.jplanson.cloze.model.ClozeText;

public class ClozeQuestionDAO 
{
	Connection conn;
	
	public ClozeQuestionDAO()
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
	
	public int insertClozeQuestion(ClozeQuestion cq) throws Exception
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
	
	public List<ClozeQuestion> getAllClozeQuestions() throws Exception
	{
		List<ClozeQuestion> allClozeQuestions = new ArrayList<ClozeQuestion>();
		try
		{
			Statement s = conn.createStatement();
			ResultSet resultSet = s.executeQuery("SELECT * FROM clozeQuestions;");
			
			while (resultSet.next())
			{
				ClozeQuestion clozeText = generateClozeQuestion(resultSet);
				allClozeQuestions.add(clozeText);
			}
			
			return allClozeQuestions;
		}
		catch (Exception e)
		{
			throw new Exception("Failed to retrieve all cloze questions: " + e.getMessage());
		}
	}

	private ClozeQuestion generateClozeQuestion(ResultSet resultSet) throws Exception
	{
		Integer id = resultSet.getInt("id");
		Integer clozeTextId = resultSet.getInt("clozeTextId");
		Integer start = resultSet.getInt("start");
		Integer end = resultSet.getInt("end");
		
		return new ClozeQuestion(id, clozeTextId, start, end);
	}
}
