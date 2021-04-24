package com.suitemetrics.view;

import com.suitemetrics.model.Language;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 */
public class FPMetricsFormPanel extends JPanel implements ILanguageUpdate, Serializable {

    private static final long serialVersionUID = -615865897345801346L;

    protected JLabel headerLabel;

    protected JLabel simpleFactorLabel=new JLabel("Simple");

    protected JLabel avgFactorLabel = new JLabel("Average");

    protected JLabel complextFactorLabel = new JLabel("Complex");
    
     

    protected JLabel totalCountLabel;

    protected JLabel currentLanguageLabel;

    protected Language language;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    protected transient LanguagePreferencesDialog languagesDialog;

    protected transient MainFrame parent;

    public void setParent(MainFrame parent) {
        this.parent = parent;
    }

    protected JTextField totalCountTextField;

    protected JButton computeFPButton;
    protected JTextField fpComputedTextField;

    protected JButton valueAdjustmentsButton;

    protected JButton computeCodeSizeButton;

    protected JButton changeLanguageButton;
    protected JTextField valueAdjustmentsTextField;
    protected JTextField currentLanguageTextField;
    protected JTextField computeCodeSizeTextField;
    protected List<MetricControl> metricControls;
    protected  ValueAdjustmentsDialogue vDialogue;
    protected String vDialogueName = "Value Adjustment Factors";

    class MetricControl implements Serializable {

