package by.htp.eduard.service.converters;

import org.dozer.DozerConverter;

import by.htp.eduard.dao.UserDao;
import by.htp.eduard.dao.mysql.provider.DaoProvider;
import by.htp.eduard.entities.User;

public class UserIdNameConverter extends DozerConverter<Integer, String> {
	
	private final UserDao userDao;

	public UserIdNameConverter() {
		super(Integer.class, String.class);
		userDao = DaoProvider.getInstance().getUserDao();
	}

	@Override
	public String convertTo(Integer source, String destination) {		
		User user = userDao.getUserById(source);
		return user.getName();
	}

	@Override
	public Integer convertFrom(String source, Integer destination) {
		throw new UnsupportedOperationException();
	}
}
