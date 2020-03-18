package com.edwine.model.dao;


import com.edwine.model.dao.AccountDAO;
import com.edwine.model.entity.Account;
import com.edwine.model.entity.Favorites;
import com.edwine.model.entity.Film;
import java.util.HashSet;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author edwin
 */
@RunWith(Arquillian.class)
public class AccountDAOTest {
        @Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class)
			.addClasses(AccountDAO.class, Account.class, Film.class, Favorites.class)
			.addAsResource("META-INF/persistence.xml")
			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private	AccountDAO accountDAO;
        //private Account account;

	@Before
	public void init() {
                accountDAO.removeAll();
                accountDAO.create(new Account("abc123", "123", "123", new HashSet<>()));
                accountDAO.create(new Account("def456", "456", "456", new HashSet<>()));
                accountDAO.create(new Account("kallekula", "789", "789", new HashSet<>()));
                
	}
        
        @After
        public void cleanUp() {
            accountDAO.removeAll();
        }

	@Test
	public void testGetAccountMatchingUsernameMatchesCorrectly() {
                String accountName = "abc123";
                Account res = accountDAO.getAccountMatchingUsername(accountName);
                Assert.assertEquals(accountName, res.getUsername());                
	}
        
        @Test
	public void testGetAccountMatchingUsernameReturnsNull() {
                String accountName = "21345213";
                Account res = accountDAO.getAccountMatchingUsername(accountName);
                Assert.assertEquals(null, res);                
	}
        
        @Test
        public void testgetAccountMatchingUsernameLikeMatchesCorrectly() {
                String searchUserString = "kalle";
                String accountName = "kallekula";
                Account res = accountDAO.getAccountMatchingUsernameLikeQuery(searchUserString);
                Assert.assertEquals(accountName, res.getUsername());                
	}
        
        @Test
	public void testGetAccountMatchingUsernameLikeReturnsNull() {
                String accountName = "21345213";
                Account res = accountDAO.getAccountMatchingUsernameLikeQuery(accountName);
                Assert.assertEquals(null, res);
	}
        
        @Test
        public void testUpdateFirstname() {
            String accountName = "abc123";
            Account res = accountDAO.getAccountMatchingUsername(accountName);
            
            accountDAO.updateFirstname(res, "Pontus");
            Assert.assertEquals("Pontus", res.getFirstName());
            
            accountDAO.updateFirstname(res, "Erik");
            Assert.assertEquals("Erik", res.getFirstName());
        }
        
        @Test
        public void testUpdateLastname() {
            String accountName = "abc123";
            Account res = accountDAO.getAccountMatchingUsername(accountName);
            
            accountDAO.updateLastname(res, "Backman");
            Assert.assertEquals("Backman", res.getLastName());
            
            accountDAO.updateLastname(res, "Karlsson");
            Assert.assertEquals("Karlsson", res.getLastName());
        }
        
        @Test
        public void testUpdateEmail() {
            String accountName = "abc123";
            Account res = accountDAO.getAccountMatchingUsername(accountName);
            
            accountDAO.updateEmail(res, "Pontus@abc.com");
            Assert.assertEquals("Pontus@abc.com", res.getEmail());
            
            accountDAO.updateEmail(res, "Erik@abc.com");
            Assert.assertEquals("Erik@abc.com", res.getEmail());
        }
        
        @Test
        public void testUpdateAvatarUrl() {
            String accountName = "abc123";
            Account res = accountDAO.getAccountMatchingUsername(accountName);
            
            accountDAO.updateAvatarUrl(res, "http://abc.com");
            Assert.assertEquals("http://abc.com", res.getAvatarUrl());
            
            accountDAO.updateAvatarUrl(res, "http://def.se");
            Assert.assertEquals("http://def.se", res.getAvatarUrl());
        }
}
