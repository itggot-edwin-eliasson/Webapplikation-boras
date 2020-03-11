package com.edwine.model.entity;

import com.edwine.model.dao.FilmDAO;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
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
public class FilmDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(FilmDAO.class, Film.class, Account.class, Favorites.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    private FilmDAO filmDAO;

    private Film film1;

    @Before
    public void init() {
        filmDAO.removeAll();
        film1 = new Film("123", "Batman7", "1998", "Movie", "http://abc.com/img.png");
        filmDAO.create(film1);
    }

    @After
    public void cleanUp() {
        filmDAO.removeAll();
    }

    /**
     * Test of findFilmsMatchingTitle method, of class FilmDAO.
     */
    @Test
    public void testFindFilmsMatchingTitle() throws Exception {
        System.out.println("findFilmsMatchingTitle");
        String title = "Batman7";
        List<Film> expResult = new ArrayList<>();
        expResult.add(film1);
        List<Film> result = filmDAO.findFilmsMatchingTitle(title);
        assertEquals(expResult, result);
    }

    /**
     * Test of findFilmsMatchingId method, of class FilmDAO.
     */
    @Test
    public void testFindFilmsMatchingId() throws Exception {
        System.out.println("findFilmsMatchingTitle");
        String id = "123";
        List<Film> expResult = new ArrayList<>();
        expResult.add(film1);
        List<Film> result = filmDAO.findFilmsMatchingId(id);
        assertEquals(expResult, result);
    }
}
