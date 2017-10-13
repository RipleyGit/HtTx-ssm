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
	 * value="userId" ҳ���ύ������
	 * defaultValue   ���ҳ��û�д��ݲ���,��ʹ��Ĭ��ֵ
	 * required=true  ҳ���ύ���봫�ݸò���,���򱨴�.
	 * 
	 * @param userIds
	 * @return
	 */
	@RequestMapping("/delete")
	public String deleteUsers
			(@RequestParam(value="userId",required=true) String[] userIds){
		
		userService.deleteUsers(userIds);
		
		//ҳ����ת���û��б�ҳ�� ʹ���ض���  
		return "redirect:/sysadmin/user/list";
	}
	
	
	//״̬������
	@RequestMapping("/start")
	public String toStart(@RequestParam(value="userId",required=true)String[] userIds){
		
		int state = 1;
		userService.updateState(userIds,state);
		
		return "redirect:/sysadmin/user/list";
	}
	
	//״̬��ͣ��
	@RequestMapping("/stop")
	public String toStop(@RequestParam(value="userId",required=true)String[] userIds){
		int state = 0;
		userService.updateState(userIds,state);
		
		return "redirect:/sysadmin/user/list";
	}

	// ��ת���û�������ҳ��
	@RequestMapping("/tocreate")
	public String toCreate(Model model) {
		// ׼�������б���Ϣ
		List<Dept> deptList = deptService.findAll();

		// ׼���ϼ��쵼�б� Ҫ���ȡ userId��name����
		List<UserInfo> managerList = userService.findUserInfoList();

		model.addAttribute("deptList", deptList);
		model.addAttribute("managerList", managerList);

		return "/sysadmin/user/jUserCreate";
	}

	// �û�����
	@RequestMapping("/save")
	public String saveUser(User user) {
		userService.saveUser(user);
		return "redirect:/sysadmin/user/list";
	}
	//�û��޸�
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
	 * ���û������ɫ
	 * @throws JsonProcessingException 
	 */
	@RequestMapping("/toRole")
	private String toRole(String userId,Model model) throws JsonProcessingException {
		List<Role> roleList = roleService.findAll();
		
		//����userId��ѯ�û��Ѿ�ӵ�еĽ�ɫ��Ϣ
		List<String> userRoleList = userService.findRoleListByUserId(userId);
		
		for (Role role : roleList) {
			if(userRoleList.contains(role.getRoleId())) {
				//��ʾ�û��Ѿ�ӵ�иý�ɫ��Ϣ
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
	 * �����û���ɫ��Ϣ
	 */
	@RequestMapping("/saveUserRole")
	public String saveUserRole(String userId,String[] roleIds) {
		
		userService.saveUserRole(userId,roleIds);
		return "redirect:/sysadmin/user/list";
	}
}
