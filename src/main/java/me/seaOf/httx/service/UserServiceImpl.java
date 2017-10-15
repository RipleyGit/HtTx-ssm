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
	 * 新增操作时，需要同时操作2张表   
	 * 主表 user_p   从表user_info_p
	 * 需要同时操作2个对象
	 */
	@Override
	public void saveUser(User user) {
		//表示获取随机Id值 保证UserId不相同
		String userId = UUID.randomUUID().toString();
		
		user.setUserId(userId);
		user.setCreateTime(new Date());
		user.setUpdateTime(user.getCreateTime());
		
		
		UserInfo info = user.getUserInfo();
		info.setUserInfoId(userId);
		info.setCreateTime(user.getCreateTime());
		info.setUpdateTime(user.getUpdateTime());
		
		//需要同时入库  规则：先入库主表，再入库从表
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
		
		//添加修改日期
		user.setUpdateTime(new Date());
		
		//info对象添加修改日期
		userInfo.setUpdateTime(user.getUpdateTime());
		//为UserInfoId赋值
		userInfo.setUserInfoId(user.getUserId());
		
		userMapper.updateUser(user);
		userInfoMapper.updateUser(userInfo);
		
	}
	@Override
	public void deleteUsers(String[] userIds) {
		//先删除从表数据,再删主表,这样能够在有主外键约束的表中进行正确删除
		userInfoMapper.deleteUserInfos(userIds);
		
		//删除主表数据
		userMapper.deleteUsers(userIds);
		
		//删除相应的用户权限
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
		//防止重付提交 先删除原来的数据
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
