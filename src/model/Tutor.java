package model;

public class Tutor {

	private String student_number;
	private String subject;
	private String grade;
	private String year;
	private String project_code;
	
	public String getProject_code() {
		return project_code;
	}
	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}
	public String getStudent_number() {
		return student_number;
	}
	public void setStudent_number(String student_number) {
		this.student_number = student_number;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	public Tutor(String student_number, String subject, String grade) {
		this.student_number = student_number;
		this.subject = subject;
		this.grade = grade;
	}
	
	public Tutor(String student_number, String subject, String grade, String year) {
		this.student_number = student_number;
		this.subject = subject;
		this.grade = grade;
		this.year = year;
	}
	
	public Tutor(String student_number, String subject, String grade, String year, String project_code) {
		this.student_number = student_number;
		this.subject = subject;
		this.grade = grade;
		this.year = year;
		this.project_code = project_code;
	}
	
	public Tutor() {
		// TODO Auto-generated constructor stub
	}
}
