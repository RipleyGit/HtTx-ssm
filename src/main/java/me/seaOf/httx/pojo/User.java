package me.seaOf.httx.pojo;

public class User extends BaseEntity {
	private Dept dept;		//部门对象   一对一
	private UserInfo userInfo; //用户扩展信息表  一对一
	
	private String userId;   //用户id
	private String username;	//用户名
	private String password; 	//密码
	private Integer state; 		//状态   0停用  1启用
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
