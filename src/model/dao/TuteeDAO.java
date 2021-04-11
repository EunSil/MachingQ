package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Tutee;

public class TuteeDAO implements impl.TuteeDAOImpl{
private JDBCUtil jdbcUtil = null;
	
	private static String query = 	"SELECT TUTEE.STUDENT_NUMBER, " +
	  								"       TUTEE.PREFERENCE_SUBJECT, " +
	  								"       TUTEE.GRADE, " +
	  								"FROM TUTEE ";
	
	public TuteeDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	@Override
	public Tutee getTuteeByCode(String student_number) {
		String searchQuery = "SELECT student_number, preference_subject, grade "
							+ "FROM Tutee "
							+ "WHERE student_number=? ";
		Object[] param = new Object[] {student_number};
		
		jdbcUtil.setSqlAndParameters(searchQuery, param);
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				Tutee dto = new Tutee(
						rs.getString("STUDENT_NUMBER"),
						rs.getString("PREFERENCE_SUBJECT"),
						rs.getString("GRADE"));
				return dto;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	@Override
	public List<Tutee> getTuteeList(String project_code) {
		// TODO Auto-generated method stub
		String searchQuery = query + "WHERE TUTEE.PROJECT_CODE = ?";
		Object[] param = new Object[] {project_code};
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		
		List<Tutee> tuteeList = new ArrayList<Tutee>();
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			Tutee dto = new Tutee();
			while (rs.next()) {
				dto.setStudent_number(rs.getString("STUDENT_NUMBER"));
				dto.setPreference_subject(rs.getString("PREFERENCE_SUBJECT"));
				dto.setGrade(rs.getString("GOAL"));
				tuteeList.add(dto);
			}
			return tuteeList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	@Override
	public List<Tutee> getTuteeList() {
		// TODO Auto-generated method stub
		String searchQuery = query;
		jdbcUtil.setSqlAndParameters(searchQuery, null);
		
		List<Tutee> tuteeList = new ArrayList<Tutee>();
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			Tutee dto = new Tutee();
			while (rs.next()) {
				dto.setStudent_number(rs.getString("STUDENT_NUMBER"));
				dto.setPreference_subject(rs.getString("PREFERENCE_SUBJECT"));
				dto.setGrade(rs.getString("GRADE"));
				tuteeList.add(dto);
			}
			return tuteeList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	@Override
	public int insertTutee(Tutee tutee) {
		// TODO Auto-generated method stub
		int result = 0;
		String query = "INSERT INTO TUTEE (STUDENT_NUMBER, PREFERENCE_SUBJECT, GRADE) "
				+ "VALUES (?, ?, ?)";
		Object[] param = new Object[] {tutee.getStudent_number(), tutee.getPreference_subject(), tutee.getGrade()};
		
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

	@Override
	public int updateTutee(Tutee tutee) {
		// TODO Auto-generated method stub
		int result = 0;
		String query = "UPDATE TUTOR "
				+ "SET STUDENT_NUMBER = ?, PREFERENCE_SUBJECT = ?, GRADE = ? "
				+ "WHERE STUDENT_NUMBER = ?";
		Object[] param = new Object[] {tutee.getStudent_number(), tutee.getPreference_subject(), tutee.getGrade()};
		
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

	@Override
	public int deleteTutee(String student_number) {
		// TODO Auto-generated method stub
		int result = 0;
		String query = "DELETE FROM TUTEE "
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
	public boolean existingTutee(String student_number) throws SQLException {
		String sql = "SELECT count(*) FROM TUTEE WHERE STUDENT_NUMBER=?";
		Object[] param = new Object[] {student_number};
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 query문과 매개 변수 설정

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
