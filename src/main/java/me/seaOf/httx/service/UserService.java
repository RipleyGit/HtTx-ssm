package me.seaOf.httx.service;

import java.util.List;

import me.seaOf.httx.pojo.User;
import me.seaOf.httx.pojo.UserInfo;

public interface UserService {
	public List<User> findAll();

	public List<UserInfo> findUserInfoList();

	public void saveUser(User user);

	public User findUserById(String userId);

	public UserInfo findUserInfoById(String userId);

	public void updateUser(User user);

	public void deleteUsers(String[] userIds);

	public void updateState(String[] userIds, int state);
}
