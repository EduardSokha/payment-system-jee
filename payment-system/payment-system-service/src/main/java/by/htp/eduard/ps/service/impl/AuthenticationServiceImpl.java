package by.htp.eduard.ps.service.impl;

import by.htp.eduard.ps.dao.AuthenticationDao;
import by.htp.eduard.ps.dao.entities.Authentication;
import by.htp.eduard.ps.dao.entities.User;
import by.htp.eduard.ps.dao.mysql.provider.DaoProvider;
import by.htp.eduard.ps.service.AuthenticationService;
import by.htp.eduard.ps.service.EntityDtoConverter;
import by.htp.eduard.ps.service.ServiceProvider;
import by.htp.eduard.ps.service.dto.AuthenticationDto;
import by.htp.eduard.ps.service.dto.UserDto;

public class AuthenticationServiceImpl implements AuthenticationService {
	
	private final AuthenticationDao authenticationDao;
	
	private EntityDtoConverter converter;

	public AuthenticationServiceImpl() {
		authenticationDao = DaoProvider.getInstance().getAuthenticationDao();
		converter = ServiceProvider.getInstance().getEntityDtoConverter();
	}

	@Override
	public UserDto signIn(AuthenticationDto authenticationDto) {
		Authentication authentication = converter.convertToEntity(authenticationDto, Authentication.class);
		User user = authenticationDao.signIn(authentication);
		UserDto userDto = converter.convertToDto(user, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto forgetPassword(AuthenticationDto authenticationDto) {
		Authentication authentication = converter.convertToEntity(authenticationDto, Authentication.class);
		User user = authenticationDao.forgetPassword(authentication);
		UserDto userDto = converter.convertToDto(user, UserDto.class);
		return userDto;
	}

}
