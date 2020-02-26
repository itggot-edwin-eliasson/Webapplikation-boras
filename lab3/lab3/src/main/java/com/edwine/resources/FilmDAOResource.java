/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.resources;

import com.edwine.model.dao.FilmDAO;
import com.edwine.model.dao.FavoritesDAO;
import com.edwine.model.entity.Film;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author ogge
 */
@Path("filmDao")
public class FilmDAOResource {
    
	@EJB
	private FilmDAO filmDao;

	@GET // curl -X GET --basic http://localhost:8080/frontend-react/ws/filmDao
	public List<Film> list() {
                filmDao.create(new Film("asd" + Math.random(), 123, "aöldj","dsjf"));
		return new ArrayList<>(filmDao.findAll());
	}
        
        @GET // curl -X GET --basic http://localhost:8080/frontend-react/ws/filmDao/0
	@Path("{serial}")
	public Film get(@PathParam("serial") int id) {
		return filmDao.find(id);
	}
        
        
        
}
