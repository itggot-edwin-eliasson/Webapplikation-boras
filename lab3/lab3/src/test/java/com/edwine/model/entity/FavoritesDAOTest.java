package com.edwine.model.entity;


import com.edwine.model.dao.AccountDAO;
import com.edwine.model.dao.FavoritesDAO;
import com.edwine.model.dao.FilmDAO;
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
			.addClasses(FavoritesDAO.class, Favorites.class, Account.class, Film.class, AccountDAO.class)
			.addAsResource("META-INF/persistence.xml")
			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private	FavoritesDAO favoritesDAO;
        @EJB
        private AccountDAO accountDAO;
        @EJB
        //private FilmDAO filmDAO;

	@Before
	public void init() {
                //favoritesDAO.removeAll();
                //favoritesDAO.create(new Favorites(new FavoritesKey("id:1234", "Pedds"), accountDAO, , 70));
                //favoritesDAO.create(new Favorites());
                //favoritesDAO.create(new Favorites());
	}

	@Test
	public void checkThatFindCarsMatchingNameMatchesCorrectly() {
		Assert.assertTrue(true); /* Some better condition */
	}
}