package me.seaOf.httx.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.seaOf.httx.pojo.Dept;
import me.seaOf.httx.pojo.Role;
import me.seaOf.httx.pojo.User;
import me.seaOf.httx.pojo.UserInfo;
import me.seaOf.httx.service.DeptService;
import me.seaOf.httx.service.RoleService;
import me.seaOf.httx.service.UserService;

@Controller
@RequestMapping("/sysadmin/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/list")
	public String findAll(Model model) {
		List<User> userList = userService.findAll();
		model.addAttribute("userList", userList);
		return "/sysadmin/user/jUserList";
	}
	
	@RequestMapping("/toview")
	public String toView(String userId,Model model){
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		return "/sysadmin/user/jUserView";
	}
	/**@RequestParam
	 * value="userId" 页面提交的名称
	 * defaultValue   如果页面没有传递参数,则使用默认值
	 * required=true  页面提交必须传递该参数,否则报错.
	 * 
	 * @param userIds
	 * @return
	 */
	@RequestMapping("/delete")
	public String deleteUsers
			(@RequestParam(value="userId",required=true) String[] userIds){
		
		userService.deleteUsers(userIds);
		
		//页面跳转到用户列表页面 使用重定向  
		return "redirect:/sysadmin/user/list";
	}
	
	
	//状态的启用
	@RequestMapping("/start")
	public String toStart(@RequestParam(value="userId",required=true)String[] userIds){
		
		int state = 1;
		userService.updateState(userIds,state);
		
		return "redirect:/sysadmin/user/list";
	}
	
	//状态的停用
	@RequestMapping("/stop")
	public String toStop(@RequestParam(value="userId",required=true)String[] userIds){
		int state = 0;
		userService.updateState(userIds,state);
		
		return "redirect:/sysadmin/user/list";
	}

	// 跳转到用户的新增页面
	@RequestMapping("/tocreate")
	public String toCreate(Model model) {
		// 准备部门列表信息
		List<Dept> deptList = deptService.findAll();

		// 准备上级领导列表 要求获取 userId和name属性
		List<UserInfo> managerList = userService.findUserInfoList();

		model.addAttribute("deptList", deptList);
		model.addAttribute("managerList", managerList);

		return "/sysadmin/user/jUserCreate";
	}

	// 用户新增
	@RequestMapping("/save")
	public String saveUser(User user) {
		userService.saveUser(user);
		return "redirect:/sysadmin/user/list";
	}
	//用户修改
	@RequestMapping("/toupdate")
	public String toUpdate(String userId, Model model) {
		User user = userService.findUserById(userId);
//		UserInfo userInfo = userService.findUserInfoById(userId);
		List<Dept> deptList = deptService.findAll();
		List<UserInfo> managerList = userService.findUserInfoList();
		model.addAttribute("user", user);
//		model.addAttribute("userInfo", userInfo);
		model.addAttribute("deptList", deptList);
		model.addAttribute("managerList", managerList);
		return "/sysadmin/user/jUserUpdate";
	}
	@RequestMapping("/update")
	public String updateUser(User user){
		
		userService.updateUser(user);
		
		return "redirect:/sysadmin/user/list";
	}
	/**
	 * 给用户分配角色
	 * @throws JsonProcessingException 
	 */
	@RequestMapping("/toRole")
	private String toRole(String userId,Model model) throws JsonProcessingException {
		List<Role> roleList = roleService.findAll();
		
		//根据userId查询用户已经拥有的角色信息
		List<String> userRoleList = userService.findRoleListByUserId(userId);
		
		for (Role role : roleList) {
			if(userRoleList.contains(role.getRoleId())) {
				//表示用户已经拥有该角色信息
				role.setChecked(true);
			}
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		String zTreeJSON = objectMapper.writeValueAsString(roleList);
		model.addAttribute("userId", userId);
		model.addAttribute("zTreeJSON", zTreeJSON);
		return "/sysadmin/user/jUserRole";
	}
	/**
	 * 保存用户角色信息
	 */
	@RequestMapping("/saveUserRole")
	public String saveUserRole(String userId,String[] roleIds) {
		
		userService.saveUserRole(userId,roleIds);
		return "redirect:/sysadmin/user/list";
	}
}
