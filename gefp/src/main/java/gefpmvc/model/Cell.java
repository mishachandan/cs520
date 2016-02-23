package gefpmvc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="cell",
		uniqueConstraints=@UniqueConstraint(columnNames={"runway_fk", "stage_fk","plan_id_fk"}))
public class Cell {
	
	@Id
	@GeneratedValue
	@Column(name="cell_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="runway_fk",nullable=false)
	private Runway runway;
	
	@ManyToOne
	@JoinColumn(name="stage_fk",nullable=false)
	private Stage stage;
	
	@ManyToOne
	@JoinColumn(name="plan_id_fk",nullable=false,insertable=true)
	private Plan plan;
	

	@OneToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name="cell_id_fk")//, nullable=false)
    @OrderColumn(name="checkpoint_index")
	private List<Checkpoint> checkpoints;
	
	public Cell() {
		super();
		checkpoints = new ArrayList<Checkpoint>();
	}
	
	public Cell(Runway runway, Stage stage) {
		super();
		this.runway = runway;
		this.stage = stage;
	}

	public Cell(Long id, Runway runway, Stage stage) {
		super();
		this.id = id;
		this.runway = runway;
		this.stage = stage;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
		
	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	
	public List<Checkpoint> getCheckpoints() {
		return checkpoints;
	}

	public void setCheckpoints(List<Checkpoint> checkpoints) {
		this.checkpoints = checkpoints;
	}

	
	public Checkpoint removeCheckpoint(Checkpoint checkpoint) {
		for( int i = 0; i < checkpoints.size(); ++i )
			if(checkpoints.get( i )!=null){
            if( checkpoints.get( i ).getCheckpointId().equals( checkpoint.getCheckpointId() ) )
            {
            	return checkpoints.remove( i );
            }
			}else{
				
			}
		
		return null;
	}
	public static String[] getAddress(String index){
		 StringTokenizer st = new StringTokenizer(index.trim(),",");
		 String address[] = new String[3];
		 address[0] =st.nextToken();
		 address[1] = st.nextToken();
		 address[2] = st.nextToken();
		 return address;
	}
	/*@Override
	public int hashCode() {
		return 1; // constant
	}
	@Override
	public boolean equals(Object obj) {
		Cell curCell = (Cell)obj;
		try{
		if(this.id.trim().equals(curCell.id.trim())){
			return true;
		}else{
			return false;
		}
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
	}*/
}
