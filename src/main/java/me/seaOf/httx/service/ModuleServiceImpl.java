package me.seaOf.httx.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.seaOf.httx.mapper.ModuleMapper;
import me.seaOf.httx.pojo.Module;
@Service
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	private ModuleMapper moduleMapper;
	@Override
	public List<Module> findAll() {
		return moduleMapper.findAll();
	}
	@Override
	public void saveModule(Module module) {
		module.setModuleId(UUID.randomUUID().toString());
		module.setCreateTime(new Date());
		moduleMapper.saveModule(module);
	}
	@Override
	public Module findModuleById(String moduleId) {
		return moduleMapper.findModuleById(moduleId);
	}
	@Override
	public void deleteById(String moduleId) {
		moduleMapper.deleteById(moduleId);
		
	}
	@Override
	public void updateState(String[] moduleIds, int state) {
		moduleMapper.updateState(moduleIds,state);
	}
	@Override
	public void deleteModules(String[] moduleIds) {
		moduleMapper.deleteModules(moduleIds);
	}

}
