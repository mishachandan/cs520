package gefpmvc.model.dao;

import gefpmvc.model.Stage;

import java.util.List;



public interface StageDao {
	public List<Stage> getStages();

	public Stage getStageById(Long id);
	
	public Stage saveStage(Stage stage);
}
