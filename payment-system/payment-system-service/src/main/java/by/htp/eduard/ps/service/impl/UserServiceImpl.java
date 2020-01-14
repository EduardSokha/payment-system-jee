package by.htp.eduard.service.impl;

import java.util.List;

import by.htp.eduard.dao.UserDao;
import by.htp.eduard.dao.mysql.provider.DaoProvider;
import by.htp.eduard.entities.ListAccountsAndCards;
import by.htp.eduard.entities.User;
import by.htp.eduard.service.UserService;

public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	
	public UserServiceImpl() {
		userDao = DaoProvider.getInstance().getUserDao();
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public List<ListAccountsAndCards> getAccountsAndCards() {
		return userDao.getAccountsAndCards();
	}

	@Override
	public User getUserById(Integer id) {
		return userDao.getUserById(id);
	}

	@Override
	public User saveUser(User user) {
		if(user.getId() == null) {
			return userDao.saveUser(user);
		}
		
		return userDao.updateUser(user);
	}

	@Override
	public void deleteUser(Integer id) {
		userDao.deleteUser(id);
	}
}
