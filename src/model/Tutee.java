package model;

public class Tutee {
	private String student_number;
	private String preference_subject;
	private String grade;
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
	public String getPreference_subject() {
		return preference_subject;
	}
	public void setPreference_subject(String preference_subject) {
		this.preference_subject = preference_subject;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public Tutee() {
		
	}
	
	public Tutee(String student_number, String preference_subject, String grade) {
		this.student_number = student_number;
		this.preference_subject = preference_subject;
		this.grade = grade;
	}
	
	public Tutee(String student_number, String preference_subject, String grade, String project_code) {
		this.student_number = student_number;
		this.preference_subject = preference_subject;
		this.grade = grade;
		this.project_code = project_code;
		
		
	}
	

}
