package repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pojo.*;
import serviceimplimentation.Validation;

public class LoginRepository {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("student_result_management");
	EntityManager entityManager = entityManagerFactory.createEntityManager();

	/*
	 * This method is used to validate the user(admin/student) from the database
	 * based on the credentitals(userid and password) given by the user while login
	 */
	public String check(User_Auth credentials) {
		String role = "Invalid";
		try {
			entityManager.getTransaction().begin();
			User_Auth u = entityManager.find(User_Auth.class, credentials.getUserid());
			if (u.getUserid() == credentials.getUserid()) {
				if (u.getPassword().equals(credentials.getPassword()) && u.getType().equals("admin")) {
					role = "admin";
				} else if (u.getPassword().equals(credentials.getPassword()) && u.getType().equals("student")) {
					role = "student";
				}
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return role;
	}

	/*
	 * This method is used to change the user's(admin/student) password in the database
	 * based on the credentitals(old_password and new_password) given by the user.
	 */
	public String changePassword(Password password) {
		String status = "error";
		Validation validation = new Validation();
		boolean validPassword = validation.passwordValidation(password.getNew_password());
		if (validPassword == true) {
			try {
				entityManager.getTransaction().begin();
				User_Auth user = entityManager.find(User_Auth.class, password.getUserid());
				if (user.getUserid() == password.getUserid()) {
					if (user.getPassword().equals(password.getOld_password())) {
						user.setPassword(password.getNew_password());
						entityManager.persist(user);
						entityManager.getTransaction().commit();
						status = "successful";
					} else {
						status = "unsuccessful";
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				entityManager.close();
				entityManagerFactory.close();
			}
		}
		return status;
	}

}
