package gefpmvc.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="persistent_logins")
public class Persistent_logins {
	@Id
	@GeneratedValue
	@Column(name="persistent_id")
	private Long persistent;
	
	private String username ;
	
	private String series ;
	
	private String token ;
	
	private Timestamp last_used ;

	public Long getPersistent() {
		return persistent;
	}

	public void setPersistent(Long persistent) {
		this.persistent = persistent;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getLast_used() {
		return last_used;
	}

	public void setLast_used(Timestamp last_used) {
		this.last_used = last_used;
	}
	
	
}
