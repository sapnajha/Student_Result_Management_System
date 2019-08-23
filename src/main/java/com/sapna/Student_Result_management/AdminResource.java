package com.sapna.Student_Result_management;

import java.io.IOException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pojo.*;
import repositories.*;
/**
 * Root resource (exposed at "adminresource" path)
 */
@Path("adminresource")
public class AdminResource {

	User_Auth user_Auth = new User_Auth();

	@Path("add_student")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addStudent(Student_Info student_info) throws IOException {
		AdminRepository adminrepository = new AdminRepository();
		String password = adminrepository.addStudent(student_info);
		return password;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("add_admin")
	public String addAdmin(User_Auth user_Auth) throws IOException {
		AdminRepository adminrepository = new AdminRepository();
		String password = adminrepository.addAdmin(user_Auth);
		return password;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("delete_student")
	public String deleteStudent(Student_Info student_info) throws IOException {
		AdminRepository adminrepository = new AdminRepository();
		String status = adminrepository.deleteStudent(student_info);
		return status;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("delete_admin")
	public String deleteAdmin(User_Auth user_Auth) throws IOException {
		AdminRepository adminrepository = new AdminRepository();
		String status = adminrepository.deleteAdmin(user_Auth);
		return status;
	}

	@Path("view_user")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<User_Auth> viewUsers(User_Auth user_Auth) {
		ArrayList<User_Auth> result = new AdminRepository().viewUser();
		return result;

	}

	@Path("view_grade")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Student_Info> viewGrade(Student_Info student_info) {
		ArrayList<Student_Info> result = new AdminRepository().viewGrade(student_info.getGrade());
		return result;

	}
}
