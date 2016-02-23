package gefpmvc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department {

	@Id
	@GeneratedValue
	@Column(name="dept_no")
	private Long deptNo;
	
	@Column(name="department_name")//,nullable=false)
	private String deptName;
	
	@Column(name="office")//,nullable=false)
	private String office;
	
	@Column(name="phone")//,nullable=false)
	private String phone;
	
	@OneToOne
	@JoinColumn(name="current_plan_id_fk")
	private Plan currentPlan;

	@OneToMany//(targetEntity=Plan.class,mappedBy="associatedDepartment")
	@JoinColumn(name="associated_dept_id_fk")
	private List<Plan> associatedPlans;
	
	
/*	@OneToMany(targetEntity=User.class,mappedBy="major")
	private Set<User> user;*/
	
	
	
	public Department() {
		super();
		associatedPlans = new ArrayList<Plan>();
		//user = new HashSet<User>();
	}

	
	
	public Department(String deptName, String office, String phone, Plan currentPlan, List<Plan> associatedPlans) {
		super();
		this.deptName = deptName;
		this.office = office;
		this.phone = phone;
		this.currentPlan = currentPlan;
		this.associatedPlans = associatedPlans;
	}



	public Long getDeptNo() {
		return deptNo;
	}



	public void setDeptNo(Long deptNo) {
		this.deptNo = deptNo;
	}



	public String getDeptName() {
		return deptName;
	}



	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}



	public String getOffice() {
		return office;
	}



	public void setOffice(String office) {
		this.office = office;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public List<Plan> getAssociatedPlans() {
		return associatedPlans;
	}



	public void setAssociatedPlans(List<Plan> associatedPlans) {
		this.associatedPlans = associatedPlans;
	}



	public Plan getCurrentPlan() {
		return currentPlan;
	}



	public void setCurrentPlan(Plan currentPlan) {
		this.currentPlan = currentPlan;
	}



	/*public Set<User> getUser() {
		return user;
	}



	public void setUser(Set<User> user) {
		this.user = user;
	}*/



	
	
	
}
