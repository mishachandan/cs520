package gefpmvc.model.UI;

import gefpmvc.model.User;




public class UserUIModel {

	private Long userId;
	
	private String username;
	
	private String fname;
	
	private String lname;
	
	private String password;
	
	private String confirmPassword;
	
	private String emailid;

	private String address;
	
	private String cin;

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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	
	public User convertToUser(User user){
	

		user.setAddress(this.address);
		user.setEmailid(this.emailid);
		user.setFname(this.fname);
		user.setLname(this.lname);
		if(this.password.length()>0)
		user.setPassword(this.password);
		
		return user;
	}
	
	
	public void convertToUserUI(User user){
		this.setCin(user.getCin());
		this.setAddress(user.getAddress());
		this.setEmailid(user.getEmailid());
		this.setFname(user.getFname());
		this.setLname(user.getLname());
		this.setUsername(user.getUsername());
		this.setConfirmPassword(user.getPassword());
		this.setPassword(user.getPassword());
	}
}
