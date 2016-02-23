package gefpmvc.model.dao.jpa;

import gefpmvc.model.Checkpoint;
import gefpmvc.model.dao.CheckpointDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class CheckpointDaoImpl implements CheckpointDao{

	@PersistenceContext 
	EntityManager entityManager;
	
	@Override
	public Checkpoint getCheckpointById(Long checkpointId) {
		List<Checkpoint> listCheckpoint = entityManager.createQuery(" from Checkpoint where checkpointId =:id ").setParameter("id", checkpointId).getResultList();
		return listCheckpoint!=null && listCheckpoint.size()>0 ? listCheckpoint.get(0):null;
	}

}
