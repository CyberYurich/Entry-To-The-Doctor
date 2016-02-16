/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messageServise.interfaces;

/**
 *
 * @author ALEX
 */
public interface IMessageService {

    void showError(String message, String title);

    void showInformation(String message, String title);

    void showWarning(String message, String title);

    boolean showYesNoQuestion(String message, String title);

}
