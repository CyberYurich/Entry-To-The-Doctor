/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.model.interfaces.IDbModel;
import com.model.interfaces.IEntry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author ALEX
 */
public class MySqlDbModel implements IDbModel {

    private static final String DB_NAME = "entry_to_the_doctor";
    private static final String PATIENTS_TABLE_NAME = "patients_entries";

    private String url = "";
    private String username = "";
    private String password = "";

    @Override
    public void setConnectionParameters(List<String> parameters)
            throws SQLException {
        String tempUrl = new StringBuilder(60)
                .append("jdbc:mysql://")
                .append(parameters.get(0))
                .append(":")
                .append(parameters.get(1))
                .append("/")
                .append(DB_NAME)
                .toString();
        String tempUsername = parameters.get(2);
        String tempPassword = parameters.get(3);

        try (Connection connection
                = DriverManager.getConnection(tempUrl, tempUsername, tempPassword);) {

            this.url = tempUrl;
            this.username = tempUsername;
            this.password = tempPassword;

        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void create(IEntry entry)
            throws SQLException, ClassNotFoundException {

        String query = new StringBuilder(200)
                .append("insert into ")
                .append(PATIENTS_TABLE_NAME)
                .append(" (date,time,lastname,firstname,middlename,phone,email,shoe_size,product_model)")
                .append(" values (?,?,?,?,?,?,?,?,?)")
                .toString();

        java.sql.Date sqlDate = new java.sql.Date(entry.getDate().getTime());
        java.sql.Time sqlTime = new java.sql.Time(entry.getTime().getTime());

        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setDate(1, sqlDate);
            statement.setTime(2, sqlTime);
            statement.setString(3, entry.getLastname());
            statement.setString(4, entry.getFirstname());
            statement.setString(5, entry.getMiddlename());
            statement.setString(6, entry.getPhone());
            statement.setString(7, entry.getEmail());
            statement.setString(8, entry.getShoeSize());
            statement.setString(9, entry.getProductModel());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        }
    }

    @Override
    public IEntry readById(int id) throws SQLException, ClassNotFoundException {

        String query = new StringBuilder(60)
                .append("select * from ")
                .append(PATIENTS_TABLE_NAME)
                .append(" where id = ?")
                .toString();

        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery();) {
                resultSet.next();
                return makeEntry(resultSet);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        }
    }

    @Override
    public List<IEntry> readByDate(Date date) throws SQLException, ClassNotFoundException {

        String query = new StringBuilder(60)
                .append("select * from ")
                .append(PATIENTS_TABLE_NAME)
                .append(" where date = ?")
                .toString();

        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setDate(1, sqlDate);
            try (ResultSet resultSet = statement.executeQuery();) {
                return makeEntriesList(resultSet);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        }
    }

    @Override
    public List<IEntry> readAll() throws SQLException, ClassNotFoundException {

        String query = new StringBuilder(60)
                .append("select * from ")
                .append(PATIENTS_TABLE_NAME)
                .toString();

        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();) {
            return makeEntriesList(resultSet);
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        }
    }

    @Override
    public void update(IEntry entry) {
        //TODO
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {

        String query = new StringBuilder(60)
                .append("delete from ")
                .append(PATIENTS_TABLE_NAME)
                .append(" where id = ?")
                .toString();

        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        }
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "UTF-8");
        return DriverManager.getConnection(url, properties);
    }

    private IEntry makeEntry(ResultSet resultSet) throws SQLException {
        IEntry entry = new Entry();
        entry.setId(resultSet.getInt(1));
        entry.setDate(resultSet.getDate(2));
        entry.setTime(resultSet.getTime(3));
        entry.setLastname(resultSet.getString(4));
        entry.setFirstname(resultSet.getString(5));
        entry.setMiddlename(resultSet.getString(6));
        entry.setPhone(resultSet.getString(7));
        entry.setEmail(resultSet.getString(8));
        entry.setShoeSize(resultSet.getString(9));
        entry.setProductModel(resultSet.getString(10));

        return entry;
    }

    private List<IEntry> makeEntriesList(ResultSet resultSet) throws SQLException {
        List<IEntry> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(makeEntry(resultSet));
        }
        return list;
    }
}
