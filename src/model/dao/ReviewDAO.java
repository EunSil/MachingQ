package model.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.*;
import oracle.net.aso.r;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * Community 테이블에서 커뮤니티 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class ReviewDAO extends TeamProjectDAO{
	private JDBCUtil jdbcUtil = null;
	
	public ReviewDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public void setClassName(Review review, int classid) {
		String sql = "SELECT title FROM course WHERE courseid = (SELECT courseid FROM class WHERE classid = " + classid + ")";
		//System.out.println("setClassName 쿼리: " + sql);
		
		JDBCUtil jdbcUtil2 = new JDBCUtil();
		jdbcUtil2.setSqlAndParameters(sql, null);
		
		try {
			
			ResultSet rs2 = jdbcUtil2.executeQuery();	// query 실행
			
			while (rs2.next()) {
				String title = rs2.getString("title");
				review.setClassName(title);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
	}
	//SQLException - 부적합한 열 이름 / 열 인덱스
	public int create(Review review) throws SQLException {
		String sql = "INSERT INTO REVIEW (revid, opensemester, lecturetotal, project, teamproject, creditrate, attendance, numberofexam, lecturereview, classid) " 
					+ "VALUES(revid_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] {review.getOpenSemester(), review.getLectureTotal(), review.getProject(),
				review.getTeamProject(), review.getCreditRate(), review.getAttendance(), 
				review.getNumberOfExam(), review.getLectureReview(), review.getClassId()};
		
		jdbcUtil.setSqlAndParameters(sql, param);
//		String sql = "INSERT INTO REVIEW ()VALUES(revid_seq.nextval," + review.getOpenSemester() + "," +  review.getLectureTotal() + "," 
//						+  review.getTeamProject() + "," +  review.getCreditRate() + "," +
//						review.getAttendance()+ "," + review.getNumberOfExam()+ "," +
//						review.getLectureReview() + "," + review.getClassId() + ")";
//		
//		System.out.println(sql);
//		jdbcUtil.setSqlAndParameters(sql, null);
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch(Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		
		}
		
		return 0;
	}
	
	//읽을 리뷰 선택 시 해당 리뷰등장
	public Review findReview(int rid) throws SQLException {
		String sql = "SELECT * FROM REVIEW WHERE revid = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {rid});
		Review rev = null;
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						
				rev = new Review(
						rid,
					rs.getInt("opensemester"),
					rs.getString("lecturetotal"),
					rs.getString("project"),
					rs.getString("teamproject"),
					rs.getString("creditrate"),
					rs.getString("attendance"),
					rs.getString("numberofexam"),
					rs.getString("lecturereview"),
					rs.getInt("classid"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return rev;
	}
	
	//리뷰리스트
		public List<Review> findReviewList() throws SQLException {
			String sql = "SELECT * FROM REVIEW ORDER BY revid DESC";
			jdbcUtil.setSqlAndParameters(sql, null);
			Review rev = null;
			
			try {
				ResultSet rs = jdbcUtil.executeQuery();	// query 실행
				List<Review> reviewList = new ArrayList<Review>();
				
				while (rs.next()) {						
					rev = new Review(
						rs.getInt("revid"),
						rs.getInt("opensemester"),
						rs.getString("lecturetotal"),
						rs.getString("project"),
						rs.getString("teamproject"),
						rs.getString("creditrate"),
						rs.getString("attendance"),
						rs.getString("numberofexam"),
						rs.getString("lecturereview"),
						rs.getInt("classid"));
					setClassName(rev, rs.getInt("classid"));
					rev.setClassDto(findClass(rs.getInt("classid")));
					//System.out.println("야 방금 구했다" + rev.getClassDto().getCourseTitle());
					reviewList.add(rev);
					
				}
				return reviewList;
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource 반환
			}
			return null;
		}
	public List<Review> findReviewListById(String revid) throws SQLException { // 코드구현 편의상 list로 받음.
			String sql =  "SELECT * FROM REVIEW WHERE revid = " + revid;
			jdbcUtil.setSqlAndParameters(sql, null);
			Review rev = null;
			System.out.println(sql);
			try {
				ResultSet rs = jdbcUtil.executeQuery();	// query 실행
				List<Review> reviewList = new ArrayList<Review>();
				
				while (rs.next()) {						
					rev = new Review(
						rs.getInt("revid"),
						rs.getInt("opensemester"),
						rs.getString("lecturetotal"),
						rs.getString("project"),
						rs.getString("teamproject"),
						rs.getString("creditrate"),
						rs.getString("attendance"),
						rs.getString("numberofexam"),
						rs.getString("lecturereview"),
						rs.getInt("classid"));
					setClassName(rev, rs.getInt("classid"));
					reviewList.add(rev);
				}
				return reviewList;
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource 반환
			}
			return null;
		}
	
	/**
	 * 주어진 교수명에 해당하는 전체 수업 정보를 데이터베이스에서 찾아 ClassDTO 도메인 클래스에 
	 * 저장하여 리스트로 반환.
	 */
	public List<ClassDTO> SearchClassListForProf(String profName) throws SQLException {
        String sql = "SELECT * " 
        		   + "FROM class "
        		   + "WHERE profid in (SELECT profid FROM professor WHERE name LIKE '%" + profName + "%') ";
//        		   + "GROUP BY courseid, profid";
        jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
        System.out.println("prof쿼리: " + sql);
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<ClassDTO> SearchClassList = new ArrayList<ClassDTO>();	// ClassDTO들의 리스트 생성
			while (rs.next()) {
				ClassDTO c = new ClassDTO(	// ClassDTO 객체를 생성하여 class 정보를 저장
						rs.getInt("classId"),
						findCourseTitle(rs.getInt("courseId")),
						rs.getInt("year"),
						rs.getInt("semester"),
						rs.getInt("division"),
						findProfName(rs.getInt("profid")));	
				
				SearchClassList.add(c);				// List에 ClassDTO 객체 저장
			}		
			return SearchClassList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	public List<Review> SearchReviewListForName(String className) throws SQLException {
		//그냥 검색키워드로 profID FIND해봐서 없으면 0이고 SQL문에는 or profid='%className%'해서 검색 합쳐버리기
        String sql = "SELECT * " 
        		   + "FROM review "
        		   + "WHERE classid in (SELECT classid FROM course join class on course.courseid = class.courseid WHERE title LIKE '%" +className +"%')";
        System.out.println("쿼리: " + sql);
        //Object[] obj = new Object[] {className};
		jdbcUtil.setSqlAndParameters(sql, null);
		
//		String sql = "SELECT * FROM REVIEW";
//		jdbcUtil.setSqlAndParameters(sql, null);
		Review rev = null;
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();	// query 실행
			List<Review> reviewList = new ArrayList<Review>();
			while (rs.next()) {						
				rev = new Review(
					rs.getInt("revid"),
					rs.getInt("opensemester"),
					rs.getString("lecturetotal"),
					rs.getString("project"),
					rs.getString("teamproject"),
					rs.getString("creditrate"),
					rs.getString("attendance"),
					rs.getString("numberofexam"),
					rs.getString("lecturereview"),
					rs.getInt("classid"));
				setClassName(rev, rs.getInt("classid"));
				reviewList.add(rev);
			}
			return reviewList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
public List<ClassDTO> SearchClassListForName(String className) throws SQLException {
        String sql = "SELECT * " 
        		   + "FROM class "
        		   + "WHERE courseid in (SELECT courseid FROM course WHERE title LIKE '%" +className +"%')";
        System.out.println("cname쿼리: " + sql);

		jdbcUtil.setSqlAndParameters(sql, null);
		
		ClassDTO rev = null;
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();	// query 실행
			List<ClassDTO> classList = new ArrayList<ClassDTO>();
			//System.out.println(rs);
			while (rs.next()) {						
				rev = new ClassDTO(	// ClassDTO 객체를 생성하여 class 정보를 저장
						rs.getInt("classid"),
						findCourseTitle(rs.getInt("courseid")),
						rs.getInt("year"),
						rs.getInt("semester"),
						rs.getInt("division"),
						findProfName(rs.getInt("profid"))
						);	
				classList.add(rev);
			}
			return classList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//jdbcUtil.close();		// resource 반환
		}		
		return null;
	}
	
	//입력한 matchingkeyword의 리스트를 찾아옴
	public List<Keyword> findKeywordList() throws SQLException {
		String sql = "SELECT * " + 
				"FROM keyword";
		jdbcUtil.setSqlAndParameters(sql, null);

		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Keyword> keyList = new ArrayList<Keyword>();	// Community들의 리스트 생성
			while (rs.next()) {	
				Keyword c = new Keyword(	// ClassDTO 객체를 생성하여 class 정보를 저장
						rs.getInt("keyId"),
						rs.getString("item"),
						rs.getString("value").split(","));	
				
				keyList.add(c);				
			}
			return keyList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	public KeywordMatching findKeywordMatching(int keyId, int profId, int courseId) throws SQLException {
		String sql = "SELECT * FROM KEYWORDMATCHING WHERE keyId=? and profId=? and courseId=?";
		Object[] param = new Object[] {keyId, profId, courseId};
		jdbcUtil.setSqlAndParameters(sql, param);

		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			while (rs.next()) {	
				KeywordMatching mat = new KeywordMatching(	
						rs.getInt("keyId"),
						rs.getInt("profId"),
						rs.getInt("courseId"),
						rs.getInt("count"));	
				return mat;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	public List<KeywordMatching> findKeywordMatching() throws SQLException {
		String sql = "SELECT * FROM KEYWORDMATCHING";
		jdbcUtil.setSqlAndParameters(sql, null);

		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<KeywordMatching> matchingList = new ArrayList<KeywordMatching>();
			while (rs.next()) {	
				KeywordMatching mat = new KeywordMatching(	
						rs.getInt("keyId"),
						rs.getInt("profId"),
						rs.getInt("courseId"),
						rs.getInt("count"));	
				matchingList.add(mat);
			}
			return matchingList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	//입력한 matchingkeyword의 리스트를 찾아옴
	public List<KeywordMatching> findMatchingByReview(Review rev, int keyId, String keyword) throws SQLException {
		List<KeywordMatching> keyList = null;
		jdbcUtil = new JDBCUtil();
		String sql = "SELECT c.courseid, c.profid "
				+ "FROM review r join class c on r.classid = c.classid "
				+ "WHERE lecturereview LIKE '%" + keyword + "%'" + " and lecturereview = '" + rev.getLectureReview() + "'";
		//System.out.println("findMatching한 쿼리: " + sql);
		jdbcUtil.setSqlAndParameters(sql, null);

		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
			keyList = new ArrayList<KeywordMatching>();	
			while (rs.next()) {	
				KeywordMatching c = new KeywordMatching(	
						keyId,
						rs.getInt("profid"),
						rs.getInt("courseid"));	
				//System.out.println("키워드: " + c);
				keyList.add(c);				
			}
			//System.out.println(keyList);
			return keyList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return keyList;
	}
	
	public int createKeywordMatching(int keyId, int profId, int courseId) throws SQLException {
		String sql = "INSERT INTO KEYWORDMATCHING VALUES(revid_seq.nextval, ?, ?, ?, ?)";
		System.out.println("CreaeteKeywordMatching: " +  keyId);
		System.out.println("CreaeteKeywordMatching: " +  profId);
		System.out.println("CreaeteKeywordMatching: " +  courseId);
		Object[] param = new Object[] {keyId, profId, courseId, 1};
		
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch(Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		
		}
		return 0;
	}
	
	public int updateKeywordMatching(int keyId, int profId, int courseId, int count) throws SQLException {
		int cnt = count + 1;
		System.out.printf("카운트 업데이트하면  %d", cnt);
		String sql = "UPDATE KEYWORDMATCHING SET count=? WHERE keyId=? and profId=? and courseId=?";
		Object[] param = new Object[] {cnt, keyId, profId, courseId};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch(Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		
		}
		return 0;
	}
	
	public int findMatchingCount(int keyId, int profId, int courseId) throws SQLException {
		  int count = 0;
	      String sql = "SELECT count "
	      			+ "FROM keywordMatching "
	      			+ "WHERE profId=? and keyId=? and courseId=?";              
			jdbcUtil.setSqlAndParameters(sql, new Object[] {profId, keyId, courseId});	// JDBCUtil에 query문과 매개 변수 설정
			
			try {
				ResultSet rs = jdbcUtil.executeQuery();		// query 실행
				if (rs.next()) {						// professor 정보 발견
					count = rs.getInt("count");	// 교수명을 저장
					System.out.println("카운트 하러 들어온 쿼리: " + count);
					
				}
				return count;
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource 반환
			}
			return 0;
	}
}
