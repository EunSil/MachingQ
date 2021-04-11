package model;
import java.util.*;

public class Club {
	private String club_code;
	private String club_name;
	private String president_code;
	private String president_phone;
	private String professor_code;
	private String professor_phone;
	private String club_place;
	private String club_time;
	private String activity;
	private String purpose;
	private String category;
	private String description;
	//private ArrayList<Student> sList = new List<Student>();

	public Club() {
		this.club_code = club_code;
		this.club_name = club_name;
		this.president_code = president_code;
		this.president_phone = president_phone;
		this.professor_code = professor_code;
		this.professor_phone = professor_phone;
		this.club_place = club_place;
		this.club_time = club_time;
		this.activity = activity;
		this.purpose = purpose;
		this.category = category;
	}
	
	public Club(String club_code, String club_name, String president_code, String president_phone,
			String professor_code, String professor_phone, String club_place, String club_time, String activity,
			String purpose, String category, String description) {
		super();
		this.club_code = club_code;
		this.club_name = club_name;
		this.president_code = president_code;
		this.president_phone = president_phone;
		this.professor_code = professor_code;
		this.professor_phone = professor_phone;
		this.club_place = club_place;
		this.club_time = club_time;
		this.activity = activity;
		this.purpose = purpose;
		this.category = category;
		this.description = description;
	}

	
	public String getClub_code() {
		return club_code;
	}

	public void setClub_code(String club_code) {
		this.club_code = club_code;
	}

	public String getClub_name() {
		return club_name;
	}

	public void setClub_name(String club_name) {
		this.club_name = club_name;
	}

	public String getPresident_code() {
		return president_code;
	}

	public void setPresident_code(String president_code) {
		this.president_code = president_code;
	}

	public String getPresident_phone() {
		return president_phone;
	}

	public void setPresident_phone(String president_phone) {
		this.president_phone = president_phone;
	}

	public String getProfessor_code() {
		return professor_code;
	}

	public void setProfessor_code(String professor_code) {
		this.professor_code = professor_code;
	}

	public String getProfessor_phone() {
		return professor_phone;
	}

	public void setProfessor_phone(String professor_phone) {
		this.professor_phone = professor_phone;
	}

	public String getClub_place() {
		return club_place;
	}

	public void setClub_place(String club_place) {
		this.club_place = club_place;
	}

	public String getClub_time() {
		return club_time;
	}

	public void setClub_time(String club_time) {
		this.club_time = club_time;
	}

	public Club(String club_code) {
		super();
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
