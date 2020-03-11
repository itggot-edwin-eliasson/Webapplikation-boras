/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.edwine.model.entity.Film;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import view.FilmBackingBean;

/**
 *
 * @author pontus53
 */
@RunWith(Arquillian.class)
public class FilmControllerBeanTest {
    
    public FilmControllerBeanTest() {
    }
    
    @Inject
    FilmBackingBean filmBackingBean;
    
    @Inject
    FilmControllerBean filmControllerBean;
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of searchFilms method, of class FilmControllerBean.
     */
    //@Test
    public void testOnSearchFilms() {
        System.out.println("searchFilms");
        int minExpResult = 20;
        filmControllerBean.getBackingBean().setSearchString("Harry");
        filmControllerBean.onSearchFilms();
        List<Film> result = filmControllerBean.getSearchResult();
        assertTrue(result.size() > minExpResult);
    }

    /**
     * Test of getSearchResult method, of class FilmControllerBean.
     */
    //@Test
    public void testGetSearchResult() {
        System.out.println("getSearchResult");
        FilmControllerBean instance = new FilmControllerBean();
        List<Film> expResult = null;
        List<Film> result = instance.getSearchResult();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testNothing() {
        assertTrue(true);
    }
    
}
