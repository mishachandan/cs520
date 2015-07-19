package gefpmvc.model;

public enum CheckpointEnum {
	TEXT(1),URL(2),FILE(3);
	private int value;

	private CheckpointEnum(int value) {
		this.value = value;
	}
	
	
}
