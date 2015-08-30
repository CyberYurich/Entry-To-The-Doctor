/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author ALEX
 */
public interface IDbModel {  
    Connection getConnection();  
    void setConnectionParameters(Vector<String> parameters) throws SQLException;   
    Vector<Vector<Object>> getDataTableByDate(java.util.Date date) throws SQLException;
}
