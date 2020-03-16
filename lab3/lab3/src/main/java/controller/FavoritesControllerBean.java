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
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import view.FavoritesBackingBean;

@Named
@RequestScoped
public class FavoritesControllerBean implements Serializable {

    @EJB
    private FavoritesDAO favDAO;

    @EJB
    private AccountDAO accDAO;

    @EJB
    private FilmDAO filmDAO;

    @Inject
    private AccountViewBean account;

    @Inject
    private FavoritesBackingBean favorite;

    public void addEntireWatchListFromUser() {
        if (account.getLoggedInUser() != null) {
            Account acc = accDAO.getAccountMatchingUsernameLikeQuery(account.getLoggedInUser());

            if (acc == null) {
                System.out.println("ERROR: Could not find logged in account!");
            } else {
                List<Film> alreadyFavoritedFilms = favDAO.getFilmsThatAccountFavorited(acc);
                List<Film> userFavoriteFilms = favorite.getFilmsFromSearchedUsersFavorites();
                for (Film film : userFavoriteFilms) {
                    if (!alreadyFavoritedFilms.contains(film)) {
                        favDAO.create(new Favorites(acc, film, 0));
                        System.out.println("SUCCESS: " + film.getTitle() + " added as favorite for user " + acc.getUsername());
                    } else {
                        System.out.println("The user already has that film as a favorite");
                    }
                }
            }
        } else {
            System.out.println("ERROR: No logged in user, can not add favorite!");
        }
    }

    public void addFavorite(Film film) {
        if (account.getLoggedInUser() != null) {
            Account acc = accDAO.getAccountMatchingUsername(account.getLoggedInUser());

            if (acc == null) {
                System.out.println("ERROR: Could not find logged in account!");
            } else {
                System.out.println("SUCCESS: " + film.getTitle() + " added as favorite for user " + acc.getUsername());
                favDAO.create(new Favorites(acc, film, 0));
            }
        } else {
            System.out.println("ERROR: No logged in user, can not add favorite!");
        }

        //System.out.println("Favorites: " + favDAO.getAccountsWhoFavoritedFilm(f).get(0).getAccount());
    }

    public void removeFavorite(Film film) {
        if (account.getLoggedInUser() != null) {
            Account acc = accDAO.getAccountMatchingUsername(account.getLoggedInUser());

            if (acc == null) {
                System.out.println("ERROR: Could not find logged in account!");
            } else {
                Favorites favorite = favDAO.getFavourite(acc, film);
                favDAO.remove(favorite);
                System.out.println("SUCCESS: " + film.getTitle() + " removed as favorite for user " + acc.getUsername());
            }
        } else {
            System.out.println("ERROR: No logged in user, can not remove favorite!");
        }

        //System.out.println("Favorites: " + favDAO.getAccountsWhoFavoritedFilm(f).get(0).getAccount());
    }
}
