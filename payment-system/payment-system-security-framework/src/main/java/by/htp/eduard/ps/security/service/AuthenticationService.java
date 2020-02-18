package by.htp.eduard.ps.security.service;

import by.htp.eduard.ps.security.dto.AuthenticationDto;
import by.htp.eduard.ps.service.dto.UserDto;

public interface AuthenticationService {
	
	UserDto signIn(AuthenticationDto authenticationDto);
	UserDto forgetPassword(AuthenticationDto authenticationDto);

}
