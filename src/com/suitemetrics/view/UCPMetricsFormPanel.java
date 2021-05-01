/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suitemetrics.view;

import com.suitemetrics.model.Language;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author prahlad
 */
public class UCPMetricsFormPanel extends FPMetricsFormPanel {
        public UCPMetricsFormPanel( Language language, LanguagePreferencesDialog languagesDialog) {
        super(language, languagesDialog);
        
        
        }
        
    protected JTextField tcfComputedTextField;
    protected JButton computeTCFButton;
    protected String tcfDialogueName = "TCF Factors";
    
    private JTextField ecfComputedTextField;
    private JButton computeECFButton;
    private String ecfDialoguename = "ECF Factors";
    private ValueAdjustmentsDialogue tcfDialogue;
    private ValueAdjustmentsDialogue ecfDialogue;
    List<JTextField> uucfTextFields ; 
    List<JTextField>    uawTextFields ;
    protected JButton computeTotalUCFButton;
    private JTextField totalUCFComputedTextField;
    
 public void initialize() {

        headerLabel = new JLabel("UCP Metrics");

        totalCountLabel = new JLabel("Total Count");
        currentLanguageLabel = new JLabel("Current Language");
        totalCountTextField = new JTextField(5);
        totalCountTextField.setEditable(false);
        totalCountTextField.setText("0");
        computeTCFButton = new JButton("Compute TCF");
        computeECFButton = new JButton("Compute ECF");
        computeTotalUCFButton = new JButton("Compute Total UCF");
        tcfComputedTextField = new JTextField(5);
        tcfComputedTextField.setEditable(false);
        
        ecfComputedTextField = new JTextField(5);
        ecfComputedTextField.setEditable(false);
        
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
        totalUCFComputedTextField = new JTextField(10);
        totalUCFComputedTextField.setEditable(false);


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
 
 
    public void layoutComponents(){
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
        gbc.gridx = 1;
        gbc.gridwidth = 2;
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
       
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(computeTCFButton, gbc);
        gbc.gridx += 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(tcfComputedTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy += 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(computeECFButton, gbc);
        gbc.gridx += 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(ecfComputedTextField, gbc);

        
        gbc.gridx=0;
        gbc.gridy+=3;
        gbc.anchor= GridBagConstraints.LINE_START;
        add(new JLabel("UUCW:"),gbc);
        
        gbc.gridy+=3;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        simpleFactorLabel.setFont(new Font(simpleFactorLabel.getFont().getFontName(), Font.BOLD, 18));
        add(simpleFactorLabel, gbc);
////
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        avgFactorLabel.setFont(new Font(simpleFactorLabel.getFont().getFontName(), Font.BOLD, 18));
        add(avgFactorLabel, gbc);
////
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        complextFactorLabel.setFont(new Font(simpleFactorLabel.getFont().getFontName(), Font.BOLD, 18));
        add(complextFactorLabel, gbc);
        
     
        gbc.gridy+=3;
        gbc.gridx = 1;
        uucfTextFields = new ArrayList<JTextField>();
        for(int i=0;i<3;i++){
            JTextField jt = new JTextField(5);
            jt.setText("0");
            uucfTextFields.add(jt);
            add(jt, gbc);
            gbc.gridx++;
        }
        
         gbc.gridx=0;
        gbc.gridy+=3;
        gbc.anchor= GridBagConstraints.LINE_START;
        add(new JLabel("UAW:"),gbc);
        
         gbc.gridy+=3;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel simpleFactorLabel2=new JLabel(simpleFactorLabel.getText());
        simpleFactorLabel2.setFont(new Font(simpleFactorLabel.getFont().getFontName(), Font.BOLD, 18));
        add(simpleFactorLabel2, gbc);
////
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel avgFactorLabel2=new JLabel(avgFactorLabel.getText());
        avgFactorLabel2.setFont(new Font(simpleFactorLabel.getFont().getFontName(), Font.BOLD, 18));
        add(avgFactorLabel2, gbc); 
////
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel complextFactorLabel2=new JLabel(complextFactorLabel.getText());
        complextFactorLabel2.setFont(new Font(simpleFactorLabel.getFont().getFontName(), Font.BOLD, 18));
        add(complextFactorLabel2, gbc);
        
        
                gbc.gridy+=3;
        gbc.gridx = 1;
        uawTextFields = new ArrayList<JTextField>();
        for(int i=0;i<3;i++){
            JTextField jt = new JTextField(5);
            jt.setText("0");
            uawTextFields.add(jt);
            add(jt, gbc);
            gbc.gridx++;
        }
        
        gbc.gridx = 0;
        gbc.gridy += 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(computeTotalUCFButton, gbc);
        gbc.gridx += 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(totalUCFComputedTextField, gbc);
        

    }
    
    
    public void initializeButtonActions(){
        computeTCFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tcfDialogue == null){
                    tcfDialogue = new TCFAdjustDialogue(tcfDialogueName,parent, UCPMetricsFormPanel.this);
                    tcfDialogue.layoutControls();
                }

                tcfDialogue.setVisible(true);
            }
        });
        
        computeECFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ecfDialogue == null){
                    ecfDialogue = new ECFAdjustDialogue(ecfDialoguename,parent, UCPMetricsFormPanel.this);
                    ecfDialogue.layoutControls();
                }
                    
                    
                ecfDialogue.setVisible(true);
            }

        });
        
        computeTotalUCFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalValue = 0f;
                String tcfComp =  tcfComputedTextField.getText();
                if(tcfComp != null && tcfComp.length() > 0){
                    totalValue+= Double.parseDouble(tcfComp);
                }
                String ecfComp =  ecfComputedTextField.getText();
                if(ecfComp != null && ecfComp.length() > 0){
                    totalValue+= Double.parseDouble(ecfComp);
                }
                int simpleMulitplier = 5;
                int multiplier = simpleMulitplier;
                for(JTextField jt: uucfTextFields){
                    totalValue += Double.parseDouble(jt.getText())*multiplier;
                    multiplier+=5;
                }
                
                //For Actor 
                simpleMulitplier = 1;
                multiplier = simpleMulitplier;
                for(JTextField jt: uawTextFields){
                    totalValue += Double.parseDouble(jt.getText())*multiplier;
                    multiplier+=1;
                }
                
                totalUCFComputedTextField.setText(totalValue+"");
                
            }
        });
        

    }
    
    public void updateVAF(Double i) {
       DecimalFormat df = new DecimalFormat("#.##");
        tcfComputedTextField.setText(df.format(i));
    }
    
    public void updateECF(Double i){
        DecimalFormat df = new DecimalFormat("#.##");
        ecfComputedTextField.setText(df.format(i));
    }
    
}
