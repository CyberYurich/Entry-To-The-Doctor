/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.view.interfaces.IEntriesView;
import java.awt.Component;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AlexeiArtemov
 */
public abstract class AbstractEntriesFrame extends AbstractChildFrame implements IEntriesView {

    protected DefaultTableModel tableModel;

    @Override
    public void setDataTable(Vector<Vector<Object>> dataTable) {
        clearTableModel(tableModel);
        addTableModelData(tableModel, dataTable);
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

    private void clearTableModel(DefaultTableModel model) {
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }

    private void addTableModelData(DefaultTableModel model,
            Vector<Vector<Object>> dataTable) {
        for (Vector<Object> row : dataTable) {
            model.addRow(row);
        }
    }
}
