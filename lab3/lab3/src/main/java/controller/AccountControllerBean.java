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
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import util.PasswordHasher;
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

    //Registers account. Uses hashes and salt for password
    public String onRegister() throws NoSuchAlgorithmException {

        String salt = PasswordHasher.createSalt();
        String hashedPassword = null;
        try {
            hashedPassword = PasswordHasher.createHashPassword(accBackingBean.getPassword(), salt);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AccountControllerBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (hashedPassword == null) {
            System.out.println("PASSWORD COULD NOT BE HASHED, TRY AGAIN!");
            return null;
        }
       
        Account acc = new Account(accBackingBean.getUsername(), hashedPassword, salt, new HashSet<Favorites>());

        if (accDAO.getAccountMatchingUsername(accBackingBean.getUsername()) == null) {
            accDAO.create(acc);
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Username already taken", "Please enter a new username"));
            return null;
        }

        accViewBean.setLoggedInUser(accBackingBean.getUsername());
        updateProfile();
        System.out.println("SUCCESS: Account '" + accBackingBean.getUsername() + "' have been created!");
        return "browse.xhtml";
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

    public void updateAvatarUrl() {
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
