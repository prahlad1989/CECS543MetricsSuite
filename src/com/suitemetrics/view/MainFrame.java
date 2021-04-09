/**
 *
 */
package com.suitemetrics.view;

import com.suitemetrics.controller.ProjectController;
import com.suitemetrics.model.Language;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author 
 */
public class MainFrame extends JFrame implements ILanguageUpdate{

    private static final long serialVersionUID = 246976862921981364L;

    private ProjectDetailsPanel projectDetailsFormPanel;

    private FPMetricsFormPanel fpMetricsFormPanel;

    private FPMetricsFormPanel[] fpMetricsFormPanelList;

    private JSplitPane splitPane;

    private JTabbedPane tabbedPane;
    
    private Language language;

    /**
     *
     * @return
     */
    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    private NewProjectDialog newProjectDialog;

    private LanguagePreferencesDialog languagesDialog;

    ProjectController controller;

    ProjectDetails projectDetails;

    /**
     *
     */
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
        projectDetailsFormPanel = new ProjectDetailsPanel(this);

        tabbedPane = new JTabbedPane();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, projectDetailsFormPanel, tabbedPane);
        splitPane.setOneTouchExpandable(true); 
        languagesDialog = new LanguagePreferencesDialog(this);
        language = Language.C;
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
                newProjectDialog = new NewProjectDialog(MainFrame.this);
                newProjectDialog.setVisible(true);
            }
        });

        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCurrentProject();
            }
        });

        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setAcceptAllFileFilterUsed(false);
                chooser.addChoosableFileFilter(new FileNameExtensionFilter("specified", "ms"));
                int returnValue = chooser.showOpenDialog(MainFrame.this);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try {
                        ObjectInputStream oos = new ObjectInputStream(new FileInputStream(file));
                        ProjectDetails details = (ProjectDetails) oos.readObject();
                        openExistingProject(details);
                        System.out.println(MainFrame.this.projectDetails);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

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
                    saveCurrentProject();

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
                fpMetricsFormPanel = new FPMetricsFormPanel(language, languagesDialog);
                fpMetricsFormPanel.setParent(MainFrame.this);
                fpMetricsFormPanel.initializeButtonActions();
                fpMetricsFormPanel.setName(fpName);
                tabbedPane.addTab(fpName, fpMetricsFormPanel);
                projectDetailsFormPanel.createNewMetric(fpName);
                fpMetricsFormPanel.setVisible(true);

            }
        });

        return menuBar;
    }

    void updateTabSelection(String tabName) {
        int totalTabs = this.tabbedPane.getTabCount();
        for (int i = 0; i < totalTabs; i++) {
            if (tabName.equals(this.tabbedPane.getTitleAt(i))) {
                this.tabbedPane.setSelectedIndex(i);
            }
        }
    }

    /**
     *
     * @param projectName
     */
    public void createNewProject(String projectName) {
        tabbedPane.removeAll();
        projectDetails = new ProjectDetails(projectName);
        projectDetailsFormPanel.createNewProject(projectName);
    }

    public void saveCurrentProject() {
        String projectName = projectDetails.projectName;
        projectDetails.jTree = projectDetailsFormPanel.getProjectTree();
        projectDetails.panels = tabbedPane.getComponents();
        projectDetails.language = language;
        if (projectName != null && !projectName.trim().isEmpty()) {
            String fileName = projectName + ".ms";
            try {
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
                os.writeObject(projectDetails);
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

    /**
     *
     * @param details
     */
    public void openExistingProject(ProjectDetails details) {
        //projectDetails.jTabbedPane = details.jTabbedPane;
        projectDetails = details;
        projectDetailsFormPanel.openExistingProject(projectDetails.jTree);
        projectDetails.language = details.language;
        tabbedPane.removeAll();
        for (Component c : projectDetails.panels) {
            ((FPMetricsFormPanel)c).setParent(this);
            ((FPMetricsFormPanel)c).initializeButtonActions();
            tabbedPane.add(c.getName(), c);
            c.setVisible(true);
        }
        
    }

    @Override
    public void updateLaunguage(Language l) {
        this.language = l;
    }

}
