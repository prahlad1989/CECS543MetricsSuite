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

/**
 *
 */
public class LanguagePreferencesDialog extends JDialog {

	private static final long serialVersionUID = 8284938236897072282L;

	private JCheckBox assemblerCheckBox;

	private JCheckBox ada95CheckBox;

	private JCheckBox cCheckBox;

	private JCheckBox cppCheckBox;

	private JCheckBox csharpCheckBox;

	private JCheckBox cobolCheckBox;

	private JCheckBox fortranCheckBox;

	private JCheckBox htmlCheckBox;

	private JCheckBox javaCheckBox;

	private JCheckBox javascriptCheckBox;

	private JCheckBox vbscriptCheckBox;

	private JCheckBox visualBasicCheckBox;

	private ButtonGroup languageGroup;

	private JButton doneButton;

	public LanguagePreferencesDialog(JFrame parent) {
		super(parent, "Language", false);

		Dimension dimension = getPreferredSize();
		dimension.height = 150;
		dimension.width = 70;
		setPreferredSize(dimension);
		setMinimumSize(dimension);

		assemblerCheckBox = new JCheckBox();
		ada95CheckBox = new JCheckBox();
		cCheckBox = new JCheckBox();
		cppCheckBox = new JCheckBox();
		csharpCheckBox = new JCheckBox();
		cobolCheckBox = new JCheckBox();
		fortranCheckBox = new JCheckBox();
		htmlCheckBox = new JCheckBox();
		javaCheckBox = new JCheckBox();
		javascriptCheckBox = new JCheckBox();
		vbscriptCheckBox = new JCheckBox();
		visualBasicCheckBox = new JCheckBox();
		languageGroup = new ButtonGroup();
		doneButton = new JButton("Done");

		// Set up Language check box's
		assemblerCheckBox.setActionCommand(Language.ASSEMBLER.name());
		ada95CheckBox.setActionCommand(Language.ADA95.name());
		cCheckBox.setActionCommand(Language.C.name());
		cppCheckBox.setActionCommand(Language.CPP.name());
		csharpCheckBox.setActionCommand(Language.CSHARP.name());
		cobolCheckBox.setActionCommand(Language.COBOL.name());
		fortranCheckBox.setActionCommand(Language.FORTRAN.name());
		htmlCheckBox.setActionCommand(Language.HTML.name());
		javaCheckBox.setActionCommand(Language.JAVA.name());
		javascriptCheckBox.setActionCommand(Language.JAVASCRIPT.name());
		vbscriptCheckBox.setActionCommand(Language.VBSCRIPT.name());
		visualBasicCheckBox.setActionCommand(Language.VISUALBASIC.name());

		languageGroup.add(assemblerCheckBox);
		languageGroup.add(ada95CheckBox);
		languageGroup.add(cCheckBox);
		languageGroup.add(cppCheckBox);
		languageGroup.add(csharpCheckBox);
		languageGroup.add(cobolCheckBox);
		languageGroup.add(fortranCheckBox);
		languageGroup.add(htmlCheckBox);
		languageGroup.add(javaCheckBox);
		languageGroup.add(javascriptCheckBox);
		languageGroup.add(vbscriptCheckBox);
		languageGroup.add(visualBasicCheckBox);

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
		gbc.gridy += 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = rightPadding;
		add(assemblerCheckBox, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		add(new JLabel(Language.ASSEMBLER.toString()), gbc);

		// Next Row
		gbc.gridy += 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = rightPadding;
		add(ada95CheckBox, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		add(new JLabel(Language.ADA95.toString()), gbc);

		// Next Row
		gbc.gridy += 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = rightPadding;
		add(cCheckBox, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		add(new JLabel(Language.C.toString()), gbc);

		// Next Row
		gbc.gridy += 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = rightPadding;
		add(cppCheckBox, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		add(new JLabel(Language.CPP.toString()), gbc);

		// Next Row
		gbc.gridy += 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = rightPadding;
		add(csharpCheckBox, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		add(new JLabel(Language.CSHARP.toString()), gbc);

		// Next Row
		gbc.gridy += 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = rightPadding;
		add(cobolCheckBox, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		add(new JLabel(Language.COBOL.toString()), gbc);

		// Next Row
		gbc.gridy += 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = rightPadding;
		add(fortranCheckBox, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		add(new JLabel(Language.FORTRAN.toString()), gbc);

		// Next Row
		gbc.gridy += 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = rightPadding;
		add(htmlCheckBox, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		add(new JLabel(Language.HTML.toString()), gbc);

		// Next Row
		gbc.gridy += 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = rightPadding;
		add(javaCheckBox, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		add(new JLabel(Language.JAVA.toString()), gbc);

		// Next Row
		gbc.gridy += 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = rightPadding;
		add(javascriptCheckBox, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		add(new JLabel(Language.JAVASCRIPT.toString()), gbc);

		// Next Row
		gbc.gridy += 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = rightPadding;
		add(vbscriptCheckBox, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		add(new JLabel(Language.VBSCRIPT.toString()), gbc);

		// Next Row
		gbc.gridy += 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = rightPadding;
		add(visualBasicCheckBox, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		add(new JLabel(Language.VISUALBASIC.toString()), gbc);

		// Next Row
		gbc.gridy += 1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		add(doneButton, gbc);
	}

}
