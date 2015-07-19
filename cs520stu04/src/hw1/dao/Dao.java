package hw1.dao;

import hw1.config.Constants;
import hw1.model.Cell;
import hw1.model.Checkpoint;
import hw1.model.Plan;
import hw1.model.Runway;
import hw1.model.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

public class Dao {

	public Plan getGefpPlan() {
		
		Plan plan = new Plan();
		
		List<Runway> listRunway = new ArrayList<Runway>();
		Runway r1 =new Runway("Academics");
		Runway r2 =new Runway("Career Preparation");
		Runway r3 =new Runway("Leadership and Community Engagement");
		
		listRunway.add(r1);
		listRunway.add(r2);
		listRunway.add(r3);
		
		List<Stage> listStage = new ArrayList<Stage>();
		Stage s1 = new Stage("Pre-College (pre-flight checklist)");
		Stage s2 =  new Stage("Freshman (take-off)");
		listStage.add(s1);
		listStage.add(s2);
		
		plan.setRunwayList(listRunway);
		plan.setStageList(listStage);
		
		Map<Cell, List<Checkpoint>> map = new HashMap<Cell, List<Checkpoint>>();
		
		Cell cell11 = new Cell();
		cell11.setId("0,0");
		cell11.setRunway(r1);
		cell11.setStage(s1);
		
		List<Checkpoint> c11 = new ArrayList<Checkpoint>();
		c11.add(new Checkpoint("Algebra (before Yr1)"));
		c11.add(new Checkpoint("Pre-calculus (before Yr1)"));
		
		map.put(cell11, c11);
		
		Cell cell12 = new Cell();
		cell12.setId("0,1");
		cell12.setRunway(r2);
		cell12.setStage(s1);
		
		List<Checkpoint> c12 = new ArrayList<Checkpoint>();
		c12.add(new Checkpoint("Apply for financial aid and scholarships"));
		
		map.put(cell12, c12);
		
		Cell cell13 = new Cell();
		cell13.setId("0,2");
		cell13.setRunway(r3);
		cell13.setStage(s1);
		
		List<Checkpoint> c13 = new ArrayList<Checkpoint>();
		c13.add(new Checkpoint("Make a list of questions to ask during orientation and ask them!"));
		
		map.put(cell13, c13);
		
		Cell cell21 = new Cell();
		cell21.setId("1,0");
		cell21.setRunway(r1);
		cell21.setStage(s2);
		
		List<Checkpoint> c21 = new ArrayList<Checkpoint>();
		c21.add(new Checkpoint("Math 206 (Calc I)"));
		
		map.put(cell21, c21);
		
		Cell cell22 = new Cell();
		cell22.setId("1,1");
		cell22.setRunway(r2);
		cell22.setStage(s2);
		
		List<Checkpoint> c22 = new ArrayList<Checkpoint>();
		c22.add(new Checkpoint("Take a personal assessment"));
		
		map.put(cell22, c22);
		
		Cell cell23 = new Cell();
		cell23.setId("1,2");
		cell23.setRunway(r3);
		cell23.setStage(s2);
		
		List<Checkpoint> c23 = new ArrayList<Checkpoint>();
		c23.add(new Checkpoint("Find out about ECST student organizations at ECST week"));
		
		map.put(cell23, c23);
		
		plan.setCheckpointMap(map);
		System.out.println(" MAP size: "+map.size());
		return plan;
		
	}

	public static List<Checkpoint> getCheckPoint(String runway, String stage, ServletContext sc){
	
		Plan plan = (Plan) sc.getAttribute(Constants.PLAN);
		
		Runway tobefetchRunway = new Runway(runway);
		Runway fetchedRunway = plan.getRunwayList().get(plan.getRunwayList().indexOf(tobefetchRunway));
		
		Stage tobefetchedStage = new Stage(stage);
		Stage fetchedStage = plan.getStageList().get(plan.getStageList().indexOf(tobefetchedStage));
		
		Cell tobeFetchedCell = new Cell("", fetchedRunway, fetchedStage);
		List<Checkpoint> checkpoint =plan.getCheckpointMap().get(tobeFetchedCell);
		
		return checkpoint;
	}
	
	public static void main(String[] args) {
		
	}
}
