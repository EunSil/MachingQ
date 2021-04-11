package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Tutor;
import model.Tutee;
import model.dao.TutorDAO;
import model.dao.TuteeDAO;
import model.dao.Tutor_Tutee_MatchingDAO;

public class TutorTuteeManager {
	private static TutorTuteeManager ttManager = new TutorTuteeManager();
	private TuteeDAO tuteeDAO;
	private TutorDAO tutorDAO;
	private Tutor_Tutee_MatchingDAO matchingDAO;

	private TutorTuteeManager() {
		try {
			tuteeDAO = new TuteeDAO();
			tutorDAO = new TutorDAO();
			matchingDAO = new Tutor_Tutee_MatchingDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static TutorTuteeManager getInstance() {
		return ttManager;
	}
	
	public int createTutee(Tutee tuteeDTO) throws SQLException, ExistingTuteeException {
		if (tuteeDAO.existingTutee(tuteeDTO.getStudent_number()) == true) {
			throw new ExistingTuteeException(tuteeDTO.getStudent_number() + "는 존재하는 아이디입니다.");
		}
		return tuteeDAO.insertTutee(tuteeDTO);
	}

	public int remove(String student_number) throws SQLException, TuteeNotFoundException {
		return tuteeDAO.deleteTutee(student_number);
	}

	public List<Tutor> matchingList(String grade, String subject) {
		return matchingDAO.getTutorMatchingList(grade, subject);
	}
	
	public int createMatching(String tutor_number, String tutee_number) {
		return matchingDAO.insertMatching(tutor_number, tutee_number);
	}
	
	public Tutee findTutee(String student_number)
		throws SQLException, TuteeNotFoundException {
		Tutee tutee = tuteeDAO.getTuteeByCode(student_number);
		
		if (tutee == null) {
			throw new TuteeNotFoundException(student_number + "는 존재하지 않는 아이디입니다.");
		}		
		return tutee;
	}

	public List<Tutee> findTuteeList() throws SQLException {
			return tuteeDAO.getTuteeList();
	}

	public int updateTutee(Tutee tutee) throws SQLException {
		return tuteeDAO.updateTutee(tutee);				
	}

	public TutorDAO getTutorDAO() {
		return this.tutorDAO;
	}
	
	//tutor
	public int createTutor(Tutor tutorDTO) throws SQLException, ExistingTutorException {
		if (tutorDAO.existingTutor(tutorDTO.getStudent_number()) == true) {
			throw new ExistingTutorException(tutorDTO.getStudent_number() + "는 존재하는 아이디입니다.");
		}
		return tutorDAO.insertTutor(tutorDTO);
	}

	public int removeTutor(String student_number) throws SQLException {
		return tutorDAO.deleteTutor(student_number);
	}

	public Tutor findTutor(String student_number) throws SQLException, TutorNotFoundException {
		Tutor tutor = tutorDAO.getTutorByCode(student_number);
		
		if (tutor == null) {
			throw new TutorNotFoundException(student_number + "는 존재하지 않는 아이디입니다.");
		}		
		return tutor;
	}

	public List<Tutor> findTutorList() throws SQLException {
			return tutorDAO.getTutorList();
	}

	public int updateTutor(Tutor tutor) throws SQLException {
		return tutorDAO.updateTutor(tutor);				
	}
	public TuteeDAO getTuteeDAO() {
		return this.tuteeDAO;
	}
	
	public List<Tutee> findNoList(Tutor tutor) throws SQLException {
		return matchingDAO.getTuteeNoList(tutor);
	}
	
	public List<Tutee> findYesList(Tutor tutor) throws SQLException {
		return matchingDAO.getTuteeYesList(tutor);
	}
	
	public int updateMatching(String tutee_number) {
		return matchingDAO.updateMatching(tutee_number);
	}
	
	public int deleteMatching(String tutee_number) {
		tuteeDAO.deleteTutee(tutee_number);
		return matchingDAO.deleteMatching(tutee_number);
	}

}
