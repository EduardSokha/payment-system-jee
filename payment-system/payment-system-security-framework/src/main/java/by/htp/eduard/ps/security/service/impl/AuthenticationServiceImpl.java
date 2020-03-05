package by.htp.eduard.ps.security.service.impl;

import by.htp.eduard.ps.dao.UserDao;
import by.htp.eduard.ps.dao.entities.User;
import by.htp.eduard.ps.dao.mysql.provider.DaoProvider;
import by.htp.eduard.ps.security.config.SecurityConfig;
import by.htp.eduard.ps.security.dto.AuthenticationDto;
import by.htp.eduard.ps.security.service.AuthenticationService;
import by.htp.eduard.ps.service.EntityDtoConverter;
import by.htp.eduard.ps.service.ServiceProvider;
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
		User userResp = userDao.getUserByLogin(authenticationDto.getLogin());
		if(userResp == null) {
			return null;
		}
		
		String dbPassword = userResp.getPassword();
		if(!dbPassword.equals(authenticationDto.getPassword())) {
			return null;
		}
		
		UserDto userDto = converter.convertToDto(userResp, UserDto.class);
		String permitRole = SecurityConfig.getConfig().getPermitRole();
		
		if(permitRole != null) {
			String userRole = userDto.getNameRole();
			if(!permitRole.equals(userRole)) {
				return null;
			}
		}
		
		return userDto;
	}

	@Override
	public UserDto forgetPassword(AuthenticationDto authenticationDto) {
		User userResp = userDao.getUserByPassportId(authenticationDto.getPassportId());
		if(userResp == null) {
			return null;
		}
		String dbPassportSeries = userResp.getPassportSeries();
		if(!dbPassportSeries.equals(authenticationDto.getPassportSeries())) {
			return null;
		}
		
		String dbCodeWord = userResp.getCodeWord();
		if(!dbCodeWord.equals(authenticationDto.getCodeWord())) {
			return null;
		}
		
		UserDto userDto = converter.convertToDto(userResp, UserDto.class);
		return userDto;
	}
}
