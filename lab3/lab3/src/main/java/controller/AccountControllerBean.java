/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.edwine.model.AccountViewBean;
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
import javax.enterprise.context.RequestScoped;
import javax.faces.FacesException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;
import view.AccountBackingBean;

@Data
@Named
@RequestScoped
public class AccountControllerBean implements Serializable {

    @EJB
    private AccountDAO accDAO;
    
    @Inject
    private AccountViewBean accViewBean;
    
    @Inject
    private AccountBackingBean accBackingBean;

    public String login() throws NoSuchAlgorithmException {
        String hashedPassword = null;
        
        Account foundAccount = accDAO.getAccountMatchingUsername(accBackingBean.getUsername());

        if (foundAccount == null) {
            System.out.println("ERROR AccountBean: foundAccount == null, could not get an account macthing the username!");
            return null;
        }
        
        try {
            hashedPassword = createHash(accBackingBean.getPassword(), Base64.getDecoder().decode(foundAccount.getSalt()));
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AccountControllerBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (hashedPassword != null && hashedPassword.equals(foundAccount.getPassword())) {
            System.out.println("LOGIN SUCCESS!");
            accViewBean.setLoggedInUser(accBackingBean.getUsername());
            return "browse.xhtml";
        } else {
            System.out.println("LOGIN FAILED, USERNAME OR PASSWORD IS INCORRECT!");
            return null;
        }
    }

    public boolean register() throws NoSuchAlgorithmException {

        byte[] salt = createSalt();
        String hashedPassword = null;
        try {
            hashedPassword = createHash(accBackingBean.getPassword(), salt);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AccountControllerBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (hashedPassword == null) {
            System.out.println("PASSWORD COULD NOT BE HASHED, TRY AGAIN!");
            return false;
        }

        String stringSalt = Base64.getEncoder().encodeToString(salt);
        
        //Do this to decode the string in password and salt to a byte array
        //byte[] salt2 = Base64.getDecoder().decode(stringSalt);
                
        accDAO.create(new Account(accBackingBean.getUsername(), hashedPassword, stringSalt, new HashSet<Favorites>()));
        System.out.println("SUCCESS: Account '" + accBackingBean.getUsername() + "' should have been created!");
        return true;
    }
    
    public void logout() {
        if (accViewBean.isLoggedIn()) {
            accViewBean.setLoggedInUser(null);
            System.out.println("SUCCESS: User logged out!");
            //return true;
        } else {
            System.out.println("ERROR: No user logged in, can not logout!");
            //return false;
        }
    }

    private String createHash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(spec).getEncoded();

        
        String stringHash = Base64.getEncoder().encodeToString(hash);
        //Arrays.toString(hash)
        return stringHash;
    }

    private byte[] createSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }
    
    private void updateFirstname() {
        accDAO.updateFirstname(accDAO.getAccountMatchingUsername(accViewBean.getLoggedInUser()), accBackingBean.getFirstName());
    }
    
    private void updateLastname() {
        accDAO.updateLastname(accDAO.getAccountMatchingUsername(accViewBean.getLoggedInUser()), accBackingBean.getLastName());
    }
    
    private void updateEmail() {
        accDAO.updateEmail(accDAO.getAccountMatchingUsername(accViewBean.getLoggedInUser()), accBackingBean.getEmail());
    }
    
    private void updateAvatarUrl() {
        accDAO.updateAvatarUrl(accDAO.getAccountMatchingUsername(accViewBean.getLoggedInUser()), accBackingBean.getAvatarUrl());
    }
    
    public void updateProfile() {
        updateFirstname();
        updateLastname();
        updateEmail();
        updateAvatarUrl();
        
        accBackingBean.setInUpdate(false);
    }
}
