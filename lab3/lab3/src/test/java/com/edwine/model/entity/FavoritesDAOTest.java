package com.edwine.model.entity;


import com.edwine.model.dao.AccountDAO;
import com.edwine.model.dao.FavoritesDAO;
import com.edwine.model.dao.FilmDAO;
import easycriteria.JPAQuery;
import java.util.List;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
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
	private	FavoritesDAO favoritesDAO;
        @EJB
        private AccountDAO accountDAO;
        @EJB
        private FilmDAO filmDAO;
        
        Account account1;
        Film film1;
        Favorites favorite1;

	@Before
	public void init() {
                favoritesDAO.removeAll();
                
                account1 = new Account("abc123", "Pontus");
                film1 = new Film("ABC", 1998, "Action", "Edwin");
                favorite1 = new Favorites(account1, film1, 7);
                
                accountDAO.create(account1);
                filmDAO.create(film1);
                favoritesDAO.create(favorite1);
               
                
                //favoritesDAO.create(new Favorites());
                //favoritesDAO.create(new Favorites());
	}

	@Test
	public void checkThatScoreUpdatesCorrectly() {
                Favorites favoriteBefore = favoritesDAO.findFavoritesMatchingFilmAndAccount(film1, account1);
                
                favoritesDAO.setScore(favorite1, 2);
                
                Favorites favoriteAfter = favoritesDAO.findFavoritesMatchingFilmAndAccount(film1, account1);
            
		Assert.assertTrue((favoriteBefore.getScore() != favoriteAfter.getScore()) && (favoriteAfter.getScore() == 2)); /* Some better condition */
	}
}
