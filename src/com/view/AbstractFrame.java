/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.presenter.interfaces.IPresenter;
import com.view.interfaces.IConnectView;
import com.view.interfaces.IView;

import javax.swing.JFrame;

/**
 *
 * @author ALEX
 */
public abstract class AbstractFrame extends JFrame implements IView {
    
    protected IPresenter presenter;

    @Override
    public IPresenter getPresenter() {
        return presenter;
    }
    
    @Override
    public void setPresenter(IPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showView() {
        setVisible(true);
    }

    @Override
    public void closeView() {
        setVisible(false);
    }
    
}
