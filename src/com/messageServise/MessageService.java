/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messageServise;

import com.messageServise.interfaces.IMessageService;

import javax.swing.JOptionPane;

/**
 *
 * @author ALEX
 */
public class MessageService implements IMessageService{

    @Override
    public void showError(String message, String title) {
        JOptionPane.showMessageDialog(null,
                                      message,
                                      title,
                                      JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showInformation(String message, String title) {
        JOptionPane.showMessageDialog(null,
                                      message,
                                      title,
                                      JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showWarning(String message, String title) {
        JOptionPane.showMessageDialog(null,
                                      message,
                                      title,
                                      JOptionPane.WARNING_MESSAGE);
        
    }
   

    
}
