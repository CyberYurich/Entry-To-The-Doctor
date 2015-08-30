/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.model.interfaces.IDbModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALEX
 */
public class MySqlDbModel implements IDbModel{
    
    private static final String DB_NAME = "entry_to_the_doctor";
    private static final String PATIENTS_TABLE_NAME = "patients_entries";
    private static final int TABLE_ROW_COUNT = 8;
    private static final String COLUMNS_FOR_QUERY = "place_in_queue, "
                                                  + "lastname, "
                                                  + "firstname, "
                                                  + "middlename, "
                                                  + "phone, "
                                                  + "email, "
                                                  + "shoe_size, "
                                                  + "product_model";
    
    private String url = "";
    private String username = "";
    private String password = "";
    
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;
    
    @Override
    public Connection getConnection() {
        return connection;
    }
    
    @Override
    public void setConnectionParameters(Vector<String> parameters)
            throws SQLException{
        String url = "jdbc:mysql://" + parameters.elementAt(0) +
                     ":" + parameters.elementAt(1) + "/" + DB_NAME;
        String username = parameters.elementAt(2);
        String password = parameters.elementAt(3);
        
        try {
            connection = DriverManager.getConnection(url, username, password);
            
            this.url = url;
            this.username = username;
            this.password = password;

        } catch (SQLException ex) {
            throw ex;
        } finally {
            try {
                if(connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySqlDbModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }

    @Override
    public Vector<Vector<Object>> getDataTableByDate(java.util.Date date)
            throws SQLException {
        Vector<Vector<Object>> dataTable = new Vector<>();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try {
            connection = DriverManager.getConnection(url, username, password);
            
            String query = "select " + COLUMNS_FOR_QUERY + 
                           " from "+ PATIENTS_TABLE_NAME +
                           " where date = ?";           
            statement = connection.prepareStatement(query);
            statement.setDate(1, sqlDate);
            
            resultSet = statement.executeQuery();
            
            dataTable = buildDataTable(resultSet);
            
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try {
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySqlDbModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dataTable;
    }
    
    private Vector<Vector<Object>> buildDataTable(ResultSet resultSet)
        throws SQLException { 
        
        ResultSetMetaData metaData = resultSet.getMetaData();   
        int columnCount = metaData.getColumnCount();
        
        /**
         * make map of db data for sorting by key(place_in_queue)
         */
        Map<Integer, Vector<Object>> dataMap = new HashMap<>(); 
        while (resultSet.next()) {
            Vector<Object> row = new Vector<>();
            for (int i = 1; i <= columnCount; ++i) {
                row.add(resultSet.getObject(i));
            }
            Integer placeInQueue = resultSet.getInt(1);
            dataMap.put(placeInQueue, row);
        }
        
        /**
         * make 2d vector with sorted by queue data
         */
        Vector<Vector<Object>> dataTable = new Vector<>();
        for (int i = 0; i < TABLE_ROW_COUNT; ++i) {
            if (dataMap.get(i) != null) {
                dataTable.add(dataMap.get(i));
            } else {
                dataTable.add(new Vector<>());
            }
        }        
        return dataTable;
    }    

}
