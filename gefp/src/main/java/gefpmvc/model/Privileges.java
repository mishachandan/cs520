package gefpmvc.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="privilege")
public class Privileges {

	@Id
	@GeneratedValue
	@Column(name="privilege_id")
	private Integer privilegeId;
	
	@Column(name="decription",nullable=false)
	private String decription;

	@ManyToMany
	@JoinTable(name="roles_privileges",
				inverseJoinColumns={@JoinColumn(name="roleId")},
				joinColumns={@JoinColumn(name="privilegeId")})
	private Set<Role> roles;
	
	
	public Privileges() {
		super();
		roles = new HashSet<Role>();
	}

	public Integer getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
