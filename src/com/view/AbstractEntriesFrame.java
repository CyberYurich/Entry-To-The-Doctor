/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.model.EntriesTableModel;
import com.model.interfaces.IEntry;
import com.view.interfaces.IEntriesView;
import java.awt.Component;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author AlexeiArtemov
 */
public abstract class AbstractEntriesFrame extends AbstractChildFrame implements IEntriesView {

    protected EntriesTableModel tableModel = new EntriesTableModel();

    @Override
    public void setDataTable(List<IEntry> entriesList) {
        tableModel.setData(entriesList);
        tableModel.fireTableDataChanged();
    }

    protected DefaultTableCellRenderer getCenterCellRender() {
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                super.setHorizontalAlignment(SwingConstants.CENTER);
                super.getTableCellRendererComponent(table,
                        value,
                        isSelected,
                        hasFocus,
                        row,
                        column);
                return this;
            }
        };
        return centerRender;
    }
}
