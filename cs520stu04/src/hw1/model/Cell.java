package hw1.model;

import java.util.StringTokenizer;

public class Cell {
	
	private String id;
	private Runway runway;
	private Stage stage;
	
	
	public Cell() {
		super();
	}
	
	public Cell(Runway runway, Stage stage) {
		super();
		this.runway = runway;
		this.stage = stage;
	}

	public Cell(String id, Runway runway, Stage stage) {
		super();
		this.id = id;
		this.runway = runway;
		this.stage = stage;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Runway getRunway() {
		return runway;
	}
	public void setRunway(Runway runway) {
		this.runway = runway;
	}
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public static String[] getAddress(String index){
		 StringTokenizer st = new StringTokenizer(index.trim(),",");
		 String address[] = new String[3];
		 address[0] =st.nextToken();
		 address[1] = st.nextToken();
		 address[2] = st.nextToken();
		 return address;
	}
	@Override
	public int hashCode() {
		return 1; // constant
	}
	@Override
	public boolean equals(Object obj) {
		Cell curCell = (Cell)obj;
		try{
		/*if(this.id.trim().equals(curCell.id.trim())){
			return true;
		}else{
			return false;
		}*/
		if(this.runway!=null && this.stage!=null && curCell.runway!=null && curCell.stage!=null){
			 if(this.runway.equals(curCell.runway) && this.stage.equals(curCell.stage)){
					return true;
				}else{
					return false;
				}
		}else{
			return false;
		}
		
		}finally{
			curCell=null;
		}
	}
}
