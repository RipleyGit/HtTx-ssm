package me.seaOf.httx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import me.seaOf.httx.pojo.User;
import me.seaOf.httx.pojo.UserInfo;

public interface UserMapper {
	public List<User> findAll();

	public void saveUser(User user);

	public User findUserById(String userId);

	public UserInfo findUserInfoById(String userId);

	public void updateUser(User user);

	public void deleteUsers(String[] userIds);

	public void updateState(String[] userIds, int state);
	@Insert("insert into role_user_p(user_id,role_id) values(#{param1},#{param2})")
	public void saveUserRole(String userId, String roleId);
	@Delete("delete from role_user_p where user_id = #{userId}")
	public void deleteUserRoles(String userId);
	@Select("select role_id from role_user_p where user_id = #{userId}")
	public List<String> findRoleListByUserId(String userId);
	
	@Select("select * from user_p where username=#{param1} and password=#{param2}")
	public User findUserByU_P(String username, String password);
}
