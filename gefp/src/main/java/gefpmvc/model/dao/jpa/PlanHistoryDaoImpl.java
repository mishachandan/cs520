package gefpmvc.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gefpmvc.model.PlanHistory;
import gefpmvc.model.User;
import gefpmvc.model.dao.PlanHistoryDao;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PlanHistoryDaoImpl implements PlanHistoryDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	@Override
	@PreAuthorize("planHistory.student == principal")
	public PlanHistory savePlanHistory(PlanHistory planHistory) {
		return entityManager.merge(planHistory);
	}

	@Override
	public List<PlanHistory> getPlanHistory(User user){
		return entityManager.createQuery( "from PlanHistory where  student = :stud  order by toDate desc", PlanHistory.class )
				.setParameter("stud", user).getResultList();

			
	}
}
