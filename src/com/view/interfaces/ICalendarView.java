/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view.interfaces;

import com.exceptions.WrongDayOfWeekException;

import java.util.Date;

/**
 *
 * @author ALEX
 */
public interface ICalendarView extends IView{    
    Date getCheckedDate() throws WrongDayOfWeekException;
    void lockEntriesView();
    void unlockEntriesView();
}
