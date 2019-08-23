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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import pojo.Student_Info;
import pojo.User_Auth;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * This method consist of various functionalities of admin i.e. to add_student,
	 * add_admin, delete_student, delete_admin, view all the users and view students
	 * based on particular grade.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		User_Auth user_Auth = new User_Auth();
		Student_Info student_info = new Student_Info();
		String value = request.getParameter("varname");
		Client client = ClientBuilder.newClient(new ClientConfig());
		String apiURL = "http://localhost:8080/Student_Result_management/webapi/adminresource";
		/*
		 * This method is used to accept student data such as id, name and marks from
		 * (AddStudent.jsp) the form to add a student into the database.
		 */
		if (value.equals("add_student")) {
			user_Auth.setUserid(Integer.parseInt(request.getParameter("userid")));
			user_Auth.setUsername(request.getParameter("username"));
			student_info.setBean(user_Auth);
			student_info.setUserid(user_Auth.getUserid());
			student_info.setMaths(Integer.parseInt(request.getParameter("maths")));
			student_info.setScience(Integer.parseInt(request.getParameter("science")));
			student_info.setEnglish(Integer.parseInt(request.getParameter("english")));
			WebTarget webTarget = client.target(apiURL).path("add_student");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response clientResponse = invocationBuilder.post(Entity.entity(student_info, MediaType.APPLICATION_JSON));
			String password = clientResponse.readEntity(String.class);
			if (password != null) {
				session.setAttribute("msg", "Student added Successfully..." + "Userid= " + user_Auth.getUserid() + " UserName= "
						+ user_Auth.getUsername() + " Password= " + password);
				RequestDispatcher requestdispatcher = request.getRequestDispatcher("AddStudent.jsp");
				requestdispatcher.include(request, response);
			} else {
				session.setAttribute("msg","User addition Unsuccessfully...please try again!");
				RequestDispatcher requestdispatcher = request.getRequestDispatcher("AddStudent.jsp");
				requestdispatcher.include(request, response);
				
			}
		}
		/*
		 * This method is used to accept Admin data such as id and name from
		 * (AddAdmin.jsp) the form to add a admin into the database.
		 */
		else if (value.equals("add_admin")) {
			user_Auth.setUsername(request.getParameter("username"));
			user_Auth.setUserid(Integer.parseInt(request.getParameter("userid")));
			WebTarget webTarget = client.target(apiURL).path("add_admin");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response clientResponse = invocationBuilder.post(Entity.entity(user_Auth, MediaType.APPLICATION_JSON));
			String password = clientResponse.readEntity(String.class);
			if (password != null) {
				session.setAttribute("msg","Admin added Successfully........" + "Userid= " + user_Auth.getUserid() + " UserName= "
						+ user_Auth.getUsername() + " Password= " + password);
				RequestDispatcher requestdispatcher = request.getRequestDispatcher("AddAdmin.jsp");
				requestdispatcher.include(request, response);
				
			} else {
				session.setAttribute("msg","User already exist...please try again!");
				RequestDispatcher requestdispatcher = request.getRequestDispatcher("AddAdmin.jsp");
				requestdispatcher.include(request, response);
				
			}
		}
		
		/*
		 * This method is used to delete student data from the database by accepting
		 * userid and username from(DeleteStudent.jsp) the form.
		 */
		else if (value.equals("delete_student")) {
			student_info.setBean(user_Auth);
			user_Auth.setUserid(Integer.parseInt(request.getParameter("userid")));
			user_Auth.setUsername(request.getParameter("username"));
			student_info.setUserid(user_Auth.getUserid());
			WebTarget webTarget = client.target(apiURL).path("delete_student");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response clientResponse = invocationBuilder.post(Entity.entity(student_info, MediaType.APPLICATION_JSON));
			String status = clientResponse.readEntity(String.class);
			if (status.equals("successful")) {
				session.setAttribute("msg","Student deleted Successfully");
				RequestDispatcher requestdispatcher = request.getRequestDispatcher("DeleteStudent.jsp");
				requestdispatcher.include(request, response);
			} else if (status.equals("Unsuccessful")) {
				session.setAttribute("msg","Sorry User Doesn't Exist!");
				RequestDispatcher requestdispatcher = request.getRequestDispatcher("DeleteStudent.jsp");
				requestdispatcher.include(request, response);
			}
		}
		
		/*
		 * This method is used to delete admin data from the database by accepting
		 * userid and username from(DeleteAdmin.jsp) the form.
		 */
		else if (value.equals("delete_admin")) {
			user_Auth.setUserid(Integer.parseInt(request.getParameter("userid")));
			user_Auth.setUsername(request.getParameter("username"));
			WebTarget webTarget = client.target(apiURL).path("delete_admin");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response clientResponse = invocationBuilder.post(Entity.entity(user_Auth, MediaType.APPLICATION_JSON));
			String status = clientResponse.readEntity(String.class);
			if (status.equals("successful")) {
				session.setAttribute("msg","User deleted Successfully");
				RequestDispatcher requestdispatcher = request.getRequestDispatcher("DeleteAdmin.jsp");
				requestdispatcher.include(request, response);
			} else if (status.equals("Unsuccessful")) {
				session.setAttribute("msg","Sorry User Doesn't Exist!");
				RequestDispatcher requestdispatcher = request.getRequestDispatcher("DeleteAdmin.jsp");
				requestdispatcher.include(request, response);
			}
		}

		
		/*
		 * This method is used to view student result from the database based on a particular grade
		 * given by the admin in the form (View_Grade.jsp).
		 */
		else if (value.equals("view_grade")) {
			student_info.setGrade(request.getParameter("value"));
			WebTarget webTarget = client.target(apiURL).path("view_grade");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response clientResponse = invocationBuilder.post(Entity.entity(student_info, MediaType.APPLICATION_JSON));
			GenericType<ArrayList<Student_Info>> gType = new GenericType<ArrayList<Student_Info>>() {
			};
			ArrayList<Student_Info> list = clientResponse.readEntity(gType);
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("Grade.jsp");
			request.setAttribute("view_grade", list);
			requestdispatcher.forward(request, response);
		}
	}
	/*
	 * This method is used to view all users from the database (ViewUsers.jsp).
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String value = request.getParameter("varname");
		if (value.equals("view_user")) {
			Client client = ClientBuilder.newClient(new ClientConfig());
			WebTarget webTarget = client
					.target("http://localhost:8080/Student_Result_management/webapi/adminresource/view_user");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response clientResponse = invocationBuilder.get();
			GenericType<ArrayList<User_Auth>> gType = new GenericType<ArrayList<User_Auth>>() {
			};
			ArrayList<User_Auth> list = clientResponse.readEntity(gType);
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("ViewUsers.jsp");
			request.setAttribute("user_review", list);
			requestdispatcher.forward(request, response);
		}
	}
}
