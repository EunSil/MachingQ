package model;

public class Course {
	private int courseId;
	private String title;
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Course(int courseId, String title) {
		super();
		this.courseId = courseId;
		this.title = title;
	}
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", title=" + title + "]";
	}
}
