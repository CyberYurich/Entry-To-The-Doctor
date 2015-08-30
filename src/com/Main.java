/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.messageServise.MessageService;
import com.model.MySqlDbModel;
import com.presenter.MainPresenter;
import com.view.CalendarFrame;
import com.view.ConnectFrame;
import com.view.EntriesFrame;

/**
 *
 * @author ALEX
 */
public class Main {
    
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MySqlDbModel mysqlModel = new MySqlDbModel();
                CalendarFrame calendarFrame = new CalendarFrame();
                EntriesFrame entriesFrame = new EntriesFrame();
                ConnectFrame connectFrame = new ConnectFrame();
                MessageService messageService = new MessageService();
        
                MainPresenter mainPresenter = new MainPresenter(mysqlModel,
                                                                calendarFrame,
                                                                entriesFrame,
                                                                connectFrame,
                                                                messageService);
                
                calendarFrame.setVisible(true);
            }
        });
    }
    
}
