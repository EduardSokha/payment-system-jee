package by.htp.eduard.ps.service.converters;

import org.dozer.DozerConverter;

import by.htp.eduard.ps.dao.RoleDao;
import by.htp.eduard.ps.dao.entities.Role;
import by.htp.eduard.ps.dao.mysql.provider.DaoProvider;

public class RoleIdNameConverter extends DozerConverter<Integer, String>{
	
	private final RoleDao roleDao;

	public RoleIdNameConverter() {
		super(Integer.class, String.class);
		roleDao = DaoProvider.getInstance().getRoleDao();
	}

	@Override
	public String convertTo(Integer source, String destination) {
		Role role = roleDao.getNameRoleById(source);
		return role.getName();
	}

	@Override
	public Integer convertFrom(String source, Integer destination) {
		throw new UnsupportedOperationException();
	}

}
