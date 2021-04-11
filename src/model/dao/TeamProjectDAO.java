package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ClassDTO;
import model.ClassRemove;
import model.Comment;
import model.StudentDTO;
import model.TeamProject;
import model.TeamProjectMatching;

/**
 * 팀프로젝트 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * USERINFO 테이블에 사용자 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class TeamProjectDAO {
	private JDBCUtil jdbcUtil = null;
	
	public TeamProjectDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	/******Teamroject 관련 DAO******/
	/**
	 * TeamProject 관리 테이블에 새로운 사용자 생성.
	 */
	public int create(TeamProject teamPro, String stuId) throws SQLException {
		try {
			int teamId = findTeamProjectId();
			String sql = "INSERT INTO teamProjectTable (teamId, teamMemberCnt, classId, tMeetSchedule, tMeetPlace, tKeyword, stuId) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";		
			Object[] param = new Object[] {teamId, teamPro.getTeamMemberCnt(), teamPro.getC().getClassId(), teamPro.gettMeetSchedule(), teamPro.gettMeetPlace(), teamPro.gettKeyword(), stuId};				
			jdbcUtil.setSqlAndParameters(sql, param);	
			int result = jdbcUtil.executeUpdate();	
			
			StudentDTO s = new StudentDTO();
			s.setStuId(stuId);
			TeamProjectMatching tm = new TeamProjectMatching(teamId, s, null);
			
			createMatching(tm);
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
//			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;			
	}
	
	public int findTeamProjectId() throws SQLException {
        String sql = "SELECT project_sequence.nextval as teamId "
        			+ "FROM dual";              
		jdbcUtil.setSqlAndParameters(sql, null);	
		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {					
				int teamId = rs.getInt("teamId");
				
				return teamId;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return 0;
	}

	/**
	 * 주어진 teamProject ID에 해당하는 사용자 정보를 데이터베이스에서 찾아 TeamProject 도메인 클래스에 
	 * 저장하여 반환. (detailView.jsp)
	 */
	public TeamProject findTeamProject(int teamId) throws SQLException {
        String sql = "SELECT * "
        			+ "FROM teamProjectTable "
        			+ "WHERE teamId=?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {teamId});

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {						
				TeamProject teamPro = new TeamProject(	
					teamId,
					rs.getInt("teamMemberCnt"),
					rs.getString("tMeetSchedule"),
					rs.getString("tMeetPlace"),
					rs.getString("tKeyword"));
				teamPro.setC(findClass(rs.getInt("classId")));
				teamPro.setRequestMemberCnt(Integer.parseInt(findJoinCnt(teamId)) - 1);
				return teamPro;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();	
		}
		return null;
	}

	/**
	 * 전체 팀프로젝트 정보를 검색하여 List에 저장 및 반환 (list.jsp)
	 */
	public List<TeamProject> findTeamProjectList() throws SQLException {
        String sql = "SELECT * " 
        		   + "FROM teamProjectTable "
        		   + "ORDER BY teamId DESC";
		jdbcUtil.setSqlAndParameters(sql, null);		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
			List<TeamProject> teamProList = new ArrayList<TeamProject>();	
			List<Integer> classIds = new ArrayList<Integer>();
			while (rs.next()) {
				TeamProject teamPro = new TeamProject(	
						rs.getInt("teamId"),
						rs.getInt("teamMemberCnt"),
						rs.getString("tMeetSchedule"),
						rs.getString("tMeetPlace"),
						rs.getString("tKeyword"));
				
		
				classIds.add(rs.getInt("classId"));				
				teamProList.add(teamPro);				
			}
			for(int i = 0; i < teamProList.size(); i++) {
				teamProList.get(i).setC(findClass(classIds.get(i)));
				teamProList.get(i).setRequestMemberCnt(Integer.parseInt(findJoinCnt(teamProList.get(i).getTeamId())) - 1);
			}
			return teamProList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	
	/**
	 * 검색한 과목에 해당하는 팀프로젝트 정보 반환 (view.jsp)
	 */
	public List<TeamProject> findTeamProjectList(int courseId, int profId) throws SQLException {
		 String sql = "SELECT * " 
		      		 + "FROM teamProjectTable "
		      		 + "WHERE classId in (SELECT classId FROM class WHERE courseId=? and profId=?) ORDER BY teamId DESC";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {courseId, profId});		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
			List<TeamProject> teamProList = new ArrayList<TeamProject>();	
			List<Integer> classIds = new ArrayList<Integer>();
			while (rs.next()) {
				TeamProject teamPro = new TeamProject(	
						rs.getInt("teamId"),
						rs.getInt("teamMemberCnt"));
				
				classIds.add(rs.getInt("classId"));				
				teamProList.add(teamPro);				
			}
			for(int i = 0; i < teamProList.size(); i++) {
				teamProList.get(i).setC(findClass(classIds.get(i)));
				teamProList.get(i).setRequestMemberCnt(Integer.parseInt(findJoinCnt(teamProList.get(i).getTeamId())) - 1);
			}
			return teamProList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	/**
	 * 해당 과목에 해당하는 팀프로젝트 정보 반환 (selectForm.jsp)
	 */
	public List<TeamProject> findTeamProjectList(int classId) throws SQLException {
		 String sql = "SELECT * " 
		      		 + "FROM teamProjectTable "
		      		 + "WHERE classId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {classId});		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			List<TeamProject> teamProList = new ArrayList<TeamProject>();	
			List<Integer> classIds = new ArrayList<Integer>();
			while (rs.next()) {
				TeamProject teamPro = new TeamProject(	
						rs.getInt("teamId"),
						rs.getInt("teamMemberCnt"),
						rs.getString("tMeetSchedule"),
						rs.getString("tMeetPlace"),
						rs.getString("tKeyword"));
				
				classIds.add(classId);				
				teamProList.add(teamPro);			
			}
			for(int i = 0; i < teamProList.size(); i++) {
				teamProList.get(i).setC(findClass(classIds.get(i)));
				teamProList.get(i).setRequestMemberCnt(Integer.parseInt(findJoinCnt(teamProList.get(i).getTeamId())) - 1);
			}
			return teamProList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	/**
	 * 전체 내 팀프로젝트 정보를 검색하여 List에 저장 및 반환
	 */
	public List<TeamProject> findMyTeamProjectList(String stu_id) throws SQLException {
        String sql = "SELECT * " 
        		   + "FROM teamProjectTable tp join teamProjectMatching tm on tp.teamId = tm.teamId "
        		   + "WHERE tm.stuId=? ORDER BY joinDate DESC";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {stu_id});		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<TeamProject> teamProList = new ArrayList<TeamProject>();	// TeamProject들의 리스트 생성
			List<Integer> classIds = new ArrayList<Integer>();
			while (rs.next()) {
				TeamProject teamPro = new TeamProject(	// TeamProject 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("teamId"),
						rs.getInt("teamMemberCnt"));
				classIds.add(rs.getInt("classId"));		
				teamProList.add(teamPro);				// List에 TeamProject 객체 저장
			}
			for(int i = 0; i < teamProList.size(); i++) {
				teamProList.get(i).setC(findClass(classIds.get(i)));
			}
			return teamProList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 주어진 과목명에 해당하는 전체 수업 정보를 데이터베이스에서 찾아 ClassDTO 도메인 클래스에 
	 * 저장하여 리스트로 반환.
	 */
	public List<ClassRemove> SearchClassListForTitle(String lectureTitle) throws SQLException {
        String sql = "SELECT courseId, profId " 
        		   + "FROM class "
        		   + "WHERE courseId in (SELECT courseId FROM course WHERE title LIKE '%" + lectureTitle + "%') "
        		   + "GROUP BY courseId, profId";
        jdbcUtil.setSqlAndParameters(sql, null);		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
			List<ClassRemove> SearchClassList = new ArrayList<ClassRemove>();	
			while (rs.next()) {
				ClassRemove cr = new ClassRemove(	
						rs.getInt("courseId"),
						rs.getInt("profId"));	
				
				SearchClassList.add(cr);				
			}	
			
			for(int i = 0; i < SearchClassList.size(); i++) {
				SearchClassList.get(i).setCourseTitle(findCourseTitle(SearchClassList.get(i).getCourseId()));
				SearchClassList.get(i).setProfName(findProfName(SearchClassList.get(i).getProfId()));
			}
			
			return SearchClassList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	/**
	 * 주어진 teamId에 해당하는 사용자가 존재하는지 검사 
	 */
	public boolean isMyTeamProject(int teamId, String stu_id) throws SQLException {
		String sql = "SELECT count(*) FROM teamProjectMatching WHERE teamId=? and stuId=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {teamId, stu_id});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}
	
	/**
	 * 주어진 과목에 해당하는 개설학기 정보 반환
	 */
	public List<ClassDTO> findSemestertList(int courseId, int profId) throws SQLException {
        String sql = "SELECT * "
        			+ "FROM class "
        			+ "WHERE courseId=? and profId=? ORDER BY year DESC, semester DESC";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {courseId, profId});
		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			List<ClassDTO> classList = new ArrayList<ClassDTO>();	
			while (rs.next()) {				
				ClassDTO c = new ClassDTO(
						rs.getInt("classId"),
						rs.getInt("year"),
						rs.getInt("semester"),
						rs.getInt("division")
						);
				classList.add(c);		
			}
			return classList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();	
		}
		return null;
	}
	/************/
	
	/******객체의 정보를 찾기 위해 도와주는 DAO******/
	public StudentDTO findStudent(String stuId) throws SQLException {
	      String sql = "SELECT * "
	      			+ "FROM student "
	      			+ "WHERE stuId=?";              
			jdbcUtil.setSqlAndParameters(sql, new Object[] {stuId});	

			try {
				ResultSet rs = jdbcUtil.executeQuery();		
				StudentDTO s = null;
				
				if (rs.next()) {					
					s = new StudentDTO(
					stuId,
					rs.getString("name"),
					rs.getInt("grade"));	
				}
				return s;
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
//				jdbcUtil.close();		
			}
			return null;
		}
	
	public ClassDTO findClass(int classId) throws SQLException {
      String sql = "SELECT * "
      			+ "FROM class "
      			+ "WHERE classId=?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {classId});

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			ClassDTO c = null;
			int profId = 0;
			int courseId = 0;
			
			if (rs.next()) {				
				c = new ClassDTO(	
				classId,
				rs.getInt("year"),
				rs.getInt("semester"),
				rs.getInt("division"));	
				
				profId = rs.getInt("profId");
				courseId = rs.getInt("courseId");
			}
			c.setProfName(findProfName(profId));
			c.setCourseTitle(findCourseTitle(courseId));
			return c;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
//			jdbcUtil.close();		
		}
		return null;
	}
	
	public String findClass(int courseId, int year, int semester, int division, int profId) throws SQLException {
	      	String sql = "SELECT * "
	      			+ "FROM class "
	      			+ "WHERE courseId=? and year=? and semester=? and division=? and profId=?";              
			jdbcUtil.setSqlAndParameters(sql, new Object[] {courseId, year, semester, division, profId});	

			try {
				ResultSet rs = jdbcUtil.executeQuery();		
				String classId = null;
				
				if (rs.next()) {					
					classId = Integer.toString(rs.getInt("classId"));	
				}
				
				return classId;
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		
			}
			return null;
	}
	
	public String findClass(String courseTitle, int year, int semester, int division, String profName) throws SQLException {
      	String sql = "SELECT * "
      			+ "FROM class c, course co, professor p "
      			+ "WHERE c.courseId=co.courseId and c.profId=p.profId and title=? and name=? and year=? and semester=? and division=?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {courseTitle, profName, year, semester, division});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			String classId = null;
			
			if (rs.next()) {				
				classId = Integer.toString(rs.getInt("classId"));	
			}
			
			return classId;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	public String findCourseTitle(int courseId) throws SQLException {
	      String sql = "SELECT title "
	      			+ "FROM course "
	      			+ "WHERE courseId=" + courseId;
	      	jdbcUtil.setSqlAndParameters(sql, null);	
			try {
				ResultSet rs = jdbcUtil.executeQuery();		
				if (rs.next()) {						
					String title = rs.getString("title");	
					return title;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				//jdbcUtil.close();		
			}
			return null;
	}
	
	public String findProfName(int profId) throws SQLException {
	      String sql = "SELECT name "
	      			+ "FROM professor "
	      			+ "WHERE profId=" + profId;
	      	jdbcUtil.setSqlAndParameters(sql, null);	

			try {
				ResultSet rs = jdbcUtil.executeQuery();		
				if (rs.next()) {						
					String profName = rs.getString("name");	
					return profName;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				//jdbcUtil.close();		
			}
			return null;
	}
	
	public String findJoinCnt(int teamId) throws SQLException {
	      String sql = "SELECT count(*) as cnt "
	      			+ "FROM teamProjectMatching "
	      			+ "WHERE teamId=" + teamId;
	      	jdbcUtil.setSqlAndParameters(sql, null);	

			try {
				ResultSet rs = jdbcUtil.executeQuery();		
				if (rs.next()) {						
					int cnt = rs.getInt("cnt");	
					return Integer.toString(cnt);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		
			}
			return null;
	}
	/************/
	
	
	/******커뮤니티를 위한 DAO******/
	/**
	 * TeamProjectMatching 관리 테이블에 새로운 관계 생성.
	 */
	public int createMatching(TeamProjectMatching teamProMat) throws SQLException {
		String sql = "INSERT INTO teamProjectMatching (teamId, stuId, joinDate) "
					+ "VALUES (?, ?, SYSDATE)";		
		Object[] param = new Object[] {teamProMat.getTeamId(), teamProMat.getS().getStuId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}
	
	/**
	 * Comment 관리 테이블에 새로운 관계 생성.
	 */
	public int createComment(Comment comm) throws SQLException {
		String sql = "INSERT INTO commentTable (commentId, teamId, comments, commDate, stuId) "
					+ "VALUES (comment_seq.nextval, ?, ?, SYSDATE, ?)";		
		Object[] param = new Object[] {comm.getTeamId(), comm.getComments(), comm.getS().getStuId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}
	
	/**
	 * 주어진 teamId에 해당하는 사용자 정보 받아오기
	 */
	public List<TeamProjectMatching> findWithTeamProject(int teamId) throws SQLException {
		String sql = "SELECT * FROM teamProjectMatching WHERE teamId=? ORDER BY joinDate";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {teamId});	

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			List<TeamProjectMatching> teamProMatList = new ArrayList<TeamProjectMatching>();
			List<String> stuList = new ArrayList<String>();
			while (rs.next()) {
				TeamProjectMatching teamProMat = new TeamProjectMatching(	
						teamId,
						rs.getDate("joinDate"));
				stuList.add(rs.getString("stuId"));		
				teamProMatList.add(teamProMat);				
			}
			for(int i = 0; i < teamProMatList.size(); i++) {
				teamProMatList.get(i).setS(findStudent(stuList.get(i)));
			}
			return teamProMatList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	
	/**
	 * 주어진 teamId에 해당하는 댓글 리스트 반환
	 */
	public List<Comment> findMyTeamProjectCommentList(int teamId, String stu_id) throws SQLException {
        String sql = "SELECT * " 
        		   + "FROM commenttable "
        		   + "WHERE teamId=? and commDate > (SELECT joinDate FROM teamProjectMatching WHERE teamId=? and stuId=?) "
        		   + "ORDER BY commDate";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {teamId, teamId, stu_id});		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
			List<Comment> commList = new ArrayList<Comment>();	
			List<String> stuList = new ArrayList<String>();
			while (rs.next()) {
				Comment comm = new Comment(	
						rs.getInt("teamId"),
						rs.getString("comments"),
						rs.getDate("commDate")
						);
				stuList.add(rs.getString("stuId"));
				commList.add(comm);				
			}
			for(int i = 0; i < commList.size(); i++) {
				commList.get(i).setS(findStudent(stuList.get(i)));
			}
			return commList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	/**
	 * 주어진 teamId에 해당하는 사용자 삭제
	 */
	public int remove(String stuId, int teamId) throws SQLException {
		String sql = "DELETE FROM teamProjectMatching WHERE stuId=? and teamId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {stuId, teamId});	
		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;
	}
	
	/**
	 * 주어진 teamId에 해당하는 팀프로젝트 정보 모두 삭제
	 */
	public int removeAll(String stuId, int teamId) throws SQLException {
		String sql = "DELETE FROM teamProjectMatching WHERE stuId=? and teamId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {stuId, teamId});	
		try {				
			int result = jdbcUtil.executeUpdate();	
			removeAll_1(teamId);
			removeAll_2(teamId);
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;
	}
	
	public int removeAll_1( int teamId) throws SQLException {
		String sql = "DELETE FROM commentTable WHERE teamId=?";				
		jdbcUtil.setSqlAndParameters(sql, new Object[] {teamId});	
		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
//			jdbcUtil.close();	
		}		
		return 0;
	}
	
	public int removeAll_2(int teamId) throws SQLException {
		String sql = "DELETE FROM teamProjectTable WHERE teamId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {teamId});	
		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
//			jdbcUtil.commit();
//			jdbcUtil.close();	
		}		
		return 0;
	}
	
}
