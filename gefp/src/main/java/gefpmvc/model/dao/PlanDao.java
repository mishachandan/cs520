package gefpmvc.model.dao;

import gefpmvc.model.Plan;

public interface PlanDao {

	public Plan getPlanById(Long id);
	
	public Plan savePlan(Plan plan);
	
	public void addPlanWithAllObjects(String deptNo, String runway, String stage, String[] checkpointName,String planName);

}
