package by.htp.eduard.ps.service.converters;

import org.dozer.DozerConverter;

import by.htp.eduard.ps.dao.UserDao;
import by.htp.eduard.ps.dao.entities.User;
import by.htp.eduard.ps.dao.mysql.provider.DaoProvider;

public class UserIdNameConverter extends DozerConverter<Integer, String> {
	
	private final UserDao userDao;

	public UserIdNameConverter() {
		super(Integer.class, String.class);
		userDao = DaoProvider.getInstance().getUserDao();
	}

	@Override
	public String convertTo(Integer source, String destination) {		
		User user = userDao.getUserById(source);
		return user.getLogin();
	}

	@Override
	public Integer convertFrom(String source, Integer destination) {
		throw new UnsupportedOperationException();
	}
}
