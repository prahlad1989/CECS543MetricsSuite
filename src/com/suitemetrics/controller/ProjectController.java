/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suitemetrics.controller;

import com.suitemetrics.view.FPMetricsFormPanel;
import com.suitemetrics.view.MainFrame;
import com.suitemetrics.view.NewProjectDialog;
import com.suitemetrics.view.ProjectDetailsPanel;
import java.io.Serializable;

/**
 *
 * @author prahlad
 */
public class ProjectController implements Controller1, Serializable {
    
    public NewProjectDialog newProjectDialog;
    public ProjectDetailsPanel projectDetailsPanel;
    public MainFrame mainFrame;

    public ProjectController(MainFrame aThis) {
        mainFrame = aThis;
    }
    
//    @Override
//    public void createNewProject(String projectName) {
//        projectDetailsPanel.createNewProject(projectName);
//    }
    
    @Override
    public void createNewMetric(String metricName){
        projectDetailsPanel.createNewMetric(metricName);
       
    }
    
    @Override
    public void createNewMetric(String metricName, FPMetricsFormPanel fpMetricsFormPanel) {
        projectDetailsPanel.createNewMetric(metricName);
    }
    
    
}
