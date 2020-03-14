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
import com.edwine.model.entity.Film;
import com.edwine.model.dao.FavoritesDAO;
import com.edwine.model.dao.AccountDAO;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.omnifaces.cdi.ViewScoped;

@Data
@Named
@ViewScoped
public class FavoritesBackingBean implements Serializable {
    
    private SearchObject film;
    private String searchedUser;
    
    private String searchString;
    
    private List<Film> filmsFromSearchedUsersFavorites;
    private List<Favorites> favoritesFromSearchedUsersFavorites;
    
    @Inject
    private AccountViewBean account;
    
    @EJB
    private FavoritesDAO favDAO;
    
    @EJB
    private AccountDAO accDAO;
    
    public void favoritesSearchedUser(){
        Account acc = accDAO.getAccountMatchingUsernameLikeQuery(searchedUser);
        
        filmsFromSearchedUsersFavorites = favDAO.getFilmsThatAccountFavorited(acc);
        
    }
    
    public List<Film> getFavoriteFilms(){
        Account acc = accDAO.getAccountMatchingUsername(account.getLoggedInUser());
        
        return favDAO.getFilmsThatAccountFavorited(acc);
    }
    
    
    public boolean userLoggedInAndHasNotFavorited(Film film){
        Account acc = accDAO.getAccountMatchingUsername(account.getLoggedInUser());
        if (acc == null) return false;
        if (film == null) return false;
        Favorites fav = favDAO.getFavourite(acc, film);
        return fav == null;
    }
    
    public boolean userLoggedInAndHasFavorited(Film film){
        Account acc = accDAO.getAccountMatchingUsername(account.getLoggedInUser());
        if (acc == null) return false;
        if (film == null) return false;
        Favorites fav = favDAO.getFavourite(acc, film);
        return fav != null;
    }
    public void favoriteFilmsForSearchedUser() {
        favoritesSearchedUser();
        List<Film> films = new ArrayList();
        
        for(Favorites f : favoritesFromSearchedUsersFavorites){
            films.add(f.getFilm());
        }
        
        filmsFromSearchedUsersFavorites = films;
        
    }
    
}
