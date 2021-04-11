package model.service;

import java.sql.SQLException;
import java.util.List;

import model.ClassDTO;
import model.ProfDTO;
import model.Review;
import model.StudentDTO;
import model.dao.ReviewDAO;

public class ReviewManager {
	private static ReviewManager revMan = new ReviewManager();
	private static ReviewDAO reviewDAO;
	private static ReviewAnalysis revAna; 
	private ReviewManager() {
		try {
			reviewDAO = new ReviewDAO();
			revAna = new ReviewAnalysis(reviewDAO);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ReviewManager getInstance() {
		return revMan;
	}
	
	public int create(Review review) throws SQLException{
		return reviewDAO.create(review);
	}
	
	public List<Review> findReviewList() throws SQLException {
		return reviewDAO.findReviewList();
	}
	public List<Review> SearchReviewListForName(String className) throws SQLException {
		return reviewDAO.SearchReviewListForName(className);
	}
	public List<Review> findReviewListById(String revId) throws SQLException {
		return reviewDAO.findReviewListById(revId);
	}
	public List<ClassDTO> SearchClassListForName(String className) throws SQLException {
		return reviewDAO.SearchClassListForName(className);
	}
	public List<ClassDTO> SearchClassListForProf(String profName) throws SQLException {
		return reviewDAO.SearchClassListForProf(profName);
	}

	public ProfDTO getProfMatching(int keyId) throws Exception {
		return ReviewAnalysis.recommendProfs(keyId);
	}
	public List<Review> getReviewMatching(StudentDTO user) throws Exception {
		return ReviewAnalysis.recommendReviews(user);
	}
	public int registerKeywordMatching(Review rev) throws Exception {
		return ReviewAnalysis.registerKeywordMatching(rev);
	}
	
	
	
}
