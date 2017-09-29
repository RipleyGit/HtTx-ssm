package me.seaOf.httx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.seaOf.httx.mapper.UserMapper;
import me.seaOf.httx.pojo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	public List<User> getUserList() {
		return userMapper.selectUserList();
	}

}
