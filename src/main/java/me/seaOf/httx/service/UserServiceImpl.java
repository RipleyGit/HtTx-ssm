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
		// TODO Auto-generated method stub
		return userMapper.findUserById(userId);
	}
	@Override
	public UserInfo findUserInfoById(String userId) {
		// TODO Auto-generated method stub
		return userMapper.findUserInfoById(userId);
	}

}
