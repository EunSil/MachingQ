package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import model.Tutor;

public class TutorDAO implements impl.TutorDAOImpl{

private JDBCUtil jdbcUtil = null;
	
	private static String query = 	"SELECT TUTOR.STUDENT_NUMBER, " +
	  								"       TUTOR.SUBJECT, " +
	  								"       TUTOR.GRADE, " +
	  								"       TUTOR.YEAR " +
	  								"FROM TUTOR ";
	
	public TutorDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		jdbcUtil = new JDBCUtil();
	}
	
	public Tutor getTutorByCode(String student_number) {
		String searchQuery = "SELECT Student_number, subject, grade, year, project_code " +
						"FROM Tutor " +
						"WHERE STUDENT_NUMBER = ? ";
		Object[] param = new Object[] {student_number};
		
		jdbcUtil.setSqlAndParameters(searchQuery, param);
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			Tutor dto;
			if (rs.next()) {
				dto = new Tutor(
						rs.getString("STUDENT_NUMBER"),
						rs.getString("SUBJECT"),
						rs.getString("GRADE"),
						rs.getString("YEAR"),
						rs.getString("PROJECT_CODE"));
				return dto;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public List<Tutor> getTutorList(String subject) {
		// TODO Auto-generated method stub
		String searchQuery = query + "WHERE TUTOR.SUBJECT = ?";
		Object[] param = new Object[] {subject};
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		
		List<Tutor> tutorList = new ArrayList<Tutor>();
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			Tutor dto = new Tutor();
			while (rs.next()) {
				dto.setStudent_number(rs.getString("STUDENT_NUMBER"));
				dto.setSubject(rs.getString("SUBJECT"));
				dto.setGrade(rs.getString("GRADE"));
				dto.setYear(rs.getString("YEAR"));
				tutorList.add(dto);
			}
			return tutorList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public List<Tutor> getTutorList() {
		// TODO Auto-generated method stub
		String searchQuery = query;
		jdbcUtil.setSqlAndParameters(searchQuery, null);
		
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Tutor> tutorList = new ArrayList<Tutor>();
			
			while (rs.next()) {
				Tutor dto = new Tutor(rs.getString("STUDENT_NUMBER"),
						rs.getString("SUBJECT"),
						rs.getString("GRADE"),
						rs.getString("YEAR")
						);
				
				tutorList.add(dto);
			}
			return tutorList;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public int insertTutor(Tutor tutor) {
		// TODO Auto-generated method stub
		int result = 0;
		String query = "INSERT INTO TUTOR (STUDENT_NUMBER, SUBJECT, GRADE, YEAR) "
				+ "VALUES (?, ?, ?, ?)";
		Object[] param = new Object[] {tutor.getStudent_number(), tutor.getSubject(),
								tutor.getGrade(), tutor.getYear()};
		
		/*DAOFactory factory = new DAOFactory();
		ProfDAO profDAO = factory.getStuDAO(); // factory 를 통해 학생에 대한 DAO 획득
		ProfDTO profDTO = profDAO.getStuByName(stu.getProfName());
		String pCode = profDTO.getPCode();
		String pCode = club.getPresident_code();
		if (pCode == null) { System.out.println("해당 학번이 없습니다."); return 0; }*/
		
		jdbcUtil.setSqlAndParameters(query, param);
	
		try {
			result = jdbcUtil.executeUpdate();
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return result;
	}

	public int updateTutor(Tutor tutor) {
		// TODO Auto-generated method stub
		int result = 0;
		String query = "UPDATE TUTOR "
				+ "SET SUBJECT = ?, GRADE = ?, YEAR = ? "
				+ "WHERE STUDENT_NUMBER = ? ";
		Object[] param = new Object[] {tutor.getSubject(),
					tutor.getGrade(), tutor.getYear(), tutor.getStudent_number()};
		
		/*DAOFactory factory = new DAOFactory();
		ProfDAO profDAO = factory.getStuDAO(); // factory 를 통해 학생에 대한 DAO 획득
		ProfDTO profDTO = profDAO.getStuByName(stu.getProfName());
		String pCode = profDTO.getPCode();
		String pCode = club.getPresident_code();
		if (pCode == null) { System.out.println("해당 학번이 없습니다."); return 0; }*/
		
		jdbcUtil.setSqlAndParameters(query, param);
	
		try {
			result = jdbcUtil.executeUpdate();
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return result;
	}

	public int deleteTutor(String student_number) {
		// TODO Auto-generated method stub
		int result = 0;
		String query = "DELETE FROM TUTOR "
				+ "WHERE STUDENT_NUMBER = ? ";
		Object[] param = new Object[] {student_number};
		
		jdbcUtil.setSqlAndParameters(query, param);

	
		try {
			result = jdbcUtil.executeUpdate();
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return result;
	}
	
	/**
	 * 주어진 클럽 ID에 해당하는 동아리가 존재하는지 검사 
	 */
	public boolean existingTutor(String student_number) throws SQLException {
		String sql = "SELECT count(*) FROM TUTOR WHERE STUDENT_NUMBER=?";
		Object[] param = new Object[] {student_number};
		jdbcUtil.setSqlAndParameters(sql, param);

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
