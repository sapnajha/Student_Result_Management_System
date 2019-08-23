package pojo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Student_Info {
		@Id
		private int userid;
		private int maths;
		private int science;
		private int english;
		private int total;
		private String grade;
		@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
		@JoinColumn(name="userid")
		private User_Auth user_Auth;
		public User_Auth getBean() {
			return user_Auth;
		}
		public void setBean(User_Auth user_Auth) {
			this.user_Auth = user_Auth;
		}
		public int getUserid() {
			return userid;
		}
		public void setUserid(int userid) {
			this.userid = userid;
		}
		public int getMaths() {
			return maths;
		}
		public void setMaths(int maths) {
			this.maths = maths;
		}
		public int getScience() {
			return science;
		}
		public void setScience(int science) {
			this.science = science;
		}
		public int getEnglish() {
			return english;
		}
		public void setEnglish(int english) {
			this.english = english;
		}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		public String getGrade() {
			return grade;
		}
		public void setGrade(String grade) {
			this.grade = grade;
		}
}