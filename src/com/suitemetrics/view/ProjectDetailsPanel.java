package com.suitemetrics.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 */
public class ProjectDetailsPanel extends JPanel {

	private static final long serialVersionUID = -7975906712806757497L;

	private JTree projectTree;

	public ProjectDetailsPanel() {
		projectTree = new JTree();

		setLayout(new BorderLayout());

		projectTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Action Listeners
		projectTree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode current = (DefaultMutableTreeNode) projectTree.getLastSelectedPathComponent();

				System.out.println(current.getUserObject());
			}
		});

		add(new JScrollPane(projectTree), BorderLayout.CENTER);
	}

	@SuppressWarnings("unused")
	private DefaultMutableTreeNode createTree() {
		return null;
	}

}
