package me.seaOf.httx.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.seaOf.httx.mapper.RoleMapper;
import me.seaOf.httx.pojo.Role;


@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Role> findAll() {
		
		return roleMapper.findAll();
	}

	@Override
	public void saveRole(Role role) {
		//获取roleId
		role.setRoleId(UUID.randomUUID().toString());
		
		role.setCreateTime(new Date());
		roleMapper.saveRole(role);
	}
	/**
	 * 删除角色信息
	 * 先删除用户关联角色中的数据
	 * 再删除角色表中的数据
	 */
	@Override
	public void deleteRoleByRoleId(String roleId) {
		//删除用户的角色信息
		roleMapper.deleteUserRoleByRoleId(roleId);
		//删除角色表里的信息
		roleMapper.deleteRoleByRoleId(roleId);
	}

}
