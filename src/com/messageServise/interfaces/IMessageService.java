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
    void ShowError(String message, String title);
    void ShowInformation(String message, String title);
    void ShowWarning(String message, String title);

}
