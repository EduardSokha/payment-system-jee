package by.htp.eduard.ps.dao;

import java.util.List;

import by.htp.eduard.ps.dao.entities.Role;

public interface RoleDao {
	
	List<Role> getAllRoles();
	Role getNameRoleById(Integer id);
	Role saveRole(Role role);
	Role updateNameRole(Role role);
	void deleteRole(Integer id);

}
