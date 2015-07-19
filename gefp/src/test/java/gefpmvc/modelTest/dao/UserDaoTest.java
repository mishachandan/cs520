package gefpmvc.modelTest.dao;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test(groups = "UserDaoTestT")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoTest extends AbstractTransactionalTestNGSpringContextTests {
	 	@Autowired
	    UserDaoT userDaot;

	    @Test
	    public void getUser()
	    {
	        assert userDaot.getUserT( 1 ).getUsername().equalsIgnoreCase( "admin" );
	    }

	    @Test
	    public void getUsers()
	    {
	        assert userDaot.getUsers().size() == 2;
	    }
}
