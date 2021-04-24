/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suitemetrics.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author prahlad
 */
public class ECFAdjustDialogue extends ValueAdjustmentsDialogue{
    protected String[] valueAdjustStrs = {
           "Experience with UML",
            "Application Domain",
            "OO Programming",
            "Head Manager Ability",
            "Motivation",
            "Requirement Stability",
            "Parttime workers",
            "Programming language difficulty",
            "Easy to use",
            "concurrent",
            "special security",
            "direct access for external data",
            "Spical user training"
        };
    
     public ECFAdjustDialogue(String panelName, MainFrame parent, FPMetricsFormPanel fPMetricsFormPanel) {
		super(panelName, parent, fPMetricsFormPanel);
	}
     
     protected void layoutControls() {
	 super.valueAdjustStrs = valueAdjustStrs;	
         super.layoutControls();
                okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
                                double vafSum = 0f;
                                for(Component c: controlsPanel.getComponents()){
                                    if(c instanceof JComboBox){
                                        JComboBox<Integer> jc = (JComboBox<Integer>)c;
                                        Integer selected = (Integer)jc.getSelectedItem();
                                        vafSum+= selected;
                                    }
                                }
                                vafSum = 1.4+(-.03*vafSum);
                                ((UCPMetricsFormPanel)fPMetricsFormPanel).updateECF(vafSum);
                                
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
