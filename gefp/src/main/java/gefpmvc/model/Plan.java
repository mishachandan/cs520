
package gefpmvc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name="plan")
public class Plan {
	
	@Id
	@GeneratedValue
	@Column(name="plan_id")
	private Long planId;
	
	@Column(name="plan_name")
	private String name;
	
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name="plan_runway",
			  joinColumns={@JoinColumn(name="planId")},
			  inverseJoinColumns={@JoinColumn(name="runwayId")})
	@OrderColumn(name="runway_index")
	private List<Runway> runwayList;
	
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name="plan_stage",
				joinColumns={@JoinColumn(name="planId")},
				inverseJoinColumns={@JoinColumn(name="stageId")})
	@OrderColumn(name="stage_index")
	private List<Stage> stageList;
	

	@OneToMany(targetEntity=Cell.class,mappedBy="plan",cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Set<Cell> cell;
	
	
	/*
	 * This values (publishedDate) will be null if it's the current plan of some department. 
	 * If it's old plan, then it will have date on which this plan was published.
	 */	
	@Column(name="published_date")
	private Date publishedDate;

	
	/*
	 * This(currentDepartment)  will have published date null;
	 */
	@OneToOne(targetEntity=Department.class,mappedBy="currentPlan",cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Department currentDepartment;
	
	/*
	 * This will store old dept which will have published date not null. 
	 */
/*	removed bidirectional
 * 	@ManyToOne
	@JoinColumn(name="associated_dept_id_fk")
	private Department associatedDepartment;*/
	
	
	/*removed bidirectional
	 * @OneToMany(targetEntity=User.class,mappedBy="officialPlan")
	private Set<User> officialStudent;*/
	
	public Plan() {
		super();
		runwayList = new ArrayList<Runway>();
		stageList = new ArrayList<Stage>();
		cell = new HashSet<Cell>();
		//officialStudent = new HashSet<User>();
		//publishedDate = new Date();
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


	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public Set<Cell> getCell() {
		return cell;
	}
	public void setCell(Set<Cell> cell) {
		this.cell = cell;
	}

	
	public Department getCurrentDepartment() {
		return currentDepartment;
	}


	public void setCurrentDepartment(Department currentDepartment) {
		this.currentDepartment = currentDepartment;
	}

	public boolean contains (long checkpointID){
		boolean found = false;
		
		for (Iterator iterator = cell.iterator(); iterator.hasNext();) {
			Cell cell2 = (Cell) iterator.next();
			for (Iterator iterator2 = cell2.getCheckpoints().iterator(); iterator2.hasNext();) {
				Checkpoint checkpoint = (Checkpoint) iterator2.next();
				if(checkpoint.getCheckpointId()==checkpointID){
					found=true;
					break;
				}
					
			}
			if(found == true)
				break;
		}
		
		return found;
	}

	/*public Department getAssociatedDepartment() {
		return associatedDepartment;
	}


	public void setAssociatedDepartment(Department associatedDepartment) {
		this.associatedDepartment = associatedDepartment;
	}*/


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public Date getPublishedDate() {
		return publishedDate;
	}


	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Stage removeStage(Stage stage) {
		for( int i = 0; i < stageList.size(); ++i )
            if( stageList.get( i ).getStageId().equals( stage.getStageId() ) )
            {
            	return stageList.remove( i );
            }
		
		return null;
	}

	public Runway removeRunway(Runway runway) {
		for( int i = 0; i < runwayList.size(); ++i )
            if( runwayList.get( i ).getRunwayId().equals( runway.getRunwayId() ) )
            {
            	return runwayList.remove( i );
            }
		
		return null;
	}


	/*public Set<User> getOfficialStudent() {
		return officialStudent;
	}


	public void setOfficialStudent(Set<User> officialStudent) {
		this.officialStudent = officialStudent;
	}*/


	
	
	
	
}
