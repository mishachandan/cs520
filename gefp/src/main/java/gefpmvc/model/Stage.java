package gefpmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="stage")
public class Stage {
	
	@Id
	@GeneratedValue
	@Column(name="stage_id")
	private Long stageId;

	@Column(name="description")
	private String desc;

	
	public Stage() {
		super();
	}

	public Stage(String desc) {
		super();
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Long getStageId() {
		return stageId;
	}

	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}

	@Override
	public String toString() {
		return stageId.toString();
	}


	/*@Override
	public boolean equals(Object obj) {
		String thisDesc = this.desc.toLowerCase().trim();
		String objDesc = ((Stage)obj).desc.toLowerCase().trim();
		
		return thisDesc.equals(objDesc);
	}*/
}
