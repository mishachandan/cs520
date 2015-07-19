package gefpmvc.web.controller;

import gefpmvc.model.Cell;
import gefpmvc.model.Checkpoint;
import gefpmvc.model.CheckpointEnum;
import gefpmvc.model.Plan;
import gefpmvc.model.Runway;
import gefpmvc.model.Stage;
import gefpmvc.model.dao.CellDao;
import gefpmvc.model.dao.CheckpointDao;
import gefpmvc.model.dao.PlanDao;
import gefpmvc.model.dao.RunwayDao;
import gefpmvc.model.dao.StageDao;
import gefpmvc.util.Constants;
import gefpmvc.web.model.CheckpointModel;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckpointController {

	@Autowired
	RunwayDao runwayDao;
	
	@Autowired
	StageDao stageDao;
	
	@Autowired
	PlanDao planDao;
	
	@Autowired
	CellDao cellDao;
	
	@Autowired
	CheckpointDao checkPointDao;
	
	@RequestMapping(value={"/admin/saveCheckpoint.html"},method=RequestMethod.GET)
	public String saveCheckpoint( ModelMap models, HttpSession session){
		Plan plan = planDao.getPlanById(Long.parseLong((String) session.getAttribute(Constants.SESSIONPLANID)));

		
		CheckpointModel checkPointModel = new CheckpointModel();
		
		String checkpointId = (String) models.get("checkpointId");
		String runwayId = (String) models.get("runwayId");
		String stageId = (String) models.get("stageId");
		
		if(checkpointId!=null && !checkpointId.equals("")){ // editing plan 
			
			Checkpoint checkPoint =checkPointDao.getCheckpointById(Long.parseLong(checkpointId));
			checkPoint.setValue(checkPoint.getValue());
			checkPointModel.setCheckpoint(checkPoint);
			checkPointModel.setRunway(runwayId);
			checkPointModel.setStage(stageId);
			String value = checkPoint.getValue();
		/*	value = value.replace("<p>", "");
			value = value.replace("</p>", "");*/
			System.out.println(" --- value:"+value);
			checkPointModel.setCheckpointDesc(value);
			models.put("oldRunwayId", runwayId);
			models.put("oldStageId", stageId);
		}

		
		checkPointModel.setRunwayList(plan.getRunwayList());
		checkPointModel.setStageList(plan.getStageList());
		models.put("checkpointModel",checkPointModel);
		
		return "admin/saveCheckpoint";
	}
	
	
	@RequestMapping(value={"/admin/saveCheckpoint.html"},method=RequestMethod.POST)
	public String saveCheckpointPost(@RequestParam String checkPointID, @ModelAttribute CheckpointModel checkpointModel, HttpSession session , @RequestParam (required=false) String oldStageId,  @RequestParam (required=false) String oldRunwayId){
		
		if(checkpointModel.getCheckpointDesc()==null || checkpointModel.getCheckpointDesc().trim().equals("")){
			return	"redirect:saveCheckpoint.html?message=Checkpoint cannot be null";

		}
		
		Plan plan = planDao.getPlanById(Long.parseLong((String)session.getAttribute(Constants.SESSIONPLANID)));
		boolean editingInsameCell = false;
		int editingInsameCellIndex = -1;
		if(!oldRunwayId.equals("") && !oldStageId.equals("") ){
			
			Runway oldrunway = runwayDao.getRunwayById(Long.parseLong(oldRunwayId));
			Stage oldstage = stageDao.getStageById(Long.parseLong(oldStageId));
			Cell oldcell = cellDao.getCellByRunwayStage(oldrunway, oldstage, plan);
			Checkpoint oldcheckPoint = checkPointDao.getCheckpointById(Long.parseLong(checkPointID));

			if(oldRunwayId.equals(checkpointModel.getRunway()) &&  oldStageId.equals(checkpointModel.getStage())){ // editing on same ccell
				editingInsameCell=true;
				editingInsameCellIndex  = oldcell.getCheckpoints().indexOf(oldcheckPoint);
			}
			oldcell.removeCheckpoint(oldcheckPoint);
			cellDao.saveCell(oldcell);
		}
		
		Runway runway = runwayDao.getRunwayById(Long.parseLong(checkpointModel.getRunway()));
		Stage stage = stageDao.getStageById(Long.parseLong(checkpointModel.getStage()));
				
		Cell cell = cellDao.getCellByRunwayStage(runway, stage, plan);
		
		
		if(cell==null){
			cell = new Cell();			
		}
		
		cell.setPlan(plan);
		cell.setRunway(runway);
		cell.setStage(stage);
		
		List<Checkpoint> listCheckpoints = cell.getCheckpoints();
	
		if(listCheckpoints==null || listCheckpoints.size()==0){
			listCheckpoints = new ArrayList<Checkpoint>();
		}
		
		Checkpoint fetchedCheckpoint = null;
		if(checkPointID != null && !checkPointID.trim().equals("")){
			fetchedCheckpoint = checkPointDao.getCheckpointById(Long.parseLong(checkPointID));
		}
		
		
		if(fetchedCheckpoint == null){
			fetchedCheckpoint = new Checkpoint();
		}
		
		fetchedCheckpoint.setCheckpointType(CheckpointEnum.TEXT.toString());
		fetchedCheckpoint.setValue(checkpointModel.getCheckpointDesc());
		
		if(!editingInsameCell){
			listCheckpoints.add(fetchedCheckpoint);
		}else{ // editing on same ccell			
			listCheckpoints.add(editingInsameCellIndex, fetchedCheckpoint);
		}
		cell.setCheckpoints(listCheckpoints);
		
		cellDao.saveCell(cell);
		
		
		return	"redirect:viewPlan.html?planId="+session.getAttribute(Constants.SESSIONPLANID)+"&deptId="+session.getAttribute(Constants.SESSIONDEPTID);
	}
	
	@RequestMapping(value={"/admin/editCheckpoint.html"},method=RequestMethod.GET)
	public String editCheckpoint(@RequestParam String checkpointId,  @RequestParam String runwayId, @RequestParam String stageId,ModelMap models , HttpSession session){
		models.put("checkpointId", checkpointId);
		models.put("runwayId", runwayId);
		models.put("stageId", stageId);
		return saveCheckpoint(models,session);
	}
	
	@RequestMapping(value={"/admin/removeCheckpoint.html"},method=RequestMethod.GET)
	public String removeCheckpoint(@RequestParam String checkpointId,  @RequestParam String runwayId, @RequestParam String stageId,ModelMap models , HttpSession session){
		Plan plan = planDao.getPlanById(Long.parseLong((String)session.getAttribute(Constants.SESSIONPLANID)));

		Runway runway = runwayDao.getRunwayById(Long.parseLong(runwayId));
		Stage stage = stageDao.getStageById(Long.parseLong(stageId));
		Cell cell = cellDao.getCellByRunwayStage(runway, stage, plan);
		Checkpoint oldcheckPoint = checkPointDao.getCheckpointById(Long.parseLong(checkpointId));

		cell.removeCheckpoint(oldcheckPoint);
		cellDao.saveCell(cell);
		return	"redirect:viewPlan.html?planId="+session.getAttribute(Constants.SESSIONPLANID)+"&deptId="+session.getAttribute(Constants.SESSIONDEPTID);
	}
	
		public static void main(String[] args) {
			String value = "<p>wewew<strong>ertrtwe</strong><span style='color:#FFD700'>wewe3</span></p>";
			System.out.println(value.replace("<p>", ""));
		}
}
