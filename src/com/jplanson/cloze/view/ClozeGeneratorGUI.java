package com.jplanson.cloze.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.LineBorder;

import com.jplanson.cloze.controller.AnswerQuestionController;
import com.jplanson.cloze.controller.DeleteClozeSetController;
import com.jplanson.cloze.controller.DisplayQuestionController;
import com.jplanson.cloze.controller.GenerateClozeQuestionsController;
import com.jplanson.cloze.controller.ProcessClozeInputController;
import com.jplanson.cloze.controller.StartTestController;
import com.jplanson.cloze.controller.UpdateClozeSetListController;
import com.jplanson.cloze.model.ClozeText;
import com.jplanson.cloze.model.Model;
import java.awt.BorderLayout;
import java.awt.Component;

public class ClozeGeneratorGUI extends JFrame 
{
	private static final long serialVersionUID = 8826062099639243348L;
	
	Model model;
	
	public JPanel panelHome;
	public JList<ClozeText> listClozeSet;
	
	public JPanel panelCreateCloze;
	public JTextArea inputSampleText;
	public JTextArea inputTranslation;
	public JPanel panelProcessing;
	
	public JPanel panelTest;
	public JLabel lblTestTranslation;
	public JLabel lblTestQuestion;
	public JLabel lblTestAnswer;
	public JButton btnTestAdvance;
	public JButton btnTestPrevious;
	
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
		panelSideBar.setBackground(new Color(0x404040));
		splitPane.setLeftComponent(panelSideBar);
		
		JPanel panelContent = new JPanel();
		splitPane.setRightComponent(panelContent);
		panelContent.setLayout(new CardLayout(0, 0));
		
		// HOME BUTTON
		JButton btnHome = new JButton("Home");
		// Text
		btnHome.setHorizontalTextPosition(SwingConstants.CENTER);
		btnHome.setForeground(Color.WHITE);
		btnHome.setFont(new Font("Consolas", Font.PLAIN, 16));
		// Icon
		btnHome.setRolloverEnabled(true);
		btnHome.setMargin(new Insets(0, 0, 0, 0));
		btnHome.setBorder(null);
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
		
		// START CREATE CLOZE 
		
