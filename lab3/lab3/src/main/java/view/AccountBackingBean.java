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
import java.util.Arrays;
import java.util.List;

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
    
    private List<String> avatarArray = Arrays.asList(new String[]
    {
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
    private void init(){
        if(accViewBean.getLoggedInUser() != null){
            Account acc = accDAO.getAccountMatchingUsername(accViewBean.getLoggedInUser());
            this.email = acc.getEmail();
            this.firstName = acc.getFirstName();
            this.lastName = acc.getLastName();
            this.avatarUrl = acc.getAvatarUrl();
        }
    }
    
    public void onLogout() {
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
