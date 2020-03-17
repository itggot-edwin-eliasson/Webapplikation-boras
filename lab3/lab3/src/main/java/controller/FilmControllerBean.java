/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.edwine.model.dao.FilmDAO;
import com.edwine.model.entity.Film;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import omdb.OmdbService;
import omdb.model.SearchObject;
import org.omnifaces.cdi.ViewScoped;
import view.FilmBackingBean;

@Named
@ViewScoped
public class FilmControllerBean implements Serializable {

    private List<Film> mostRecentSearchResults;

    @Inject
    private FilmBackingBean filmBackingBean;

    @EJB
    private FilmDAO filmDAO;

    //Uses omdb service to find movies and create them in db aswell as
    //adding them to mostrecentsearch list
    public void onSearchFilms() {
        System.out.println(filmBackingBean.getSearchString());

        List<SearchObject> searchResults = new ArrayList();
        int maxNumberOfResults = 30;

        for (int i = 1; searchResults.size() < maxNumberOfResults; i++) {
            List<SearchObject> tmpList = new ArrayList();

            tmpList = OmdbService.getSearchObjectsFromSearchString(filmBackingBean.getSearchString(), i);
            if (tmpList.size() == 0) {
                maxNumberOfResults = 0;
            }

            searchResults.addAll(tmpList);
        }

        mostRecentSearchResults = new ArrayList();

        for (SearchObject s : searchResults) {
            try {
                Film film = new Film(s.getImdbID(), s.getTitle(), s.getYear(), s.getType(), s.getPoster());
                mostRecentSearchResults.add(film);
                filmDAO.create(film);
            } catch (Exception e) {
                System.out.println(
                        "Error when adding ID to database, probably because the movie already exists in the database! "
                        + e.getMessage());
            }
        }

        System.out.println("Found " + searchResults.size() + " search results!");
    }

    public List<Film> getSearchResult() {
        return mostRecentSearchResults;
    }

    // Only for testing!
    public FilmBackingBean getBackingBean() {
        return filmBackingBean;
    }
}
