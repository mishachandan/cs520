package gefpmvc.web.controller;

import gefpmvc.model.Plan;
import gefpmvc.model.Stage;
import gefpmvc.model.dao.PlanDao;
import gefpmvc.model.dao.StageDao;
import gefpmvc.util.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StageController {

	@Autowired
	StageDao stageDao;
	
	@Autowired
	PlanDao planDao;
	
	@RequestMapping(value={"/admin/saveStage.html"},method=RequestMethod.GET)
	public String saveStage( ModelMap models, HttpSession session){
		Plan plan = planDao.getPlanById(Long.parseLong((String) session.getAttribute(Constants.SESSIONPLANID)));
		
		models.put("stage", new Stage());
		models.put("stageList", plan.getStageList());
		
		return "admin/saveStage";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value={"/admin/saveStage.html"},method=RequestMethod.POST)
	public String saveStagePost(@ModelAttribute Stage stage, HttpSession session){
		if(stage==null || stage.getDesc().equals("")){
			return	"redirect:saveStage.html?message=Stage name cannot be blank&planId="+ session.getAttribute(Constants.SESSIONPLANID)+"&deptId="+ session.getAttribute(Constants.SESSIONDEPTID);
		}
		Plan plan = planDao.getPlanById(Long.parseLong((String) session.getAttribute(Constants.SESSIONPLANID)));
		List<Stage> listStage = plan.getStageList();
	
		if(listStage==null){
			listStage = new ArrayList<Stage>();
		}
		listStage.add(stage);
		
	
		planDao.savePlan(plan);
		return	"redirect:viewPlan.html?planId="+session.getAttribute(Constants.SESSIONPLANID)+"&deptId="+session.getAttribute(Constants.SESSIONDEPTID);
		
	}
	

	@RequestMapping(value={"/admin/editStage.html"},method=RequestMethod.GET)
	public String editStage(@RequestParam String stageId, ModelMap models, HttpSession session){

		Stage stage = stageDao.getStageById(Long.parseLong(stageId));
		models.put("stage", stage);
		
		return "admin/editStage";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value={"/admin/editStage.html"},method=RequestMethod.POST)
	public String editStagePost(@ModelAttribute Stage stage, HttpSession session){
		if(stage==null || stage.getDesc().equals("")){
			return	"redirect:editStage.html?message=Stage name cannot be blank&planId="+ session.getAttribute(Constants.SESSIONPLANID)+"&deptId="+ session.getAttribute(Constants.SESSIONDEPTID);
		}
		Stage fetchedStage = stageDao.getStageById(stage.getStageId());
	
		fetchedStage.setDesc(stage.getDesc());

		stageDao.saveStage(fetchedStage);
		
		return	"redirect:viewPlan.html?planId="+session.getAttribute(Constants.SESSIONPLANID)+"&deptId="+session.getAttribute(Constants.SESSIONDEPTID);
		
	}
}
