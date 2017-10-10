package me.seaOf.httx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.seaOf.httx.pojo.Dept;

public interface DeptMapper {
	
	public List<Dept> findAll();
	//ͨ��@Paramע�⽫���ݷ�װΪһ��map   Map = {deptIds:[1,2,3,4],state=0}
	public void updateState(@Param("deptIds") String[] deptIds, @Param("state") int state);

	// ����ɾ��
	public void deleteDepts(String[] deptIds);

	// ��ʾ���ŵ�����
	public void saveDept(Dept dept);

	// ����deptId��ѯ������Ϣ
	public Dept findDeptById(String deptId);

	// ��ѯ�ų��Լ�֮�������
	public List<Dept> findParentDept(String deptId);

	// �޸Ĳ�����Ϣ
	public void updateDept(Dept dept);
}
