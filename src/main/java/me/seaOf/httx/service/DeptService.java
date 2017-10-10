package me.seaOf.httx.service;

import java.util.List;

import me.seaOf.httx.pojo.Dept;

public interface DeptService {
	public List<Dept> findAll();

	// 状态的修改
	public void updateState(String[] deptIds, int state);

	// 批量删除
	public void deleteDepts(String[] deptIds);

	// 新增部门
	public void saveDept(Dept dept);

	// 部门查询
	public Dept findDeptById(String deptId);

	// 查询排除自己之外的部门信息
	public List<Dept> findParentDept(String deptId);

	// 修改部门信息
	public void updateDept(Dept dept);
}
