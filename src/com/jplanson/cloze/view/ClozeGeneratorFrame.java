package com.jplanson.cloze.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;

public class ClozeGeneratorFrame extends JFrame 
{
	private static final long serialVersionUID = 8826062099639243348L;
	private JTextField textField_1;
	
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
		panelCreateCloze.setLayout(new MigLayout("", "[40.00][][225.00][150.00][225.00][grow]", "[40.00][][33.00][][43.00]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		panelCreateCloze.add(panel, "cell 0 0,grow");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(null);
		panelCreateCloze.add(panel_1, "cell 5 0,grow");
		
		JLabel lblSampleText = new JLabel("Sample Text:");
		lblSampleText.setFont(new Font("Consolas", Font.PLAIN, 24));
		panelCreateCloze.add(lblSampleText, "cell 1 1,alignx trailing");
		
		JScrollPane scrollPane = new JScrollPane();
		panelCreateCloze.add(scrollPane, "cell 2 1 3 2,grow");
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Dialog", Font.PLAIN, 24));
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		JLabel lblTranslation = new JLabel("Translation:");
		lblTranslation.setFont(new Font("Consolas", Font.PLAIN, 24));
		panelCreateCloze.add(lblTranslation, "cell 1 3,alignx trailing");
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 24));
		panelCreateCloze.add(textField_1, "cell 2 3 3 1,growx");
		textField_1.setColumns(10);
		
		JButton btnProcess = new JButton("Process");
		btnProcess.setFont(new Font("Consolas", Font.PLAIN, 24));
		panelCreateCloze.add(btnProcess, "cell 3 4,grow");
		btnProcess.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println(textArea.getText());
				textField_1.setText(textArea.getText());
			}
		});
		
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
