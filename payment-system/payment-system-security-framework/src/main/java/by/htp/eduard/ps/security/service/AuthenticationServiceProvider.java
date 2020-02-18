package by.htp.eduard.ps.security.service;

import by.htp.eduard.ps.security.service.impl.AuthenticationServiceImpl;

public class AuthenticationServiceProvider {
	
private static final AuthenticationServiceProvider instance = new AuthenticationServiceProvider();
	
	private AuthenticationServiceProvider() {}
	
	private AuthenticationService authenticationService = null;
	
	
	public static AuthenticationServiceProvider getInstance() {
		return instance;
	}

	public AuthenticationService getAuthenticationService() {
		if(authenticationService == null) {
			authenticationService = new AuthenticationServiceImpl();
		}
		
		return authenticationService;
	}

	
}
