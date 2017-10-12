package me.seaOf.httx.pojo;

public class Role extends BaseEntity{
	private String roleId;
	private String name;
	private String remarks;
	private Integer orderNo;
	private Boolean checked;  //为了zTree树的结构要求
	public String getRoleId() {
		return roleId;
	}
	
	//为了满足zTree树的格式要求 id name checked 需要添加getXX()方法
	public String getId(){
		return this.roleId;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
		
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	
	
	
	
	
}
