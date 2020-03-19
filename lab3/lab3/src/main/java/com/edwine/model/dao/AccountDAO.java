/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.model.dao;

import com.edwine.model.entity.Account;
import com.edwine.model.entity.QAccount_;
import easycriteria.JPAQuery;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class AccountDAO extends AbstractDAO<Account> {

    @Getter
    @PersistenceContext(unitName = "flicktier")
    private EntityManager entityManager;

    public AccountDAO() {
        super(Account.class);
    }

    public Account getAccountMatchingUsername(String name) {
        QAccount_ account = new QAccount_();

        try {
            Account result = new JPAQuery(entityManager)
                    .select(account)
                    .where(account.username.eq(name))
                    .getResultList().get(0);
            return result;
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR: Could not find any account matching the username!");
        }
        
        return null;
    }

    //This query gets a user more insensitive. Used when searching for users
    public Account getAccountMatchingUsernameLikeQuery(String name) {
        String tokenizedString = name.toLowerCase();
        QAccount_ account = new QAccount_();

        try {
            Account result = new JPAQuery(entityManager)
                    .select(account)
                    .where(account.username.like("%" + tokenizedString + "%"))
                    .getResultList().get(0);
            return result;
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR: Could not find any account matching the username!");
        }

        return null;
    }

    public void updateFirstname(Account acc, String newName) {
        acc.setFirstName(newName);
        update(acc);
    }

    public void updateLastname(Account acc, String newName) {
        acc.setLastName(newName);
        update(acc);
    }

    public void updateEmail(Account acc, String newEmail) {
        acc.setEmail(newEmail);
        update(acc);
    }

    public void updateAvatarUrl(Account acc, String newUrl) {
        acc.setAvatarUrl(newUrl);
        update(acc);
    }
}
