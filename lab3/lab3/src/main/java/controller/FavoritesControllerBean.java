/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.edwine.model.dao.AccountDAO;
import com.edwine.model.dao.FavoritesDAO;
import com.edwine.model.dao.FilmDAO;
import com.edwine.model.entity.Account;
import com.edwine.model.AccountViewBean;
import com.edwine.model.entity.Favorites;
import com.edwine.model.entity.Film;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import omdb.model.SearchObject;
import view.FavoritesBackingBean;

/**
 *
 * @author edwin
 */
@Named
@RequestScoped
public class FavoritesControllerBean implements Serializable{
    @EJB
    private FavoritesDAO favDAO;

    @EJB
    private AccountDAO accDAO;

    @EJB
    private FilmDAO filmDAO;
    
    @EJB
    private AccountViewBean account;
   
    @Inject
    private FavoritesBackingBean favoritesBackingBean;

    public void addFavorite() {
        if (account.getLoggedInUser() != null) {
            Account acc = accDAO.getAccountMatchingUsername(account.getLoggedInUser());

            if (acc == null) {
                System.out.println("ERROR: Could not find logged in account!");
            } else {
                Film f = filmDAO.findFilmsMatchingTitle(favoritesBackingBean.getFilm().getTitle()).get(0);
                System.out.println("SUCCESS: " + f.getTitle() + " added as favorite for user " + acc.getUsername());
                favDAO.add(acc, f, 0);
            }
        } else {
            System.out.println("ERROR: No logged in user, can not add favorite!");
        }

        //System.out.println("Favorites: " + favDAO.getAccountsWhoFavoritedFilm(f).get(0).getAccount());
    }
    
    public void removeFavorite() {
        if (account.getLoggedInUser() != null) {
            Account acc = accDAO.getAccountMatchingUsername(account.getLoggedInUser());

            if (acc == null) {
                System.out.println("ERROR: Could not find logged in account!");
            } else {
                Film f = filmDAO.findFilmsMatchingTitle(favoritesBackingBean.getFilm().getTitle()).get(0);
                Favorites favorite = favDAO.getFavourite(acc, f);
                favDAO.remove(favorite);
                System.out.println("SUCCESS: " + f.getTitle() + " removed as favorite for user " + acc.getUsername());
            }
        } else {
            System.out.println("ERROR: No logged in user, can not remove favorite!");
        }

        //System.out.println("Favorites: " + favDAO.getAccountsWhoFavoritedFilm(f).get(0).getAccount());
    }
}
