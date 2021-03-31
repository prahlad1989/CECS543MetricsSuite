/**
 * 
 */
package com.suitemetrics.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.suitemetrics.model.Language;
import java.util.Enumeration;
import javax.swing.AbstractButton;

/**
 *
 */
public class LanguagePreferencesDialog extends JDialog {

	private static final long serialVersionUID = 8284938236897072282L;

//	private JCheckBox assemblerCheckBox;
//
//	private JCheckBox ada95CheckBox;
//
//	private JCheckBox cCheckBox;
//
//	private JCheckBox cppCheckBox;
//
//	private JCheckBox csharpCheckBox;
//
//	private JCheckBox cobolCheckBox;
//
//	private JCheckBox fortranCheckBox;
//
//	private JCheckBox htmlCheckBox;
//
//	private JCheckBox javaCheckBox;
//
//	private JCheckBox javascriptCheckBox;
//
//	private JCheckBox vbscriptCheckBox;
//
//	private JCheckBox visualBasicCheckBox;

	private ButtonGroup languageGroup;

	private JButton doneButton;

	public LanguagePreferencesDialog(JFrame parent) {
		super(parent, "Language", false);

		Dimension dimension = getPreferredSize();
		dimension.height = 150;
		dimension.width = 70;
		setPreferredSize(dimension);
		setMinimumSize(dimension);
                
                languageGroup = new ButtonGroup();
		doneButton = new JButton("Done");

                for(Language l: Language.values()){
                    JCheckBox cbox = new JCheckBox();
                    cbox.setActionCommand(l.name());
                    languageGroup.add(cbox);
                    
                }
                

		// Action Listeners
		doneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		layoutComponents();

		setSize(250, 600);
		setLocationRelativeTo(parent);
	}

	private void layoutComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Insets rightPadding = new Insets(0, 0, 0, 5);
		Insets noPadding = new Insets(0, 0, 0, 0);

		gbc.gridy = 0;

		// First Row
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		add(new JLabel("Select one language"), gbc);

		// Second Row
                Enumeration elements = languageGroup.getElements();
                while(elements.hasMoreElements()){
                   JCheckBox jcb = (JCheckBox)elements.nextElement();
                    
                    gbc.gridy += 1;
                    gbc.weightx = 1;
                    gbc.weighty = 0.1;
                    gbc.gridx = 0;
                    gbc.anchor = GridBagConstraints.EAST;
                    gbc.insets = rightPadding;
                    add(jcb, gbc);

                    gbc.gridx = 1;
                    gbc.anchor = GridBagConstraints.WEST;
                    gbc.insets = noPadding;
                    add(new JLabel(jcb.getActionCommand()), gbc);
                }
                    


		// Next Row
		gbc.gridy += 1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		add(doneButton, gbc);
	}

}
