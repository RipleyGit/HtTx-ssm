package me.seaOf.httx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import me.seaOf.httx.pojo.Role;


public interface RoleMapper {
	
	@Select("select * from role_p ")
	public List<Role> findAll();
	
	public void saveRole(Role role);
	@Delete("delete from role_user_p where role_id=#{roleId}")
	public void deleteUserRoleByRoleId(String roleId);
	@Delete("delete from role_p where role_id=#{roleId}")
	public void deleteRoleByRoleId(String roleId);
	
}
