package com.suitemetrics.view;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 */
public class ProjectDetailsPanel extends JPanel {

	private static final long serialVersionUID = -7975906712806757497L;

	private JTree projectTree;
        
        private JScrollPane pane ;
        
        
        

	public ProjectDetailsPanel() {
		projectTree = new JTree();

		setLayout(new BorderLayout());

		projectTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
                
                projectTree.getComponents();
                
                JPanel panel =new JPanel();
                
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                
                pane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                add(pane);

		// Action Listeners
//		projectTree.addTreeSelectionListener(new TreeSelectionListener() {
//			@Override
//			public void valueChanged(TreeSelectionEvent e) {
//				DefaultMutableTreeNode current = (DefaultMutableTreeNode) projectTree.getLastSelectedPathComponent();
//
//				System.out.println(current.getUserObject());
//			}
//		});


		//add(new JScrollPane(projectTree), BorderLayout.CENTER);
                
                
                
	}
        
        
        
        public void createNewProject(String projectName){
            
            DefaultMutableTreeNode root = new DefaultMutableTreeNode(projectName);
        //create the child nodes
         
        //add the child nodes to the root node


		//setLayout(new BorderLayout());

		//pTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
                
                    

		// Action Listeners
//		pTree.addTreeSelectionListener(new TreeSelectionListener() {
//			@Override
//			public void valueChanged(TreeSelectionEvent e) {
//				DefaultMutableTreeNode current = (DefaultMutableTreeNode) pTree.getLastSelectedPathComponent();
//
//
//				System.out.println(current.getUserObject());
//			}
//		});

		
               JTree tree = new JTree(root);
               JPanel panal =  (JPanel)(pane.getViewport().getView());
               panal.add(tree);
               
                revalidate();
                repaint();
                System.out.println();
            
        }

	@SuppressWarnings("unused")
	private DefaultMutableTreeNode createTree() {
		return null;
	}

}
