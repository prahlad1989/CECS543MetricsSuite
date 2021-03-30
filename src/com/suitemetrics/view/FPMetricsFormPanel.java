package com.suitemetrics.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 */
public class FPMetricsFormPanel extends JPanel {

	private static final long serialVersionUID = -6158657189704080196L;

	private JLabel headerLabel;

	private JLabel simpleFactorLabel;

	private JLabel avgFactorLabel;

	private JLabel complextFactorLabel;

	private JLabel extInputsLabel;

	private JLabel extOutputsLabel;

	private JLabel extInqLabel;

	private JLabel intLogicalFilesLabel;

	private JLabel extInterfaceFilesLabel;

	private JLabel totalCountLabel;

	private JLabel currentLanguageLabel;

	public FPMetricsFormPanel() {
		Dimension dimension = getPreferredSize();
		dimension.width = 500;
		setPreferredSize(dimension);
		setMinimumSize(dimension);

		headerLabel = new JLabel("Weighting Factors");
		simpleFactorLabel = new JLabel("Simple");
		avgFactorLabel = new JLabel("Average");
		complextFactorLabel = new JLabel("Complex");
		extInputsLabel = new JLabel("External Inputs");
		extOutputsLabel = new JLabel("External Outputs");
		extInqLabel = new JLabel("External Inquiries");
		intLogicalFilesLabel = new JLabel("Internal Logical Files");
		extInterfaceFilesLabel = new JLabel("External Interface Files");
		totalCountLabel = new JLabel("Total Count");
		currentLanguageLabel = new JLabel("Current Language");

		layoutComponents();
	}

	private void layoutComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Insets rightPadding = new Insets(0, 0, 0, 5);
		Insets noPadding = new Insets(0, 0, 0, 0);

		gbc.gridy = 0;

		// First Row
		gbc.anchor = GridBagConstraints.CENTER;
		add(headerLabel, gbc);

		// Second Row
		gbc.gridy++;

		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.WEST;
		add(simpleFactorLabel, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		add(avgFactorLabel, gbc);

		gbc.gridx = 2;
		gbc.anchor = GridBagConstraints.EAST;
		add(complextFactorLabel, gbc);

		// Next Row
		gbc.gridy += 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = rightPadding;
		add(extInputsLabel, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		add(new JTextField(15), gbc);
	}
}
