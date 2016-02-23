package gefpmvc.model;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="users")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="user_name",nullable=false,unique=true)
	private String username;
	
	@Column(name="f_name",nullable=false)
	private String fname;
	
	@Column(name="l_name",nullable=false)
	private String lname;
	
	@Column/*(nullable=false)*/
	private String password;
	
	@Column/*(nullable=false)*/
	private String emailid;
	
	@Column/*(nullable=false)*/
	private String address;
	
	
	@Column(name="cin",nullable=false,unique=true)
	private String cin;

	@Column(name="enabled")
	private Boolean enabled = true;
	
	@ManyToMany
	@JoinTable( name="student_checkpoint",
	joinColumns={@JoinColumn(name="userId")},
	inverseJoinColumns={@JoinColumn(name="checkpointId")})
	private Set<Checkpoint> studentCheckpoints;
	
	/*
	 * This (major) may change when a user changes a plan.
	 */
	@ManyToOne
	@JoinColumn(name="department_id_fk",nullable=false)
	private Department major;
	
	/*
	 * This (officialPlan) stores the official plan of student 
	 * which refers to plan for student based on his/her major. 
	 * This field is kept so that for old student's, it will store
	 * plan_id of old student.
	 * This field will change on change of major. 
	 */
	@ManyToOne
	@JoinColumn(name="official_plan_id_fk")
	private Plan officialPlan;
	
	
	
	@ManyToMany
	@JoinTable( name="user_roles",
	joinColumns={@JoinColumn(name="userId")},
	inverseJoinColumns={@JoinColumn(name="roleId")})
	private Set<Role> roles;

	public User() {
		super();
		this.enabled = true;
		studentCheckpoints = new HashSet<Checkpoint>();
		//roles = new HashSet<String>();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Checkpoint> getStudentCheckpoints() {
		return studentCheckpoints;
	}

	public void setStudentCheckpoints(Set<Checkpoint> studentCheckpoints) {
		this.studentCheckpoints = studentCheckpoints;
	}

	public Department getMajor() {
		return major;
	}

	public void setMajor(Department major) {
		this.major = major;
	}

	public Plan getOfficialPlan() {
		return officialPlan;
	}

	public void setOfficialPlan(Plan officialPlan) {
		this.officialPlan = officialPlan;
	}

	public boolean isAdmin(){
		for (Iterator iterator = this.getRoles().iterator(); iterator.hasNext();) {
			Role fetchedRole = (Role) iterator.next();
			if(fetchedRole.getRoleName().trim().equalsIgnoreCase(RoleEnum.ADMIN.toString())){
				return true;
			}
		}	
		return false;
	}
	
	public boolean isStudent(){
		for (Iterator iterator = this.getRoles().iterator(); iterator.hasNext();) {
			Role fetchedRole = (Role) iterator.next();
			if(fetchedRole.getRoleName().trim().equalsIgnoreCase(RoleEnum.STUDENT.toString())){
				return true;
			}
		}	
		return false;
	}

	public boolean isAdvisor() {
		for (Iterator iterator = this.getRoles().iterator(); iterator.hasNext();) {
			Role fetchedRole = (Role) iterator.next();
			if(fetchedRole.getRoleName().trim().equalsIgnoreCase(RoleEnum.ADVISOR.toString())){
				return true;
			}
		}	
		return false;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for( Role role : roles )
            authorities.add( new SimpleGrantedAuthority( role.getRoleName()) );
        return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		 return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		 return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		 return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	
	
	
	
	
}
