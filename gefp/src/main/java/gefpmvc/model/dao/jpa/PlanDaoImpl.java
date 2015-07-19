package gefpmvc.model.dao.jpa;



import gefpmvc.model.Cell;
import gefpmvc.model.Checkpoint;
import gefpmvc.model.CheckpointEnum;
import gefpmvc.model.CheckpointType;
import gefpmvc.model.Department;
import gefpmvc.model.Plan;
import gefpmvc.model.Runway;
import gefpmvc.model.Stage;
import gefpmvc.model.dao.DepartmentDao;
import gefpmvc.model.dao.PlanDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PlanDaoImpl implements PlanDao{

	@PersistenceContext
	private EntityManager entityManager ;
	
	@Autowired
	DepartmentDao departmentDao;
	
	@Override
	public Plan getPlanById(Long id) {
		Plan plan =  entityManager.createQuery( "from Plan where  planId = :planid  ", Plan.class )
		.setParameter("planid", id).getSingleResult();
		
		return plan;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void addPlanWithAllObjects(String deptNo, String runway, String stage, String[] checkpointName,String planName) {
		
		Department department = departmentDao.getDeptByDeptNo(Long.parseLong(deptNo));
		//Department department = new Department("testingdept11","headoff11","478303489",null,null);
				
		
		Plan plan = new Plan();
		plan.setName(planName);
		
		
		
		List<Runway> listRunway = new ArrayList<Runway>();
		Runway newRunway = new Runway(runway);
		//entityManager.persist(newRunway);
		listRunway.add(newRunway);
		
		List<Stage> listStage = new ArrayList<Stage>();
		Stage newStage = new Stage(stage);
		//entityManager.persist(newStage);
		listStage.add(newStage);
		
		plan.setRunwayList(listRunway);
		plan.setStageList(listStage);
		
		
		//plan.setCurrentDepartment(department);
		
		//List<Plan> assocaiatedPlan = new ArrayList<Plan>();
		
		department.getAssociatedPlans().add(plan);
		
		//department.setAssociatedPlans(assocaiatedPlan);
		//entityManager.persist(department);
		
		Set<Cell> setCell = new HashSet<Cell>();
		Cell cell1 = new Cell();
		List<Checkpoint> listCheckpoint = new ArrayList<Checkpoint>();
		for (String eachCheckpointName : checkpointName) {
			System.out.println(CheckpointEnum.TEXT.toString());
			Checkpoint checkpoint = new Checkpoint(CheckpointEnum.TEXT.toString(),eachCheckpointName);
			//entityManager.persist(checkpoint);
			listCheckpoint.add(checkpoint);
		}
		
		cell1.setCheckpoints(listCheckpoint);
		
		cell1.setPlan(plan);
		cell1.setRunway(newRunway);
		cell1.setStage(newStage);
		//entityManager.persist(cell1);
		setCell.add(cell1);
		plan.setCell(setCell);
		
		entityManager.merge(plan);
		//entityManager.persist(plan);
	}

	@Transactional
	@Override
	 @PreAuthorize("#assignment.section.isInstructor(principal)")
	public Plan savePlan(Plan plan) {
		plan = entityManager.merge(plan);
		return plan;
	}

}
