package model;


// 학생과 관련한 정보를 저장하기 위한 DTO(Data Transition Object)
public class StudentDTO {
   private String stuId;				// 학번
   private String stuName = null;		// 성명
   private String password = null;		// 비번
   private int grade;					// 학년
   private String dProject = null;		// 과제
   private String dTeamProject = null;	// 팀플
   private String dCreditRate = null;	// 학점비율
   private String dAttendance = null;	// 출결
   private String dNumberOfExam = null;	// 시험횟수
   
   public StudentDTO() { } //기본 생성자
   
   public StudentDTO(String stuId, String stuName, int grade) {
	   this.stuId = stuId;
	   this.stuName = stuName;
	   this.grade = grade;
   }
   
   public StudentDTO(String stuId, String stuName, String password, int grade, String dProject, String dTeamProject, String dCreditRate, String dAttendance, String dNumberOfExam) {
	   this.stuId = stuId;
	   this.stuName = stuName;
	   this.password = password;
	   this.grade = grade;
	   this.dProject = dProject;
	   this.dTeamProject = dTeamProject;
	   this.dCreditRate = dCreditRate;
	   this.dAttendance = dAttendance;
	   this.dNumberOfExam = dNumberOfExam;
   }
   
   public String getStuId() {
	   return stuId;
   }
   public void setStuId(String stuId) {
	   this.stuId = stuId;
   }
   public String getStuName() {
	   return stuName;
   }
   public void setStuName(String stuName) {
	   this.stuName = stuName;
   }
   public String getPassword() {
	return password;
   }
   public void setPassword(String password) {
	   this.password = password;
   }
   public int getGrade() {
	   return grade;
   }
   public void setGrade(int grade) {
	   this.grade = grade;
   }
   public String getdProject() {
	   return dProject;
   }
   public void setdProject(String dProject) {
	   this.dProject = dProject;
   }
   public String getdTeamProject() {
	   return dTeamProject;
   }
   public void setdTeamProject(String dTeamProject) {
	   this.dTeamProject = dTeamProject;
   }
   public String getdCreditRate() {
	   return dCreditRate;
   }
   public void setdCreditRate(String dCreditRate) {
	   this.dCreditRate = dCreditRate;
   }
   public String getdAttendance() {
	   return dAttendance;
   }
   public void setdAttendance(String dAttendance) {
	   this.dAttendance = dAttendance;
   }
   public String getdNumberOfExam() {
	   return dNumberOfExam;
   }
   public void setdNumberOfExam(String dNumberOfExam) {
	   this.dNumberOfExam = dNumberOfExam;
   }
   
   /* 비밀번호 검사 */
	public boolean matchStuPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameStudent(String stuId) {
       return this.stuId.equals(stuId);
   }

	@Override
	public String toString() {
		return "User [stuId=" + stuId + ", password=" + password + ", name=" + stuName + ", grade=" + grade 
				+ ", dProject=" + dProject + ", dTeamProject=" + dTeamProject + ", dCreditRate=" + dCreditRate 
				+ ", dAttendance=" + dAttendance + ", dNumberOfExam=" + dNumberOfExam + "]";
	}	
}
