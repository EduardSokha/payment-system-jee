package by.htp.eduard.ps.mvc.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ModelAndView {
	
	private String viewName;
	private Map<String, Object> viewData;
	private Set<String> validationErrors;
	
	public ModelAndView(String viewName) {
		this.viewName = viewName;
		
		viewData = new HashMap<>();
		validationErrors = new HashSet<>();
	}

	public void addViewData(String dataName, Object dataValue) {
		viewData.put(dataName, dataValue);
	}
	
	public void addAllViewData(Map<String, Object> allViewData) {
		viewData.putAll(allViewData);
	}
	
	public void addValidationError(String errorCode) {
		validationErrors.add(errorCode);
	}
	
	public void addAllValidationError(Set<String> errors) {
		validationErrors.addAll(errors);
	}

	public String getViewName() {
		return viewName;
	}

	public Map<String, Object> getViewData() {
		return viewData;
	}

	public Set<String> getValidationErrors() {
		return validationErrors;
	}
}
