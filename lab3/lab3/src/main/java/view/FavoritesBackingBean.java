/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import lombok.Data;
import omdb.model.SearchObject;
import com.edwine.model.AccountViewBean;
import com.edwine.model.entity.Favorites;
import com.edwine.model.entity.Account;
import com.edwine.model.dao.FavoritesDAO;
import com.edwine.model.dao.AccountDAO;
import java.util.List;
import org.omnifaces.cdi.ViewScoped;

@Data
@Named
@ViewScoped
public class FavoritesBackingBean implements Serializable {
    
    private SearchObject film;
    
    @EJB
    private AccountViewBean account;
    
    @EJB
    private FavoritesDAO favDAO;
    
    @EJB
    private AccountDAO accDAO;
    
    
    
    public List<Favorites> getFavorites(){
        Account acc = accDAO.getAccountMatchingUsername(account.getLoggedInUser());
        
        return favDAO.getFilmsThatAccountFavorited(acc);
    }
    
}
