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

	// deptId=100,200,300,Ϊ�������������������ʹ��@RequestParamע�⣬�����޸Ĳ�������
	// ״̬��ͣ��
	@RequestMapping("/stop")
	public String toStop(@RequestParam("deptId") String[] deptIds) {
		int state = 0; // ͣ��
		deptService.updateState(deptIds, state);

		// �ض����û��б�ҳ��
		return "redirect:/sysadmin/dept/list";
	}

	// ״̬������
	@RequestMapping("/start")
	public String toStart(@RequestParam("deptId") String[] deptIds) {
		int state = 1; // ����

		deptService.updateState(deptIds, state);

		// �ض����û��б�ҳ��
		return "redirect:/sysadmin/dept/list";
	}

	// ����ɾ��
	@RequestMapping("/delete")
	public String toDelete(@RequestParam("deptId") String[] deptIds) {

		deptService.deleteDepts(deptIds);

		return "redirect:/sysadmin/dept/list";
	}

	// ����ҳ�����ת
	@RequestMapping("/tocreate")
	public String tocreate(Model model) {

		// ׼���ϼ����ŵ��б���Ϣ ��Ҫ�ϼ���Id������
		List<Dept> parentDeptList = deptService.findAll();
		model.addAttribute("parentDeptList", parentDeptList);

		return "/sysadmin/dept/jDeptCreate";
	}

	// ���ŵ�����
	@RequestMapping("/save")
	public String saveDept(Dept dept) {

		deptService.saveDept(dept);

		return "redirect:/sysadmin/dept/list";

	}

	// ���Ų鿴
	@RequestMapping("/toview")
	public String findDept(String deptId, Model model) {

		// ����deptId��ѯ����
		Dept dept = deptService.findDeptById(deptId);
		model.addAttribute("dept", dept);

		// ��ת�����Ų鿴ҳ��
		return "/sysadmin/dept/jDeptView";
	}

	// ת�����޸�
	@RequestMapping("/toupdate")
	public String toUpdateDept(String deptId, Model model) {

		Dept dept = deptService.findDeptById(deptId);
		model.addAttribute("dept", dept);

		// ׼���ϼ������б���Ϣ ���в��ܰ����Լ�
		List<Dept> parentList = deptService.findParentDept(deptId);
		model.addAttribute("parentList", parentList);

		// ת�򵽲����޸�ҳ��
		return "/sysadmin/dept/jDeptUpdate";

	}

	// ���ŵ��޸�
	@RequestMapping("/update")
	public String updateDept(Dept dept) {

		deptService.updateDept(dept);

		return "redirect:/sysadmin/dept/list";
	}
		
}
