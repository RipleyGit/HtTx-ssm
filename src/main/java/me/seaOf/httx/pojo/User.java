package me.seaOf.httx.pojo;

public class User extends BaseEntity {
	private Dept dept;		//���Ŷ���   һ��һ
	private UserInfo userInfo; //�û���չ��Ϣ��  һ��һ
	
	private String userId;   //�û�id
	private String username;	//�û���
	private String password; 	//����
	private Integer state; 		//״̬   0ͣ��  1����
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
