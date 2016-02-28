/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view.interfaces;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 *
 * @author AlexeiArtemov
 */
public interface ICreateEntryView extends IView {

    void setDate(Date date);

    void setTimes(List<Time> timesList);
}
