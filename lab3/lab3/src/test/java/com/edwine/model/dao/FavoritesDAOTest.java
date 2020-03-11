package com.edwine.model.dao;

import com.edwine.model.dao.AccountDAO;
import com.edwine.model.dao.FavoritesDAO;
import com.edwine.model.dao.FilmDAO;
import com.edwine.model.entity.Account;
import com.edwine.model.entity.Favorites;
import com.edwine.model.entity.Film;
import easycriteria.JPAQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author edwin
 */
@RunWith(Arquillian.class)
public class FavoritesDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(FavoritesDAO.class, Favorites.class, Account.class, Film.class, AccountDAO.class, FilmDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    private FavoritesDAO favoritesDAO;
    @EJB
    private AccountDAO accountDAO;
    @EJB
    private FilmDAO filmDAO;

    Account account1;
    Film film1;
    Favorites favorite1;
    
    
    @Before
    public void init() {
        account1 = new Account("abc123", "123", "123", new HashSet<>());
        film1 = new Film("123", "Batman7", "1998", "Movie", "http://abc.com/img.png");
        favorite1 = new Favorites(account1, film1, 0);
        
        accountDAO.create(account1);
        filmDAO.create(film1);
        favoritesDAO.create(favorite1);
    }

    @After
    public void cleanUp() {
        favoritesDAO.removeAll();
        accountDAO.removeAll();
        filmDAO.removeAll();
    }

    /**
     * Test of getFavourite method, of class FavoritesDAO.
     */
    @Test
    public void testGetFavourite() throws Exception {
        System.out.println("getFavourite");
        Favorites expResult = favorite1;
        Favorites result = favoritesDAO.getFavourite(account1, film1);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setScore method, of class FavoritesDAO.
     */
    @Test
    public void testSetScore() throws Exception {
        System.out.println("setScore");
        int score = 7;
        favoritesDAO.setScore(favorite1, score);
        int res = favoritesDAO.getFavourite(account1, film1).getScore();
        
        assertEquals(score, res);
    }

    /**
     * Test of getAccountsWhoFavoritedFilm method, of class FavoritesDAO.
     */
    @Test
    public void testGetAccountsWhoFavoritedFilm() throws Exception {
        System.out.println("getAccountsWhoFavoritedFilm");
        List<Account> expResult = new ArrayList<Account>();
        expResult.add(account1);
        List<Account> result = favoritesDAO.getAccountsWhoFavoritedFilm(film1);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFilmsThatAccountFavorited method, of class FavoritesDAO.
     */
    @Test
    public void testGetFilmsThatAccountFavorited() throws Exception {
        System.out.println("getFilmsThatAccountFavorited");
        List<Film> expResult = new ArrayList<>();
        expResult.add(film1);
        List<Film> result = favoritesDAO.getFilmsThatAccountFavorited(account1);
        assertEquals(expResult, result);
    }
}
