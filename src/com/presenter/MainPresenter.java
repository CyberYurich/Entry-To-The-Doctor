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
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author ALEX
 */
public class MainPresenter implements IPresenter{

    private final IDbModel dbModel;
    private final ICalendarView calendarView;
    private final IEntriesView entriesView;
    private final IConnectView connectView;
    private final IMessageService messageService;
    
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
            
            Vector<Vector<Object>> dataTable = dbModel.getDataTableByDate(date); 
            
            entriesView.setDataTable(dataTable);
            entriesView.setCurentDateAndTime(date);
            
            calendarView.closeView();
            entriesView.showView();
            
        } catch (WrongDayOfWeekException ex) {
            messageService.ShowError("Выбран неправильный день недели.\n"
                                   + "Дни недели для приема: "
                                   + "понедельник, среда и Суббота.",
                                     "Неправильный день недели");
        } catch (SQLException ex) {
            messageService.ShowError("Соединение с базой данных утеряно\n"
                                   + "или отсутствует таблица с данными.",
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
    
}
