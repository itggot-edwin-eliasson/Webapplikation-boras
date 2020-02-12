
import com.edwine.model.dao.FilmDAO;
import com.edwine.model.entity.Account;
import com.edwine.model.entity.Film;
import java.util.Date;
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
public class FilmDAOTest {
    @Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class)
			.addClasses(FilmDAO.class, Film.class)
			.addAsResource("META-INF/persistence.xml")
			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private	FilmDAO filmDAO;

	@Before
	public void init() {
                filmDAO.removeAll();
                //filmDAO.create(new Film("id:1234","The Joker",2019,"Society","Todd Phillips"));
                //filmDAO.create(new Film());
                //filmDAO.create(new Film());
	}

	@Test
	public void checkThatFindCarsMatchingNameMatchesCorrectly() {
		Assert.assertTrue(true); /* Some better condition */
	}
}
