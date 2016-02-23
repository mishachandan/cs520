package gefpmvc.web.controllerTest;

import javax.persistence.EntityManager;

import gefpmvc.model.Plan;
import gefpmvc.model.dao.PlanDao;
import gefpmvc.model.dao.RunwayDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller(value="planControllerTest")
public class PlanController {

	@Autowired
	PlanDao planDao;

	
	@RequestMapping(value={"/displayPlan.html"})
	public String getPlanById(@RequestParam String   planid){
		
		Plan plan =planDao.getPlanById(new Long(planid));
		
		return	"home";
	}
	
	@RequestMapping(value={"/addPlanAll.html"})
	public String addPlanAll(){
		String runway = "extra-curricular";
		String stage = "international";
		String[] checkpoints = {"Visit career center and find out what services they offer","Find out what you need to get a summer internship after your 3rd year."};
		String planName= "planTest";
		String departmentNo = "1";
		
		planDao.addPlanWithAllObjects(departmentNo,runway,stage,checkpoints,planName);
		
		return	"home";
	}
	

	
}
