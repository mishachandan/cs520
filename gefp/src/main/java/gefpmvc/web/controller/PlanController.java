package gefpmvc.web.controller;

import gefpmvc.model.Cell;
import gefpmvc.model.Checkpoint;
import gefpmvc.model.Department;
import gefpmvc.model.Plan;
import gefpmvc.model.Runway;
import gefpmvc.model.Stage;
import gefpmvc.model.User;
import gefpmvc.model.dao.CellDao;
import gefpmvc.model.dao.CheckpointDao;
import gefpmvc.model.dao.DepartmentDao;
import gefpmvc.model.dao.PlanDao;
import gefpmvc.model.dao.RunwayDao;
import gefpmvc.model.dao.StageDao;
import gefpmvc.model.dao.UserDao;
import gefpmvc.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlanController {

	@Autowired
	PlanDao planDao;

	@Autowired
	DepartmentDao departmentDao;
	
	@Autowired
	UserDao userDao;

	@Autowired
	CheckpointDao checkpointDao;
	
	@Autowired
	RunwayDao runwayDao;
	
	@Autowired
	StageDao stageDao;
	
	@Autowired
	CellDao cellDao;
	
	 private static Logger logger = LoggerFactory.getLogger( PlanController.class );
	
	@RequestMapping(value="admin/welcome-file-admin.html")
	public String adminViewPlan(ModelMap models){
		
		models.put("departmentList" ,departmentDao.getAllDepartment());
		models.put("department", new Department());
		
		
		return "admin/welcome-file-admin";
	}
	@RequestMapping(value = "/admin/viewPlan.html")//, method=RequestMethod.GET)
	public String plan(@RequestParam String planId,@RequestParam String deptId, ModelMap models, HttpSession session){
		Plan plan = planDao.getPlanById(Long.parseLong(planId));		
		
		/*for (Cell cell : plan.getCell()) {
			for (Checkpoint checkpoint : cell.getCheckpoints()) {
				String value = checkpoint.getValue();
				value = value.replace("<p>", "");
				value = value.replace("</p>", "");
				checkpoint.setValue(value);
			}
		}*/
		models.put("plan", plan);
		
		session.setAttribute(Constants.SESSIONDEPTID, deptId);
		session.setAttribute(Constants.SESSIONPLANID, planId);
		
		return "admin/viewPlan";
		
	}
	
	@RequestMapping(value={"/admin/createPlan.html"},method={RequestMethod.GET})
	public String createPlan(@RequestParam String deptId, ModelMap models){
		
		models.put("deptId", deptId);
		models.put("plan", new Plan());
		//models.put("runway", runwayDao.getRunways());
		//models.put("stage", stageDao.getStages());
		return "admin/createPlan";
	}
	

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value={"/admin/createPlan.html"},method={RequestMethod.POST})
	public String addPlan(@ModelAttribute Plan	plan, @RequestParam String deptId){
		if(plan==null || plan.getName().equals("")){
			return	"redirect:createPlan.html?deptId="+deptId+"&message=Plan name cannot be blank";
		}
		//String planName= "planTest12";
		Department department = departmentDao.getDeptByDeptNo(Long.parseLong(deptId));
		plan.setCurrentDepartment(department);

		List<Plan> listAssociatedPlan = department.getAssociatedPlans();
		if(listAssociatedPlan==null){
			listAssociatedPlan = new ArrayList<Plan>();
		}
		listAssociatedPlan.add(plan);
		
		plan = planDao.savePlan(plan);
		
		return	"redirect:../reloadlogin.html";
	}
	
	@RequestMapping(value = "student/viewPlan.html")
	public String planStudent(ModelMap models, HttpSession session){
		
		User sessionUser = (User) session.getAttribute(Constants.AUTHENTICATEDUSER);
		
		User fetchedUser = userDao.getUserByCIN(sessionUser.getCin());
		
		//models.put("plan", fetchedUser.getOfficialPlan());
		models.put("student", fetchedUser);
		
		session.setAttribute(Constants.SESSIONDEPTID, fetchedUser.getMajor().getDeptNo());
		session.setAttribute(Constants.SESSIONPLANID, fetchedUser.getOfficialPlan().getPlanId());
		
		return "student/viewPlan";
		
	}	
	
	@PreAuthorize("authenticated and principal.officialPlan.contains(#checkpointId) and principal.officialPlan.planId == #planId")
	@RequestMapping(value = "student/editCheckpoint.html",method={RequestMethod.POST})
	public String editCheckpoint(@RequestParam Long planId,  @RequestParam Long checkpointId ,@RequestParam String operation, HttpSession session){
			//modification
		System.out.println(" PlanId: "+planId+" checkpoint "+checkpointId);
		User sessionUser = (User) session.getAttribute(Constants.AUTHENTICATEDUSER);
		
		User fetchedUser = userDao.getUserByCIN(sessionUser.getCin());
		
		Checkpoint targetCheckpoit = checkpointDao.getCheckpointById(checkpointId);
		
		if(operation.equals("add")){
			fetchedUser.getStudentCheckpoints().add(targetCheckpoit);
		}else if(operation.equals("remove")){
			fetchedUser.getStudentCheckpoints().remove(targetCheckpoit);
		}else{
			return null;
		}
		
		userDao.saveUser(fetchedUser);
		
		return "student/viewPlan";
		
	}
	
	@RequestMapping(value = "advisor/viewPlan.html", method=RequestMethod.GET)
	public String planStudentByAdvisor(ModelMap models, HttpSession session, @RequestParam Long studentId, HttpServletResponse response){
		
		User fetchedUser = userDao.getUserById(studentId);
		
		if(fetchedUser==null){
			logger.info(" tried to access invalid userid");
			//generate 404 error
			try {
				response.sendError(HttpStatus.BAD_REQUEST.value(), "tried to access invalid userid");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				return "";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		//models.put("plan", fetchedUser.getOfficialPlan());
		models.put("student", fetchedUser);
		
		session.setAttribute(Constants.SESSIONDEPTID, fetchedUser.getMajor().getDeptNo());
		session.setAttribute(Constants.SESSIONPLANID, fetchedUser.getOfficialPlan().getPlanId());
		
		return "advisor/viewPlan";
		
	}

	@PreAuthorize("authenticated and principal.officialPlan.contains(#checkpointId) and principal.officialPlan.planId == #planId")
	@RequestMapping(value = "advisor/editCheckpoint.html",method={RequestMethod.POST})
	public String editCheckpointByAdvisor(@RequestParam Long planId, @RequestParam Long userId, @RequestParam Long checkpointId ,@RequestParam String operation, HttpSession session){
		//modification
		User fetchedUser = userDao.getUserById(userId);
		
		Checkpoint targetCheckpoit = checkpointDao.getCheckpointById(checkpointId);
		
		if(operation.equals("add")){
			fetchedUser.getStudentCheckpoints().add(targetCheckpoit);
		}else if(operation.equals("remove")){
			fetchedUser.getStudentCheckpoints().remove(targetCheckpoit);
		}else{
			return null;
		}
		
		userDao.saveUser(fetchedUser);
		
		return "student/viewPlan";
		
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "admin/reorderStage.html",method={RequestMethod.POST})
	public String reorderStageByAdvisor(@RequestParam String planId,@RequestParam String stageId,  @RequestParam String newIndex){
		System.out.println(" called.. server"+ planId+"\t"+newIndex);
		Plan officialPlan = planDao.getPlanById(Long.parseLong(planId));
		Stage targetStage = stageDao.getStageById(Long.parseLong(stageId));
		
		officialPlan.removeStage(targetStage);
		
		officialPlan.getStageList().add( Integer.parseInt(newIndex), targetStage );
        
		planDao.savePlan(officialPlan );
		return "admin/viewPlan";
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "admin/reorderRunway.html",method={RequestMethod.POST})
	public String reorderRunwayByAdvisor(@RequestParam String planId,@RequestParam String runwayId,  @RequestParam String newIndex){
		System.out.println(" called.. server"+ planId+"\t"+newIndex);
		Plan officialPlan = planDao.getPlanById(Long.parseLong(planId));
		Runway targetRunway = runwayDao.getRunwayById(Long.parseLong(runwayId));
		
		officialPlan.removeRunway(targetRunway);
		
		officialPlan.getRunwayList().add( Integer.parseInt(newIndex), targetRunway );
        
		planDao.savePlan(officialPlan );
		return "admin/viewPlan";
		
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "admin/reorderCheckpoint.html",method={RequestMethod.POST})
	public String reorderRunwayByAdmin(@RequestParam String planId,@RequestParam String runwayId, @RequestParam String stageId,@RequestParam String checkpointId, @RequestParam String newIndex){
		System.out.println(" called.. server"+ planId+"\t"+newIndex);
		Plan officialPlan = planDao.getPlanById(Long.parseLong(planId  ));
		Runway runway = runwayDao.getRunwayById(Long.parseLong(runwayId));
		Stage stage = stageDao.getStageById(Long.parseLong(stageId));
		
		Checkpoint checkpointTarget = checkpointDao.getCheckpointById(Long.parseLong(checkpointId));
		Cell cell = cellDao.getCellByRunwayStage(runway, stage, officialPlan);
		
		Checkpoint checkpoint = cell.removeCheckpoint(checkpointTarget);
		        
		if(checkpoint!=null){
			cell.getCheckpoints().add(Integer.parseInt(newIndex), checkpointTarget);
			planDao.savePlan(officialPlan );
		}
		
		return "admin/viewPlan";
		
	}
}