        public MetricControl(String labelName, String simple, String average, String complex) {
            buttonGroup = new ButtonGroup();
            ActionListener totalCountActionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    updateTotalCount();
                }

            };
            String[] groupNames = {simple, average, complex};
            for (String s : groupNames) {
                JRadioButton radio = new JRadioButton(s);
                radio.addActionListener(totalCountActionListener);
                radio.setActionCommand(s);
                buttonGroup.add(radio);
                if (s.equals(average)) {
                    buttonGroup.setSelected(radio.getModel(), true);
                }

            }
            this.name = labelName;
            this.label = new JLabel(labelName);
            numberInputField = new JTextField(5);
            numberInputField.addActionListener(totalCountActionListener);
            calcJTextField = new JTextField(5);
            calcJTextField.setEditable(false);
        }

        String name;
        JTextField numberInputField;
        JTextField calcJTextField;
        ButtonGroup buttonGroup;
        JLabel label;
        String weigngingFactor;
    }

    public FPMetricsFormPanel( Language language, LanguagePreferencesDialog languagesDialog) {
        this.language = language;
        this.languagesDialog = languagesDialog;
        initialize();
    }
    
    public void initialize() {

        headerLabel = new JLabel("Weighting Factors");
        simpleFactorLabel = new JLabel("Simple");
        avgFactorLabel = new JLabel("Average");
        complextFactorLabel = new JLabel("Complex");
        totalCountLabel = new JLabel("Total Count");
        currentLanguageLabel = new JLabel("Current Language");
        totalCountTextField = new JTextField(5);
        totalCountTextField.setEditable(false);
        totalCountTextField.setText("0");
        computeFPButton = new JButton("Compute FP");
        fpComputedTextField = new JTextField(5);
        fpComputedTextField.setEditable(false);
        valueAdjustmentsButton = new JButton("Value Adjustments");
        valueAdjustmentsTextField = new JTextField();
        valueAdjustmentsTextField.setEditable(false);
        computeCodeSizeButton = new JButton("Compute Code Size");
        changeLanguageButton = new JButton("Change Language");
        String currentLanguage = language != null ? language.toString() : "None";
        currentLanguageTextField = new JTextField();
        currentLanguageTextField.setText(currentLanguage);
        currentLanguageTextField.setEditable(false);
        computeCodeSizeTextField = new JTextField();
        computeCodeSizeTextField.setEditable(false);

        MetricControl[] metricControlsTemp = {
            new MetricControl("External Inputs", "3", "4", "6"),
            new MetricControl("External Outputs", "4", "5", "7"),
            new MetricControl("External Enquiries", "3", "4", "6"),
            new MetricControl("Internal Logical Files", "7", "10", "15"),
            new MetricControl("External Interface Files", "5", "7", "10")
        };

        metricControls = new ArrayList<MetricControl>();
        for (MetricControl mc : metricControlsTemp) {
            metricControls.add(mc);
        }
        

        layoutComponents();
    }

    
    
    public void initializeButtonActions(){
    
    changeLanguageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languagesDialog.setPanelNeedLanguage(FPMetricsFormPanel.this);
                languagesDialog.setVisible(true);
            }

        });

        valueAdjustmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(vDialogue == null){
                    vDialogue = new ValueAdjustmentsDialogue(vDialogueName,parent, FPMetricsFormPanel.this);
                    
                    vDialogue.layoutControls();
                }
                    
                vDialogue.setVisible(true);
            }

        });
        
        

        computeFPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTotalCount();
                try {
                    int totalCount = Integer.parseInt(totalCountTextField.getText());
                    String temp = valueAdjustmentsTextField.getText();

                    int valueAdjs = 0;
                    if (temp != null && temp.trim().length() > 0) {
                        valueAdjs = Integer.parseInt(temp);
                    }
                    Double fpCalc = totalCount *( .65 +valueAdjs* .01);
                    fpCalc = Math.round(fpCalc * 100) / 100.0;
                    fpComputedTextField.setText(fpCalc.toString());

                } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                }
            }

        });
    }
    
    private void updateTotalCount() {
        Integer totalCount = 0;
        for (MetricControl mc : metricControls) {
            String input = mc.numberInputField.getText();
            try {
                Integer number = Integer.parseInt(input);
                String radioValue = mc.buttonGroup.getSelection().getActionCommand();
                Integer i = Integer.parseInt(radioValue);
                Integer calcInput = i * number;
                mc.calcJTextField.setText(calcInput.toString());
                totalCount += calcInput;
            } catch (NumberFormatException e) {
                //e.printStackTrace();
                mc.calcJTextField.setText("");
            }

        }

        totalCountTextField.setText(totalCount.toString());

    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
//		Insets rightPadding = new Insets(0, 0, 0, 5);
//		Insets noPadding = new Insets(0, 0, 0, 0);
//                Insets downPadding = new Insets(0, 0, 0, 2);
        Insets allPadding = new Insets(5, 5, 5, 5);
//		gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.insets = allPadding;
//                gbc.weighty = 0.5;
//
//		// First Row
        //gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 3;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        headerLabel.setFont(new Font(headerLabel.getFont().getFontName(), Font.BOLD, 24));
        add(headerLabel, gbc);

        //reset
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        // Second Row
        gbc.gridy += 2;
//
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        simpleFactorLabel.setFont(new Font(simpleFactorLabel.getFont().getFontName(), Font.BOLD, 18));
        add(simpleFactorLabel, gbc);
////
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        avgFactorLabel.setFont(new Font(simpleFactorLabel.getFont().getFontName(), Font.BOLD, 18));
        add(avgFactorLabel, gbc);
////
        gbc.gridx = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        complextFactorLabel.setFont(new Font(simpleFactorLabel.getFont().getFontName(), Font.BOLD, 18));
        add(complextFactorLabel, gbc);

        gbc.gridy += 2;

        //gbc.ipady = 40;
        gbc.gridx = 0;
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 5;
        for (MetricControl mc : metricControls) {
            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.LINE_START;
            add(mc.label, gbc);
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridx++;
            add(mc.numberInputField, gbc);
            gbc.gridx++;

            Enumeration<AbstractButton> elements = mc.buttonGroup.getElements();
            while (elements.hasMoreElements()) {
                add(elements.nextElement(), gbc);
                gbc.gridx++;

            }
            add(mc.calcJTextField, gbc);
            gbc.gridy = gbc.gridy + 1;

        }

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(totalCountLabel, gbc);
        gbc.gridx += 5;
        gbc.anchor = GridBagConstraints.CENTER;
        add(totalCountTextField, gbc);

        gbc.gridy += 3;

        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(computeFPButton, gbc);
        gbc.gridx += 5;
        gbc.anchor = GridBagConstraints.CENTER;
        add(fpComputedTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy += 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(valueAdjustmentsButton, gbc);
        gbc.gridx += 5;
        gbc.ipadx = 50;
        gbc.anchor = GridBagConstraints.CENTER;
        add(valueAdjustmentsTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy += 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(computeCodeSizeButton, gbc);
        gbc.gridx += 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(currentLanguageLabel, gbc);
        gbc.gridx++;
        gbc.ipadx = 50;
        add(currentLanguageTextField, gbc);
        gbc.gridx++;
        add(computeCodeSizeTextField, gbc);
        gbc.gridy += 2;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(changeLanguageButton, gbc);
    }

    @Override
    public void updateLaunguage(Language l) {
        this.language = l;
        String currentLanguage = language != null ? language.toString() : "None";
        currentLanguageTextField.setText(currentLanguage);
        languagesDialog.setPanelNeedLanguage(null);
    }

    public void updateVAF(Double i) {
        valueAdjustmentsTextField.setText(Math.round(i)+"");
    }

}
