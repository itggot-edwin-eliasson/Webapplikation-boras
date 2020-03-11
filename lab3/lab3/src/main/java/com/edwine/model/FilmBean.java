/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.model;

import com.edwine.model.dao.FilmDAO;
import com.edwine.model.entity.Film;
import com.edwine.model.entity.Favorites;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import omdb.OmdbService;
import omdb.model.FilmObject;
import omdb.model.SearchObject;
import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

/**
 *
 * @author edwin
 */

@Data
@Named
@ViewScoped
public class FilmBean implements Serializable {
    
    private List<Film> films;
    
    private String searchString;
    
    private List<SearchObject> mostRecentSearchResults;
    
    private boolean searchActive = false;
    
    @EJB
    private FilmDAO filmDAO;
    
    @PostConstruct
    public void init(){
        /*
        films = new ArrayList<Film>();
        films.add(new Film("test1",2020,"test1","test1"));
        films.add(new Film("test2",2020,"test2","test2"));
        films.add(new Film("test3",2020,"test3","test3"));
*/

    }
    
    public List<Film> getAllFilms(){
        return filmDAO.findAll();
    }
    
    public int getAllFilmsSize(){
        return filmDAO.findAll().size();
    }
    
    public List<SearchObject> searchFilms() {
        System.out.println(this.getSearchString());
        List<SearchObject> searchResults = OmdbService.getSearchObjectsFromSearchString(getSearchString());


            for (SearchObject s : searchResults) {
                try {
                    filmDAO.create(new Film(s.getImdbID(), s.getTitle(), s.getYear() ,s.getType(), s.getPoster()));
                }catch (Exception e) {
                    System.out.println("Error when adding ID to database, probably because the movie already exists in the database! " + e.getMessage());
                }
            }
            
       
        mostRecentSearchResults = searchResults;
        return searchResults;
    }
    
    public FilmObject getFilmObjectFromId(String filmId) {
        FilmObject f = OmdbService.getFilmObjectFromId(filmId);
        
        return f;
    }
    
    public String getPosterWithPlaceholderFromFilm(Film f) {
        if (f.getPoster().equals("N/A")) {
            return "https://www.justgotochef.com/content/images/xno_image_found.png.pagespeed.ic.o7sjGbPlVj.png"; // placeholder img
        } else {
            return f.getPoster();
        }
    }
    
    public String getPosterWithPlaceholderFromSearchObject(SearchObject s){
        if (s.getPoster().equals("N/A")) {
            return "https://www.justgotochef.com/content/images/xno_image_found.png.pagespeed.ic.o7sjGbPlVj.png"; // placeholder img
        } else {
            return s.getPoster();
        }
    }
    
    public List<SearchObject> getSearchResult(){
        return mostRecentSearchResults;
    }
}
