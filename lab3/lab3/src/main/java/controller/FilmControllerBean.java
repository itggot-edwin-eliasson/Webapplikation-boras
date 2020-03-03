/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.edwine.model.dao.FilmDAO;
import com.edwine.model.entity.Film;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import omdb.OmdbService;
import omdb.model.SearchObject;
import view.FilmBackingBean;

/**
 *
 * @author edwin
 */
@Named
@RequestScoped
public class FilmControllerBean implements Serializable {
    
    private List<SearchObject> mostRecentSearchResults;
    
    @Inject
    private FilmBackingBean filmBackingBean;
    
    @EJB
    private FilmDAO filmDAO;
    
    public List<SearchObject> searchFilms() {
        System.out.println(filmBackingBean.getSearchString());
        List<SearchObject> searchResults = OmdbService.getSearchObjectsFromSearchString(filmBackingBean.getSearchString());


            for (SearchObject s : searchResults) {
                try {
                    filmDAO.create(new Film(s.getImdbID(), s.getTitle(), s.getYear() ,s.getType(), s.getPoster()));
                }catch (Exception e) {
                    System.out.println("Error when adding ID to database, probably because the movie already exists in the database! " + e.getMessage());
                }
            }
            
       
        mostRecentSearchResults = searchResults;
        //push.send("newdata");
        return searchResults;
    }
    
    public List<SearchObject> getSearchResult(){
        return mostRecentSearchResults;
    }
}
