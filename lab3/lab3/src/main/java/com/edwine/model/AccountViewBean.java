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
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;

@Data
@Named
@SessionScoped
public class AccountViewBean implements Serializable {
    private String loggedInUser = null;
    
    public boolean isLoggedIn() {
        if (loggedInUser == null) {
            return false;
        } else {
            return true;
        }
    }
}
