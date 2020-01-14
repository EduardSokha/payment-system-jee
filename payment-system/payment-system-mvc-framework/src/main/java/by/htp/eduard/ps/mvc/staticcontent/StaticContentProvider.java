package by.htp.eduard.ps.mvc.staticcontent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StaticContentProvider {
	
	public void provideStaticContent(HttpServletRequest request, HttpServletResponse response, String url) {
		
		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath(url);
		ServletOutputStream os = null;
		
		try {
			Path path = Paths.get(realPath);
			byte[] staticBytes = Files.readAllBytes(path);
			os = response.getOutputStream();
			os.write(staticBytes);
		} catch (IOException e) {
			try {
				response.sendError(404, "file for url - " + url + " not found");
			} catch (IOException e1) {
			}
		} finally {
			try {
				if(os != null) {
					os.close();
				}
			} catch (IOException e) {
			}
		}
		
		
	}

}
