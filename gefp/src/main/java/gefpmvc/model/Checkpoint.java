package gefpmvc.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="checkpoint")
public class Checkpoint {
	
	@Id
	@GeneratedValue
	@Column(name="checkpoint_id")
	private Long checkpointId;
	
	@Column(name="checkpoint_type_id_fk",nullable=false)
	private String checkpointType;
	
	@Column(name="value",nullable=false)//,columnDefinition= " For file type, the path of file which is uploaded will be stored.")
	private String value;

	/*@ManyToOne
	@JoinColumn(name="cell_id")
	private Cell cell;
	*/
	public Checkpoint() {
		super();
	}
	

	public Checkpoint(String checkpointType, String value) {
		super();
		this.checkpointType = checkpointType;
		this.value = value;
	}


	public Long getCheckpointId() {
		return checkpointId;
	}
	
	public void setCheckpointId(Long checkpointId) {
		this.checkpointId = checkpointId;
	}


	public String getCheckpointType() {
		return checkpointType;
	}

	public void setCheckpointType(String checkpointType) {
		this.checkpointType = checkpointType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
