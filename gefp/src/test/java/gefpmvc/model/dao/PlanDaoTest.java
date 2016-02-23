package gefpmvc.model.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test(groups = "PlanDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PlanDaoTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	PlanDao planDao;
	
	/*@Test
	public void getPlansById(){
		
		Plan plan = planDao.getPlanById(new Long(1));
		System.out.println("PLan: "+plan.getPlanId()+"\t Name:"+plan.getName());
		
		 assert plan !=null;
	}*/
	
	@Test
	public void addPlan(){
		String runway = "extra-curricular";
		String stage = "international";
		String[] checkpoints = {"Visit career center and find out what services they offer","Find out what you need to get a summer internship after your 3rd year."};
		String planName= "planTest";
		String departmentNo = "2";
		
	//	planDao.addPlan(departmentNo);
//		planDao.addPlanWithAllObjects(departmentNo,runway,stage,checkpoints,planName);
	}	
}
