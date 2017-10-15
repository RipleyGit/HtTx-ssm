package me.seaOf.httx.service;

import java.util.List;

import me.seaOf.httx.pojo.Role;



public interface RoleService {
	public List<Role> findAll();

	public void saveRole(Role role);

	public void deleteRoleByRoleId(String roleId);

	public void updateState(String[] roleIds, int state);
	
}
