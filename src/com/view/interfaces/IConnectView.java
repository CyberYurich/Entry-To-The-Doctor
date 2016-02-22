/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view.interfaces;

import java.util.List;

/**
 *
 * @author ALEX
 */
public interface IConnectView extends IView {

    List<String> getConnectParameters();

    void setConnectParameters(List<String> parametersList);

    boolean saveParametersChecked();

    void setSaveParametersCheckBox(boolean isSelected);
}
