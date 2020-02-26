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
    
    public void login(){
        Account foundAccount = null;

        try {
            foundAccount = accDAO.getAccountMatchingUsername(username);
        } catch (NullPointerException e) {
            System.out.println("ERROR: There was no account matching the given username!");
        }


        if (foundAccount != null && password.equals(foundAccount.getPassword())) {
            System.out.println("LOGIN SUCCESS!");
        } else {
            System.out.println("LOGIN FAILED, USERNAME OR PASSWORD IS INCORRECT!");
        }
    }
    
    public void register() throws NoSuchAlgorithmException{

        byte[] salt = createSalt();
        String hashedPassword = null;
        try {
            hashedPassword = createHash(password, salt);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AccountBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(hashedPassword == null){
            System.out.println("PASSWORD COULD NOT BE HASHED, TRY AGAIN!");
            return;
        }

        String stringSalt = Arrays.toString(salt);

        accDAO.create(new Account(username, hashedPassword, stringSalt, new HashSet<Favorites>()));
    }

    private String createHash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException{

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(spec).getEncoded();

        return Arrays.toString(hash);
    }

    private byte[] createSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }
}
