/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.model;

import java.io.Serializable;
import com.edwine.model.dao.AccountDAO;
import com.edwine.model.entity.Account;
import com.edwine.model.entity.Favorites;
import java.util.HashSet;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;

@Data
@Named
@ViewScoped
public class AccountBean implements Serializable {
   
    @EJB
    private AccountDAO accDAO;
    
    private String username;
    private String password;
    
    public void login(String username, String password){
        if (password.equals(accDAO.getAccountMatchingUsername(username).getPassword())) {
            System.out.println("LOGIN SUCCESS!");
        } else {
            System.out.println("LOGIN FAILED, USERNAME OR PASSWORD IS INCORRECT!");
        }
    }
    
    public void register(String username, String password){
        accDAO.create(new Account(username, password, new HashSet<Favorites>()));
    }
}
