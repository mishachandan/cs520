package gefpmvc.model.dao.jpa;

import gefpmvc.model.Cell;
import gefpmvc.model.Plan;
import gefpmvc.model.Runway;
import gefpmvc.model.Stage;
import gefpmvc.model.dao.CellDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CellDaoImpl implements CellDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override	
	public Cell getCellByRunwayStage(Runway runway, Stage stage, Plan plan) {
		
		List<Cell> listCell = entityManager.createQuery("  from Cell where  runway.runwayId = :runwayId  and  stage.stageId = :stageId and plan.planId = :planId  ", Cell.class )
			.setParameter("runwayId", runway.getRunwayId()).setParameter("stageId", stage.getStageId()).setParameter("planId", plan.getPlanId()).getResultList();
		
		return  listCell!=null && listCell.size()>0?listCell.get(0):null;
	}

	@Transactional
	@Override
	public Cell saveCell(Cell cell) {
		return entityManager.merge(cell);
	}

	@Override
	public Cell getCellById(Long id) {
		List<Cell> listCell = entityManager.createQuery("  from Cell where  id =:id  ", Cell.class )
				.setParameter("id", id).getResultList();
			
			return  listCell!=null && listCell.size()>0?listCell.get(0):null;
	}

}
