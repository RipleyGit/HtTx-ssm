package me.seaOf.httx.mapper;

import java.util.List;

import me.seaOf.httx.pojo.User;
import me.seaOf.httx.pojo.UserInfo;

public interface UserMapper {
	public List<User> findAll();

	public void saveUser(User user);

	public User findUserById(String userId);

	public UserInfo findUserInfoById(String userId);
}
