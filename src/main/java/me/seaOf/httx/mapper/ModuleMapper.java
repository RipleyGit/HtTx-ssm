package me.seaOf.httx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import me.seaOf.httx.pojo.Module;

public interface ModuleMapper {

	List<Module> findAll();

	void saveModule(Module module);
	
	Module findModuleById(String moduleId);
	
	@Delete("delete from module_d where module_id=#{moduleId}")
	void deleteById(String moduleId);

	void updateState(String[] moduleIds, int state);

	void deleteModules(String[] moduleIds);

}
