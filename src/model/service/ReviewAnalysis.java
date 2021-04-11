package model.service;

import model.dao.ReviewDAO;
import model.dao.TeamProjectDAO;
import model.*;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewAnalysis {
	private static ReviewDAO dao;
	static List<Keyword> keywordList;
	public ReviewAnalysis() {}

	public ReviewAnalysis(ReviewDAO dao) throws SQLException {
		super();
		this.dao = dao;
		
	}

	
	
	//비즈니스 메소드. 추천 알고리즘. 
	//static 하래서 했다
	public static int registerKeywordMatching(Review review) throws Exception{
		//매칭 알고리즘
		keywordList = dao.findKeywordList(); //keyword들이 등록되어 있는 keyword리스트를 불러온다.
		for(Keyword key : keywordList) //keyword리스트에서 keyword를 하나씩 불러온다.
			for(String keyword : key.getValue()) { //keyword의 value부분(ex. "[빡센,빡세요,빡세다..]")의 리스트에서 하나씩 뽑는다. 미리 split 해두었다.
				//System.out.println("registerKeywordMatching 들어가는 키워드: " + keyword); //각각 빡센, 빡세요, 빡세다.. 등등이 keyword
				List<KeywordMatching> matching = dao.findMatchingByReview(review, key.getKeyId(), keyword);
				if(matching != null)
					for(KeywordMatching value : matching) {
						System.out.println("드디어 들어온 것은: " + keyword);
						System.out.println("registerKeywordMatching: " +  value.getKeyId());
						System.out.println("registerKeywordMatching: " +  value.getProfId());
						System.out.println("registerKeywordMatching: " +  value.getCourseId());
						
						if(dao.findMatchingCount(value.getKeyId(), value.getProfId(), value.getCourseId()) == 0) {
							System.out.println("create하러 들어감");
							dao.createKeywordMatching(value.getKeyId(), value.getProfId(), value.getCourseId());
						}
						else {
							System.out.println("update하러 들어감");
							value = dao.findKeywordMatching(value.getKeyId(), value.getProfId(), value.getCourseId()); //db의 count 구해주기
							dao.updateKeywordMatching(value.getKeyId(), value.getProfId(), value.getCourseId(), value.getCount());
							
						}
					}
			}
		//나중에 class 별점 평균매겨서 넣는 알고리즘도 필요할 듯
		return 0;
	}
	public static ProfDTO recommendProfs(int keyId) throws Exception{
		//List<ProfDTO> profs= new ArrayList<ProfDTO>();
		List<KeywordMatching> keywordList = dao.findKeywordMatching();
		int max = 0;
		int profId = 0;
		String profName = "";
		
		System.out.println("여기는 추천의 현장");
		for(KeywordMatching key : keywordList) {
			if(key.getKeyId() == keyId)
				if(key.getCount() > max) {
					max = key.getCount();
					profId = key.getProfId();
				}		
		}
		System.out.println(profId);
		profName = dao.findProfName(profId);
		System.out.println(profName);
		ProfDTO prof = new ProfDTO(String.valueOf(profId), profName);
		//나중에 class 별점 평균매겨서 넣는 알고리즘도 필요할 듯
		return prof;
	}
	
	public static List<Review> recommendReviews(StudentDTO user) throws Exception{
		//해당되는 리뷰를 추천함
		List<Review> reviewList = dao.findReviewList();
		List<Review> resultList = new ArrayList<Review>();
		
		System.out.println("여기는 강의평 추천의 현장");
		//매칭 알고리즘
		for(Review review : reviewList) {
			int same = 0;
			
			if(review.getProject().equals(user.getdProject()))
				same++;
			if(review.getTeamProject().equals(user.getdTeamProject()))
				same++;
			if(review.getNumberOfExam().equals(user.getdNumberOfExam()))
				same++;
			if(review.getCreditRate().equals(user.getdCreditRate()))
				same++;
			if(review.getAttendance().equals(user.getdAttendance()))
				same++;
			
			if(same >= 4) {
				resultList.add(review);
				System.out.println(review.toString());
			}
			
		}

		return resultList;
	}
	

}
