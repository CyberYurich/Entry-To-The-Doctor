/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.model.interfaces.IEntry;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author ALEX
 */
public class Entry implements IEntry, Comparable<Entry> {

    private int id;
    private Date date;
    private Time time;
    private String lastname;
    private String firstname;
    private String middlename;
    private String phone;
    private String email;
    private String shoeSize;
    private String productModel;

    public Entry() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Time getTime() {
        return time;
    }

    @Override
    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String getLastname() {
        return lastname;
    }

    @Override
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String getFirstname() {
        return firstname;
    }

    @Override
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String getMiddlename() {
        return middlename;
    }

    @Override
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getShoeSize() {
        return shoeSize;
    }

    @Override
    public void setShoeSize(String shoeSize) {
        this.shoeSize = shoeSize;
    }

    @Override
    public String getProductModel() {
        return productModel;
    }

    @Override
    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    @Override
    public int compareTo(Entry o) {
        int result = this.date.compareTo(o.date);
        if (result != 0) {
            return result;
        }
        return this.time.compareTo(o.time);
    }
}
