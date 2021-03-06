/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.model.dao;

import com.edwine.model.entity.Film;
import com.edwine.model.entity.QFilm_;
import easycriteria.JPAQuery;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class FilmDAO extends AbstractDAO<Film> {

    @Getter
    @PersistenceContext(unitName = "flicktier")
    private EntityManager entityManager;

    public FilmDAO() {
        super(Film.class);
    }

    public List<Film> findFilmsMatchingTitle(String title) {
        QFilm_ film = new QFilm_();

        List<Film> result = new JPAQuery(entityManager)
                .select(film)
                .where(film.title.like(title))
                .getResultList();
        System.out.println(result.toString());

        return result;
    }

    public List<Film> findFilmsMatchingId(String id) {
        QFilm_ film = new QFilm_();

        List<Film> result = new JPAQuery(entityManager)
                .select(film)
                .where(film.id.like(id))
                .getResultList();
        System.out.println(result.toString());

        return result;
    }
}
