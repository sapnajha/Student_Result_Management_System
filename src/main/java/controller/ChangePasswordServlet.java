package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import pojo.*;

/**
 * Servlet implementation class ChangePasswordServlet
 */
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * This method is used to change the current password of a user and give a valid
	 * new password which will be updated in the database. If password is changed
	 * successfully user needs to login again with his/her new credentials, else
	 * he/she will be redirected to same page with an error message.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Password password = new Password();
		String apiURL = "http://localhost:8080/Student_Result_management/webapi/loginresource";
		Client client = ClientBuilder.newClient(new ClientConfig());
		HttpSession session = request.getSession();
		password.setUserid((int) session.getAttribute("userid"));
		password.setOld_password(request.getParameter("old_password"));
		password.setNew_password(request.getParameter("new_password"));
		WebTarget webTarget = client.target(apiURL).path("change_password");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response clientResponse = invocationBuilder.post(Entity.entity(password, MediaType.APPLICATION_JSON));
		String status = clientResponse.readEntity(String.class);
		if (status.equals("successful")) {
			session.setAttribute("msg", "Password Updated Successfully...Thank You!");
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("Login.jsp");
			requestdispatcher.include(request, response);
			
		} else {
			session.setAttribute("msg","Please Enter Correct Password...Thank You!");
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("Change_password.jsp");
			requestdispatcher.include(request, response);
			
		}
	}
}
