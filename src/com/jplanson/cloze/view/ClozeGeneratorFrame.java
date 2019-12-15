package com.jplanson.cloze.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.CardLayout;

public class ClozeGeneratorFrame extends JFrame 
{
	private static final long serialVersionUID = 8826062099639243348L;
	
	public ClozeGeneratorFrame()
	{
		this.setTitle("Cloze Generator v1.0");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(new Dimension(
				(int) (screenSize.getWidth() * 3)/4, 
				(int) (screenSize.getHeight() * 3)/4)
		);
		
		JSplitPane splitPane = new JSplitPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 1422, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(splitPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
		);
		
		JPanel panelSideBar = new JPanel();
		splitPane.setLeftComponent(panelSideBar);
		
		JPanel panelContent = new JPanel();
		splitPane.setRightComponent(panelContent);
		panelContent.setLayout(new CardLayout(0, 0));
		
		JButton btnHome = new JButton("Home");
		btnHome.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnHome.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				CardLayout cl = (CardLayout) panelContent.getLayout();
				cl.show(panelContent, "home");
			}
		});
		
		JButton btnCreateClozeSet = new JButton("Create Cloze Set");
		btnCreateClozeSet.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnCreateClozeSet.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				CardLayout cl = (CardLayout) panelContent.getLayout();
				cl.show(panelContent, "createCloze");
			}
		});
		
		JButton btnEditClozeSet = new JButton("Edit Cloze Set");
		btnEditClozeSet.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnEditClozeSet.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				CardLayout cl = (CardLayout) panelContent.getLayout();
				cl.show(panelContent, "editCloze");
			}
		});
		
		JButton btnTest = new JButton("Test");
		btnTest.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnTest.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				CardLayout cl = (CardLayout) panelContent.getLayout();
				cl.show(panelContent, "test");
			}
		});
		
		JPanel panelLogo = new JPanel();
		
		GroupLayout gl_panelSideBar = new GroupLayout(panelSideBar);
		gl_panelSideBar.setHorizontalGroup(
			gl_panelSideBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSideBar.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSideBar.createParallelGroup(Alignment.LEADING)
						.addComponent(panelLogo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
						.addComponent(btnHome, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
						.addComponent(btnCreateClozeSet, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
						.addComponent(btnEditClozeSet, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
						.addComponent(btnTest, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelSideBar.setVerticalGroup(
			gl_panelSideBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSideBar.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelLogo, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnHome, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCreateClozeSet, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEditClozeSet, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnTest, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(388, Short.MAX_VALUE))
		);
		panelSideBar.setLayout(gl_panelSideBar);
		
		JPanel panelHome = new JPanel();
		panelHome.setBackground(Color.RED);
		panelContent.add(panelHome, "home");
		
		JPanel panelCreateCloze = new JPanel();
		panelCreateCloze.setBackground(Color.WHITE);
		panelContent.add(panelCreateCloze, "createCloze");
		
		JPanel panelEditCloze = new JPanel();
		panelEditCloze.setBackground(Color.GREEN);
		panelContent.add(panelEditCloze, "editCloze");
		
		JPanel panelTest = new JPanel();
		panelTest.setBackground(Color.CYAN);
		panelContent.add(panelTest, "test");
		getContentPane().setLayout(groupLayout);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
