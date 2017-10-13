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
		//��ȡroleId
		role.setRoleId(UUID.randomUUID().toString());
		
		role.setCreateTime(new Date());
		roleMapper.saveRole(role);
	}
	/**
	 * ɾ����ɫ��Ϣ
	 * ��ɾ���û�������ɫ�е�����
	 * ��ɾ����ɫ���е�����
	 */
	@Override
	public void deleteRoleByRoleId(String roleId) {
		//ɾ���û��Ľ�ɫ��Ϣ
		roleMapper.deleteUserRoleByRoleId(roleId);
		//ɾ����ɫ�������Ϣ
		roleMapper.deleteRoleByRoleId(roleId);
	}

}
