/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.model.dao;

import com.edwine.model.entity.Account;
import com.edwine.model.entity.Favorites;
import com.edwine.model.entity.QFavorites_;
import easycriteria.JPAQuery;
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
    @Getter @PersistenceContext(unitName = "flicktier")
    private EntityManager entityManager;
    
    public FavoritesDAO(){
        super(Favorites.class);
    }
    
    public List<Account> findUsersMatchingName() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    
    public void setScore(Favorites favorite,int score) {
        //QFavorites_ favorites = new QFavorites_();
        
        //List<Favorites> result = new JPAQuery(entityManager).select(favorites).where(favorites.account.username.like(favorite.getAccount().getUsername())).getResultList();
        //List<Favorites> result = new JPAQuery(entityManager).select(favorites).where(favorites.account.username.like(favorite.getAccount().getUsername()).and(favorites.film.));
        
        favorite.setScore(score);
        super.update(favorite);
    }
    
}