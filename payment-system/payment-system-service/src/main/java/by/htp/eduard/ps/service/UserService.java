package by.htp.eduard.ps.service;

import java.util.List;

import by.htp.eduard.ps.service.dto.UserDto;

public interface UserService {
	
	List<UserDto> getAllUsers();
	UserDto getUserById(Integer id);
	boolean isLoginExists(String login);
	UserDto getUserByLogin(String login);
	UserDto saveUser(UserDto userDto);
	void deleteUser(Integer id);
	
}
