package hw1.model;


public class Runway {
	
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
	
	@Override
	public boolean equals(Object obj) {
		String thisDesc = this.desc.toLowerCase().trim();
		String objDesc = ((Runway)obj).desc.toLowerCase().trim();
		
		return thisDesc.equals(objDesc);
	}
	
}
