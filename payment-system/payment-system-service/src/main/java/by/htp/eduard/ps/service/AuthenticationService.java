package by.htp.eduard.ps.service;

import by.htp.eduard.entities.Authentication;
import by.htp.eduard.ps.service.dto.UserDto;

public interface AuthenticationService {
	
	UserDto signIn(Authentication authentication);
	UserDto forgetPassword(Authentication authentication);

}
