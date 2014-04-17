package com.aspire.web.action;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.opensymphony.xwork2.ActionContext;

public class LoginAction {
	protected Logger log = Logger.getLogger(LoginAction.class.getName());

	public String execute() {
		log.info("Executing method : execute()");
		String forward = "failure";
		Map<String, Object> session = ActionContext.getContext().getSession();
		try {
			User user = (User) session.get("user");			
			if (SecurityContextHolder.getContext() != null) {
				user = (User) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
			}
			if (user != null) {
				session.put("user", user);
				forward = "success";
			}
		} catch (Exception e) {
			log.error("Error in authentication," + e);
		}
		return forward;
	}
}
