package gefpmvc.web.controller;

import gefpmvc.model.Department;
import gefpmvc.model.Plan;
import gefpmvc.model.PlanHistory;
import gefpmvc.model.User;
import gefpmvc.model.dao.DepartmentDao;
import gefpmvc.model.dao.PlanDao;
import gefpmvc.model.dao.PlanHistoryDao;
import gefpmvc.model.dao.UserDao;
import gefpmvc.util.Constants;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DepartmentController {


	@Autowired
	DepartmentDao departmentDao;
	
	@Autowired
	PlanDao planDao;
	
	@Autowired
	UserDao userDao;
	
	
	@Autowired
	PlanHistoryDao planHistoryDao;
	
    @RequestMapping("/admin/setToDefaultPlan.html")
    public String setDefaultPlan(@RequestParam String planId , @RequestParam String deptId){/*@RequestBody String search*/
    	
    	Department department = departmentDao.getDeptByDeptNo(Long.parseLong(deptId));
    	
    	Plan currentPlan = planDao.getPlanById(Long.parseLong(planId));
    	
 /*   	Date date = new Date();
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS'0'");
    	String text = df.format(date);
    	
    	
    	currentPlan.setPublishedDate(publishedDate);*/
    	 
    	
    	    
    	currentPlan.setPublishedDate(new Date());
    	department.setCurrentPlan(currentPlan);
    	
    	departmentDao.saveDepartment(department);
    	return "redirect:../reloadlogin.html";
    }
    
    @RequestMapping(value={"/student/showDepartment.html"})
    public String showDepartment(HttpSession session, ModelMap models ){
    
    	User sessionUser = (User) session.getAttribute(Constants.AUTHENTICATEDUSER);
    	
    	User fetchedUser = userDao.getUserByCIN(sessionUser.getCin());
    	
    	List<Department> listDept = departmentDao.getAllDepartment();
    	
    	List<PlanHistory> planHistoryList = planHistoryDao.getPlanHistory(fetchedUser);
    	models.put("departmentList", listDept);
    	models.put("fetchedUser",fetchedUser);
    	models.put("department", new Department());
    	models.put("planHistoryList", planHistoryList);
    	models.put("deptNo", fetchedUser.getMajor().getDeptNo());
    	return "student/showDepartment"; 
    }
    
    @RequestMapping(value = {"/student/showDepartment.html"}, method=RequestMethod.POST)
    public String showDepartment(HttpSession session, @ModelAttribute Department department ,ModelMap models){
    
    	User sessionUser = (User) session.getAttribute(Constants.AUTHENTICATEDUSER);
    	
    	User fetchedUser = userDao.getUserByCIN(sessionUser.getCin());
    	
    	
    	PlanHistory planHistory = new PlanHistory();
    	try{
    		//planHistory.setFromDate();
        	planHistory.setToDate(new Date());
        	planHistory.setPlan(fetchedUser.getOfficialPlan());
        	planHistory.setStudent(fetchedUser);
        	planHistoryDao.savePlanHistory(planHistory);
        	
        	Department fetchedDepartment = departmentDao.getDeptByDeptNo(department.getDeptNo());
        	fetchedUser.setOfficialPlan(fetchedDepartment.getCurrentPlan());
        	fetchedUser.setMajor(fetchedDepartment);
        	userDao.saveUser(fetchedUser);
    		
        	models.put("student", fetchedUser);
        	
    	}catch(JpaSystemException exception){
    		exception.printStackTrace();
    		
    	}
    	
    	
    	return "student/viewPlan"; 
    }
    
}
