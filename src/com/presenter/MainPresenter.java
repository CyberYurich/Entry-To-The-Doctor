/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presenter;

import com.exceptions.WrongDayOfWeekException;
import com.messageServise.interfaces.IMessageService;
import com.model.interfaces.IDbModel;
import com.presenter.interfaces.IPresenter;
import com.view.interfaces.ICalendarView;
import com.view.interfaces.IConnectView;
import com.view.interfaces.IEntriesView;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ALEX
 */
public class MainPresenter implements IPresenter{
    
    private static final int ONE_MINUTE_IN_MILLISECS = 60000;
    
    private final IDbModel dbModel;
    private final ICalendarView calendarView;
    private final IEntriesView entriesView;
    private final IConnectView connectView;
    private final IMessageService messageService;
    
    @Override
    public IDbModel getDbModel() {
        return dbModel;
    }

    @Override
    public ICalendarView getCalendarView() {
        return calendarView;
    }

    @Override
    public IEntriesView getEntriesView() {
        return entriesView;
    }
    
    @Override
    public IConnectView getConnectView() {
        return connectView;
    }
    
    @Override
    public IMessageService getMessageService() {
        return messageService;
    }

    public MainPresenter(IDbModel dbModel,
                         ICalendarView calendarView, 
                         IEntriesView entriesView,
                         IConnectView connectView,
                         IMessageService messageService) {
        this.dbModel = dbModel;
        this.calendarView = calendarView;
        this.entriesView = entriesView;
        this.connectView = connectView;
        this.messageService = messageService;
        
        calendarView.setPresenter(this);
        connectView.setPresenter(this);
        entriesView.setPresenter(this);
    }
    
    @Override
    public void showEntries() {
        try {
            Date date = calendarView.getCheckedDate();           
            entriesView.setCurentDate(date);
            
            Vector<Vector<Object>> dataTable = dbModel.getDataTableByDate(date); 
            DefaultTableModel defTableModel = (DefaultTableModel)entriesView.getTableModel();
            clearTableModel(defTableModel);
            addTableModelData(defTableModel, dataTable);
            addTableModelTime(defTableModel, date);

            calendarView.closeView();
            entriesView.showView();
            
        } catch (WrongDayOfWeekException ex) {
            messageService.ShowError("Выбран неправильный день недели.\n"
                                   + "Дни недели для приема: "
                                   + "понедельник, среда и Суббота.",
                                     "Неправильный день недели");
        } catch (SQLException ex) {
            messageService.ShowError("Соединение с базой данных утеряно.",
                                     "Ошибка подключения");
            
            calendarView.lockEntriesView();
        }
    }

    @Override
    public void showCalendar() {
        entriesView.closeView();
        connectView.closeView();
        calendarView.showView();
    }
    
        @Override
    public void showConnect() {
        calendarView.closeView();
        connectView.showView();
    }
    
        @Override
    public void createConnection() {
        
        try {
            dbModel.setConnectionParameters(connectView.getConnectParameters());
            messageService.ShowInformation("Соединение с базой данных установлено.",
                                           "Успешное подключение");
            calendarView.unlockEntriesView();
            showCalendar();
        } catch (SQLException ex) {
            messageService.ShowError("Невозможно подключиться к базе данных.",
                                     "Ошибка подключения");
        }
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
    
    private void addTableModelTime(DefaultTableModel model,
                                   Date date) {        
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        
        try {
            // create time and format
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date timeForRows;
            
            if (dayOfWeek == Calendar.SATURDAY) {
                // set start time on 13:00
                timeForRows = timeFormat.parse("13:00");
            } else {
                // set start time on 16:00
                timeForRows = timeFormat.parse("16:00");
            }
            
            // insert time in each row and increments by 30 minutes
            for (int i = 0; i < model.getRowCount(); ++i) {        
                model.setValueAt(timeFormat.format(timeForRows), i, 0);
                
                long t = timeForRows.getTime();
                timeForRows = new Date(t + (30 * ONE_MINUTE_IN_MILLISECS));
            }
        } catch (ParseException ex) {
                Logger.getLogger(MainPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
}
