package hw1.model;

import java.util.List;
import java.util.Map;

public class Plan {
	List<Runway> runwayList;
	List<Stage> stageList;
	Map<Cell,List<Checkpoint>> checkpointMap;
	
	public List<Runway> getRunwayList() {
		return runwayList;
	}
	public void setRunwayList(List<Runway> runwayList) {
		this.runwayList = runwayList;
	}
	public List<Stage> getStageList() {
		return stageList;
	}
	public void setStageList(List<Stage> stageList) {
		this.stageList = stageList;
	}
	public Map<Cell, List<Checkpoint>> getCheckpointMap() {
		return checkpointMap;
	}
	public void setCheckpointMap(Map<Cell, List<Checkpoint>> checkpointMap) {
		this.checkpointMap = checkpointMap;
	}	
	
}
