/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suitemetrics.controller;

import com.suitemetrics.view.NewProjectDialog;
import com.suitemetrics.view.ProjectDetailsPanel;

/**
 *
 * @author prahlad
 */
public class ProjectController implements Controller1 {
    
    public NewProjectDialog newProjectDialog;
    public ProjectDetailsPanel projectDetailsPanel;
    
    @Override
    public void createNewProject(String projectName) {
        projectDetailsPanel.createNewProject(projectName);
    }
    
    
}
