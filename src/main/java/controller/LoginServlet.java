package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import pojo.User_Auth;
/**
 * Servlet implementation class LoginServlet
 */
import pojo.Student_Info;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*This method is used to find user_type whether the loggedin user is an Admin or Student. 
	 * Based on the user it will redirect to corresponding page.
	 * If it is not a valid user then it will show an error message. */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String apiURL = "http://localhost:8080/Student_Result_management/webapi";
		Client client = ClientBuilder.newClient(new ClientConfig());
		Student_Info student_info = new Student_Info();
		User_Auth user_Auth = new User_Auth();		
		user_Auth.setUserid(Integer.parseInt(request.getParameter("userid")));
		user_Auth.setPassword(request.getParameter("password"));
		WebTarget webTarget = client.target(apiURL).path("loginresource/validate");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response clientResponse = invocationBuilder.post(Entity.entity(user_Auth, MediaType.APPLICATION_JSON));
		User_Auth validate = clientResponse.readEntity(User_Auth.class);
		if (validate.getType().equals("admin")) {
			RequestDispatcher rs = request.getRequestDispatcher("Admin.jsp");
			rs.include(request, response);
			HttpSession session = request.getSession();
			session.setAttribute("userid", validate.getUserid());
		} else if (validate.getType().equals("student")) {
			student_info.setUserid(user_Auth.getUserid());
			WebTarget webTarget1 = client.target(apiURL).path("studentresource/view_details");
			Invocation.Builder invocationBuilder1 = webTarget1.request(MediaType.APPLICATION_JSON);
			Response clientResponse1 = invocationBuilder1.post(Entity.entity(student_info, MediaType.APPLICATION_JSON));
			GenericType<ArrayList<Student_Info>> gType = new GenericType<ArrayList<Student_Info>>() {
			};
			ArrayList<Student_Info> list = clientResponse1.readEntity(gType);
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("Student.jsp");
			request.setAttribute("view_details", list);
			requestdispatcher.include(request, response);
			HttpSession session = request.getSession();
			session.setAttribute("userid", validate.getUserid());
		} else {
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("Login.jsp");
			requestdispatcher.include(request, response);
			out.print("Username or Password incorrect");
		}

	}
}
