/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presenter.interfaces;

/**
 *
 * @author ALEX
 */
public interface IPresenter {

    void showDateEntries();

    void showAllEntries();

    void showCalendar();

    void showConnect();

    void createConnection();

    void exportToExcel();

    boolean deleteEntry(int id);
}
