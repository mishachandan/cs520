package gefpmvc.web.controller;

import gefpmvc.model.Department;
import gefpmvc.model.Role;
import gefpmvc.model.RoleEnum;
import gefpmvc.model.User;
import gefpmvc.model.UI.UserUIModel;
import gefpmvc.model.dao.DepartmentDao;
import gefpmvc.model.dao.RoleDao;
import gefpmvc.model.dao.UserDao;
import gefpmvc.util.BCryptPasswordEncoderSpring;
import gefpmvc.util.Constants;
import gefpmvc.web.validator.UserUIValidator;
import gefpmvc.web.validator.UserValidator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller

public class UserController {

	@Autowired
	DepartmentDao departmentDao;

	@Autowired
	UserDao userDao;

	@Autowired
	UserValidator validator;
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	UserUIValidator userUIValidator;
	
	 private static Logger logger = LoggerFactory.getLogger( UserController.class );
	 
	@RequestMapping(value={"/login.html"},method= RequestMethod.GET)
	public String login(ModelMap models  ){
		logger.info(" /login.html starts. " );
		models.put("user", new User());
		
		logger.info(" /login.html ends. " );
		return "login";
	}
	
	@RequestMapping(value={"/login.html"},method= RequestMethod.POST)
	public String login( @ModelAttribute User user , ModelMap models , BindingResult bindingResult , HttpSession session){
		
		validator.validate(user, bindingResult);
		
		if( bindingResult.hasErrors() ) return "login";
		 
		return "";
		
	}
	
	
	@RequestMapping(value={"/reloadlogin.html"},method= RequestMethod.GET)
	public String reloadlogin( ModelMap models, HttpSession session){
		session.removeAttribute(Constants.SESSIONPLANID);
		session.removeAttribute(Constants.SESSIONDEPTID);
		models.put("departmentList" ,departmentDao.getAllDepartment());
		models.put("department", new Department());
		User sessionUser = (User) session.getAttribute(Constants.AUTHENTICATEDUSER);
		User fetchedUser = userDao.getUserByCIN(sessionUser.getCin());
		
		if(fetchedUser.isAdmin()){
			return "admin/welcome-file-admin";
		}else if(fetchedUser.isStudent()){
			return "welcome-file-student";	
		}else{
			return logout(session);
		}
		
		
	}
	
	@RequestMapping(value={"/logout.html"})
	public String logout(HttpSession session ){
		session.invalidate();
		return "logout";
	}
	
	@RequestMapping(value={"/viewUserProfile.html"})
	public String  viewUserProfile(ModelMap models,HttpSession session){
		
		User sessionUser = (User) session.getAttribute(Constants.AUTHENTICATEDUSER);
		User fetchedUser = userDao.getUserByCIN(sessionUser.getCin());
		UserUIModel userUIModel = new UserUIModel();
		userUIModel.convertToUserUI(fetchedUser);
		
		if(fetchedUser.isAdmin()){
			return "";
		}
		else if(fetchedUser.isStudent()){
			models.put("userUIModel", userUIModel);
			return "student/viewUserProfile";
		}
		else
			return "";
	}
	
	@RequestMapping(value={"/viewUserProfile.html"},method=RequestMethod.POST)
	public String  viewUserProfilePost(@ModelAttribute UserUIModel userUIModel, HttpSession session, BindingResult bindingResult , ModelMap models){
		User sessionUser = (User) session.getAttribute(Constants.AUTHENTICATEDUSER);
		User fetchedUser = userDao.getUserByCIN(sessionUser.getCin());
		
		userUIModel.setCin(fetchedUser.getCin());
		userUIModel.setUsername(fetchedUser.getUsername());
		

		userUIValidator.validate(userUIModel, bindingResult);
		
		if( bindingResult.hasErrors() ) return "student/viewUserProfile";
		
		User userFromUI = userUIModel.convertToUser(fetchedUser);
		
		/*if(fetchedUser.isAdmin()){
			return "";
		}
		else if(fetchedUser.isStudent()){
			models.put("userUIModel", userUIModel);
			return "student/viewUserProfile";
		}
		else
			return "";*/
		
		userDao.saveUser(userFromUI);
		
		//return "redirect:/viewUserProfile.html";
		models.put("status", "success");
		return viewUserProfile(models,session);
	}
	
	@RequestMapping(value={"advisor/welcome.html"},method=RequestMethod.GET)
	public String welcomeAdvisor(){
		
		return "/advisor/welcome-file-advisor";
	}
	
	@RequestMapping(value={"advisor/viewStudents.html"},method=RequestMethod.GET)
	public String viewStudents(ModelMap models){
		
		models.put("department", departmentDao.getAllDepartment());
		return "/advisor/viewStudent";
	}
	
	 @RequestMapping(value = "/user/search",method=RequestMethod.POST)
	 @ResponseBody
	 public ResponseEntity<String> search( @RequestParam(required = false) String term,
	        @RequestParam(required = false) String dept, ModelMap models )
	    {
	        List<User> users = null;
	        if( StringUtils.hasText( term ) )
	        {
	            users = userDao.searchUsers( term );
	        }
	        if(users ==null || users.size()==0){
	        	return new ResponseEntity<String>("empty", HttpStatus.BAD_REQUEST );	
	        }else{
	        	
	        	JSONArray array = new JSONArray();
	        	for (Iterator iterator = users.iterator(); iterator.hasNext();) {
					User user = (User) iterator.next();
					JSONObject jsonObject = new JSONObject();
		        	try {
		        		
						jsonObject.put("id",user.getUserId());
						jsonObject.put("username",user.getUsername());
						jsonObject.put("name",user.getFname()+" "+user.getLname());
						jsonObject.put("cin",users.get(0).getCin());
						jsonObject.put("departmentName",users.get(0).getMajor().getDeptName());
						jsonObject.put("officialPlan",users.get(0).getOfficialPlan().getName());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST );	
					}
		        	array.put(jsonObject);
				}
	        	
	            models.addAttribute( "users", users.get(0) );
	            return new ResponseEntity<String>(array.toString(), HttpStatus.OK);
	        }
	  
	       // return "redirect:/advisor/viewStudents.html";

	    }

	 
	 @RequestMapping(value="/user/add", method=RequestMethod.POST)
	 public String addUser(@RequestParam String firstname, @RequestParam String lastname, @RequestParam String cin , @RequestParam Long major){
		  
		 User user = new User();
		 if(cin==""){
			 cin ="G" + (100000000 + Math.floor(Math.random()*100000000));
		 }
		 String cin1 = cin.replace(".", "").trim();
		 user.setCin(cin1);
		 user.setFname(firstname);
		 user.setLname(lastname);
		 user.setEnabled(true);
		 user.setUsername(cin1);
		 user.setPassword(BCryptPasswordEncoderSpring.encodeMd5(cin1));
		 Set<Role> roles = new HashSet<Role>();
		 roles.add(roleDao.getRole(RoleEnum.STUDENT.toString()));
		 user.setRoles(roles);
		 Department majorDept = departmentDao.getDeptByDeptNo(major);
		 
		 user.setMajor(majorDept);
		 user.setOfficialPlan(majorDept.getCurrentPlan());
		 userDao.saveUser(user);
		 return "redirect:/advisor/viewStudents.html";
	 }
}
