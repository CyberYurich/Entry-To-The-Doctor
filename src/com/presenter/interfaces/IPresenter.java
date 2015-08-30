/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presenter.interfaces;

import com.messageServise.interfaces.IMessageService;
import com.model.interfaces.IDbModel;
import com.view.interfaces.ICalendarView;
import com.view.interfaces.IConnectView;
import com.view.interfaces.IEntriesView;

/**
 *
 * @author ALEX
 */
public interface IPresenter {
    IDbModel getDbModel();
    ICalendarView getCalendarView();
    IEntriesView getEntriesView();
    IConnectView getConnectView();
    IMessageService getMessageService();
    
    void showCalendar();
    void showEntries();
    void showConnect();   
    void createConnection();
}
