package gefpmvc.model.dao;

import gefpmvc.model.Cell;
import gefpmvc.model.User;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test(groups = "UserDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoTest extends AbstractTransactionalTestNGSpringContextTests {
	 	@Autowired
	    UserDao userDao;

	    @Test
	    public void getUserByUsername(){
	    	
	    	String user1 = "jdoe1".trim().toLowerCase();
	        assert userDao.getUserByUsername(user1)!=null ;
	        
	        String user2 = "jdoe2".trim().toLowerCase();
	        assert userDao.getUserByUsername(user2)!=null;
	    }
	    
	    @Test
	    public void checkOneCheckpoint(){
	    	
	    	String user1 = "jdoe1".trim().toLowerCase();
	        //assert userDao.getUserByUsername(user1).getStudent().getStudentCheckpoint().size()==1 ;
	    	assert userDao.getUserByUsername(user1).getStudentCheckpoints().size()==1 ;
	    }
	    
	     @Test
	    public void checkAllCheckpoint(){
	    	
	    	String user2 = "jdoe2".trim().toLowerCase();
	    	User currentUser = userDao.getUserByUsername(user2); 
	    
	        int userCheckpoint = currentUser.getStudentCheckpoints().size() ;
	           
	        int totalCellinDept = 0;
	       
	        for (Iterator<Cell> iterator = currentUser.getMajor().getCurrentPlan().getCell().iterator(); iterator.hasNext();) {
				Cell cell = (Cell) iterator.next();
				totalCellinDept += (cell.getCheckpoints()!=null?cell.getCheckpoints().size():0);
			}
	      //  int deptCheckpoint = currentUser.getStudent().getDepartment().getPlan().getCell().size() ;
	      
	        assert userCheckpoint==totalCellinDept;
	    }
	 	
}