		// NEW CLOZE SET BUTTON
		JButton btnCreateClozeSet = new JButton("  Create Cloze Set  ");
		// Text
		btnCreateClozeSet.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCreateClozeSet.setForeground(Color.WHITE);
		btnCreateClozeSet.setFont(new Font("Consolas", Font.PLAIN, 16));
		// Icon
		btnCreateClozeSet.setRolloverEnabled(true);
		btnCreateClozeSet.setMargin(new Insets(0, 0, 0, 0));
		btnCreateClozeSet.setBorder(null);
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
				cl.show(panelContent, "createCloze");
			}
		});
		
		JButton btnTest = new JButton("Test");
		// Text
		btnTest.setHorizontalTextPosition(SwingConstants.CENTER);
		btnTest.setForeground(Color.WHITE);
		btnTest.setFont(new Font("Consolas", Font.PLAIN, 16));
		// Icon
		btnTest.setRolloverEnabled(true);
		btnTest.setMargin(new Insets(0, 0, 0, 0));
		btnTest.setBorder(null);
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
		
		ImagePanel panelLogo = new ImagePanel("res/cloze_gen_logo.png");
		
		GroupLayout gl_panelSideBar = new GroupLayout(panelSideBar);
		gl_panelSideBar.setHorizontalGroup(
			gl_panelSideBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSideBar.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSideBar.createParallelGroup(Alignment.LEADING)
						.addComponent(panelLogo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
						.addComponent(btnHome, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
						.addComponent(btnCreateClozeSet, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
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
					.addComponent(btnTest, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(437, Short.MAX_VALUE))
		);
		panelSideBar.setLayout(gl_panelSideBar);
		
		panelHome = new JPanel();
		panelHome.setBackground(Color.WHITE);
		panelContent.add(panelHome, "home");
		
		JLabel lblClozeTextList = new JLabel("Cloze Sets:");
		lblClozeTextList.setFont(new Font("Consolas", Font.PLAIN, 24));
		
		JScrollPane scrollClozeSet = new JScrollPane();
		scrollClozeSet.setBorder(new LineBorder(new Color(130, 135, 144), 2));
		GroupLayout gl_panelHome = new GroupLayout(panelHome);
		gl_panelHome.setHorizontalGroup(
			gl_panelHome.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHome.createSequentialGroup()
					.addGap(59)
					.addGroup(gl_panelHome.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollClozeSet, GroupLayout.PREFERRED_SIZE, 1108, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblClozeTextList))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		gl_panelHome.setVerticalGroup(
			gl_panelHome.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHome.createSequentialGroup()
					.addGap(65)
					.addComponent(lblClozeTextList)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollClozeSet, GroupLayout.PREFERRED_SIZE, 603, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(57, Short.MAX_VALUE))
		);
		
		listClozeSet = new JList<ClozeText>();
		listClozeSet.setFont(new Font("MS Gothic", Font.BOLD, 18));
		listClozeSet.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (SwingUtilities.isRightMouseButton(e))
				{
					if (listClozeSet.getSelectedIndex() == -1) { return; }
					JPopupMenu menuRightClick = new JPopupMenu();
					JMenuItem edit = new JMenuItem("Edit");
					edit.addActionListener(new ActionListener() 
					{
						@Override
						public void actionPerformed(ActionEvent arg0) 
						{
							System.out.println(listClozeSet.getSelectedIndex());
						}
					});
					JMenuItem delete = new JMenuItem("Delete");
					delete.addActionListener(new ActionListener() 
					{
						@Override
						public void actionPerformed(ActionEvent arg0) 
						{
							System.out.println(listClozeSet.getSelectedIndex());
							DeleteClozeSetController dcs = new DeleteClozeSetController(model, ClozeGeneratorGUI.this);
							dcs.process(listClozeSet.getSelectedIndex());
						}
					});
					
					menuRightClick.add(edit);
					menuRightClick.add(delete);
//					menuRightClick.show(ClozeGeneratorGUI.this, listClozeSet.getX() + e.getX(), listClozeSet.getY() + e.getY());
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
		scrollClozeSet.setViewportView(listClozeSet);
		panelHome.setLayout(gl_panelHome);
		
		panelCreateCloze = new JPanel();
		panelCreateCloze.setBackground(Color.WHITE);
		panelContent.add(panelCreateCloze, "createCloze");
		panelCreateCloze.setLayout(new MigLayout("", "[122.00][grow][225.00][150.00][225.00][131.00][grow]", "[40.00][][33.00][][38.00][17.00][43.00][30.00][326.00,grow][23.00][45.00]"));
		
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
		
		JScrollPane scrollProcessing = new JScrollPane();
		panelCreateCloze.add(scrollProcessing, "cell 1 8 5 1,grow");
		
		panelProcessing = new JPanel();
		panelProcessing.setBackground(Color.WHITE);		
		panelProcessing.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panelProcessing.setLayout(new WrapLayout(FlowLayout.LEFT, 0, 0));
		scrollProcessing.setViewportView(panelProcessing);
		
		JPanel panelSpaceFill4 = new JPanel();
		panelSpaceFill4.setBorder(null);
		panelSpaceFill4.setBackground(Color.WHITE);
		panelCreateCloze.add(panelSpaceFill4, "cell 3 9,grow");
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setFont(new Font("Consolas", Font.PLAIN, 24));
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
		panelCreateCloze.add(btnGenerate, "cell 3 10,grow");
		
		// END EDIT CLOZE
		
		// START TEST
		
		panelTest = new JPanel();
		panelTest.setBackground(Color.WHITE);
		panelContent.add(panelTest, "test");
		panelTest.setLayout(new CardLayout(0, 0));
		
		JPanel panelTestSelect = new JPanel();
		panelTestSelect.setBackground(Color.WHITE);
		panelTest.add(panelTestSelect, "testSelect");
		
		JButton btnStartTest = new JButton("Start");
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
		GroupLayout gl_panelTestSelect = new GroupLayout(panelTestSelect);
		gl_panelTestSelect.setHorizontalGroup(
			gl_panelTestSelect.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTestSelect.createSequentialGroup()
					.addGap(484)
					.addComponent(btnStartTest, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(613, Short.MAX_VALUE))
		);
		gl_panelTestSelect.setVerticalGroup(
			gl_panelTestSelect.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelTestSelect.createSequentialGroup()
					.addContainerGap(658, Short.MAX_VALUE)
					.addComponent(btnStartTest, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(66))
		);
		panelTestSelect.setLayout(gl_panelTestSelect);
		
		JPanel panelTestPerform = new JPanel();
		panelTestPerform.setBackground(Color.WHITE);
		panelTest.add(panelTestPerform, "testPerform");
		panelTestPerform.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTestContent = new JPanel();
		panelTestContent.setBackground(Color.WHITE);
		panelTestPerform.add(panelTestContent, BorderLayout.CENTER);
		
		JScrollPane scrollTestQuestion = new JScrollPane();
		scrollTestQuestion.setBorder(null);
		scrollTestQuestion.setBackground(Color.WHITE);
		
		JScrollPane scrollTestTranslation = new JScrollPane();
		scrollTestTranslation.setBorder(null);
		
		JSeparator separatorTest = new JSeparator();
		
		JScrollPane scrollTestAnswer = new JScrollPane();
		scrollTestAnswer.setBorder(null);
		scrollTestAnswer.setBackground(Color.WHITE);
		GroupLayout gl_panelTestContent = new GroupLayout(panelTestContent);
		gl_panelTestContent.setHorizontalGroup(
			gl_panelTestContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTestContent.createSequentialGroup()
					.addGap(42)
					.addComponent(separatorTest, GroupLayout.PREFERRED_SIZE, 1128, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(44, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panelTestContent.createSequentialGroup()
					.addContainerGap(315, Short.MAX_VALUE)
					.addComponent(scrollTestAnswer, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
					.addGap(299))
				.addGroup(Alignment.TRAILING, gl_panelTestContent.createSequentialGroup()
					.addGap(312)
					.addGroup(gl_panelTestContent.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollTestQuestion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
						.addComponent(scrollTestTranslation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
					.addGap(302))
		);
		gl_panelTestContent.setVerticalGroup(
			gl_panelTestContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTestContent.createSequentialGroup()
					.addGap(68)
					.addComponent(scrollTestQuestion, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(scrollTestTranslation, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(separatorTest, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(scrollTestAnswer, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		
		lblTestTranslation = new JLabel("");
		lblTestTranslation.setForeground(Color.BLACK);
		lblTestTranslation.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestTranslation.setBorder(null);
		lblTestTranslation.setBackground(Color.WHITE);
		lblTestTranslation.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTestTranslation.setOpaque(true);
		lblTestTranslation.setFont(new Font("MS Gothic", Font.PLAIN, 36));
		scrollTestTranslation.setViewportView(lblTestTranslation);
		
		lblTestQuestion = new JLabel("");
		lblTestQuestion.setForeground(Color.BLACK);
		lblTestQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestQuestion.setOpaque(true);
		lblTestQuestion.setBorder(null);
		lblTestQuestion.setBackground(Color.WHITE);
		lblTestQuestion.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTestQuestion.setFont(new Font("MS Gothic", Font.PLAIN, 36));
		scrollTestQuestion.setViewportView(lblTestQuestion);
		
		lblTestAnswer = new JLabel("");
		lblTestAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestAnswer.setOpaque(true);
		lblTestAnswer.setBorder(null);
		lblTestAnswer.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTestAnswer.setBackground(Color.WHITE);
		lblTestAnswer.setForeground(Color.BLACK);
		lblTestAnswer.setFont(new Font("MS Gothic", Font.PLAIN, 36));
		scrollTestAnswer.setViewportView(lblTestAnswer);
		panelTestContent.setLayout(gl_panelTestContent);
		
		JPanel panelTestButtons = new JPanel();
		panelTestPerform.add(panelTestButtons, BorderLayout.SOUTH);
		panelTestButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnTestPrevious = new JButton("Previous");
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
		
		btnTestAdvance = new JButton("Answer");
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
		getContentPane().setLayout(groupLayout);
		
		// END TEST
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
				UpdateClozeSetListController ucsl = new UpdateClozeSetListController(model, ClozeGeneratorGUI.this);
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
