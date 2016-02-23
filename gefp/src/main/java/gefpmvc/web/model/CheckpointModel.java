package gefpmvc.web.model;

import gefpmvc.model.Checkpoint;
import gefpmvc.model.Runway;
import gefpmvc.model.Stage;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CheckpointModel {

	private List<Runway> runwayList;
	
	private List<Stage> stageList;
	
	private Checkpoint checkpoint;

	private String runway;
	
	private String stage;
	
	private String checkpointDesc;
	
	
	
	public CheckpointModel() {
		super();
	}

	public CheckpointModel(List<Runway> runwayList, List<Stage> stageList, Checkpoint checkpoint, String runway,
			String stage, String checkpointDesc) {
		super();
		this.runwayList = runwayList;
		this.stageList = stageList;
		this.checkpoint = checkpoint;
		this.runway = runway;
		this.stage = stage;
		this.checkpointDesc = checkpointDesc;
	}

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

	public Checkpoint getCheckpoint() {
		return checkpoint;
	}

	public void setCheckpoint(Checkpoint checkpoint) {
		this.checkpoint = checkpoint;
	}

	public String getRunway() {
		return runway;
	}

	public void setRunway(String runway) {
		this.runway = runway;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getCheckpointDesc() {
		return checkpointDesc;
	}

	public void setCheckpointDesc(String checkpointDesc) {
		this.checkpointDesc = checkpointDesc;
	}
	
	
}
