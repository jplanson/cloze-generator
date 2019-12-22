package com.jplanson.cloze.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.border.LineBorder;

import com.jplanson.cloze.controller.AnswerQuestionController;
import com.jplanson.cloze.controller.DeleteClozeQuestionController;
import com.jplanson.cloze.controller.DeleteClozeSetController;
import com.jplanson.cloze.controller.DisplayQuestionController;
import com.jplanson.cloze.controller.GenerateClozeQuestionsController;
import com.jplanson.cloze.controller.ProcessClozeInputController;
import com.jplanson.cloze.controller.StartTestController;
import com.jplanson.cloze.controller.UpdateClozeQuestionListController;
import com.jplanson.cloze.model.ClozeQuestion;
import com.jplanson.cloze.model.Model;
import java.awt.Component;

public class ClozeGeneratorGUI extends JFrame 
{
	private static final long serialVersionUID = 8826062099639243348L;
	
	Model model;
	
	public JPanel panelHome;
	public JList<ClozeQuestion> listClozeSet;
	
	public JPanel panelNewCloze;
	public JTextArea inputSampleText;
	public JTextArea inputTranslation;
	public JPanel panelProcessing;
	
	public JPanel panelTest;
	public JLabel lblTestTranslation;
	public JLabel lblTestQuestion;
	public JLabel lblTestAnswer;
	public JButton btnTestAdvance;
	public JButton btnTestPrevious;
	public JButton btnTestEnd;
	
	public static int frameWidth;
	public static int frameHeight;
	
	private Image sideDefault = new ImageIcon("res/default_side.png").getImage();
	private Image sideHover = new ImageIcon("res/rollover_side.png").getImage();
	private Image sideSelect = new ImageIcon("res/clicked_side.png").getImage();
	
	// Cloze specifier variable
	public int dragColor = -1;
	
