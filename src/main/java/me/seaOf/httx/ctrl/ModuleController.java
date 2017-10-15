package me.seaOf.httx.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.seaOf.httx.pojo.Module;
import me.seaOf.httx.service.ModuleService;


@Controller
@RequestMapping("/sysadmin/module")
public class ModuleController extends BaseController {
	@Autowired
	private ModuleService moduleService;
	
	/**
	 * 模块列表
	 */
	@RequestMapping("/list")
	public String moduleList(Model model) {
		List<Module> moduleList = moduleService.findAll();
		model.addAttribute("moduleList", moduleList);
		return "/sysadmin/module/jModuleList";
	}
	/**
	 * 模块新增
	 */
	@RequestMapping("/tocreate")
	public String toCreate(Model model) {
		List<Module> moduleList = moduleService.findAll();
		model.addAttribute("moduleList", moduleList);
		return "/sysadmin/module/jModuleCreate";
	}
	/**
	 * 根据前端发出的ctype改变响应
	 * @param ctype
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/chgType")
	public String chgType(Integer ctype) {
		List<Module> moduleList = moduleService.findAll();
		for (Module module : moduleList) {
			if(module.getCtype() >= ctype) {
				moduleList.remove(module);
			}
		}
		return "";
	}
	/**
	 * 保存模块
	 */
	@RequestMapping("save")
	public String saveModule(Module module) {
		moduleService.saveModule(module);
		return "redirect:/sysadmin/module/list";
	}
	/**
	 * 查看模块信息
	 * @param moduleId
	 * @param model
	 * @return
	 */
	@RequestMapping("/toview")
	public String toView(String moduleId,Model model) {
		Module module = moduleService.findModuleById(moduleId);
		model.addAttribute("modu", module);
		List<Module> moduleList = moduleService.findAll();
		model.addAttribute("moduleList", moduleList);
		return "/sysadmin/module/jModuleView";
	}

	// 状态的启用
	@RequestMapping("/start")
	public String toStart(@RequestParam(value = "moduleId", required = true) String[] moduleIds) {

		int state = 1;
		moduleService.updateState(moduleIds, state);

		return "redirect:/sysadmin/module/list";
	}

	// 状态的停用
	@RequestMapping("/stop")
	public String toStop(@RequestParam(value = "moduleId", required = true) String[] moduleIds) {
		int state = 0;
		moduleService.updateState(moduleIds, state);

		return "redirect:/sysadmin/module/list";
	}
	/**
	 * 根据模板id删除
	 * @param moduleIds
	 * @return
	 */
	@RequestMapping("/delete")
	public String deleteModules
			(@RequestParam(value="moduleId",required=true) String[] moduleIds){
		
		moduleService.deleteModules(moduleIds);
		
		//页面跳转到用户列表页面 使用重定向  
		return "redirect:/sysadmin/module/list";
	}
}
