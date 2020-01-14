package by.htp.eduard.service.impl;

import java.util.List;

import by.htp.eduard.dao.RoleDao;
import by.htp.eduard.dao.mysql.provider.DaoProvider;
import by.htp.eduard.dto.RoleDto;
import by.htp.eduard.entities.Role;
import by.htp.eduard.service.EntityDtoConverter;
import by.htp.eduard.service.RoleService;
import by.htp.eduard.service.ServiceProvider;

public class RoleServiceImpl implements RoleService{
	
	private final RoleDao roleDao;
	
	private EntityDtoConverter converter;

	public RoleServiceImpl() {
		roleDao = DaoProvider.getInstance().getRoleDao();
		converter = ServiceProvider.getInstance().getEntityDtoConverter();
	}

	@Override
	public List<RoleDto> getAllRoles() {
		List<Role> allRoles = roleDao.getAllRoles();
		List<RoleDto> allRolesDto = converter.convertToDtoList(allRoles, RoleDto.class);
		return allRolesDto;
	}

	@Override
	public RoleDto getNameRoleById(Integer id) {
		Role roleEntity = roleDao.getNameRoleById(id);
		RoleDto roleDto = converter.convertToDto(roleEntity, RoleDto.class);
		return roleDto;
	}

	@Override
	public RoleDto saveRole(RoleDto roleDto) {
		Role role = converter.convertToEntity(roleDto, Role.class);
		
		if(role.getId() == null) {
			role = roleDao.saveRole(role);
			RoleDto dto = converter.convertToDto(role, RoleDto.class);
			return dto;
		}
		
		role = roleDao.updateNameRole(role);
		RoleDto dto = converter.convertToDto(role, RoleDto.class);
		return dto;
	}

	@Override
	public void deleteRole(Integer id) {
		roleDao.deleteRole(id);
	}
}
