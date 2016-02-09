/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.interfaces;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author AlexeiArtemov
 */
public interface IEntry {

    public int getId();

    public void setId(int id);

    public Date getDate();

    public void setDate(Date date);

    public Time getTime();

    public void setTime(Time time);

    public String getLastname();

    public void setLastname(String lastname);

    public String getFirstname();

    public void setFirstname(String firstname);

    public String getMiddlename();

    public void setMiddlename(String middlename);

    public String getPhone();

    public void setPhone(String phone);

    public String getEmail();

    public void setEmail(String email);

    public String getShoeSize();

    public void setShoeSize(String shoeSize);

    public String getProductModel();

    public void setProductModel(String productModel);
}
