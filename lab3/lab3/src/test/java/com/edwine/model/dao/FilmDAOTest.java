package com.edwine.model.dao;

import com.edwine.model.dao.FilmDAO;
import com.edwine.model.entity.Account;
import com.edwine.model.entity.Favorites;
import com.edwine.model.entity.Film;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import static org.junit.Assert.assertEquals;
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
    private Film film2;

    @Before
    public void init() {
        filmDAO.removeAll();
        film1 = new Film("123", "Batman7", "1998", "Movie", "http://abc.com/img.png", 5.5, "abc");
        film2 = new Film("456", "Batman8", "1999", "Movie", "http://abc.com/img.png", 5.5, "abc");
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
    
    /**
     * Test of count method, of class FilmDAO.
     */
    @Test
    public void testCount() throws Exception {
        System.out.println("count");
        long result = filmDAO.count();
        long expResult = 1;
        assertEquals(expResult, result);
    }

    /**
     * Test of create method, of class FilmDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        filmDAO.create(film2);
        
        assertEquals(2, filmDAO.count());
        
        filmDAO.remove(film2);
    }

    /**
     * Test of findAll method, of class FilmDAO.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        int expResult = 1;
        List<Film> result = filmDAO.findAll();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of remove method, of class FilmDAO.
     */
    @Test
    public void testRemove() throws Exception {
        System.out.println("remove");
        filmDAO.remove(film1);
        assertEquals(0, filmDAO.count());
        
        filmDAO.create(film1);
    }

    /**
     * Test of update method, of class FilmDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Film entity = filmDAO.findFilmsMatchingId("123").get(0);
        entity.setReleaseYear("2000");
        filmDAO.update(entity);
        
        assertEquals("2000", entity.getReleaseYear());
    }
    
    /**
     * Test of removeAll method, of class FilmDAO.
     */
    @Test
    public void testRemoveAll() throws Exception {
        System.out.println("removeAll");
        filmDAO.removeAll();
        assertEquals(0, filmDAO.count());
    }
}
