package com.suitemetrics.view;

import com.suitemetrics.controller.Controller1;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 */
public class NewProjectDialog extends JDialog implements Serializable{

	private static final long serialVersionUID = 3877101414215581632L;

	private JTextField projectNameField;

	private JTextField productNameField;

	private JTextField creatorNameField;

	private JTextField commentsField;

	private JButton okButton;

	private JButton cancelButton;
        
       
        
        private MainFrame parent;

	public NewProjectDialog(MainFrame parent) {
		super(parent, "New Project", false);

		this.parent = parent;
		productNameField = new JTextField(10);
                projectNameField = new JTextField(10);
		creatorNameField = new JTextField(10);
		commentsField = new JTextField(10);
		okButton = new JButton("Ok");
		cancelButton = new JButton("Cancel");

		layoutControls();

		// Action Listeners
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
                                parent.createNewProject(projectNameField.getText());
                                System.out.println("project name is "+projectNameField.getText());
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		setSize(450, 320);
		setLocationRelativeTo(parent);
	}

	private void layoutControls() {
		// Controls Panel
		JPanel controlsPanel = new JPanel();
		Border spaceBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		Border titleBorder = BorderFactory.createTitledBorder("CECS 543 Metrics Suite New Project");
		controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

		controlsPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Insets rightPadding = new Insets(0, 0, 0, 5);
		Insets noPadding = new Insets(0, 0, 0, 0);

		gbc.gridy = 0;

		// First Row
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = rightPadding;
		controlsPanel.add(new JLabel("Project Name:"), gbc);

		gbc.gridx += 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		gbc.fill = GridBagConstraints.NONE;
		controlsPanel.add(projectNameField, gbc);

		// Next Row
		gbc.gridy += 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = rightPadding;
		controlsPanel.add(new JLabel("Product Name:"), gbc);

		gbc.gridx += 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		gbc.fill = GridBagConstraints.NONE;
		controlsPanel.add(productNameField, gbc);

		// Next Row
		gbc.gridy += 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = rightPadding;
		controlsPanel.add(new JLabel("Creator:"), gbc);

		gbc.gridx += 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		gbc.fill = GridBagConstraints.NONE;
		controlsPanel.add(creatorNameField, gbc);

		// Next Row
		gbc.gridy += 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = rightPadding;
		controlsPanel.add(new JLabel("Comments:"), gbc);

		gbc.gridx += 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = noPadding;
		gbc.fill = GridBagConstraints.NONE;
		controlsPanel.add(commentsField, gbc);

		// Buttons Panel
		JPanel buttonsPanel = new JPanel();

		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.add(okButton, gbc);
		buttonsPanel.add(cancelButton, gbc);
		okButton.setPreferredSize(cancelButton.getPreferredSize());

		// Add sub panels to the Dialog
		setLayout(new BorderLayout());
		add(controlsPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}

}
