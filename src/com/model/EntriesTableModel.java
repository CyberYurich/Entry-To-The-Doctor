/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.model.interfaces.IEntry;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author AlexeiArtemov
 */
public class EntriesTableModel extends AbstractTableModel {

    private List<IEntry> data;
    private final List<String> columnNames;
    private final List<Integer> visibleColumns;

    public EntriesTableModel() {

        data = new ArrayList<>();

        columnNames = new ArrayList<>();
        columnNames.add("id");
        columnNames.add("Дата приема");
        columnNames.add("Время приема");
        columnNames.add("Фамилия");
        columnNames.add("Имя");
        columnNames.add("Отчество");
        columnNames.add("Телефон");
        columnNames.add("E-mail");
        columnNames.add("Размер обуви");
        columnNames.add("Модель стелек");

        visibleColumns = new ArrayList<>();
        for (int i = 0; i < columnNames.size(); ++i) {
            visibleColumns.add(i);
        }
    }

    public void setData(List<IEntry> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return visibleColumns.size();
    }

    @Override
    public String getColumnName(int column) {
        int trueColumn = visibleColumns.get(column);
        return columnNames.get(trueColumn);
    }

    @Override
    public Object getValueAt(int row, int column) {
        int trueColumn = visibleColumns.get(column);

        switch (trueColumn) {
            case 0:
                return data.get(row).getId();
            case 1:
                return data.get(row).getDate();
            case 2:
                return data.get(row).getTime();
            case 3:
                return data.get(row).getLastname();
            case 4:
                return data.get(row).getFirstname();
            case 5:
                return data.get(row).getMiddlename();
            case 6:
                return data.get(row).getPhone();
            case 7:
                return data.get(row).getEmail();
            case 8:
                return data.get(row).getShoeSize();
            case 9:
                return data.get(row).getProductModel();
            default:
                return null;
        }
    }

    public void hideColumn(int column) {
        int index = visibleColumns.indexOf(column);
        if (index != -1) {
            visibleColumns.remove(index);
        }
        fireTableStructureChanged();
    }

}
