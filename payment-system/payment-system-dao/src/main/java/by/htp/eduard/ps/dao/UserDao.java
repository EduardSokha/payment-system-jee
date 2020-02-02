package by.htp.eduard.ps.dao;

import java.util.List;

import by.htp.eduard.ps.dao.entities.User;

public interface UserDao {
	
	List<User> getAllUsers();
	User getUserById(Integer id);
	User getUserByLogin(String login);
	User saveUser(User user);
	User updateUser(User user);
	void deleteUser(Integer id);
	User signIn(User user);
	User forgetPassword(User user);

}
