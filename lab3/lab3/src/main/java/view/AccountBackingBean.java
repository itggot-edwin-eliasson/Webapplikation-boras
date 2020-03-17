/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.edwine.model.AccountViewBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Email;
import lombok.Data;
import com.edwine.model.dao.AccountDAO;
import com.edwine.model.entity.Account;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import util.PasswordHasher;

@Named
@Data
@ViewScoped
public class AccountBackingBean implements Serializable {

    private String username;
    private String password;

    @Email
    private String email;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private Boolean inUpdate = false;

    //Used as a small feature to add a avatar to userprofile
    private List<String> avatarArray = Arrays.asList(new String[] {
        "https://cdn0.iconfinder.com/data/icons/shift-avatar/32/Max-512.png",
        "https://cdn0.iconfinder.com/data/icons/shift-avatar/32/Sofia-512.png",
        "https://cdn0.iconfinder.com/data/icons/shift-avatar/32/Ms.Jenifer-512.png",
        "https://cdn0.iconfinder.com/data/icons/shift-avatar/32/Jonathan-512.png"
    });

    @Inject
    private AccountViewBean accViewBean;

    @EJB
    private AccountDAO accDAO;

    @PostConstruct
    private void init() {
        if (accViewBean.getLoggedInUser() != null) {
            Account acc = accDAO.getAccountMatchingUsername(accViewBean.getLoggedInUser());
            this.email = acc.getEmail();
            this.firstName = acc.getFirstName();
            this.lastName = acc.getLastName();
            this.avatarUrl = acc.getAvatarUrl();
        }
    }

    public String onLogin() throws NoSuchAlgorithmException {
        String hashedPassword = null;

        Account foundAccount = accDAO.getAccountMatchingUsername(username);

        if (foundAccount == null) {
            System.out.println("ERROR AccountBean: foundAccount == null, could not get an account macthing the username!");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect username or password", "Please enter corret username and password"));
            return null;
        }

        try {
            hashedPassword = PasswordHasher.createHashPassword(password, foundAccount.getSalt());
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AccountBackingBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (hashedPassword != null && hashedPassword.equals(foundAccount.getPassword())) {
            System.out.println("LOGIN SUCCESS!");
            accViewBean.setLoggedInUser(username);
            return "browse.xhtml";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect username or password", "Please enter corret username and password"));
            System.out.println("LOGIN FAILED, USERNAME OR PASSWORD IS INCORRECT!");
            return null;
        }
    }

    public void onLogout() {
        if (accViewBean.isLoggedIn()) {
            accViewBean.setLoggedInUser(null);
            System.out.println("SUCCESS: User logged out!");
    
        } else {
            System.out.println("ERROR: No user logged in, can not logout!");
        }
    }

    public String getAvatarWithPlaceholder() {
        if (this.avatarUrl == null) {
            return "https://indol.se/wp-content/uploads/2017/04/profile-placeholder.png";
        } else {
            return this.avatarUrl;
        }
    }
}
