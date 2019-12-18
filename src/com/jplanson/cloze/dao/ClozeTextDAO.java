package com.jplanson.cloze.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jplanson.cloze.model.ClozeText;

public class ClozeTextDAO 
{
	Connection conn;
	
	public ClozeTextDAO()
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
	
	public int insertClozeText(ClozeText ct) throws Exception
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("INSERT INTO clozeTexts(sampleText, translation, createTime) VALUES (?, ?, strftime('%s', 'now'));");
			ps.setString(1, ct.sampleText);
			ps.setString(2, ct.translation);
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next())
			{
				return rs.getInt(1);
			}
			
			return -1;
		}
		catch (SQLException se)
		{
			throw new Exception("Failed to insert cloze text into database: " + se.getMessage());
		}
	}
	
	public ClozeText getById(Integer id) throws Exception
	{
		ClozeText clozeText = null;
		
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM clozeTexts WHERE id = ?;");
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next())
			{
				clozeText = generateClozeText(resultSet);
			}
		}
		catch (Exception e)
		{
			throw new Exception("Failed to retrieve cloze text: " + e.getMessage());
		}
		
		return clozeText;
	}
	
	public List<ClozeText> getAllClozeTexts() throws Exception
	{
		List<ClozeText> allClozeTexts = new ArrayList<ClozeText>();
		try
		{
			Statement s = conn.createStatement();
			ResultSet resultSet = s.executeQuery("SELECT * FROM clozeTexts;");
			
			while (resultSet.next())
			{
				ClozeText clozeText = generateClozeText(resultSet);
				allClozeTexts.add(clozeText);
			}
			
			return allClozeTexts;
		}
		catch (Exception e)
		{
			throw new Exception("Failed to retrieve all cloze texts: " + e.getMessage());
		}
	}

	private ClozeText generateClozeText(ResultSet resultSet) throws Exception
	{
		Integer id = resultSet.getInt("id");
		String sampleText = resultSet.getString("sampleText");
		String translation = resultSet.getString("translation");
		
		return new ClozeText(id, sampleText, translation);
	}

	public void deleteClozeText(Integer id) throws Exception
	{	
		// Delete all related ClozeQuestions
		new ClozeQuestionDAO().deleteByTextId(id);
		
		// Delete the cloze text itself
		Connection conn = DatabaseUtil.connect();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM clozeTexts WHERE id = ?");
		ps.setInt(1, id);
		ps.executeUpdate();
	}
}
