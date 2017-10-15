package me.seaOf.httx.pojo;

public class Module extends BaseEntity{
	private String moduleId;  //ģ��ID
	private Module parentModule; //����ģ��
	private String name;         //ģ������
	private Integer ctype;       //1���˵�/2���˵�/3��ť
	private Integer state;       //״̬   1 ���� 0.ͣ��
	private Integer orderNo;     //�����
	private String remark;       //��ע��Ϣ
	
	//ΪzTree����չ���ύ����
	private Boolean checked;    
	
	public String getId(){
		return moduleId;
	}
	
	public String getpId(){
		//��ȡ�ϼ�ģ���id
		if(parentModule !=null){
			return parentModule.getModuleId();
		}
		return "";
	}
	
	
	
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public Module getParentModule() {
		return parentModule;
	}
	public void setParentModule(Module parentModule) {
		this.parentModule = parentModule;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCtype() {
		return ctype;
	}
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getRemark() {
		return remark;
	}      
	public void setRemark(String remark) {
		this.remark = remark;
	}
  }