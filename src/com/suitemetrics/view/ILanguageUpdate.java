/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suitemetrics.view;

import com.suitemetrics.model.Language;
import java.io.Serializable;

/**
 *
 * @author 
 */
public interface ILanguageUpdate extends Serializable{
    public void updateLaunguage(Language s);
}
