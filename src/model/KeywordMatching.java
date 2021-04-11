package model;

public class KeywordMatching {
	private int matId;
	private int keyId;
	private int profId;
	private int courseId;
	private int count;
	public KeywordMatching(int keyId, int profId, int courseId) {
		super();
		this.keyId = keyId;
		this.profId = profId;
		this.courseId = courseId;
	}
	public KeywordMatching() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KeywordMatching(int keyId, int profId, int courseId, int count) {
		super();
		this.keyId = keyId;
		this.profId = profId;
		this.courseId = courseId;
		this.count = count;
	}
	@Override
	public String toString() {
		return "KeywordMatching [matId=" + matId + ", keyId=" + keyId + ", profId=" + profId + ", courseId=" + courseId
				+ ", count=" + count + "]";
	}
	public int getMatId() {
		return matId;
	}
	public void setMatId(int matId) {
		this.matId = matId;
	}
	public int getKeyId() {
		return keyId;
	}
	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}
	public int getProfId() {
		return profId;
	}
	public void setProfId(int profId) {
		this.profId = profId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
