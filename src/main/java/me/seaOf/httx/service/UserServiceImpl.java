package me.seaOf.httx.service;

import java.util.Date;
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
		// TODO Auto-generated method stub
		return userMapper.findAll();
	}
	@Override
	public List<UserInfo> findUserList() {
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
		
		//��Ҫͬʱ���  ��������������������ӱ�
		userMapper.saveUser(user);
		userInfoMapper.saveUserInfo(info);
	}
	@Override
	public User findUserById(String userId) {
		// TODO Auto-generated method stub
		return userMapper.findUserById(userId);
	}
	@Override
	public UserInfo findUserInfoById(String userId) {
		// TODO Auto-generated method stub
		return userMapper.findUserInfoById(userId);
	}

}