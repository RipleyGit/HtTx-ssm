package me.seaOf.httx.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import me.seaOf.httx.pojo.Role;
import me.seaOf.httx.service.RoleService;

@Controller
@RequestMapping("/sysadmin/role")
public class RoleController extends BaseController {
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 角色列表展示
	 */
	@RequestMapping("/list")
	public String toList(Model model) {
		List<Role> roleList = roleService.findAll();
		model.addAttribute("roleList", roleList);
		return "/sysadmin/role/jRoleList";
	}
	/**
	 * 角色创建页面
	 */
	@RequestMapping("/tocreate")
	public String toCreate() {
		return "/sysadmin/role/jRoleCreate";
	}
	/**
	 * 新增角色
	 */
	@RequestMapping("/save")
	public String saveRole(Role role) {
		roleService.saveRole(role);
		return "redirect:/sysadmin/role/list";
	}
	/**
	 * 删除角色信息
	 */
	@RequestMapping("/delete")
	public String doDelete(@RequestParam(value="roleId",required=true) String[] roleIds) {
		for (String roleId : roleIds) {
			roleService.deleteRoleByRoleId(roleId);
		}
		return "redirect:/sysadmin/role/list";
		
	}
	//状态的启用
		@RequestMapping("/start")
		public String toStart(@RequestParam(value="roleId",required=true)String[] roleIds){
			
			int state = 1;
			roleService.updateState(roleIds,state);
			
			return "redirect:/sysadmin/user/list";
		}
		
		//状态的停用
		@RequestMapping("/stop")
		public String toStop(@RequestParam(value="roleId",required=true)String[] roleIds){
			int state = 0;
			roleService.updateState(roleIds,state);
			
			return "redirect:/sysadmin/user/list";
		}
	/**
	 * 分配模块信息
	 */
	@RequestMapping("/toModule")
	public String toModule() {
		//准备全部的模块列表信息
		
		//
		return "sysadmin/role/jRoleModule";
	}
}
