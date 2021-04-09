/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suitemetrics.controller;

import com.suitemetrics.view.FPMetricsFormPanel;
import com.suitemetrics.view.ProjectDetailsPanel;

/**
 *
 * @author 
 */
public interface Controller1 {
    //public void createNewProject(String projectName);
    public void createNewMetric(String metricName);
    public void createNewMetric(String fpName, FPMetricsFormPanel fpMetricsFormPanel);
    
}
