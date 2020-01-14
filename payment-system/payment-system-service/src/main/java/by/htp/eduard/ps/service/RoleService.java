package by.htp.eduard.ps.service;

import java.util.List;

import by.htp.eduard.ps.service.dto.RoleDto;

public interface RoleService {

	List<RoleDto> getAllRoles();
	RoleDto getNameRoleById(Integer id);
	RoleDto saveRole(RoleDto roleDto);
	void deleteRole(Integer id);

}
