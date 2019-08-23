package com.sapna.Student_Result_management;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import pojo.*;
import repositories.AdminRepository;
import repositories.LoginRepository;
import repositories.StudentRepository;

/**
 * Root resource (exposed at "loginresource" path)
 */
@Path("loginresource")
public class LoginResource {
	User_Auth user_Auth = new User_Auth();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("validate")
	public User_Auth validate(User_Auth user_Auth) throws IOException {
		LoginRepository logindao = new LoginRepository();
		String type = logindao.check(user_Auth);
		user_Auth.setType(type);
		return user_Auth;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("change_password")
	public String changePassword(Password password) throws IOException {
		LoginRepository logindao = new LoginRepository();
		String status = logindao.changePassword(password);
		return status;
	}
}
