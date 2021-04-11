package model;

public class Review {
	private int revid;
	private int openSemester;
	private String lectureTotal;
	private String project;
	private String teamProject;
	private String creditRate; 
	private String attendance;
	private String numberOfExam;
	private String lectureReview;
	private int classId;
	private String className;
	private ClassDTO classDto;
	
	public Review() { }

	public Review(int revid, int openSemester, String lectureTotal, String project, String teamProject,
			String creditRate, String attendance, String numberOfExam, String lectureReview, int classId, ClassDTO classDto) {
		super();
		this.revid = revid;
		this.openSemester = openSemester;
		this.lectureTotal = lectureTotal;
		this.project = project;
		this.teamProject = teamProject;
		this.creditRate = creditRate;
		this.attendance = attendance;
		this.numberOfExam = numberOfExam;
		this.lectureReview = lectureReview;
		this.classId = classId;
		this.classDto = classDto;
	}
	
	public Review(int revid, int openSemester, String lectureTotal, String project, String teamProject,
			String creditRate, String attendance, String numberOfExam, String lectureReview, int classId) {
		super();
		this.revid = revid;
		this.openSemester = openSemester;
		this.lectureTotal = lectureTotal;
		this.project = project;
		this.teamProject = teamProject;
		this.creditRate = creditRate;
		this.attendance = attendance;
		this.numberOfExam = numberOfExam;
		this.lectureReview = lectureReview;
		this.classId = classId;
	}
	public Review(int revid, String lectureTotal, int classId) {
		this.revid = revid;
		this.lectureTotal = lectureTotal;
		this.classId = classId;
	}

	@Override
	public String toString() {
		return "ReviewDTO [revid=" + revid + ", openSemester=" + openSemester + ", lectureTotal=" + lectureTotal
				+ ", project=" + project + ", teamProject=" + teamProject + ", creditRate=" + creditRate
				+ ", attendance=" + attendance + ", numberOfExam=" + numberOfExam + ", lectureReview=" + lectureReview
				+ ", classId=" + classId + "]";
	}

	public int getRevid() {
		return revid;
	}

	public void setRevid(int revid) {
		this.revid = revid;
	}

	public int getOpenSemester() {
		return openSemester;
	}

	public void setOpenSemester(int openSemester) {
		this.openSemester = openSemester;
	}

	public String getLectureTotal() {
		return lectureTotal;
	}

	public void setLectureTotal(String lectureTotal) {
		this.lectureTotal = lectureTotal;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getTeamProject() {
		return teamProject;
	}

	public void setTeamProject(String teamProject) {
		this.teamProject = teamProject;
	}

	public String getCreditRate() {
		return creditRate;
	}

	public void setCreditRate(String creditRate) {
		this.creditRate = creditRate;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	public String getNumberOfExam() {
		return numberOfExam;
	}

	public void setNumberOfExam(String numberOfExam) {
		this.numberOfExam = numberOfExam;
	}

	public String getLectureReview() {
		return lectureReview;
	}

	public void setLectureReview(String lectureReview) {
		this.lectureReview = lectureReview;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public ClassDTO getClassDto() {
		return classDto;
	}

	public void setClassDto(ClassDTO classDto) {
		this.classDto = classDto;
	}
	
	
	
	

}
