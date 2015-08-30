/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view.interfaces;

import com.presenter.interfaces.IPresenter;

/**
 *
 * @author ALEX
 */
public interface IView {
    IPresenter getPresenter();
    void setPresenter(IPresenter presenter);
    
    void showView();
    void closeView();
}
