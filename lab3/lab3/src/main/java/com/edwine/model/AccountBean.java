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
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.EJB;
import javax.faces.FacesException;
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

    public boolean login() throws NoSuchAlgorithmException {
        String hashedPassword = null;
        
        Account foundAccount = accDAO.getAccountMatchingUsername(username);

        if (foundAccount == null) {
            System.out.println("ERROR AccountBean: foundAccount == null, could not get an account macthing the username!");
            return false;
        }
        
        try {
            hashedPassword = createHash(password, foundAccount.getSalt().getBytes());
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AccountBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (hashedPassword != null && hashedPassword.equals(foundAccount.getPassword())) {
            System.out.println("LOGIN SUCCESS!");
            return true;
        } else {
            System.out.println("LOGIN FAILED, USERNAME OR PASSWORD IS INCORRECT!");
            return false;
        }
    }

    public boolean register() throws NoSuchAlgorithmException {

        byte[] salt = createSalt();
        String hashedPassword = null;
        try {
            hashedPassword = createHash(password, salt);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AccountBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (hashedPassword == null) {
            System.out.println("PASSWORD COULD NOT BE HASHED, TRY AGAIN!");
            return false;
        }

        String stringSalt = Arrays.toString(salt);

        accDAO.create(new Account(username, hashedPassword, stringSalt, new HashSet<Favorites>()));
        System.out.println("SUCCESS: Account '" + username + "' should have been created!");
        return true;
    }

    private String createHash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(spec).getEncoded();

        return Arrays.toString(hash);
    }

    private byte[] createSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }
}
