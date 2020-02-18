package by.htp.eduard.ps.mvc.router;

import java.io.IOException;

import javax.servlet.ServletException;

import by.htp.eduard.ps.mvc.model.ModelAndView;

public interface Rourter {
	
	public void route(ModelAndView modelAndView) throws ServletException, IOException;

}
