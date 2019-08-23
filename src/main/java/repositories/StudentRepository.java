package repositories;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import pojo.Student_Info;

public class StudentRepository {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("student_result_management");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	/*
	 * This method is used to dislpay the result of a particular student from the database
	 * based on his/her credentitals(userid and password) given while login.
	 */
	public ArrayList<Student_Info> viewDetails(Student_Info student_info) {
		entityManager.getTransaction().begin();
		TypedQuery<Student_Info> query = entityManager.createQuery("SELECT c FROM Student_Info c where userid=:id",
				Student_Info.class);
		query.setParameter("id", student_info.getUserid());
		ArrayList<Student_Info> results = (ArrayList<Student_Info>) query.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return results;
	}

}
