/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.model;

import java.io.Serializable;
import com.edwine.model.dao.FavoritesDAO;
import com.edwine.model.dao.AccountDAO;
import com.edwine.model.dao.FilmDAO;
import com.edwine.model.entity.Film;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;
import omdb.model.SearchObject;

@Data
@Named
@ViewScoped
public class FavoritesBean implements Serializable {
    
    @EJB
    private FavoritesDAO favDAO;
   
    @EJB
    private AccountDAO accDAO;
    
    @EJB
    private FilmDAO filmDAO;
    
    public void addFavorite(SearchObject film){
        Film f = filmDAO.findFilmsMatchingTitle(film.getTitle()).get(0);
        favDAO.add(accDAO.findAll().get(0), f, 0);
    }
    
}
