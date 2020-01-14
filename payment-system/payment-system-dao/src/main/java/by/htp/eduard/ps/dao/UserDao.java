package by.htp.eduard.ps.dao;

import java.util.List;

import by.htp.eduard.ps.dao.entities.ListAccountsAndCards;
import by.htp.eduard.ps.dao.entities.User;

public interface UserDao {
	
	List<User> getAllUsers();
	List<ListAccountsAndCards> getAccountsAndCards();
	User getUserById(Integer id);
	User saveUser(User user);
	User updateUser(User user);
	void deleteUser(Integer id);

}
