package gefpmvc.web.controller;

import gefpmvc.model.Plan;
import gefpmvc.model.Runway;
import gefpmvc.model.dao.PlanDao;
import gefpmvc.model.dao.RunwayDao;
import gefpmvc.util.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RunwayController {

	@Autowired
	RunwayDao runwayDao;
	
	@Autowired
	PlanDao planDao;
	
	@RequestMapping(value={"/admin/saveRunway.html"}, method=RequestMethod.GET)
	public String saveRunway( ModelMap models, HttpSession session){
		Plan plan = planDao.getPlanById(Long.parseLong((String) session.getAttribute(Constants.SESSIONPLANID)));
		
		models.put("runway", new Runway());
		models.put("runwayList", plan.getRunwayList());
		return "admin/saveRunway";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value={"/admin/saveRunway.html"}, method=RequestMethod.POST)
	public String saveRunwayPost(@ModelAttribute Runway runway, HttpSession session){
		if(runway==null || runway.getDesc().equals("")){
			return	"redirect:saveRunway.html?deptId="+session.getAttribute(Constants.SESSIONDEPTID)+"&message=Runway name cannot be blank";
		}
		
		Plan plan = planDao.getPlanById(Long.parseLong((String) session.getAttribute(Constants.SESSIONPLANID)));
		List<Runway> listRunway = plan.getRunwayList();
	
		if(listRunway==null){
			listRunway = new ArrayList<Runway>();
		}
		listRunway.add(runway);
		
		
		planDao.savePlan(plan);
		return	"redirect:viewPlan.html?planId="+session.getAttribute(Constants.SESSIONPLANID)+"&deptId="+session.getAttribute(Constants.SESSIONDEPTID);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value={"/admin/editRunway.html"}, method=RequestMethod.GET)
	public String editRunway(@RequestParam String runwayId, ModelMap models){
		System.out.println(" runwayID: "+runwayId);
		
		Runway fecthedRunway = runwayDao.getRunwayById(Long.parseLong(runwayId));
		models.put("runway", fecthedRunway);
		return "admin/editRunway";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value={"/admin/editRunway.html"}, method=RequestMethod.POST)
	public String editRunwayPost(@ModelAttribute Runway runway, HttpSession session){
		if(runway==null || runway.getDesc().equals("")){
			return	"redirect:saveRunway.html?deptId="+session.getAttribute(Constants.SESSIONDEPTID)+"&message=Runway name cannot be blank";
		}
		System.out.println(" runwayID: "+runway.getRunwayId());
		Runway fecthedRunway = runwayDao.getRunwayById(runway.getRunwayId());
		fecthedRunway.setDesc(runway.getDesc());
		runwayDao.saveRunway(fecthedRunway);
		return	"redirect:viewPlan.html?planId="+session.getAttribute(Constants.SESSIONPLANID)+"&deptId="+session.getAttribute(Constants.SESSIONDEPTID);
	}
}
