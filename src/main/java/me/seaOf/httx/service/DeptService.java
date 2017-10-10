package me.seaOf.httx.service;

import java.util.List;

import me.seaOf.httx.pojo.Dept;

public interface DeptService {
	public List<Dept> findAll();

	// ״̬���޸�
	public void updateState(String[] deptIds, int state);

	// ����ɾ��
	public void deleteDepts(String[] deptIds);

	// ��������
	public void saveDept(Dept dept);

	// ���Ų�ѯ
	public Dept findDeptById(String deptId);

	// ��ѯ�ų��Լ�֮��Ĳ�����Ϣ
	public List<Dept> findParentDept(String deptId);

	// �޸Ĳ�����Ϣ
	public void updateDept(Dept dept);
}
