/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author ALEX
 */
public abstract class AbstractChildFrame extends AbstractFrame{
    
    void setChildCloseOperation() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                presenter.showCalendar();
            }
        });
    }
}
