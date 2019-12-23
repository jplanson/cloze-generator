package com.jplanson.cloze.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class CG_ErrorMessage extends JDialog implements ActionListener
{
	private static final long serialVersionUID = -6308498549063021169L;

	private CG_Button okButton = new CG_Button("OK");
	private CG_Button options[] = {okButton};
	private CG_Button defaultOption = okButton;
	
	public CG_ErrorMessage(String errorMessage)
	{
		JOptionPane pane = new JOptionPane(errorMessage, JOptionPane.ERROR_MESSAGE, JOptionPane.OK_OPTION, null, options, defaultOption);
		getComponents(pane);
		
		okButton.addActionListener(this);
		
		this.setTitle("Error");
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setContentPane(pane);
		this.getContentPane().setBackground(Color.WHITE);
		this.setLocationRelativeTo(null);
		
		pack();
        setVisible(true);
	}
	
	private void getComponents(Container c)
	{
	    Component[] m = c.getComponents();

	    for(int i = 0; i < m.length; i++){

	        if(m[i].getClass().getName() == "javax.swing.JPanel")
	            m[i].setBackground(Color.white);
	        	m[i].setFont(new Font("Consolas", Font.PLAIN, 18));

	        if(c.getClass().isInstance(m[i]));
	            getComponents((Container)m[i]);
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		dispose();
		setVisible(false);
	}
}
