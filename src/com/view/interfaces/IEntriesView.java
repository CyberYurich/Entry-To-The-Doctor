/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view.interfaces;

import com.model.interfaces.IEntry;
import java.util.List;

/**
 *
 * @author AlexeiArtemov
 */
public interface IEntriesView extends IView {

    void setDataTable(List<IEntry> entriesList);
}