	public ClozeGeneratorGUI()
	{
		model = new Model();
		
		this.setTitle("Cloze Generator v1.0");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameWidth = (int) (screenSize.getWidth() * 7) / 8;
		frameHeight = (int) (screenSize.getHeight() * 7) / 8;
		this.setPreferredSize(new Dimension(frameWidth, frameHeight));
		
		int sidePanelSize = (int) (frameWidth * 0.15);
		GridBagLayout gblMain = new GridBagLayout();
		gblMain.columnWidths = new int[] { sidePanelSize, frameWidth - sidePanelSize};
		gblMain.rowWeights = new double[] {1.0};
		getContentPane().setLayout(gblMain);
		
		
		// addSidePanel();
		JPanel panelSideBar = new JPanel();
		panelSideBar.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbcSidePanel = new GridBagConstraints();
		gbcSidePanel.fill = GridBagConstraints.BOTH;
		gbcSidePanel.gridx = 0;
		gbcSidePanel.gridy = 0;
		getContentPane().add(panelSideBar, gbcSidePanel);
	    
	    // addContentPanel();
		JPanel panelContent = new JPanel(new CardLayout(0, 0));
		panelContent.setBackground(Color.RED);
	    GridBagConstraints gbcContentPanel = new GridBagConstraints();
	    gbcContentPanel.fill = GridBagConstraints.BOTH;
	    gbcContentPanel.weightx = 0.85;
	    gbcContentPanel.weighty = 1;
	    gbcContentPanel.gridx = 1;
	    gbcContentPanel.gridy = 0;
	    getContentPane().add(panelContent, gbcContentPanel);
	    
	    this.pack();
	    
	    GridBagLayout gbl_panelSideBar = new GridBagLayout();
	    gbl_panelSideBar.columnWidths = new int[]{0, 0};
	    gbl_panelSideBar.rowHeights = new int[]{sidePanelSize, 55, 55, 55, 0};
	    gbl_panelSideBar.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	    gbl_panelSideBar.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    panelSideBar.setLayout(gbl_panelSideBar);
	    
	    // LOGO PANEL
	    
	    ImagePanel panelLogo = new ImagePanel("res/cloze_gen_logo.png");
		GridBagConstraints gbc_panelLogo = new GridBagConstraints();
	    gbc_panelLogo.insets = new Insets(10, 10, 5, 10);
	    gbc_panelLogo.fill = GridBagConstraints.BOTH;
	    gbc_panelLogo.gridx = 0;
	    gbc_panelLogo.gridy = 0;
	    panelSideBar.add(panelLogo, gbc_panelLogo);
	    
		// HOME BUTTON
		JButton btnHome = new JButton("Home");
		// Text
		btnHome.setHorizontalTextPosition(SwingConstants.CENTER);
		btnHome.setForeground(Color.WHITE);
		btnHome.setFont(new Font("Consolas", Font.PLAIN, 20));
		// Icon
		btnHome.setRolloverEnabled(true);
		btnHome.setBorder(null);
		// Positioning
		GridBagConstraints gbc_btnHome = new GridBagConstraints();
	    gbc_btnHome.fill = GridBagConstraints.BOTH;
	    gbc_btnHome.insets = new Insets(5, 10, 5, 10);
	    gbc_btnHome.gridx = 0;
	    gbc_btnHome.gridy = 1;
		// Action
		btnHome.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				CardLayout cl = (CardLayout) panelContent.getLayout();
				cl.show(panelContent, "home");
			}
		});
		panelSideBar.add(btnHome, gbc_btnHome);
		
		// START CREATE CLOZE 
		
		// NEW CLOZE SET BUTTON
		JButton btnCreateClozeSet = new JButton("New Cloze Set");
		// Text
		btnCreateClozeSet.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCreateClozeSet.setForeground(Color.WHITE);
		btnCreateClozeSet.setFont(new Font("Consolas", Font.PLAIN, 20));
		// Icon
		btnCreateClozeSet.setRolloverEnabled(true);
		btnCreateClozeSet.setMargin(new Insets(0, 0, 0, 0));
		btnCreateClozeSet.setBorder(null);
		// Positioning
		GridBagConstraints gbc_btnNewClozeSet = new GridBagConstraints();
	    gbc_btnNewClozeSet.fill = GridBagConstraints.BOTH;
	    gbc_btnNewClozeSet.insets = new Insets(5, 10, 5, 10);
	    gbc_btnNewClozeSet.gridx = 0;
	    gbc_btnNewClozeSet.gridy = 2;
		// Action
		btnCreateClozeSet.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				inputSampleText.setText("");
				inputTranslation.setText("");
				panelProcessing.removeAll();
				model.createClozeText = null;
				
				CardLayout cl = (CardLayout) panelContent.getLayout();
				cl.show(panelContent, "newClozeSet");
			}
		});
		panelSideBar.add(btnCreateClozeSet, gbc_btnNewClozeSet);
		
		JButton btnTest = new JButton("Test");
		// Text
		btnTest.setHorizontalTextPosition(SwingConstants.CENTER);
		btnTest.setForeground(Color.WHITE);
		btnTest.setFont(new Font("Consolas", Font.PLAIN, 20));
		// Icon
		btnTest.setRolloverEnabled(true);
		btnTest.setMargin(new Insets(0, 0, 0, 0));
		btnTest.setBorder(null);
		//Positioning 
		GridBagConstraints gbc_btnTest = new GridBagConstraints();
	    gbc_btnTest.fill = GridBagConstraints.BOTH;
	    gbc_btnTest.insets = new Insets(5, 10, 5, 10);
	    gbc_btnTest.gridx = 0;
	    gbc_btnTest.gridy = 3;
		// Action
		btnTest.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				CardLayout testLayout = (CardLayout) panelContent.getLayout();
				if (model.testState.started)
				{
					testLayout.show(panelContent, "testPerform");
				}
				else
				{
					testLayout.show(panelContent, "testSelect");
				}
				
				CardLayout cl = (CardLayout) panelContent.getLayout();
				cl.show(panelContent, "test");
			}
		});
		panelSideBar.add(btnTest, gbc_btnTest);
		
	    // HOME PANEL
		
		panelHome = new JPanel();
		panelHome.setBackground(Color.WHITE);
		panelContent.add(panelHome, "home");
		GridBagLayout gbl_panelHome2 = new GridBagLayout();
		gbl_panelHome2.columnWidths = new int[]{0, 0, 0};
		gbl_panelHome2.rowHeights = new int[]{80, 0, 0};
		gbl_panelHome2.columnWeights = new double[]{0.07, 0.86, 0.07};
		gbl_panelHome2.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panelHome.setLayout(gbl_panelHome2);
		
		JPanel panelHomeLeft = new JPanel();
		panelHomeLeft.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelHomeLeft = new GridBagConstraints();
		gbc_panelHomeLeft.gridheight = 2;
		gbc_panelHomeLeft.fill = GridBagConstraints.BOTH;
		gbc_panelHomeLeft.gridx = 0;
		gbc_panelHomeLeft.gridy = 0;
		panelHome.add(panelHomeLeft, gbc_panelHomeLeft);
		
		JPanel panelHomeRight = new JPanel();
		panelHomeRight.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelHomeRight = new GridBagConstraints();
		gbc_panelHomeRight.gridheight = 2;
		gbc_panelHomeRight.fill = GridBagConstraints.BOTH;
		gbc_panelHomeRight.gridx = 2;
		gbc_panelHomeRight.gridy = 0;
		panelHome.add(panelHomeRight, gbc_panelHomeRight);
		
		JLabel lblClozeSets = new CG_HeaderLabel("Cloze Questions:");
		GridBagConstraints gbc_lblClozeSets = new GridBagConstraints();
		gbc_lblClozeSets.insets = new Insets(0, 0, 0, 0);
		gbc_lblClozeSets.fill = GridBagConstraints.BOTH;
		gbc_lblClozeSets.gridx = 1;
		gbc_lblClozeSets.gridy = 0;
		panelHome.add(lblClozeSets, gbc_lblClozeSets);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(Color.BLACK, 2));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 70, 0);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panelHome.add(scrollPane, gbc_scrollPane);
		
		listClozeSet = new JList<ClozeQuestion>();
		listClozeSet.setSelectionBackground(Color.DARK_GRAY);
		listClozeSet.setSelectionForeground(Color.WHITE);
		listClozeSet.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		JPopupMenu menuRightClick = new JPopupMenu();
		menuRightClick.setBorder(new LineBorder(Color.DARK_GRAY, 1));
		CG_MenuItem edit = new CG_MenuItem("Edit");
		edit.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println(listClozeSet.getSelectedIndex());
				CardLayout layout = (CardLayout) panelContent.getLayout();
				layout.show(panelContent, "editClozeSet");
			}
		});
		menuRightClick.add(edit);
		CG_MenuItem delete = new CG_MenuItem("Delete");
		delete.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DeleteClozeQuestionController dcq = new DeleteClozeQuestionController(model, ClozeGeneratorGUI.this);
				dcq.process(listClozeSet.getSelectedIndex());
			}
		});
		menuRightClick.add(delete);
		CG_MenuItem deleteSet = new CG_MenuItem("Delete Set");
		deleteSet.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				DeleteClozeSetController dcs = new DeleteClozeSetController(model, ClozeGeneratorGUI.this);
				dcs.process(listClozeSet.getSelectedIndex());
			}
		});
		menuRightClick.add(deleteSet);
		
		listClozeSet.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (SwingUtilities.isRightMouseButton(e))
				{
					if (listClozeSet.getSelectedIndex() == -1) { return; }
					menuRightClick.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		scrollPane.setViewportView(listClozeSet);
		
		// END HOME
		
		// START NEW CLOZE SET
		
		panelNewCloze = new JPanel();
		panelNewCloze.setBackground(Color.WHITE);
		panelContent.add(panelNewCloze, "newClozeSet");
		GridBagLayout gbl_panelNewCloze = new GridBagLayout();
		gbl_panelNewCloze.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelNewCloze.rowHeights = new int[]{0, 0, 0};
		gbl_panelNewCloze.columnWeights = new double[]{0.15, 0.7, 0.15, Double.MIN_VALUE};
		gbl_panelNewCloze.rowWeights = new double[]{0.3, 0.7, Double.MIN_VALUE};
		panelNewCloze.setLayout(gbl_panelNewCloze);
		
		// Pad left
		JPanel panelNewClozeLeft = new JPanel();
		panelNewClozeLeft.setBackground(Color.WHITE);
		panelNewClozeLeft.setPreferredSize(new Dimension((int) (frameWidth - sidePanelSize * 0.15), 50));
		GridBagConstraints gbc_panelNewClozeLeft = new GridBagConstraints();
		gbc_panelNewClozeLeft.insets = new Insets(0, 0, 0, 0);
		gbc_panelNewClozeLeft.fill = GridBagConstraints.BOTH;
		gbc_panelNewClozeLeft.gridx = 0;
		gbc_panelNewClozeLeft.gridy = 0;
		panelNewCloze.add(panelNewClozeLeft, gbc_panelNewClozeLeft);
		
		// Pad right
		JPanel panelNewClozeRight = new JPanel();
		panelNewClozeRight.setBackground(Color.WHITE);
		panelNewClozeRight.setPreferredSize(new Dimension((int) (frameWidth - sidePanelSize * 0.15), 50));
		GridBagConstraints gbc_panelNewClozeRight = new GridBagConstraints();
		gbc_panelNewClozeRight.insets = new Insets(0, 0, 0, 0);
		gbc_panelNewClozeRight.fill = GridBagConstraints.BOTH;
		gbc_panelNewClozeRight.gridx = 0;
		gbc_panelNewClozeRight.gridy = 2;
		panelNewCloze.add(panelNewClozeRight, gbc_panelNewClozeRight);
		
		// Upper panel
		JPanel panelNewClozeUpper = new JPanel();
		panelNewClozeUpper.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelNewClozeUpper = new GridBagConstraints();
		gbc_panelNewClozeUpper.gridheight = 1;
		gbc_panelNewClozeUpper.gridwidth = 1;
		gbc_panelNewClozeUpper.insets = new Insets(0, 0, 0, 0);
		gbc_panelNewClozeUpper.fill = GridBagConstraints.BOTH;
		gbc_panelNewClozeUpper.gridx = 1;
		gbc_panelNewClozeUpper.gridy = 0;
		panelNewCloze.add(panelNewClozeUpper, gbc_panelNewClozeUpper);
		GridBagLayout gbl_panelNewClozeUpper = new GridBagLayout();
		gbl_panelNewClozeUpper.columnWidths = new int[]{0, 0};
		gbl_panelNewClozeUpper.rowHeights = new int[]{50, 0, 50, 0, (int) CG_Button.dim.getHeight() * 2, 0};
		gbl_panelNewClozeUpper.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelNewClozeUpper.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelNewClozeUpper.setLayout(gbl_panelNewClozeUpper);
		
		// Label sample text
		JLabel lblSampleText = new CG_HeaderLabel("Sample Text:");
		GridBagConstraints gbc_lblSampleText = new GridBagConstraints();
		gbc_lblSampleText.fill = GridBagConstraints.BOTH;
		gbc_lblSampleText.insets = new Insets(0, 0, 0, 0);
		gbc_lblSampleText.gridx = 0;
		gbc_lblSampleText.gridy = 0;
		panelNewClozeUpper.add(lblSampleText, gbc_lblSampleText);
		
		// Sample text input area
		JScrollPane scrollSampleText = new JScrollPane();
		GridBagConstraints gbc_scrollSampleText = new GridBagConstraints();
		gbc_scrollSampleText.insets = new Insets(0, 0, 0, 0);
		gbc_scrollSampleText.fill = GridBagConstraints.BOTH;
		gbc_scrollSampleText.gridx = 0;
		gbc_scrollSampleText.gridy = 1;
		inputSampleText = new JTextArea();
		inputSampleText.setFont(new Font("MS Gothic", Font.PLAIN, 24));
		inputSampleText.setLineWrap(true);
		inputSampleText.setWrapStyleWord(true);
		scrollSampleText.setViewportView(inputSampleText);
		panelNewClozeUpper.add(scrollSampleText, gbc_scrollSampleText);
		
		// Label translation
		JLabel lblTranslation = new CG_HeaderLabel("Translation:");
		GridBagConstraints gbc_lblTranslation = new GridBagConstraints();
		gbc_lblTranslation.fill = GridBagConstraints.BOTH;
		gbc_lblTranslation.insets = new Insets(0, 0, 0, 0);
		gbc_lblTranslation.gridx = 0;
		gbc_lblTranslation.gridy = 2;
		panelNewClozeUpper.add(lblTranslation, gbc_lblTranslation);
		
		// Translation input area
		JScrollPane scrollTranslation = new JScrollPane();
		GridBagConstraints gbc_scrollTranslation = new GridBagConstraints();
		gbc_scrollTranslation.insets = new Insets(0, 0, 0, 0);
		gbc_scrollTranslation.fill = GridBagConstraints.BOTH;
		gbc_scrollTranslation.gridx = 0;
		gbc_scrollTranslation.gridy = 3;
		inputTranslation = new JTextArea();
		inputTranslation.setFont(new Font("Dialog", Font.PLAIN, 24));
		inputTranslation.setLineWrap(true);
		scrollTranslation.setViewportView(inputTranslation);
		panelNewClozeUpper.add(scrollTranslation, gbc_scrollTranslation);
		
		// "Process" button
		CG_Button btnProcess = new CG_Button("Process");
		GridBagConstraints gbc_btnProcess = new GridBagConstraints();
		gbc_btnProcess.insets = new Insets(0, 0, 0, 0);
		gbc_btnProcess.gridx = 0;
		gbc_btnProcess.gridy = 4;
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
		panelNewClozeUpper.add(btnProcess, gbc_btnProcess);
		
		// Lower panel
		JPanel panelNewClozeLower = new JPanel();
		panelNewClozeLower.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelNewClozeLower = new GridBagConstraints();
		gbc_panelNewClozeLower.fill = GridBagConstraints.BOTH;
		gbc_panelNewClozeLower.insets = new Insets(0, 0, 0, 0);
		gbc_panelNewClozeLower.gridx = 1;
		gbc_panelNewClozeLower.gridy = 1;
		panelNewCloze.add(panelNewClozeLower, gbc_panelNewClozeLower);
		GridBagLayout gbl_panelNewClozeLower = new GridBagLayout();
		gbl_panelNewClozeLower.columnWidths = new int[]{0, 0};
		gbl_panelNewClozeLower.rowHeights = new int[] {0, (int) CG_Button.dim.getHeight() * 2, 0};
		gbl_panelNewClozeLower.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelNewClozeLower.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panelNewClozeLower.setLayout(gbl_panelNewClozeLower);
		
		JScrollPane scrollProcessing = new JScrollPane();
		GridBagConstraints gbc_scrollProcessing = new GridBagConstraints();
		gbc_scrollProcessing.insets = new Insets(0, 0, 0, 0);
		gbc_scrollProcessing.fill = GridBagConstraints.BOTH;
		gbc_scrollProcessing.gridx = 0;
		gbc_scrollProcessing.gridy = 0;
		panelNewClozeLower.add(scrollProcessing, gbc_scrollProcessing);
		panelProcessing = new JPanel();
		panelProcessing.setBackground(Color.WHITE);
		panelProcessing.setLayout(new WrapLayout(FlowLayout.LEFT, 0, 0));
		scrollProcessing.setViewportView(panelProcessing);
		
		CG_Button btnGenerate = new CG_Button("Generate");
		GridBagConstraints gbc_btnGenerate = new GridBagConstraints();
		gbc_btnGenerate.insets = new Insets(0, 0, 0, 0);
		gbc_btnGenerate.gridx = 0;
		gbc_btnGenerate.gridy = 1;
		btnGenerate.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				SwingUtilities.invokeLater(new Runnable() {
	                @Override
	                public void run() {
	                	GenerateClozeQuestionsController gcq = new GenerateClozeQuestionsController(model, ClozeGeneratorGUI.this);
	    				gcq.process();
	                }
	            });
			}
		});
		panelNewClozeLower.add(btnGenerate, gbc_btnGenerate);
		
		JPanel panelEditCloze = new JPanel();
		panelEditCloze.setBackground(Color.WHITE);
		panelContent.add(panelEditCloze, "editClozeSet");
		GridBagLayout gbl_panelEditCloze = new GridBagLayout();
		gbl_panelEditCloze.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelEditCloze.rowHeights = new int[]{0, 0};
		gbl_panelEditCloze.columnWeights = new double[]{0.15, 0.7, 0.15, Double.MIN_VALUE};
		gbl_panelEditCloze.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelEditCloze.setLayout(gbl_panelEditCloze);
		
		JPanel panelEditClozeLeft = new JPanel();
		GridBagConstraints gbc_panelEditClozeLeft = new GridBagConstraints();
		gbc_panelEditClozeLeft.fill = GridBagConstraints.BOTH;
		gbc_panelEditClozeLeft.gridx = 0;
		gbc_panelEditClozeLeft.gridy = 0;
		panelEditCloze.add(panelEditClozeLeft, gbc_panelEditClozeLeft);
		
		JPanel panelEditClozeRight = new JPanel();
		GridBagConstraints gbc_panelEditClozeRight = new GridBagConstraints();
		gbc_panelEditClozeRight.fill = GridBagConstraints.BOTH;
		gbc_panelEditClozeRight.gridx = 2;
		gbc_panelEditClozeRight.gridy = 0;
		panelEditCloze.add(panelEditClozeRight, gbc_panelEditClozeRight);
		
		JPanel panelEditClozeContent = new JPanel();
		GridBagConstraints gbc_panelEditClozeContent = new GridBagConstraints();
		gbc_panelEditClozeContent.fill = GridBagConstraints.BOTH;
		gbc_panelEditClozeContent.gridx = 1;
		gbc_panelEditClozeContent.gridy = 0;
		panelEditCloze.add(panelEditClozeContent, gbc_panelEditClozeContent);
		
		// END EDIT CLOZE
		
		// START TEST
		
		panelTest = new JPanel();
		panelTest.setBackground(Color.WHITE);
		panelContent.add(panelTest, "test");
		panelTest.setLayout(new CardLayout(0, 0));
		
		JPanel panelTestSelect = new JPanel();
		panelTestSelect.setBackground(Color.WHITE);
		panelTest.add(panelTestSelect, "testSelect");
		GridBagLayout gbl_panelTestSelect = new GridBagLayout();
		gbl_panelTestSelect.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelTestSelect.rowHeights = new int[]{0, 0, 0};
		gbl_panelTestSelect.rowWeights = new double[]{0.8, 0.2, Double.MIN_VALUE};
		gbl_panelTestSelect.columnWeights = new double[]{0.15, 0.7, 0.15, Double.MIN_VALUE};
		panelTestSelect.setLayout(gbl_panelTestSelect);
		
		CG_Button btnStartTest = new CG_Button("Start");
		btnStartTest.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run() 
					{
						StartTestController st = new StartTestController(model, ClozeGeneratorGUI.this);
						st.process();
					}
				});
			}	
		});
		
		JPanel panelTestSelectLeft = new JPanel();
		panelTestSelectLeft.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelTestSelectLeft = new GridBagConstraints();
		gbc_panelTestSelectLeft.gridheight = 2;
		gbc_panelTestSelectLeft.insets = new Insets(0, 0, 0, 0);
		gbc_panelTestSelectLeft.fill = GridBagConstraints.BOTH;
		gbc_panelTestSelectLeft.gridx = 0;
		gbc_panelTestSelectLeft.gridy = 0;
		panelTestSelect.add(panelTestSelectLeft, gbc_panelTestSelectLeft);
		
		JPanel panelTestSelectRight = new JPanel();
		panelTestSelectRight.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelTestSelectRight = new GridBagConstraints();
		gbc_panelTestSelectRight.gridheight = 2;
		gbc_panelTestSelectRight.insets = new Insets(0, 0, 0, 0);
		gbc_panelTestSelectRight.fill = GridBagConstraints.BOTH;
		gbc_panelTestSelectRight.gridx = 2;
		gbc_panelTestSelectRight.gridy = 0;
		panelTestSelect.add(panelTestSelectRight, gbc_panelTestSelectRight);
		
		JLabel lblTestCustomizationIs = new JLabel("<html><div style='text-align:center'>Test customization is an upcoming feature. For now, each test is a random permutation of all possible cloze questions.</div></html>");
		lblTestCustomizationIs.setFont(new Font("Consolas", Font.PLAIN, 24));
		lblTestCustomizationIs.setForeground(Color.BLACK);
		lblTestCustomizationIs.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTestCustomizationIs.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestCustomizationIs.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTestCustomizationIs = new GridBagConstraints();
		gbc_lblTestCustomizationIs.fill = GridBagConstraints.BOTH;
		gbc_lblTestCustomizationIs.insets = new Insets(0, 0, 0, 0);
		gbc_lblTestCustomizationIs.gridx = 1;
		gbc_lblTestCustomizationIs.gridy = 0;
		panelTestSelect.add(lblTestCustomizationIs, gbc_lblTestCustomizationIs);
		GridBagConstraints gbc_btnStartTest = new GridBagConstraints();
		gbc_btnStartTest.gridx = 1;
		gbc_btnStartTest.gridy = 1;
		panelTestSelect.add(btnStartTest, gbc_btnStartTest);
		
		// Panel test perform
		JPanel panelTestPerform = new JPanel();
		panelTestPerform.setBackground(Color.WHITE);
		panelTest.add(panelTestPerform, "testPerform");
		GridBagLayout gbl_panelTestPerformNew = new GridBagLayout();
		gbl_panelTestPerformNew.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelTestPerformNew.rowHeights = new int[]{0, 0, 50, 0, (int) CG_Button.dim.getHeight() * 2, 0};
		gbl_panelTestPerformNew.columnWeights = new double[]{0.15, 0.7, 0.15, Double.MIN_VALUE};
		gbl_panelTestPerformNew.rowWeights = new double[]{1.0, 1.0, 0, 1.0, 0, Double.MIN_VALUE};
		panelTestPerform.setLayout(gbl_panelTestPerformNew);
		
		// Question label
		JScrollPane scrollTestQuestion = new JScrollPane();
		scrollTestQuestion.setBorder(null);
		scrollTestQuestion.setBackground(Color.WHITE);
		GridBagConstraints gbc_scrollTestQuestion = new GridBagConstraints();
		gbc_scrollTestQuestion.fill = GridBagConstraints.BOTH;
		gbc_scrollTestQuestion.gridx = 1;
		gbc_scrollTestQuestion.gridy = 0;
		panelTestPerform.add(scrollTestQuestion, gbc_scrollTestQuestion);
		lblTestQuestion = new JLabel("");
		lblTestQuestion.setForeground(Color.BLACK);
		lblTestQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestQuestion.setOpaque(true);
		lblTestQuestion.setBorder(null);
		lblTestQuestion.setBackground(Color.WHITE);
		lblTestQuestion.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTestQuestion.setFont(new Font("MS Gothic", Font.PLAIN, 36));
		scrollTestQuestion.setViewportView(lblTestQuestion);
		
		// Translation label
		JScrollPane scrollTestTranslation = new JScrollPane();
		scrollTestTranslation.setBorder(null);
		GridBagConstraints gbc_scrollTestTranslation = new GridBagConstraints();
		gbc_scrollTestTranslation.fill = GridBagConstraints.BOTH;
		gbc_scrollTestTranslation.gridx = 1;
		gbc_scrollTestTranslation.gridy = 1;
		lblTestTranslation = new JLabel("");
		lblTestTranslation.setForeground(Color.BLACK);
		lblTestTranslation.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestTranslation.setBorder(null);
		lblTestTranslation.setBackground(Color.WHITE);
		lblTestTranslation.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTestTranslation.setOpaque(true);
		lblTestTranslation.setFont(new Font("MS Gothic", Font.PLAIN, 36));
		scrollTestTranslation.setViewportView(lblTestTranslation);
		panelTestPerform.add(scrollTestTranslation, gbc_scrollTestTranslation);
		
		// Test separator
		JSeparator separatorTest = new JSeparator();
		separatorTest.setForeground(Color.BLACK);
		GridBagConstraints gbc_separatorTest = new GridBagConstraints();
		gbc_separatorTest.fill = GridBagConstraints.BOTH;
		gbc_separatorTest.gridx = 1;
		gbc_separatorTest.gridy = 2;
		panelTestPerform.add(separatorTest, gbc_separatorTest);
		
		// Answer label
		JScrollPane scrollTestAnswer = new JScrollPane();
		scrollTestAnswer.setBorder(null);
		scrollTestAnswer.setBackground(Color.WHITE);
		GridBagConstraints gbc_scrollTestAnswer = new GridBagConstraints();
		gbc_scrollTestAnswer.fill = GridBagConstraints.BOTH;
		gbc_scrollTestAnswer.gridx = 1;
		gbc_scrollTestAnswer.gridy = 3;
		panelTestPerform.add(scrollTestAnswer, gbc_scrollTestAnswer);
		lblTestAnswer = new JLabel("");
		lblTestAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestAnswer.setOpaque(true);
		lblTestAnswer.setBorder(null);
		lblTestAnswer.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTestAnswer.setBackground(Color.WHITE);
		lblTestAnswer.setForeground(Color.BLACK);
		lblTestAnswer.setFont(new Font("MS Gothic", Font.PLAIN, 36));
		scrollTestAnswer.setViewportView(lblTestAnswer);
		
		// Test buttons panel
		JPanel panelTestButtons = new JPanel();
		panelTestButtons.setAlignmentX(CENTER_ALIGNMENT);
		panelTestButtons.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelTestButtons = new GridBagConstraints();
		gbc_panelTestButtons.gridx = 0;
		gbc_panelTestButtons.gridy = 4;
		gbc_panelTestButtons.gridwidth = 3;
		panelTestPerform.add(panelTestButtons, gbc_panelTestButtons);
		panelTestButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		btnTestPrevious = new CG_Button("Previous");
		btnTestPrevious.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				SwingUtilities.invokeLater(new Runnable() 
				{
					@Override
					public void run() 
					{
						DisplayQuestionController dq = new DisplayQuestionController(model, ClozeGeneratorGUI.this);
						dq.process(false);
					}
				});
			}
		});
		panelTestButtons.add(btnTestPrevious);
		btnTestAdvance = new CG_Button("Answer");
		btnTestAdvance.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				SwingUtilities.invokeLater(new Runnable() 
				{
					@Override
					public void run() 
					{
						if (btnTestAdvance.getText() == "Answer")
						{
							AnswerQuestionController aq = new AnswerQuestionController(model, ClozeGeneratorGUI.this);
							aq.process();
						}
						else 
						{
							DisplayQuestionController dq = new DisplayQuestionController(model, ClozeGeneratorGUI.this);
							dq.process(true);
						}
					}
				});
			}
		});
		panelTestButtons.add(btnTestAdvance);
		btnTestEnd = new CG_Button("End");
		btnTestEnd.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run() 
					{
						CardLayout cl = (CardLayout) panelTest.getLayout();
						cl.show(panelTest, "testSelect");
						return;
					}
				});
			}	
		});
		panelTestButtons.add(btnTestEnd);
		
		// END TEST
		
		this.pack();
		this.setLocationRelativeTo(null);
		//this.setResizable(false);
		this.setVisible(true);
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
				UpdateClozeQuestionListController ucsl = new UpdateClozeQuestionListController(model, ClozeGeneratorGUI.this);
				ucsl.process();
            }
        });
		
		// HOME BUTTON - Set icons
		btnHome.setIcon(new ImageIcon(sideDefault.getScaledInstance(btnHome.getWidth(), btnHome.getHeight(), Image.SCALE_SMOOTH)));
		btnHome.setRolloverIcon(new ImageIcon(sideHover.getScaledInstance(btnHome.getWidth(), btnHome.getHeight(), Image.SCALE_SMOOTH)));
		btnHome.setSelectedIcon(new ImageIcon(sideSelect.getScaledInstance(btnHome.getWidth(), btnHome.getHeight(), Image.SCALE_SMOOTH)));
		// NEW CLOZE SET BUTTON - Set icons
		btnCreateClozeSet.setIcon(new ImageIcon(sideDefault.getScaledInstance(btnCreateClozeSet.getWidth(), btnCreateClozeSet.getHeight(), Image.SCALE_SMOOTH)));
		btnCreateClozeSet.setRolloverIcon(new ImageIcon(sideHover.getScaledInstance(btnCreateClozeSet.getWidth(), btnCreateClozeSet.getHeight(), Image.SCALE_SMOOTH)));
		btnCreateClozeSet.setSelectedIcon(new ImageIcon(sideSelect.getScaledInstance(btnCreateClozeSet.getWidth(), btnCreateClozeSet.getHeight(), Image.SCALE_SMOOTH)));
		// TEST BUTTON - Set icons
		btnTest.setIcon(new ImageIcon(sideDefault.getScaledInstance(btnTest.getWidth(), btnTest.getHeight(), Image.SCALE_SMOOTH)));
		btnTest.setRolloverIcon(new ImageIcon(sideHover.getScaledInstance(btnTest.getWidth(), btnTest.getHeight(), Image.SCALE_SMOOTH)));
		btnTest.setSelectedIcon(new ImageIcon(sideSelect.getScaledInstance(btnTest.getWidth(), btnTest.getHeight(), Image.SCALE_SMOOTH)));
		
	}
}
