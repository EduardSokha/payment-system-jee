package by.htp.eduard.ps.service.impl;

import java.util.List;

import by.htp.eduard.ps.dao.UserDao;
import by.htp.eduard.ps.dao.entities.User;
import by.htp.eduard.ps.dao.mysql.provider.DaoProvider;
import by.htp.eduard.ps.service.EntityDtoConverter;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.UserService;
import by.htp.eduard.ps.service.dto.UserDto;

public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	
	private EntityDtoConverter converter;
	
	public UserServiceImpl() {
		userDao = DaoProvider.getInstance().getUserDao();
		converter = ServiceProvider.getInstance().getEntityDtoConverter();
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allUsers = userDao.getAllUsers();
		List<UserDto> allUsersDto = converter.convertToDtoList(allUsers, UserDto.class);
		return allUsersDto;
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user = userDao.getUserById(id);
		UserDto userDto = converter.convertToDto(user, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		User user = converter.convertToEntity(userDto, User.class);
		if(user.getId() == null) {
			user = userDao.saveUser(user);
			UserDto dto = converter.convertToDto(user, UserDto.class);
			return dto;
		}
		user = userDao.updateUser(user);
		UserDto dto = converter.convertToDto(user, UserDto.class);
		return dto;
	}

	@Override
	public void deleteUser(Integer id) {
		userDao.deleteUser(id);
	}

	@Override
	public UserDto getUserByLogin(String login) {
		User user = userDao.getUserByLogin(login);
		UserDto dto = converter.convertToDto(user, UserDto.class);
		return dto;
	}

	@Override
	public boolean isLoginExists(String login) {
		UserDto dbUser = getUserByLogin(login);
		
		return (dbUser != null);
	}
}
