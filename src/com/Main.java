/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.messageServise.MessageService;
import com.messageServise.interfaces.IMessageService;
import com.model.MySqlDbModel;
import com.model.interfaces.IDbModel;
import com.presenter.MainPresenter;
import com.presenter.interfaces.IPresenter;
import com.view.AllEntriesFrame;
import com.view.CalendarFrame;
import com.view.ConnectFrame;
import com.view.DateEntriesFrame;
import com.view.interfaces.IAllEntriesView;
import com.view.interfaces.ICalendarView;
import com.view.interfaces.IConnectView;
import com.view.interfaces.IDateEntriesView;

/**
 *
 * @author ALEX
 */
public class Main {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                IDbModel dbModel = new MySqlDbModel();
                ICalendarView calendarView = new CalendarFrame();
                IDateEntriesView dateEntriesView = new DateEntriesFrame();
                IAllEntriesView allEntriesView = new AllEntriesFrame();
                IConnectView connectView = new ConnectFrame();
                IMessageService messageService = new MessageService();

                IPresenter presenter = new MainPresenter(
                        dbModel,
                        calendarView,
                        dateEntriesView,
                        allEntriesView,
                        connectView,
                        messageService);

                presenter.showCalendar();
            }
        });
    }

}
