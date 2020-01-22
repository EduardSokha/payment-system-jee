package by.htp.eduard.ps.mvc.tags;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ValidationErrorTag extends BodyTagSupport {
	
	private static final long serialVersionUID = 1L;
	
	private String code;

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		
		ServletRequest request = pageContext.getRequest();
		List<String> validationErrors = (List<String>)request.getAttribute("validationErrors");
		if(validationErrors == null) {
			return SKIP_BODY;
		}
		
		if(!validationErrors.contains(code)) {
			return SKIP_BODY;
		}
		
		return EVAL_BODY_INCLUDE;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
