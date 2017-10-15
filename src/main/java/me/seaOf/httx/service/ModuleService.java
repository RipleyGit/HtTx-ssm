package me.seaOf.httx.service;

import java.util.List;

import me.seaOf.httx.pojo.Module;

public interface ModuleService {

	List<Module> findAll();

	void saveModule(Module module);

	Module findModuleById(String moduleId);

	void deleteById(String moduleId);
	
	void updateState(String[] moduleIds, int state);

	void deleteModules(String[] moduleIds);

}
