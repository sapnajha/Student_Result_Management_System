package repositories;

import java.util.ArrayList;
import javax.persistence.*;
import pojo.*;
import serviceimplimentation.*;

public class AdminRepository {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("student_result_management");
	EntityManager entityManager = entityManagerFactory.createEntityManager();

	/*
	 * This method is used to add a student into the database based on the
	 * detials(userid, username and marks) given by the user.
	 */
	public String addStudent(Student_Info student_info) {
		String password = null;
		try {
			entityManager.getTransaction().begin();
			User_Auth user_Auth = student_info.getBean();
			Validation validate = new Validation();
			password = validate.passwordGeneration();
			user_Auth.setUserid(student_info.getUserid());
			user_Auth.setPassword(password);
			user_Auth.setType("student");
			int total = Calculate.getTotal(student_info);
			student_info.setTotal(total);
			String grade = Calculate.getGrade(total);
			student_info.setGrade(grade);
			entityManager.persist(user_Auth);
			entityManager.persist(student_info);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return password;
	}

	/*
	 * This method is used to add an admin into the database based on the
	 * detials(userid and username) given by the user.
	 */
	public String addAdmin(User_Auth user_Auth) {
		String password = null;
		try {
			Validation validate = new Validation();
			password = validate.passwordGeneration();
			user_Auth.setPassword(password);
			user_Auth.setType("admin");
			entityManager.getTransaction().begin();
			entityManager.persist(user_Auth);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return password;

	}

	/*
	 * This method is used to delete the student from the database based on the
	 * data(userid and username) given by the user.
	 */
	public String deleteStudent(Student_Info student_info) {
		String status = "Unsuccessful";
		try {
			User_Auth user_Auth = student_info.getBean();
			Student_Info student_info1 = entityManager.find(Student_Info.class, student_info.getUserid());
			user_Auth = entityManager.find(User_Auth.class, student_info.getUserid());
			if (student_info1.getUserid() == student_info.getUserid()) {
				if (user_Auth.getUsername().equals(user_Auth.getUsername()) && user_Auth.getType().equals("student")) {
		
					entityManager.getTransaction().begin();
					entityManager.remove(user_Auth);
					entityManager.remove(student_info1);
					entityManager.getTransaction().commit();
					status = "successful";
				}
		}
		}
		 catch (Exception e) {
			System.out.println(e);
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return status;
	}

	/*
	 * This method is used to delete an admin from the database based on the
	 * data(userid and username) given by the user.
	 */
	public String deleteAdmin(User_Auth user_Auth) {
		String status = "Unsuccessful";
		try {
			User_Auth admin = entityManager.find(User_Auth.class, user_Auth.getUserid());
			if (admin.getUserid() == user_Auth.getUserid()) {
				if (admin.getUsername().equals(user_Auth.getUsername()) && admin.getType().equals("admin")) {
					entityManager.getTransaction().begin();
					entityManager.remove(admin);
					entityManager.getTransaction().commit();
					status = "successful";
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return status;
	}

	/*
	 * This method is used to view all the users from the database
	 */

	public ArrayList<User_Auth> viewUser() {
		entityManager.getTransaction().begin();
		TypedQuery<User_Auth> query = entityManager.createQuery("SELECT c FROM User_Auth c", User_Auth.class);
		ArrayList<User_Auth> results = (ArrayList<User_Auth>) query.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return results;

	}

	/*
	 * This method is used to view all the students details based on a particular
	 * grade entered by the admin.
	 */
	public ArrayList<Student_Info> viewGrade(String grade) {
		entityManager.getTransaction().begin();
		TypedQuery<Student_Info> query = entityManager.createQuery("SELECT c FROM Student_Info c where c.grade=:grade",
				Student_Info.class);
		query.setParameter("grade", grade);
		ArrayList<Student_Info> results = (ArrayList<Student_Info>) query.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return results;
	}

}
