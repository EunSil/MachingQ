package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import model.dao.Tutor_Tutee_MatchingDAO;
//import model.dao.JDBCUtil;
import model.*;
import impl.Tutor_Tutee_MatchingDAOImpl;
public class Tutor_Tutee_MatchingDAO implements Tutor_Tutee_MatchingDAOImpl {
	private JDBCUtil jdbcUtil = null;
	
	public Tutor_Tutee_MatchingDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	
	public List<Tutor> getTutorMatchingList (String grade, String subject) {
		String query = 	"SELECT TUTOR.STUDENT_NUMBER, TUTOR.GRADE, TUTOR.SUBJECT, TUTOR.YEAR " +
				"FROM TUTOR " +
				"WHERE (SUBJECT = ?) AND (GRADE LIKE ?) ";
		grade = "%" + grade + "%";
		Object[] param = new Object[] {subject, grade};
		
		List<Tutor> tutorList = new ArrayList<Tutor>();
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {
				Tutor dto = new Tutor(
				rs.getString("STUDENT_NUMBER"),
				rs.getString("SUBJECT"),
				rs.getString("GRADE"),
				rs.getString("YEAR"));
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
	
	public int insertMatching (String tutor_number, String tutee_number) {
		int result = 0;
		
		String sql = "SELECT PROJECT_CODE FROM TUTOR WHERE STUDENT_NUMBER = ? ";
		Object[] param = new Object[] {tutor_number};
		jdbcUtil.setSqlAndParameters(sql, param);

		String tutor_project = null;
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				tutor_project = rs.getString("PROJECT_CODE");
				System.out.println(tutor_project);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		
		if (tutor_project != null) {
			String query = "INSERT INTO TUTOR_TUTEE_MATCHING VALUES (?, 'N', ?)";
			String tuteeQuery = "UPDATE TUTEE "
					+ "SET PROJECT_CODE = ? "
					+ "WHERE STUDENT_NUMBER = ?";
			
			Object[] queryParam = new Object[] {tutor_project, tutee_number};
			jdbcUtil.setSqlAndParameters(query, queryParam);
			
			
			try {
				result = jdbcUtil.executeUpdate();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.commit();
				jdbcUtil.close();
			}
			
			Object[] tuteeParam = new Object[] { tutor_project, tutee_number};
			jdbcUtil.setSqlAndParameters(tuteeQuery, tuteeParam);
			
			try {
				result = jdbcUtil.executeUpdate();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.commit();
				jdbcUtil.close();
			}
		}
		else {
			String query = "INSERT INTO TUTOR_TUTEE_MATCHING VALUES (study_seq.nextval, 'N', ?)";
			
			String tutorQuery = "UPDATE TUTOR "
					+ "SET PROJECT_CODE = study_seq.currval "
					+ "WHERE STUDENT_NUMBER = ?";
			String tuteeQuery = "UPDATE TUTEE "
					+ "SET PROJECT_CODE = study_seq.currval "
					+ "WHERE STUDENT_NUMBER = ?";
			Object[] param2 = new Object[] { tutee_number};
			jdbcUtil.setSqlAndParameters(query, param2);
			
			try {
				result = jdbcUtil.executeUpdate();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.commit();
				jdbcUtil.close();
			}
			
			Object[] tutorParam = new Object[] {tutor_number};
			
			jdbcUtil.setSqlAndParameters(tutorQuery, tutorParam);
			
			try {
				result = jdbcUtil.executeUpdate();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.commit();
				jdbcUtil.close();
			}
			
			Object[] tuteeParam = new Object[] {tutee_number};
			
			jdbcUtil.setSqlAndParameters(tuteeQuery, tuteeParam);
			
			try {
				result = jdbcUtil.executeUpdate();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.commit();
				jdbcUtil.close();
			}
		}
		return result;
	}
	
	public int updateMatching(String tuteeId) {
		int result = 0;
		String query = "UPDATE Tutor_Tutee_Matching "
				+ "SET Approval = 'Y' "
				+ "WHERE Tutee_Number = ? ";
		Object[] param = new Object[] {tuteeId};
		
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
	
	public int deleteMatching(String tuteeId) {
		int result = 0;
		String query = "DELETE FROM Tutor_Tutee_Matching "
				+ "WHERE TUTEE_NUMBER = ? ";
		Object[] param = new Object[] {tuteeId};
		
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
	
	public List<Tutee> getTuteeNoList (Tutor tutor) {
		String query = 	"SELECT distinct tutee.student_number, tutee.preference_subject, tutee.grade " +
				"FROM Tutee left Join tutor_tutee_matching " +
				"On tutee.student_number = tutor_tutee_matching.tutee_number " +
				"left join Tutor on tutor_tutee_matching.project = ? " +
				"Where tutee.project_code = ? and tutor_tutee_matching.approval = 'N' ";
		Object[] param = new Object[] {tutor.getProject_code(), tutor.getProject_code()};
		
		List<Tutee> tuteeList = new ArrayList<Tutee>();
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			
			while (rs.next()) {
				Tutee dto = new Tutee(
				rs.getString("STUDENT_NUMBER"),
				rs.getString("PREFERENCE_SUBJECT"),
				rs.getString("GRADE"));
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
	
	public List<Tutee> getTuteeYesList (Tutor tutor) {
		String query = 	"SELECT distinct tutee.student_number, tutee.preference_subject, tutee.grade " +
				"FROM Tutee left Join tutor_tutee_matching " +
				"On tutee.student_number = tutor_tutee_matching.tutee_number " +
				"left join Tutor on tutor_tutee_matching.project = ? " +
				"Where tutee.project_code = ? and tutor_tutee_matching.approval = 'Y' ";
		Object[] param = new Object[] {tutor.getProject_code(), tutor.getProject_code()};
		
		List<Tutee> tuteeList = new ArrayList<Tutee>();
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			
			while (rs.next()) {
				Tutee dto = new Tutee(
				rs.getString("STUDENT_NUMBER"),
				rs.getString("PREFERENCE_SUBJECT"),
				rs.getString("GRADE"));
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
}
