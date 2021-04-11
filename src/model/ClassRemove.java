package model;

public class ClassRemove {
	private int		courseId;
	private	String	courseTitle;
	private int		profId;
	private String	profName;
	
	public ClassRemove() { }		// 기본 생성자
	
	public ClassRemove(int courseId, String courseTitle, int profId, String profName) {
		this.courseId = courseId;
		this.courseTitle = courseTitle;
		this.profId = profId;
		this.profName = profName;
	}
	
	public ClassRemove(int courseId, int profId) {
		this.courseId = courseId;
		this.profId = profId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public int getProfId() {
		return profId;
	}

	public void setProfId(int profId) {
		this.profId = profId;
	}

	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) {
		this.profName = profName;
	}

	@Override
	public String toString() {
		return "ClassRemove [courseId=" + courseId + ", courseTitle=" + courseTitle + ", profId=" + profId + ", profName=" + profName  +  "]";
	}	
}
