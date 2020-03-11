/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.model.dao;

import com.edwine.model.entity.Account;
import com.edwine.model.entity.Favorites;
import com.edwine.model.entity.Film;
import com.edwine.model.entity.QFavorites_;
import easycriteria.JPAQuery;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

/**
 *
 * @author benja
 */
@Stateless
public class FavoritesDAO extends AbstractDAO<Favorites> {

    @Getter
    @PersistenceContext(unitName = "flicktier")
    private EntityManager entityManager;

    public FavoritesDAO() {
        super(Favorites.class);
    }

    public List<Account> findUsersMatchingName() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public void setScore(Favorites favorite, int score) {
        //QFavorites_ favorites = new QFavorites_();

        //List<Favorites> result = new JPAQuery(entityManager).select(favorites).where(favorites.account.username.like(favorite.getAccount().getUsername())).getResultList();
        //List<Favorites> result = new JPAQuery(entityManager).select(favorites).where(favorites.account.username.like(favorite.getAccount().getUsername()).and(favorites.film.));
        favorite.setScore(score);
        super.update(favorite);
    }

    /*public Favorites findFavoritesMatchingFilmAndAccount(Film film, Account account) {
        QFavorites_ favorites = new QFavorites_();
        
        List<Favorites> result = new JPAQuery(entityManager).select(favorites).where(favorites.account.username.like(account.getUsername()).and(favorites.film.id.eq(film.getId()))).getResultList();
        System.out.println(result.toString());
        
        return result.get(0);
        
        
        
        //hrow new UnsupportedOperationException("Not implemented yet!");
        
        return null;
    }*/
    public List<Account> getAccountsWhoFavoritedFilm(Film film) {
        QFavorites_ favorites = new QFavorites_();

        List<Favorites> resultFavorites;
        resultFavorites = new JPAQuery(entityManager).select(favorites).where(favorites.film.id.like(film.getId())).getResultList();
        
        List<Account> result = new ArrayList<>();
        
        for (Favorites f : resultFavorites) {
            result.add(f.getAccount());
        }

        return result;
    }

    public List<Film> getFilmsThatAccountFavorited(Account acc) {
        QFavorites_ favorites = new QFavorites_();

        List<Favorites> resultFavorites;
        resultFavorites = new JPAQuery(entityManager).select(favorites).where(favorites.account.username.eq(acc.getUsername())).getResultList();

        List<Film> result = new ArrayList<>();
        
        for (Favorites f : resultFavorites) {
            result.add(f.getFilm());
        }
        
        return result;
    }

    public Favorites getFavourite(Account acc, Film film) {
        QFavorites_ favorites = new QFavorites_();

        Favorites result;
        result = new JPAQuery(entityManager).select(favorites).where(favorites.account.username.eq(acc.getUsername()).and(favorites.film.id.eq(film.getId()))).getResultList().get(0);

        return result;
    }
}
