package by.htp.eduard.ps.dao;

import by.htp.eduard.ps.dao.entities.Authentication;
import by.htp.eduard.ps.dao.entities.User;

public interface AuthenticationDao {
	
	User signIn(Authentication authentication);
	User forgetPassword(Authentication authentication);
	

}
