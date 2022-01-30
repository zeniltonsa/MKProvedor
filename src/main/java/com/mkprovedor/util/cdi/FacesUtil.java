package com.mkprovedor.util.cdi;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {
	
	private FacesUtil() {}

	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}
}
