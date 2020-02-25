/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.model;

import com.edwine.model.dao.FilmDAO;
import com.edwine.model.entity.Favorites;
import com.edwine.model.entity.Film;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import lombok.Data;
import omdb.OmdbService;
import omdb.model.FilmObject;
import omdb.model.SearchObject;

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
    
    public List<SearchObject> searchFilms() {
        System.out.println(this.getSearchString());
        
        try {
            List<SearchObject> searchResults = OmdbService.getSearchObjectsFromSearchString(getSearchString());

            for (SearchObject s : searchResults) {
                filmDAO.create(new Film(s.getImdbID(), new HashSet<Favorites>()));
            }

            filmDAO.findAll();

            return searchResults;
        } catch (Exception e) {
            System.out.println("Error when adding ID to database, probably because the movie already exists in the database! " + e.getMessage());
        }
        
        return new ArrayList<SearchObject>();
    }
    
    public FilmObject getFilmFromId(String filmId) {
        FilmObject f = OmdbService.getFilmObjectFromId(filmId);
        
        return f;
    }
    
    public String getTitleFromId(String filmId) {
        FilmObject f = OmdbService.getFilmObjectFromId(filmId);
        
        return f.getTitle();
    }
}
