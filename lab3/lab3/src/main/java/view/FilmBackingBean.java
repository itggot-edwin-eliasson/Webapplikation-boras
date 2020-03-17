/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.edwine.model.dao.FilmDAO;
import com.edwine.model.entity.Film;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;
import omdb.OmdbService;
import omdb.model.FilmObject;
import omdb.model.SearchObject;

@Data
@Named
@ViewScoped
public class FilmBackingBean implements Serializable {

    private String searchString;

    @EJB
    private FilmDAO filmDAO;

    public List<Film> getAllFilms() {
        return filmDAO.findAll();
    }

    public int getAllFilmsSize() {
        return filmDAO.findAll().size();
    }

    public FilmObject getFilmObjectFromId(String filmId) {
        FilmObject filmObject = OmdbService.getFilmObjectFromId(filmId);

        return filmObject;
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
}
