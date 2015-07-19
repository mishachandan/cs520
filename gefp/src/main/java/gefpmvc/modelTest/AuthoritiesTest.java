package gefpmvc.modelTest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="authorities_test")
public class AuthoritiesTest {

	
	@Id
	@GeneratedValue
	@Column(name="authorityid_pk")
	private int authorityId;
	
	
	@Column(name="authority")
	String authority;
	

	@OneToOne(targetEntity=UserT.class,mappedBy="authority")
	UserT username;

	
	
	public AuthoritiesTest() {
		
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public UserT getUsername() {
		return username;
	}

	public void setUsername(UserT username) {
		this.username = username;
	}
	
	
}
