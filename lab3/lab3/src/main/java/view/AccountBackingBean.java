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
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import com.edwine.model.dao.AccountDAO;
import com.edwine.model.entity.Account;

/**
 *
 * @author edwin
 */
@Named
@Data
@ViewScoped
public class AccountBackingBean implements Serializable {
    
    private String username;
    private String password;
    
    @Email private String email;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private Boolean inUpdate = false;
    
    @Inject
    private AccountViewBean accViewBean;
    
    @EJB
    private AccountDAO accDAO;
    
    @PostConstruct
    private void init(){
        if(accViewBean.getLoggedInUser() != null){
            Account acc = accDAO.getAccountMatchingUsername(accViewBean.getLoggedInUser());
            this.email = acc.getEmail();
            this.firstName = acc.getFirstName();
            this.lastName = acc.getLastName();
            this.avatarUrl = acc.getAvatarUrl();
        }
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
    
    public String getAvatarWithPlaceholder() {
        if (this.avatarUrl == null)
            return "https://indol.se/wp-content/uploads/2017/04/profile-placeholder.png";
        else
            return this.avatarUrl;
    }
}
