/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view.interfaces;

import java.util.Date;
import javax.swing.table.TableModel;

/**
 *
 * @author ALEX
 */
public interface IEntriesView extends IView{
    void setCurentDate(Date date);
    
    TableModel getTableModel();
}
