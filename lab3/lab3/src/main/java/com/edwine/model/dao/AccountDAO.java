/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.model.dao;

import com.edwine.model.entity.Account;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

/**
 *
 * @author edwin
 */
@Stateless
public class AccountDAO extends AbstractDAO<Account> {
    @Getter @PersistenceContext(unitName = "flicktier")
    private EntityManager entityManager;
    
    public AccountDAO(){
        super(Account.class);
    }
    
    public List<Account> findUsersMatchingName() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    
}
