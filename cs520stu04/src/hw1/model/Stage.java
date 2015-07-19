package hw1.model;

public class Stage {
	
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
	
	@Override
	public boolean equals(Object obj) {
		String thisDesc = this.desc.toLowerCase().trim();
		String objDesc = ((Stage)obj).desc.toLowerCase().trim();
		
		return thisDesc.equals(objDesc);
	}
}
