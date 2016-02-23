package gefpmvc.model.dao;

import gefpmvc.model.Cell;
import gefpmvc.model.Plan;
import gefpmvc.model.Runway;
import gefpmvc.model.Stage;



public interface CellDao {
	public Cell getCellById(Long id);
	public Cell getCellByRunwayStage(Runway runway, Stage stage, Plan plan);
	public Cell saveCell(Cell cell);
}
