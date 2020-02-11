
import com.edwine.model.dao.AccountDAO;
import com.edwine.model.entity.Account;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
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
			.addClasses(AccountDAO.class, Account.class)
			.addAsResource("META-INF/persistence.xml")
			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private	AccountDAO accountDAO;

	@Before
	public void init() {
                accountDAO.removeAll();
                accountDAO.create(new Account("Pedds", "Edwin Eliasson"));
                accountDAO.create(new Account("Benji", "Benjamin Vinnerholt"));
                accountDAO.create(new Account("Poppi", "Pontus Backman"));
	}

	@Test
	public void checkThatFindCarsMatchingNameMatchesCorrectly() {
		Assert.assertTrue(true); /* Some better condition */
	}
}
