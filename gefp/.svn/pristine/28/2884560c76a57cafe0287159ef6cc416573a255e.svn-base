package gefpmvc.modelTest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users_test")
public class UserT {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String username;

    private String password;

    private boolean enabled = true;

    @OneToOne
    @JoinColumn(name="authorityid_fk",unique=true)
    private AuthoritiesTest authority;
    
    public UserT()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername( String username )
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled( boolean enabled )
    {
        this.enabled = enabled;
    }

   
	public AuthoritiesTest getAuthority() {
		return authority;
	}

	public void setAuthority(AuthoritiesTest authority) {
		this.authority = authority;
	}

    
}