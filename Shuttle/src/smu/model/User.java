package smu.model;

public class User {
	private String userId;
	private String userPass;
	private String userName;
	private String userDept;
	private String userArea;
	public User(String userId, String userPass, String userName, String userDept, String userArea) {
		super();
		this.userId = userId;
		this.userPass = userPass;
		this.userName = userName;
		this.userDept = userDept;
		this.userArea = userArea;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userPass=" + userPass + ", userName=" + userName + ", userDept=" + userDept
				+ ", userArea=" + userArea + "]";
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserDept() {
		return userDept;
	}
	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}
	public String getUserArea() {
		return userArea;
	}
	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}
	
	
	
}
