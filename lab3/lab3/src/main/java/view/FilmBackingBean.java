/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.edwine.model.dao.FilmDAO;
import com.edwine.model.entity.Film;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;
import omdb.OmdbService;
import omdb.model.SearchObject;
import omdb.model.FilmObject;
import org.omnifaces.util.Ajax;
import org.primefaces.PrimeFaces;

@Data
@Named
@ViewScoped
public class FilmBackingBean implements Serializable {

    private String searchString;

    private List<Film> mostRecentSearchResults = null;
    private Film currentFilm;

    @EJB
    private FilmDAO filmDAO;

    public List<Film> getAllFilms() {
        return filmDAO.findAll();
    }

    public int getAllFilmsSize() {
        return filmDAO.findAll().size();
    }

    public String getPosterWithPlaceholderFromFilm(Film film) {
        if (film.getPoster().equals("N/A")) {
            return "https://www.justgotochef.com/content/images/xno_image_found.png.pagespeed.ic.o7sjGbPlVj.png"; // placeholder img
        } else {
            return film.getPoster();
        }
    }

    public String getPosterWithPlaceholderFromSearchObject(SearchObject searchObject) {
        if (searchObject.getPoster().equals("N/A")) {
            return "https://www.justgotochef.com/content/images/xno_image_found.png.pagespeed.ic.o7sjGbPlVj.png"; // placeholder img
        } else {
            return searchObject.getPoster();
        }
    }

    public FilmObject getFilmObjectFromId(String id) {
        FilmObject film = OmdbService.getFilmObjectFromId(id);

        return film;
    }

    public void sortFilmsOnRating() {
        if (this.mostRecentSearchResults == null) {
            System.out.print("no films to sort");
            return;
        }
        Collections.sort(mostRecentSearchResults, new Comparator<Film>() {
            @Override
            public int compare(Film f1, Film f2) {
                return (Double.compare(f2.getImdbRating(), f1.getImdbRating()));
            }
        });
    }

    public void onShowFilmDetails(Film film) {
        this.currentFilm = film;
        Ajax.update("filmDetailForm:filmDetail");
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('filmDetail').show();");

    }
}
