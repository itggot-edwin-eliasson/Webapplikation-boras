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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import omdb.OmdbService;
import omdb.model.FilmObject;
import omdb.model.SearchObject;
import view.FilmBackingBean;

@Named
@RequestScoped
public class FilmControllerBean implements Serializable {

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
            if (tmpList.isEmpty()) {
                maxNumberOfResults = 0;
            }

            searchResults.addAll(tmpList);
        }

        filmBackingBean.setMostRecentSearchResults(new ArrayList());

        for (SearchObject s : searchResults) {
            try {
                FilmObject filmObject = OmdbService.getFilmObjectFromId(s.getImdbID());
                Film film = new Film(filmObject.getImdbID(), filmObject.getTitle(), filmObject.getYear(), filmObject.getType(), filmObject.getPoster(), filmObject.getImdbRating(), filmObject.getMetascore());
                filmBackingBean.getMostRecentSearchResults().add(film);
                filmDAO.create(film);
            } catch (Exception e) {
                System.out.println(
                        "Error when adding ID to database, probably because the movie already exists in the database! "
                        + e.getMessage());
            }
        }

        System.out.println("Found " + searchResults.size() + " search results!");
        sortFilmsByImdbRating();
    }

    public void sortFilmsByImdbRating() { //TODO Fixa denna
        List<Film> sortList = new ArrayList();
        sortList.addAll(filmBackingBean.getMostRecentSearchResults());
        Collections.sort(sortList, (Film f1, Film f2) -> {
            if (f1.getImdbRating() == null ? f2.getImdbRating() == null : f1.getImdbRating().equals(f2.getImdbRating())) {
                return 0;
            }
            return Double.parseDouble(f1.getImdbRating()) < Double.parseDouble(f2.getImdbRating()) ? -1 : 1;
        });
        filmBackingBean.setMostRecentSearchResults(sortList);
    }

    // Only for testing!
    public FilmBackingBean getBackingBean() {
        return filmBackingBean;
    }
}
