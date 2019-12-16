package com.jplanson.cloze.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.border.LineBorder;

import com.jplanson.cloze.controller.ProcessClozeInputController;
import com.jplanson.cloze.model.Model;

public class ClozeGeneratorGUI extends JFrame 
{
	private static final long serialVersionUID = 8826062099639243348L;
	
	Model model;
	
	public JPanel panelHome;
	
	public JPanel panelCreateCloze;
	public JTextArea inputSampleText;
	public JTextArea inputTranslation;
	public JPanel panelProcessing;
	
	public JPanel panelEditCloze;
	
	public JPanel panelTest;
	
	// Cloze specifier variable
	public int dragColor = -1;
	
	public ClozeGeneratorGUI()
	{
		model = new Model();
		
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
		panelSideBar.setBackground(Color.DARK_GRAY);
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
		
		// START CREATE CLOZE 
		
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
		
		panelHome = new JPanel();
		panelHome.setBackground(Color.RED);
		panelContent.add(panelHome, "home");
		
		panelCreateCloze = new JPanel();
		panelCreateCloze.setBackground(Color.WHITE);
		panelContent.add(panelCreateCloze, "createCloze");
		panelCreateCloze.setLayout(new MigLayout("", "[122.00][][225.00][150.00][225.00][131.00][grow]", "[40.00][][33.00][][38.00][17.00][43.00][30.00][326.00][23.00][45.00]"));
		
		JPanel panelSpaceFill1 = new JPanel();
		panelSpaceFill1.setBackground(Color.WHITE);
		panelSpaceFill1.setBorder(null);
		panelCreateCloze.add(panelSpaceFill1, "cell 0 0,grow");
		
		JPanel panelSpaceFill2 = new JPanel();
		panelSpaceFill2.setBackground(Color.WHITE);
		panelSpaceFill2.setBorder(null);
		panelCreateCloze.add(panelSpaceFill2, "cell 6 0,grow");
		
		JLabel lblSampleText = new JLabel("Sample Text:");
		lblSampleText.setFont(new Font("Consolas", Font.PLAIN, 24));
		panelCreateCloze.add(lblSampleText, "cell 1 1,alignx trailing");
		
		JScrollPane scrollSampleText = new JScrollPane();
		panelCreateCloze.add(scrollSampleText, "cell 2 1 3 2,grow");
		
		inputSampleText = new JTextArea();
		inputSampleText.setFont(new Font("MS Gothic", Font.PLAIN, 24));
		inputSampleText.setLineWrap(true);
		scrollSampleText.setViewportView(inputSampleText);
		
		JLabel lblTranslation = new JLabel("Translation:");
		lblTranslation.setFont(new Font("Consolas", Font.PLAIN, 24));
		panelCreateCloze.add(lblTranslation, "cell 1 3,alignx trailing");
		
		JScrollPane scrollTranslation = new JScrollPane();
		panelCreateCloze.add(scrollTranslation, "cell 2 3 3 2,grow");
		
		inputTranslation = new JTextArea();
		inputTranslation.setFont(new Font("Dialog", Font.PLAIN, 24));
		inputTranslation.setLineWrap(true);
		scrollTranslation.setViewportView(inputTranslation);
		
		JButton btnProcess = new JButton("Process");
		btnProcess.setFont(new Font("Consolas", Font.PLAIN, 24));
		btnProcess.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SwingUtilities.invokeLater(new Runnable() {
	                @Override
	                public void run() {
	                	ProcessClozeInputController pci = new ProcessClozeInputController(model, ClozeGeneratorGUI.this);
	    				pci.process();
	                }
	            });
			}
		});
		panelCreateCloze.add(btnProcess, "cell 3 6,grow");
		
		JPanel panelSpaceFill3 = new JPanel();
		panelSpaceFill3.setBorder(null);
		panelSpaceFill3.setBackground(Color.WHITE);
		panelCreateCloze.add(panelSpaceFill3, "cell 3 7,grow");
		
		WrapLayout wl = new WrapLayout(FlowLayout.LEFT, 0, 0);
		panelProcessing = new JPanel();
		panelProcessing.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panelProcessing.setBackground(Color.WHITE);
		panelProcessing.setLayout(wl);
		panelCreateCloze.add(panelProcessing, "cell 1 8 5 1,grow");
		
		JPanel panelSpaceFill4 = new JPanel();
		panelSpaceFill4.setBorder(null);
		panelSpaceFill4.setBackground(Color.WHITE);
		panelCreateCloze.add(panelSpaceFill4, "cell 3 9,grow");
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setFont(new Font("Consolas", Font.PLAIN, 24));
		panelCreateCloze.add(btnGenerate, "cell 3 10,grow");
		
		// END CREATE CLOZE
		
		// START EDIT CLOZE
		
		panelEditCloze = new JPanel();
		panelEditCloze.setBackground(Color.GREEN);
		panelContent.add(panelEditCloze, "editCloze");
		
		// END EDIT CLOZE
		
		// START TEST
		
		panelTest = new JPanel();
		panelTest.setBackground(Color.CYAN);
		panelContent.add(panelTest, "test");
		getContentPane().setLayout(groupLayout);
		
		// END TEST
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
