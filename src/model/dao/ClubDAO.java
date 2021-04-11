package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import model.Club;
import model.StudentDTO;
import model.Tutee;
public class ClubDAO implements impl.ClubDAOImpl{
private JDBCUtil jdbcUtil = null;
	
	private static String query = 	"SELECT SCHOOL_CLUB.CLUB_CODE, " +
	  								"       SCHOOL_CLUB.CLUB_NAME, " +
	  								"       SCHOOL_CLUB.PRESIDENT_CODE, " +
	  								"       SCHOOL_CLUB.PRESIDENT_PHONE, " +
	  								"       SCHOOL_CLUB.PROFESSOR_CODE, " +
	  								"       SCHOOL_CLUB.PROFESSOR_PHONE, " +
	  								"       SCHOOL_CLUB.CLUB_PLACE, " +
	  								"       SCHOOL_CLUB.CLUB_TIME, " +
	  								"       SCHOOL_CLUB.ACTIVITY, " +
	  								"       SCHOOL_CLUB.PURPOSE, " +
	  								"       SCHOOL_CLUB.CATEGORY, " +
	  								"       SCHOOL_CLUB.DESCRIPTION " +
	  								"FROM SCHOOL_CLUB ";
	
	public ClubDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	
	@Override
	public Club getClubByName(String name) {
		String searchQuery = query + "WHERE SCHOOL_CLUB.CLUB_NAME = ?";
		Object[] param = new Object[] {name};
		
		jdbcUtil.setSqlAndParameters(searchQuery, param);
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			Club dto = new Club();
			while (rs.next()) {
				dto.setClub_code(rs.getString("CLUB_CODE"));
				dto.setClub_name(rs.getString("CLUB_NAME"));
				dto.setPresident_code(rs.getString("PRESIDENT_CODE"));
				dto.setPresident_phone(rs.getString("PRESIDENT_PHONE"));
				dto.setProfessor_code(rs.getString("PROFESSOR_CODE"));
				dto.setProfessor_phone(rs.getString("PROFESSOR_PHONE"));
				dto.setClub_place(rs.getString("CLUB_PLACE"));
				dto.setClub_time(rs.getString("CLUB_TIME"));
				dto.setActivity(rs.getString("ACTIVITY"));
				dto.setActivity(rs.getString("PURPOSE"));
				dto.setActivity(rs.getString("CATEGORY"));
				dto.setActivity(rs.getString("DESCRIPTION"));
			}
			return dto;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	@Override
	public List<Club> getClubList() {
		// TODO Auto-generated method stub
		String searchQuery = query;
		
		jdbcUtil.setSqlAndParameters(searchQuery, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Club> clubList = new ArrayList<Club>();
			
			while (rs.next()) {
				Club dto = new Club(
				rs.getString("CLUB_CODE"),
				rs.getString("CLUB_NAME"),
				rs.getString("PRESIDENT_CODE"),
				rs.getString("PRESIDENT_PHONE"),
				rs.getString("PROFESSOR_CODE"),
				rs.getString("PROFESSOR_PHONE"),
				rs.getString("CLUB_PLACE"),
				rs.getString("CLUB_TIME"),
				rs.getString("ACTIVITY"),
				rs.getString("PURPOSE"),
				rs.getString("CATEGORY"),
				rs.getString("DESCRIPTION"));
				clubList.add(dto);
			}
			return clubList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	@Override
	public int insertClub(Club club) {
		// TODO Auto-generated method stub
		int result = 0;
		String query = "INSERT INTO SCHOOL_CLUB (club_code, club_name, president_code, president_phone, professor_code, professor_phone, club_place, club_time, activity, purpose, category, description) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
		Object[] param = new Object[] {club.getClub_code(), club.getClub_name(), club.getPresident_code(), club.getPresident_phone(),
								club.getProfessor_code(), club.getProfessor_phone(), club.getClub_place(), club.getClub_time(),
								club.getActivity(), club.getPurpose(), club.getCategory(), club.getDescription()};
		
		/*DAOFactory factory = new DAOFactory();
		ProfDAO profDAO = factory.getStuDAO(); // factory 를 통해 학생에 대한 DAO 획득
		ProfDTO profDTO = profDAO.getStuByName(stu.getProfName());
		String pCode = profDTO.getPCode();
		String pCode = club.getPresident_code();
		if (pCode == null) { System.out.println("해당 학번이 없습니다."); return 0; }*/
		
		/*DAOFactory factory = new DAOFactory();
		ProfDAO profDAO = factory.getProfDAO(); // factory 를 통해 교수에 대한 DAO 획득
		ProfDTO profDTO = profDAO.getProfByName(stu.getProfName());
		String pCode = profDTO.getPCode();
		if (profCode == null) { System.out.println("해당 교수가 없습니다."); return 0; }*/
		
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
	public int updateClub(Club club) {
		// TODO Auto-generated method stub
		int result = 0;
		String query = "UPDATE SCHOOL_CLUB "
				+ "SET CLUB_NAME = ?, PRESIDENT_CODE = ?, PRESIDENT_PHONE = ?, PROFESSOR_CODE = ?, PROFESSOR_PHONE = ?, CLUB_PLACE = ?, CLUB_TIME = ?, ACTIVITY = ?, PURPOSE = ?, CATEGORY = ?, description = ? "
				+ "WHERE CLUB_CODE = ? ";
		
		Object[] param = new Object[] { club.getClub_name(), club.getPresident_code(), club.getPresident_phone(),
								club.getProfessor_code(), club.getProfessor_phone(), club.getClub_place(), club.getClub_time(),
								club.getActivity(), club.getPurpose(), club.getCategory(), club.getDescription(), club.getClub_code()};
		
		/*DAOFactory factory = new DAOFactory();
		ProfDAO profDAO = factory.getStuDAO(); // factory 를 통해 학생에 대한 DAO 획득
		ProfDTO profDTO = profDAO.getStuByName(stu.getProfName());
		String pCode = profDTO.getPCode();
		String pCode = club.getPresident_code();
		if (pCode == null) { System.out.println("해당 학번이 없습니다."); return 0; }*/
		
		/*DAOFactory factory = new DAOFactory();
		ProfDAO profDAO = factory.getProfDAO(); // factory 를 통해 교수에 대한 DAO 획득
		ProfDTO profDTO = profDAO.getProfByName(stu.getProfName());
		String pCode = profDTO.getPCode();
		if (profCode == null) { System.out.println("해당 교수가 없습니다."); return 0; }*/
		
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
	public int deleteClub(String cCode) {
		// TODO Auto-generated method stub
		int result = 0;
		String query = "DELETE FROM SCHOOL_CLUB "
				+ "WHERE CLUB_CODE = ? ";
		Object[] param = new Object[] {cCode};
		
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
	public Club getClubByCode(String cCode) {
		// TODO Auto-generated method stub
		String searchQuery = query + "WHERE SCHOOL_CLUB.CLUB_CODE = ?";
		Object[] param = new Object[] {cCode};
		
		jdbcUtil.setSqlAndParameters(searchQuery, param);
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			Club dto;
			if (rs.next()) {
				dto = new Club(
				rs.getString("CLUB_CODE"),
				rs.getString("CLUB_NAME"),
				rs.getString("PRESIDENT_CODE"),
				rs.getString("PRESIDENT_PHONE"),
				rs.getString("PROFESSOR_CODE"),
				rs.getString("PROFESSOR_PHONE"),
				rs.getString("CLUB_PLACE"),
				rs.getString("CLUB_TIME"),
				rs.getString("ACTIVITY"),
				rs.getString("PURPOSE"),
				rs.getString("CATEGORY"),
				rs.getString("DESCRIPTION"));
				
				return dto;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	// 나중에 수정해야함 
	public List<Club> getMatchingResult(String activity, String purpose, String[] category) {
		String searchQuery = query;
		String whereQuery = "WHERE (activity = ?) AND (purpose = ?) ";
		
		List<String> categoryList = Arrays.asList(category);
		for (int i = 0; i < categoryList.size(); i++) {
			if (i == 0) {
				whereQuery += "AND ((category = ? ) ";
			}
			else {
				whereQuery += "OR (category = ? ) ";
			}
		}
		whereQuery+= ") ";
		
		searchQuery += whereQuery;
		//searchQuery  추가해야함 
		List<Club> clubList = new ArrayList<Club>();
		
		Object[] param = new Object[category.length+2];
		param[0] = activity;
		param[1] = purpose;
		for (int i = 0; i < category.length; i++) {
			param[i + 2] = category[i];
		}
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {
				Club dto = new Club(
						rs.getString("CLUB_CODE"),
						rs.getString("CLUB_NAME"),
						rs.getString("PRESIDENT_CODE"),
						rs.getString("PRESIDENT_PHONE"),
						rs.getString("PROFESSOR_CODE"),
						rs.getString("PROFESSOR_PHONE"),
						rs.getString("CLUB_PLACE"),
						rs.getString("CLUB_TIME"),
						rs.getString("ACTIVITY"),
						rs.getString("PURPOSE"),
						rs.getString("CATEGORY"),
						rs.getString("DESCRIPTION"));
						clubList.add(dto);
			}
			return clubList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
			jdbcUtil.close();
		}
		return null;
	}
	
	/**
	 * 주어진 클럽 ID에 해당하는 동아리가 존재하는지 검사 
	 */
	public boolean existingClub(String club_code) throws SQLException {
		String sql = "SELECT count(*) FROM SCHOOL_CLUB WHERE CLUB_CODE=?";
		Object[] param = new Object[] {club_code};
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
	
	public int insertMatching(String club_code, String student_number) {
		int result = 0;
		String query = "INSERT INTO CLUB_MATCHING VALUES (?, ?, 'N') ";
		Object[] param = new Object[] {club_code, student_number};
		
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			result = jdbcUtil.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return result;
	}
	
	public int updateMatching(String student_number) {
		int result = 0;
		String query = "UPDATE CLUB_MATCHING "
				+ "SET APPROVAL = 'Y' "
				+ "WHERE STUID = ?";
		Object[] param = new Object[] {student_number};
		
		/*DAOFactory factory = new DAOFactory();
		ProfDAO profDAO = factory.getStuDAO(); // factory 를 통해 학생에 대한 DAO 획득
		ProfDTO profDTO = profDAO.getStuByName(stu.getProfName());
		String pCode = profDTO.getPCode();
		String pCode = club.getPresident_code();
		if (pCode == null) { System.out.println("해당 학번이 없습니다."); return 0; }*/
		
		/*DAOFactory factory = new DAOFactory();
		ProfDAO profDAO = factory.getProfDAO(); // factory 를 통해 교수에 대한 DAO 획득
		ProfDTO profDTO = profDAO.getProfByName(stu.getProfName());
		String pCode = profDTO.getPCode();
		if (profCode == null) { System.out.println("해당 교수가 없습니다."); return 0; }*/
		
		jdbcUtil.setSqlAndParameters(query, param);
	
		try {
			result = jdbcUtil.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return result;
	}
	
	public int deleteMatching(String student_number) {
		int result = 0;
		String query = "DELETE FROM CLUB_MATCHING "
				+ "WHERE STUID = ?";
		Object[] param = new Object[] {student_number};
		
		jdbcUtil.setSqlAndParameters(query, param);
	
		try {
			result = jdbcUtil.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return result;
	}
	
	public List<String> getStuNoList (String cCode){
		
		String query = 	"SELECT distinct * " +
				"FROM club_matching " +
				"Where club_code = ? and approval = 'N' ";
		Object[] param = new Object[] {cCode};
		
		List<String> stuList = new ArrayList<String>();
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			
			while (rs.next()) {
				stuList.add(rs.getString("stuId"));
			}
			return stuList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
		
	}
	
	public List<String> getStuYesList (String cCode){
		String query = 	"SELECT distinct * " +
				"FROM club_matching " +
				"Where club_code = ? and approval = 'Y' ";
		Object[] param = new Object[] {cCode};
		
		List<String> stuList = new ArrayList<String>();
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			
			while (rs.next()) {
				stuList.add(rs.getString("stuId"));
			}
			return stuList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
}
