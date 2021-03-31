/**
 * 
 */
package com.suitemetrics.view;

import com.suitemetrics.controller.Controller1;
import com.suitemetrics.controller.ProjectController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

/**
 *
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 246976862921981364L;

	private ProjectDetailsPanel projectDetailsFormPanel;

	private FPMetricsFormPanel fpMetricsFormPanel;

	private JSplitPane splitPane;

	private JTabbedPane tabbedPane;

	private NewProjectDialog newProjectDialog;
	
	private LanguagePreferencesDialog languagesDialog;

	public MainFrame() {
		super("CECS 543 Metrics Suite");

		setLayout(new BorderLayout());
		initializePanels();
		setJMenuBar(createMenuBar());

		// Add Listeners
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				System.gc();
			}
		});

		add(splitPane, BorderLayout.CENTER);

		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(1000, 800);
		setMinimumSize(new Dimension(900, 700));
	}

	private void initializePanels() {
		projectDetailsFormPanel = new ProjectDetailsPanel();
		fpMetricsFormPanel = new FPMetricsFormPanel();

		tabbedPane = new JTabbedPane();

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, projectDetailsFormPanel, tabbedPane);
		splitPane.setOneTouchExpandable(true);

		tabbedPane.addTab("Part 1", fpMetricsFormPanel);
                
                
		//newProjectDialog = new NewProjectDialog(this);
		
		languagesDialog = new LanguagePreferencesDialog(this);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem openMenuItem = new JMenuItem("Open");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		JMenuItem exitMenuItem = new JMenuItem("Exit");

		fileMenu.add(newMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(openMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(saveMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);

		JMenu editMenu = new JMenu("Edit");
		
		JMenu preferencesMenu = new JMenu("Preferences");
		JMenuItem languagesMenuItem = new JMenuItem("Languages");
		preferencesMenu.add(languagesMenuItem);

		JMenu metricsMenu = new JMenu("Metrics");
		JMenu fpMenu = new JMenu("Function Points");
		JMenuItem smiMenuItem = new JMenuItem("SMI");
		JMenuItem ooMetricsMenuItem = new JMenuItem("OO Metrics");

		JMenuItem fpDataMenuItem = new JMenuItem("Enter FP Data");
		JMenuItem computeCodeSizeMenuItem = new JMenuItem("Compute Code Size");
		fpMenu.add(fpDataMenuItem);
		fpMenu.addSeparator();
		fpMenu.add(computeCodeSizeMenuItem);

		metricsMenu.add(fpMenu);
		metricsMenu.addSeparator();
		metricsMenu.add(smiMenuItem);
		metricsMenu.addSeparator();
		metricsMenu.add(ooMetricsMenuItem);

		JMenu helpMenu = new JMenu("Help");

		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(preferencesMenu);
		menuBar.add(metricsMenu);
		menuBar.add(helpMenu);

		// Set up Mnemonics
		fileMenu.setMnemonic(KeyEvent.VK_F);
		newMenuItem.setMnemonic(KeyEvent.VK_N);
		exitMenuItem.setMnemonic(KeyEvent.VK_X);

		// Set up Accelerators
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		// Action Listeners
		newMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                            ProjectController controller = new ProjectController();
                            controller.projectDetailsPanel = MainFrame.this.projectDetailsFormPanel;
                            newProjectDialog = new NewProjectDialog(MainFrame.this, controller);
                            
			    newProjectDialog.setVisible(true);
			}
		});
		
		languagesMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				languagesDialog.setVisible(true);
			}
		});

		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you want to exit?", "Confirm Exit",
						JOptionPane.OK_CANCEL_OPTION);

				if (action == JOptionPane.OK_OPTION) {
					WindowListener[] listeners = getWindowListeners();

					for (WindowListener listener : listeners) {
						listener.windowClosing(new WindowEvent(MainFrame.this, 0));
					}
				}
			}
		});

		fpDataMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fpName = JOptionPane.showInputDialog(MainFrame.this, "Name of this FP", "Input",
						JOptionPane.QUESTION_MESSAGE);
				System.out.println(fpName);
			}
		});

		return menuBar;
	}

}
