package model;

public class Tutor_Tutee_Matching {
	private String project_code;
	private String Approval;
	private String tutee_number;
	
	public String getApproval() {
		return Approval;
	}
	public void setApproval(String approval) {
		Approval = approval;
	}
	public String getTutee_number() {
		return tutee_number;
	}
	public void setTutee_number(String tutee_number) {
		this.tutee_number = tutee_number;
	}
	public String getProject_code() {
		return project_code;
	}
	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}
}
