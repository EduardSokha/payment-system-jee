package by.htp.eduard.ps.service;

import java.util.List;

import by.htp.eduard.entities.ListAccountsAndCards;
import by.htp.eduard.entities.User;

public interface UserService {
	
	List<User> getAllUsers();
	List<ListAccountsAndCards> getAccountsAndCards();
	User getUserById(Integer id);
	User saveUser(User user);
	void deleteUser(Integer id);
	
}
