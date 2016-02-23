package gefpmvc.model.dao.jpa;

import gefpmvc.model.Stage;
import gefpmvc.model.User;
import gefpmvc.model.dao.StageDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StageDaoImpl implements StageDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Stage> getStages() {
		List<Stage> stageList= entityManager.createQuery( "from Stage  s", Stage.class ).getResultList();
		return stageList;
	}

	@Transactional
	@Override
	public Stage saveStage(Stage stage) {
		return entityManager.merge(stage);
	}

	@Override
	public Stage getStageById(Long id) {
		List<Stage> stageList= entityManager.createQuery( "from Stage  s where stageId =:id", Stage.class ).setParameter("id", id).getResultList();
		return stageList!=null?stageList.get(0):null;
	}

}
