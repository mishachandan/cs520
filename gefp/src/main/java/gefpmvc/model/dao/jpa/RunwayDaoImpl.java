package gefpmvc.model.dao.jpa;

import gefpmvc.model.Runway;
import gefpmvc.model.dao.RunwayDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RunwayDaoImpl implements RunwayDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Runway> getRunways() {
		
		List<Runway> runwayList= entityManager.createQuery( "from Runway  ", Runway.class ).getResultList();
		return runwayList;

	}


	@Transactional
	@Override
	public Runway saveRunway(Runway runway) {
		return entityManager.merge(runway);
	}


	@Override
	public Runway getRunwayById(Long id) {
		List<Runway> runwayList= entityManager.createQuery( "from Runway  where runwayId =:id ", Runway.class ).setParameter("id", id).getResultList();
		return runwayList!=null?runwayList.get(0):null;
	}

}
