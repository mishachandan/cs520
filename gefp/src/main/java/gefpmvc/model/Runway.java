package gefpmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="runway")
public class Runway {
	
	@Id
	@GeneratedValue
	@Column(name="runway_id")
	private Long runwayId;
	
	@Column(name="decription")
	private String desc;
	
	
	public Runway() {
		super();
	}

	public Runway(String desc) {
		super();
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Long getRunwayId() {
		return runwayId;
	}

	public void setRunwayId(Long runwayId) {
		this.runwayId = runwayId;
	}


	@Override
	public String toString() {
		return runwayId.toString();
	}
	/*@Override
	public boolean equals(Object obj) {
		String thisDesc = this.desc.toLowerCase().trim();
		String objDesc = ((Runway)obj).desc.toLowerCase().trim();
		
		return thisDesc.equals(objDesc);
	}*/
	
}
