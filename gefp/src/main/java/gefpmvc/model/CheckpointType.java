package gefpmvc.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@Entity
//@Table(name="checkpoint_type")
public class CheckpointType {

	@Id
	@Column(name="checkpoint_type_id")
	private Integer checkpointTypeId;
	
	@OneToMany(targetEntity=Checkpoint.class,mappedBy="checkpointType")	
	private Set<Checkpoint> checkpoint;

	@Column(name="description")
	private String description;
	
	public CheckpointType() {
		super();
	}

	public Integer getCheckpointTypeId() {
		return checkpointTypeId;
	}

	public void setCheckpointTypeId(Integer checkpointTypeId) {
		this.checkpointTypeId = checkpointTypeId;
	}

	public Set<Checkpoint> getCheckpoint() {
		return checkpoint;
	}

	public void setCheckpoint(Set<Checkpoint> checkpoint) {
		this.checkpoint = checkpoint;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
