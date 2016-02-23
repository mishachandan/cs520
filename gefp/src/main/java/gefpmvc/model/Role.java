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
@Table(name="role")
public class Role {

	@Id
	@GeneratedValue
	@Column(name="role_id")
	private Integer roleId;
	
	@Column(name="role_name",nullable=true)
	private String roleName;
	
	@ManyToMany
	@JoinTable( name="user_roles",
	joinColumns={@JoinColumn(name="roleId")},
	inverseJoinColumns={@JoinColumn(name="userId")})
	private Set<User> user ;

	@ManyToMany
	@JoinTable(name="roles_privileges",
				joinColumns={@JoinColumn(name="roleId")},
				inverseJoinColumns={@JoinColumn(name="privilegeId")})
	private Set<Privileges> privileges;
	
	
	public Role() {
		super();
		user = new HashSet<User>();
		privileges = new HashSet<Privileges>();
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	public Set<Privileges> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privileges> privileges) {
		this.privileges = privileges;
	}

	
	
}
