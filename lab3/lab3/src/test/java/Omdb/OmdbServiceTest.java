/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Omdb;

import Omdb.model.FilmObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pontus53
 */
public class OmdbServiceTest {
    
    public OmdbServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getFilmObjectFromId method, of class OmdbService.
     */
    @Test
    public void testGetTitleFromId() {
        System.out.println("getFilmObjectFromId");
        String filmId = "tt7286456";
        String expResult = "Joker";
        String result = OmdbService.getFilmObjectFromId(filmId).getTitle();
        assertEquals(expResult, result);
    }
    
}
