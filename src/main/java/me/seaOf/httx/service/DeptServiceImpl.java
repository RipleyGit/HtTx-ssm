package me.seaOf.httx.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.seaOf.httx.mapper.DeptMapper;
import me.seaOf.httx.pojo.Dept;
@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptMapper deptMapper;
	@Override
	public List<Dept> findAll() {
		// TODO Auto-generated method stub
		return deptMapper.findAll();
	}
	@Override
	public void updateState(String[] deptIds, int state) {
		
		//由于Mybatis要求只能传递单值，需要将多值转化为单值
		deptMapper.updateState(deptIds,state);
		
	}

	@Override
	public void deleteDepts(String[] deptIds) {
		
		deptMapper.deleteDepts(deptIds);
		
	}

	@Override
	public void saveDept(Dept dept) {
		
		dept.setCreateTime(new Date());
		dept.setUpdateTime(dept.getCreateTime());
		deptMapper.saveDept(dept);
		
	}

	@Override
	public Dept findDeptById(String deptId) {
		
		return deptMapper.findDeptById(deptId);
	}

	@Override
	public List<Dept> findParentDept(String deptId) {
		
		return deptMapper.findParentDept(deptId);
	}

	@Override
	public void updateDept(Dept dept) {
		
		dept.setUpdateTime(new Date());
		deptMapper.updateDept(dept);
	}

}
