/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.model.dao;

import com.edwine.model.entity.Account;
import com.edwine.model.entity.QAccount_;
import easycriteria.JPAQuery;
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
public class AccountDAO extends AbstractDAO<Account, String> {
    @Getter @PersistenceContext(unitName = "flicktier")
    private EntityManager entityManager;
    
    public AccountDAO(){
        super(Account.class);
    }
    
    public List<Account> findAccountsMatchingUsername(String name) {
        QAccount_ account = new QAccount_();
        
        List<Account> result = new JPAQuery(entityManager).select(account).where(account.username.like(name)).getResultList();
        System.out.println(result.toString());
        
        return result;
    }
    
    
        
}
