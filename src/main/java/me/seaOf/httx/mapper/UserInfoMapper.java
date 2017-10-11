package me.seaOf.httx.mapper;

import java.util.List;

import me.seaOf.httx.pojo.UserInfo;

public interface UserInfoMapper {
	
	public List<UserInfo> findUserInfoList();
	
	public void saveUserInfo(UserInfo userInfo);
	
}
