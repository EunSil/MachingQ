package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.StudentDTO;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * STUDENT 테이블에 사용자 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class StudentDAO {
	private JDBCUtil jdbcUtil = null;
	
	public StudentDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	/**
	 * 사용자 관리 테이블에 새로운 사용자 생성.
	 */
	public int create(StudentDTO student) throws SQLException {
		String sql = "INSERT INTO Student (stuId, name, password, grade, dProject, dTeamproject, dCreditRate, dAttendance, dNumberOfExam) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {student.getStuId(), student.getStuName(),
				student.getPassword(), student.getGrade(), student.getdProject(),
				student.getdTeamProject(), student.getdCreditRate(), 
				student.getdAttendance(), student.getdNumberOfExam()};				
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
	 * 기존의 사용자 정보를 수정.
	 */
	public int update(StudentDTO student) throws SQLException {
		String sql = "UPDATE STUDENT "
					+ "SET password=?, name=?, grade=?, dProject=?, dTeamProject=?, dAttendance=?, dCreditRate=?, dNumberOfExam=?"
					+ "WHERE stuId=?";
		Object[] param = new Object[] {student.getPassword(), student.getStuName(), student.getGrade(), 
				student.getdProject(), student.getdTeamProject(), student.getdAttendance(), 
				student.getdCreditRate(), student.getdNumberOfExam(), student.getStuId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	/**
	 * 사용자 ID에 해당하는 사용자를 삭제.
	 */
	public int remove(String stuId) throws SQLException {
		String sql = "DELETE FROM STUDENT WHERE stuId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {stuId});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	/**
	 * 주어진 사용자 ID에 해당하는 사용자 정보를 데이터베이스에서 찾아 User 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public StudentDTO findUser(String stuId) throws SQLException {
        String sql = "SELECT password, name, grade, dProject, dTeamproject, dCreditRate, dAttendance, dNumberOfExam "
        			+ "FROM Student "
        			+ "WHERE stuId=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {stuId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			if (rs.next()) {			// 학생 정보 발견
				StudentDTO student = new StudentDTO(		// User 객체를 생성하여 학생 정보를 저장
					stuId,
					rs.getString("name"),
					rs.getString("password"),
					rs.getInt("grade"),
					rs.getString("dProject"),
					rs.getString("dTeamproject"),
					rs.getString("dCreditRate"),
					rs.getString("dAttendance"),
					rs.getString("dNumberOfExam"));
				return student;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	/**
	 * 전체 사용자 정보를 검색하여 List에 저장 및 반환
	 */
	public List<StudentDTO> findUserList() throws SQLException {
        String sql = "SELECT stuId, password, name, grade, dProject, dTeamproject, dCreditRate, dAttendance, dNumberOfExam " 
        		   + "FROM STUDENT "
        		   + "ORDER BY stuId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<StudentDTO> userList = new ArrayList<StudentDTO>();	// User들의 리스트 생성
			while (rs.next()) {
				StudentDTO user = new StudentDTO(			// User 객체를 생성하여 현재 행의 정보를 저장
					rs.getString("stuId"),
					rs.getString("name"),
					rs.getString("password"),
					rs.getInt("grade"),
					rs.getString("dProject"),
					rs.getString("dTeamproject"),
					rs.getString("dCreditRate"),
					rs.getString("dAttendance"),
					rs.getString("dNumberOfExam"));
				userList.add(user);				// List에 User 객체 저장
			}		
			return userList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 전체 사용자 정보를 검색한 후 현재 페이지와 페이지당 출력할 사용자 수를 이용하여
	 * 해당하는 사용자 정보만을 List에 저장하여 반환.
	 */
	public List<StudentDTO> findUserList(int currentPage, int countPerPage) throws SQLException {
        String sql = "SELECT stuId, password, name, grade, dProject, dTeamproject, dCreditRate, dAttendance, dNumberOfExam "
        		   + "FROM STUDENT "
        		   + "ORDER BY stuId";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil에 query문 설정
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 가능
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query 실행			
			int start = ((currentPage-1) * countPerPage) + 1;	// 출력을 시작할 행 번호 계산
			if ((start >= 0) && rs.absolute(start)) {			// 커서를 시작 행으로 이동
				List<StudentDTO> userList = new ArrayList<StudentDTO>();	// User들의 리스트 생성
				do {
					StudentDTO user = new StudentDTO(		// User 객체를 생성하여 현재 행의 정보를 저장
						rs.getString("stuId"),
						rs.getString("name"),
						rs.getString("password"),
						rs.getInt("grade"),
						rs.getString("dProject"),
						rs.getString("dTeamproject"),
						rs.getString("dCreditRate"),
						rs.getString("dAttendance"),
						rs.getString("dNumberOfExam"));
					userList.add(user);							// 리스트에 User 객체 저장
				} while ((rs.next()) && (--countPerPage > 0));		
				return userList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	/**
	 * 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사 
	 */
	public boolean existingUser(String stuId) throws SQLException {
		String sql = "SELECT count(*) FROM STUDENT WHERE stuId=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {stuId});	// JDBCUtil에 query문과 매개 변수 설정

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
}
