package serviceimplimentation;

import pojo.Student_Info;

public class Calculate {
	/*
	 * This method is used to Calculate the total marks obtained by the student in all the subjects.
	 */
	public static int getTotal(Student_Info student_info)
	{
		int total=student_info.getMaths()+student_info.getScience()+student_info.getEnglish();
		return total;
	}
	/*
	 * This method is used to view Calculate the total grade obtained by the student based on his/her total marks.
	 */
	public static String getGrade(int total)
	{
		if(total>=300)
		{
			return "A";
		}
		if(total<300 && total>200)
		{
			return "B";
		}
		if(total<=200 && total >100)
		{
			return "C";
		}
		else
			return "D";
	}
}
