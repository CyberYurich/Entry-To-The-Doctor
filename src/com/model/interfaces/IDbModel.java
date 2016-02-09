/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.interfaces;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ALEX
 */
public interface IDbModel {

    void setConnectionParameters(List<String> parameters) throws SQLException;

    void create(IEntry entry) throws SQLException, ClassNotFoundException;

    IEntry readById(int id) throws SQLException, ClassNotFoundException;

    List<IEntry> readByDate(Date date) throws SQLException, ClassNotFoundException;

    List<IEntry> readAll() throws SQLException, ClassNotFoundException;

    void update(IEntry entry) throws SQLException, ClassNotFoundException;

    void delete(int id) throws SQLException, ClassNotFoundException;
//    Vector<Vector<Object>> getDataTableByDate(java.util.Date date) throws SQLException;
}
