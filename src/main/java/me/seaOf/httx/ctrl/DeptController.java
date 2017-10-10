package me.seaOf.httx.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import me.seaOf.httx.pojo.Dept;
import me.seaOf.httx.service.DeptService;

@Controller
@RequestMapping("sysadmin/dept")
public class DeptController {
	@Autowired
	private DeptService deptService;
	
	@RequestMapping("/list")
	public String getDeptList(Model model) {
		List<Dept> list = deptService.findAll();
		model.addAttribute("deptList", list);
		return "sysadmin/dept/jDeptList";
	}

	// deptId=100,200,300,为了满足参数的命名规则使用@RequestParam注解，可以修改参数名称
	// 状态的停用
	@RequestMapping("/stop")
	public String toStop(@RequestParam("deptId") String[] deptIds) {
		int state = 0; // 停用
		deptService.updateState(deptIds, state);

		// 重定向到用户列表页面
		return "redirect:/sysadmin/dept/list";
	}

	// 状态的启用
	@RequestMapping("/start")
	public String toStart(@RequestParam("deptId") String[] deptIds) {
		int state = 1; // 启用

		deptService.updateState(deptIds, state);

		// 重定向到用户列表页面
		return "redirect:/sysadmin/dept/list";
	}

	// 批量删除
	@RequestMapping("/delete")
	public String toDelete(@RequestParam("deptId") String[] deptIds) {

		deptService.deleteDepts(deptIds);

		return "redirect:/sysadmin/dept/list";
	}

	// 新增页面的跳转
	@RequestMapping("/tocreate")
	public String tocreate(Model model) {

		// 准备上级部门的列表信息 需要上级的Id和名称
		List<Dept> parentDeptList = deptService.findAll();
		model.addAttribute("parentDeptList", parentDeptList);

		return "/sysadmin/dept/jDeptCreate";
	}

	// 部门的新增
	@RequestMapping("/save")
	public String saveDept(Dept dept) {

		deptService.saveDept(dept);

		return "redirect:/sysadmin/dept/list";

	}

	// 部门查看
	@RequestMapping("/toview")
	public String findDept(String deptId, Model model) {

		// 根据deptId查询数据
		Dept dept = deptService.findDeptById(deptId);
		model.addAttribute("dept", dept);

		// 跳转到部门查看页面
		return "/sysadmin/dept/jDeptView";
	}

	// 转向部门修改
	@RequestMapping("/toupdate")
	public String toUpdateDept(String deptId, Model model) {

		Dept dept = deptService.findDeptById(deptId);
		model.addAttribute("dept", dept);

		// 准备上级部门列表信息 其中不能包含自己
		List<Dept> parentList = deptService.findParentDept(deptId);
		model.addAttribute("parentList", parentList);

		// 转向到部门修改页面
		return "/sysadmin/dept/jDeptUpdate";

	}

	// 部门的修改
	@RequestMapping("/update")
	public String updateDept(Dept dept) {

		deptService.updateDept(dept);

		return "redirect:/sysadmin/dept/list";
	}
		
}
