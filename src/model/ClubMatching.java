package model;

public class ClubMatching {
	private String club_code;
	private String student_number;
	private String approval;
	
	public String getClub_code() {
		return club_code;
	}
	public void setClub_code(String club_code) {
		this.club_code = club_code;
	}
	public String getStudent_number() {
		return student_number;
	}
	public void setStudent_number(String student_number) {
		this.student_number = student_number;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	
	public ClubMatching() {
		this.club_code = club_code;
		this.student_number = student_number;
		this.approval = approval;
	}
	
	ClubMatching(String club_code, String student_number, String approval) {
		this.club_code = club_code;
		this.student_number = student_number;
		this.approval = approval;
	}
	
	ClubMatching(String club_code, String student_number) {
		this.club_code = club_code;
		this.student_number = student_number;
	}

}
