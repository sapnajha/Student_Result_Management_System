package com.sapna.Student_Result_management;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pojo.*;
import repositories.*;
/**
 * Root resource (exposed at "studentresource" path)
 */
@Path("studentresource")
public class StudentResource {

	@Path("view_details")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Student_Info> viewDetails(Student_Info student_info) {
		ArrayList<Student_Info> result = new StudentRepository().viewDetails(student_info);
		return result;
	}
}