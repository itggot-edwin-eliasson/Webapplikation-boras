/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractDAO<T> {

    private final Class<T> entityType;

    protected abstract EntityManager getEntityManager();

   
    public long count() {
        final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery cq = builder.createQuery();
        final Root<T> rt = cq.from(entityType);

        cq.select(builder.count(rt));

        final Query q = getEntityManager().createQuery(cq);
        return (Long) q.getSingleResult();
    }

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public List<T> findAll() {
        final CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityType));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public void removeAll() {
        final CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityType));
        List<T> result = getEntityManager().createQuery(cq).getResultList();
        
        for (T r : result){
            getEntityManager().remove(r);
        }
    }

    public void update(T entity) {
        getEntityManager().merge(entity);
    }
}
