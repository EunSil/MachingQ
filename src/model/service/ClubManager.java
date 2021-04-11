package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Club;
import model.dao.ClubDAO;
import model.dao.StudentDAO;
import model.StudentDTO;


public class ClubManager {
	private static ClubManager clubMan = new ClubManager();
	private ClubDAO clubDAO;
	private ClubAnalysis clubAnalysis;
	private StudentDAO stuDAO;

	public ClubManager() {
		try {
			clubDAO = new ClubDAO();
			clubAnalysis = new ClubAnalysis(clubDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static ClubManager getInstance() {
		return clubMan;
	}
	
	/*public int create(Club club) throws SQLException, ExistingClubException {
		if (clubDAO.existingClub(club.getClub_code()) == true) {
			throw new ExistingClubException(club.getClub_code() + "는 존재하는 아이디입니다.");
		}
		return clubDAO.insertClub(club);
	}*/

	public int remove(String clubId) throws SQLException, ClubNotFoundException {
		return clubDAO.deleteClub(clubId);
	}

	public Club findClub(String clubId)
		throws SQLException, ClubNotFoundException {
		Club club = clubDAO.getClubByCode(clubId);
		
		if (club == null) {
			throw new ClubNotFoundException(clubId + "는 존재하지 않는 아이디입니다.");
		}		
		return club;
	}

	public List<Club> findClubList() throws SQLException {
			return clubDAO.getClubList();
	}

	/*public boolean login(String clubId, String password)
		throws SQLException, ClubNotFoundException, PasswordMismatchException {
		Club club = findClub(clubId);

		if (!club.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	} 미구현*/

	/*public List<Club> makeFriends(String clubId) throws Exception {
		return clubAnalysis.recommendFriends(clubId);
	}*/
	
	public int createClub(Club club) throws SQLException {
		return clubDAO.insertClub(club);		
	}

	public int updateClub(Club club) throws SQLException {
		return clubDAO.updateClub(club);				
	}

	public ClubDAO getclubDAO() {
		return this.clubDAO;
	}
	
	public List<Club> getMatchingList(String activity, String purpose, String[] category) {
		return clubDAO.getMatchingResult(activity, purpose, category);
	}
	
	public int createMatching(String club_code, String student_number) {
		return clubDAO.insertMatching(club_code, student_number);
	}
	
	public int updateMatching(String student_number) {
		return clubDAO.updateMatching(student_number);
	}
	
	public int deleteMatching(String student_number) {
		return clubDAO.deleteMatching(student_number);
	}
	
	public List<String> findYesList(String cCode) {
		return clubDAO.getStuYesList(cCode);
	}
	
	public List<String> findNoList(String cCode) {
		return clubDAO.getStuNoList(cCode);
	}
}
