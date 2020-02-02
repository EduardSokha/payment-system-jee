package by.htp.eduard.ps.service.impl;

import by.htp.eduard.ps.dao.UserDao;
import by.htp.eduard.ps.dao.entities.User;
import by.htp.eduard.ps.dao.mysql.provider.DaoProvider;
import by.htp.eduard.ps.service.AuthenticationService;
import by.htp.eduard.ps.service.EntityDtoConverter;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.AuthenticationDto;
import by.htp.eduard.ps.service.dto.UserDto;

public class AuthenticationServiceImpl implements AuthenticationService {
	
	private final UserDao userDao;
	
	private EntityDtoConverter converter;

	public AuthenticationServiceImpl() {
		userDao = DaoProvider.getInstance().getUserDao();
		converter = ServiceProvider.getInstance().getEntityDtoConverter();
	}

	@Override
	public UserDto signIn(AuthenticationDto authenticationDto) {
		User user = converter.convertToEntity(authenticationDto, User.class);
		User userResp = userDao.signIn(user);
		UserDto userDto = converter.convertToDto(userResp, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto forgetPassword(AuthenticationDto authenticationDto) {
		User user = converter.convertToEntity(authenticationDto, User.class);
		User userResp = userDao.forgetPassword(user);
		UserDto userDto = converter.convertToDto(userResp, UserDto.class);
		return userDto;
	}
}
