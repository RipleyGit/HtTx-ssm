package me.seaOf.httx.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import me.seaOf.httx.pojo.Dept;
import me.seaOf.httx.pojo.User;
import me.seaOf.httx.pojo.UserInfo;
import me.seaOf.httx.service.DeptService;
import me.seaOf.httx.service.UserService;

@Controller
@RequestMapping("/sysadmin/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptService deptService;
	
	@RequestMapping("/list")
	public String findAll(Model model) {
		List<User> userList = userService.findAll();
		model.addAttribute("userList", userList);
		return "/sysadmin/user/jUserList";
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
		List<UserInfo> managerList = userService.findUserList();

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
		UserInfo userInfo = userService.findUserInfoById(userId);
		List<Dept> deptList = deptService.findAll();
		List<UserInfo> managerList = userService.findUserList();
		model.addAttribute("user", user);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("deptList", deptList);
		model.addAttribute("managerList", managerList);
		return "/sysadmin/user/jUserUpdate";
	}
	@RequestMapping("/update")
	public String updateUser(User user){
		
		userService.updateUser(user);
		
		return "redirect:/sysadmin/user/list";
	}
}
