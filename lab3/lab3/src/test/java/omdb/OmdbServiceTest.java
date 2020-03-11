/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omdb;

import java.util.List;
import omdb.model.FilmObject;
import omdb.model.SearchObject;
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
    public void testGetFilmObjectFromId() {
        System.out.println("getFilmObjectFromId");
        String filmId = "tt7286456";
        String expResult = "Joker";
        String result = OmdbService.getFilmObjectFromId(filmId).getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSearchObjectsFromSearchString method, of class OmdbService.
     */
    @Test
    public void testGetSearchObjectsFromSearchString() {
        System.out.println("getSearchObjectsFromSearchString");
        String searchValue = "Batman";
        int expResult = 10;
        int result = OmdbService.getSearchObjectsFromSearchString(searchValue).size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetSearchObjectsFromSearchString_allArgs() {
        System.out.println("getSearchObjectsFromSearchString_allArgs");
        String searchValue = "Harry";
        int expResult = 10;
        int result = OmdbService.getSearchObjectsFromSearchString(searchValue, 1).size();
        assertEquals(expResult, result);
    }
    
}
