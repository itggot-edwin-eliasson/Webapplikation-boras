/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.model;

import java.io.Serializable;
import com.edwine.model.dao.FavoritesDAO;
import com.edwine.model.dao.AccountDAO;
import com.edwine.model.dao.FilmDAO;
import com.edwine.model.entity.Film;
import com.edwine.model.entity.Favorites;
import com.edwine.model.entity.Account;
import java.util.HashSet;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;
import omdb.model.SearchObject;

@Data
@Named
@ViewScoped
public class FavoritesBean implements Serializable {

    @EJB
    private FavoritesDAO favDAO;

    @EJB
    private AccountDAO accDAO;

    @EJB
    private FilmDAO filmDAO;

    public void addFavorite(SearchObject film, String username) {
        if (username != null) {
            Account acc = accDAO.getAccountMatchingUsername(username);

            if (acc == null) {
                System.out.println("ERROR: Could not find logged in account!");
            } else {
                Film f = filmDAO.findFilmsMatchingTitle(film.getTitle()).get(0);
                System.out.println("SUCCESS: " + f.getTitle() + " added as favorite for user " + acc.getUsername());
                favDAO.add(acc, f, 0);
            }
        } else {
            System.out.println("ERROR: No logged in user, can not add favorite!");
        }

        //System.out.println("Favorites: " + favDAO.getAccountsWhoFavoritedFilm(f).get(0).getAccount());
    }
}
