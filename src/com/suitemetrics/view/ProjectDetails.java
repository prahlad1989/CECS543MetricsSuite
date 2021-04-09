/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suitemetrics.view;

import com.suitemetrics.model.Language;
import java.awt.Component;
import java.util.List;
import java.io.Serializable;
import javax.swing.JTabbedPane;
import javax.swing.JTree;

/**
 *
 * @author 
 */
public class ProjectDetails implements Serializable{
    public Component[] panels;
    public JTree jTree; 
    String projectName;
    Language language;
    
    public ProjectDetails(String projectName){
        this.projectName = projectName;
    }
}
