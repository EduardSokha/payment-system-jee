package by.htp.eduard.service.impl;

import by.htp.eduard.dao.AuthenticationDao;
import by.htp.eduard.dao.mysql.provider.DaoProvider;
import by.htp.eduard.entities.Authentication;
import by.htp.eduard.entities.User;
import by.htp.eduard.service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {
	private final AuthenticationDao authenticationDao;

	public AuthenticationServiceImpl() {
		authenticationDao = DaoProvider.getInstance().getAuthenticationDao();
	}

	@Override
	public User signIn(Authentication authentication) {
		return authenticationDao.signIn(authentication);
	}

	@Override
	public User forgetPassword(Authentication authentication) {
		return authenticationDao.forgetPassword(authentication);
	}

}
