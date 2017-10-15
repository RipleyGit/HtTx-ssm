package me.seaOf.httx.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.seaOf.httx.mapper.UserInfoMapper;
import me.seaOf.httx.mapper.UserMapper;
import me.seaOf.httx.pojo.User;
import me.seaOf.httx.pojo.UserInfo;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}
	@Override
	public List<UserInfo> findUserInfoList() {
		// TODO Auto-generated method stub
		return userInfoMapper.findUserInfoList();
	}
	
	
	/**
	 * ��������ʱ����Ҫͬʱ����2�ű�   
	 * ���� user_p   �ӱ�user_info_p
	 * ��Ҫͬʱ����2������
	 */
	@Override
	public void saveUser(User user) {
		//��ʾ��ȡ���Idֵ ��֤UserId����ͬ
		String userId = UUID.randomUUID().toString();
		
		user.setUserId(userId);
		user.setCreateTime(new Date());
		user.setUpdateTime(user.getCreateTime());
		
		
		UserInfo info = user.getUserInfo();
		info.setUserInfoId(userId);
		info.setCreateTime(user.getCreateTime());
		info.setUpdateTime(user.getUpdateTime());
		
		//��Ҫͬʱ���  ������������������ӱ�
		userMapper.saveUser(user);
		userInfoMapper.saveUserInfo(info);
	}
	@Override
	public User findUserById(String userId) {
		return userMapper.findUserById(userId);
	}
	@Override
	public UserInfo findUserInfoById(String userId) {
		return userMapper.findUserInfoById(userId);
	}
	@Override
	public void updateUser(User user) {
		
		UserInfo userInfo = user.getUserInfo();
		
		//����޸�����
		user.setUpdateTime(new Date());
		
		//info��������޸�����
		userInfo.setUpdateTime(user.getUpdateTime());
		//ΪUserInfoId��ֵ
		userInfo.setUserInfoId(user.getUserId());
		
		userMapper.updateUser(user);
		userInfoMapper.updateUser(userInfo);
		
	}
	@Override
	public void deleteUsers(String[] userIds) {
		//��ɾ���ӱ�����,��ɾ����,�����ܹ����������Լ���ı��н�����ȷɾ��
		userInfoMapper.deleteUserInfos(userIds);
		
		//ɾ����������
		userMapper.deleteUsers(userIds);
		
		//ɾ����Ӧ���û�Ȩ��
		for (String userId : userIds) {
			userMapper.deleteUserRoles(userId);
		}
	}


	@Override
	public void updateState(String[] userIds, int state) {
		
		userMapper.updateState(userIds,state);
		
	}
	@Override
	public void saveUserRole(String userId, String[] roleIds) {
		//��ֹ�ظ��ύ ��ɾ��ԭ��������
		userMapper.deleteUserRoles(userId);
		
		for (String roleId : roleIds) {
			userMapper.saveUserRole(userId,roleId);
		}
	}
	@Override
	public List<String> findRoleListByUserId(String userId) {
		 return userMapper.findRoleListByUserId(userId);
	
	}
	@Override
	public User findUserByU_P(String username, String password) {
		
		return userMapper.findUserByU_P(username,password);
	}
	
}
