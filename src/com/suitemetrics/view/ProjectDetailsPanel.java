package com.suitemetrics.view;

import com.suitemetrics.controller.Controller1;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 */
public class ProjectDetailsPanel extends JPanel {

    private static final long serialVersionUID = -7975906712806757497L;

    private JTree projectTree;

    public JTree getProjectTree() {
        return projectTree;
    }

    public void setProjectTree(JTree projectTree) {
        this.projectTree = projectTree;
    }

    Controller1 controller;

    MainFrame parent;

    public ProjectDetailsPanel(MainFrame parent) {
        this.parent = parent;
    }

    public ProjectDetailsPanel(Controller1 controller) {
        this.controller = controller;
    }

    public JTree createNewProject(String projectName) {

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(projectName);
        setLayout(new BorderLayout());
        projectTree = new JTree(root);

        projectTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        this.removeAll();
        this.add(new JScrollPane(projectTree), BorderLayout.CENTER);
        // Action Listeners
        projectTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode current = (DefaultMutableTreeNode) projectTree.getLastSelectedPathComponent();
                System.out.println("selected project" + current.getUserObject());
                parent.updateTabSelection(current.getUserObject().toString());
            }
        });
//               

        revalidate();
        repaint();
        return projectTree;

    }

    public void openExistingProject(JTree projectTree) {
        this.projectTree = projectTree;
        projectTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        setLayout(new BorderLayout());
        this.removeAll();
        this.add(new JScrollPane(projectTree), BorderLayout.CENTER);
        // Action Listeners
        projectTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode current = (DefaultMutableTreeNode) projectTree.getLastSelectedPathComponent();
                System.out.println("selected project" + current.getUserObject());
                parent.updateTabSelection(current.getUserObject().toString());
            }
        });
//               

        revalidate();
        repaint();
        System.out.println();

    }

    public void createNewMetric(String metricName) {

        DefaultTreeModel model = (DefaultTreeModel) projectTree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) projectTree.getModel()
                .getRoot();
        DefaultMutableTreeNode child = new DefaultMutableTreeNode(metricName);
        model.insertNodeInto(child, root, root.getChildCount());
        projectTree.scrollPathToVisible(new TreePath(child.getPath()));
    }

}
