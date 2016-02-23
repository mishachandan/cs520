package gefpmvc.model.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

@Test(groups = "DepartmentDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback = true)
@Transactional(propagation = Propagation.MANDATORY,readOnly=false) 
public class DepartmentDaoTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	DepartmentDao departmentDao;
	
	/*@Test
	public void getPlansByDepartment(){
		
		Department department = departmentDao.getDeptByDepartmentName("Computer Science") ;
		
		for (Plan plan2 : department.getAssociatedPlans()) {
			System.out.println("PLan: "+plan2.getPlanId()+"\t Name:"+plan2.getName());
		}
		 assert department !=null;
	}
*/	
	@Transactional
	@Test
	public void addDepartment(){
		String department = "Civil Engineering";
		
		departmentDao.addDepartment(department);
	}
}
