/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suitemetrics.view;


import com.suitemetrics.controller.Controller1;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author 
 */
class ValueAdjustmentsDialogue extends JDialog implements Serializable{
    private static final long serialVersionUID = 3875114234343581632L;

	private JTextField projectNameField;

	private JTextField productNameField;

	private JTextField creatorNameField;

	private JTextField commentsField;

	private JButton okButton;

	private JButton cancelButton;
        
        private Controller1 controller;
        
        private FPMetricsFormPanel fPMetricsFormPanel;
        
        private String[] valueAdjustStrs = {
           "Does the System Require reliable backup and recovery process?",
            "Are specialized data communications required to transefer information to or from the application?",
            "Are there distributed processing functions?",
            "Is performace critical?",
            "Will the system run in an existing, heavily utilized operational environment?",
            "Does the system required online data entry?",
            "Does the online data entry require the input transaction to be built over multiple screens or operations?",
            "Are the internal logical files updated online?",
            "Are the input, output, files or enquiries complex?",
            "Is the internal processing complex?",
            "Is the code designed to be reusable?",
            "Are the conversion and installation included in the design?",
            "Is the system designed offer multiple installations in different organizations?",
            "Is the application designed to fecilitate change and forease of use by the user?"
                
        };
        
        private JComboBox<Integer>[] valueAdjustCombos;
        
//        class ValueAdjust{
//            private Integer[] values ={0,1,2,3,4,5};
//            JLabel label;
//            JComboBox<Integer> combo;
//            public ValueAdjust(String name){
//                label = new JLabel(name);
//                combo = new JComboBox<Integer>(values);
//                combo.setSelectedIndex(0);
//            }
//            public ValueAdjust(){
//            }
//            
//            
//        }
        
        
        

	public ValueAdjustmentsDialogue(MainFrame parent, FPMetricsFormPanel fPMetricsFormPanel) {
		super(parent, "Value Adjustment Factors", false);


		okButton = new JButton("Done");
		cancelButton = new JButton("Cancel");
                this.fPMetricsFormPanel = fPMetricsFormPanel;
		layoutControls();
		setSize(900, 600);
		setLocationRelativeTo(parent);
                
                
	}

	private void layoutControls() {
		JPanel controlsPanel = new JPanel();
		Border spaceBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		Border titleBorder = BorderFactory.createTitledBorder("Value adjustment factors");
		controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

		controlsPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Insets rightPadding = new Insets(0, 0, 0, 5);
		Insets noPadding = new Insets(0, 0, 0, 0);
                Insets topPdadding = new Insets(5,0,0,0);

		gbc.gridy = 0;
                gbc.gridx = 0;
                gbc.insets = topPdadding;
                gbc.anchor=GridBagConstraints.LINE_START;
                JLabel headingLabel = new JLabel("Assign a value from 0 to 5 for each of the following value adjustment factors");
                controlsPanel.add(headingLabel, gbc);
                gbc.gridy++;
                Integer[] defaultValueAdjusts = {0,1,2,3,4,5};
                for(String s:valueAdjustStrs){
                    gbc.gridx = 0;
                    gbc.anchor=GridBagConstraints.LINE_START;
                    controlsPanel.add(new JLabel(s), gbc);
                    gbc.gridx++;
                    JComboBox<Integer> combo = new JComboBox<Integer>(defaultValueAdjusts);
                    combo.setSelectedIndex(0);
                    gbc.anchor = GridBagConstraints.LINE_END;
                    controlsPanel.add(combo, gbc);
                    gbc.gridy++;
                }
                
                
		// Buttons Panel
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		buttonsPanel.add(okButton, gbc);
		buttonsPanel.add(cancelButton, gbc);
		okButton.setPreferredSize(cancelButton.getPreferredSize());

		// Add sub panels to the Dialog
		setLayout(new BorderLayout());
		add(controlsPanel, BorderLayout.NORTH);
		add(buttonsPanel, BorderLayout.CENTER);
                
                okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
                                Integer vafSum = 0;
                                for(Component c: controlsPanel.getComponents()){
                                    if(c instanceof JComboBox){
                                        JComboBox<Integer> jc = (JComboBox<Integer>)c;
                                        Integer selected = (Integer)jc.getSelectedItem();
                                        vafSum+= selected;
                                    }
                                }
                                fPMetricsFormPanel.updateVAF(vafSum);
                                
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
                
	}
}
