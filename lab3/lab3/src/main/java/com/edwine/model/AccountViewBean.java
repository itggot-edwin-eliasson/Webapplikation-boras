/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.model;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Data;

@Data
@Named
@SessionScoped
public class AccountViewBean implements Serializable {

    private String loggedInUser = null;

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }
}
