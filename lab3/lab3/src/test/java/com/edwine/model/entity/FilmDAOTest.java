package com.edwine.model.entity;


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
public class FilmDAOTest {
    @Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class)
			.addClasses(FilmDAO.class, Film.class, Account.class, Favorites.class)
			.addAsResource("META-INF/persistence.xml")
			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private	FilmDAO filmDAO;
        private Film film;

	@Before
	public void init() {
                filmDAO.removeAll();
                film = new Film("The Joker",2019,"Society","Todd Phillips");
                filmDAO.create(film);
                //filmDAO.create(new Film());
                //filmDAO.create(new Film());
	}

	@Test
	public void checkThatFindFilmMatchesTitle() {
                Assert.assertEquals(film, filmDAO.findFilmsMatchingTitle("The Joker").get(0));
                
	}
}
