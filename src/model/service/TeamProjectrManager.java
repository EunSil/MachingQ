package model.service;

import java.sql.SQLException;
import java.util.List;

import model.ClassDTO;
import model.ClassRemove;
import model.Comment;
import model.TeamProject;
import model.TeamProjectMatching;
import model.dao.TeamProjectDAO;

/**
 * TeamProject 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * TeamProjectDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class TeamProjectrManager {
	private static TeamProjectrManager teamProMan = new TeamProjectrManager();
	private TeamProjectDAO teamProDAO;

	public TeamProjectrManager() {
		try {
			teamProDAO = new TeamProjectDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static TeamProjectrManager getInstance() {
		return teamProMan;
	}
	
	public int create(TeamProject teamPro, String stuId) throws SQLException {
		return teamProDAO.create(teamPro, stuId);
	}
	
	public int createMatching(TeamProjectMatching teamProMat) throws SQLException {
		return teamProDAO.createMatching(teamProMat);
	}
	
	public int createComment(Comment comm) throws SQLException {
		return teamProDAO.createComment(comm);
	}
	
	public List<TeamProject> findMyTeamProjectList(String stu_id) throws SQLException {
		return teamProDAO.findMyTeamProjectList(stu_id);
	}
	
	public List<Comment> findMyTeamProjectCommentList(int teamId, String stu_id) throws SQLException {
		return teamProDAO.findMyTeamProjectCommentList(teamId, stu_id);
	}

	public TeamProject findTeamProject(int teamId) throws SQLException, UserNotFoundException {
		TeamProject teamPro = teamProDAO.findTeamProject(teamId);				
		return teamPro;
	}

	public List<TeamProject> findTeamProjectList() throws SQLException {
		return teamProDAO.findTeamProjectList();
	}
	
	public List<TeamProject> findTeamProjectList(int courseId, int profId) throws SQLException {
		return teamProDAO.findTeamProjectList(courseId, profId);
	}
	
	public List<TeamProject> findTeamProjectList(int classId) throws SQLException {
		return teamProDAO.findTeamProjectList(classId);
	}
	
	public ClassDTO findClass(int classId) throws SQLException, NotExistingClassException {
		return teamProDAO.findClass(classId);
	}
	
	public int findClass(int courseId, int year, int semester, int division, int profId) throws SQLException, NotExistingClassException {
		String result = teamProDAO.findClass(courseId, year, semester, division, profId);
		if (result == null) {
			throw new NotExistingClassException("해당 과목은 존재하지 않습니다.");
		}
		return Integer.parseInt(result);
	}
	
	public int findClass(String courseTitle, int year, int semester, int division, String profName) throws SQLException, NotExistingClassException {
		String result = teamProDAO.findClass(courseTitle, year, semester, division, profName);
		if (result == null) {
			throw new NotExistingClassException("해당 과목은 존재하지 않습니다.");
		}
		return Integer.parseInt(result);
	}
	
	public List<ClassDTO> findSemestertList(int courseId, int profId) throws SQLException {
		return teamProDAO.findSemestertList(courseId, profId);
	}

	public List<TeamProjectMatching> findWithTeamProject(int teamId) throws SQLException {
		return teamProDAO.findWithTeamProject(teamId);
	}
	
	public boolean isMyTeamProject(int teamId, String stu_id) throws SQLException {
		return teamProDAO.isMyTeamProject(teamId, stu_id);
	}
	
	public TeamProjectDAO getTeamProjectDAO() {
		return this.teamProDAO;
	}
	
	public List<ClassRemove> SearchClassListForTitle(String lectureTitle) throws SQLException {
		return teamProDAO.SearchClassListForTitle(lectureTitle);
	}
	
	public int remove(String userId, int teamId) throws SQLException {
		return teamProDAO.remove(userId, teamId);
	}
	
	public int removeAll(String userId, int teamId) throws SQLException, ExistingMemberException {
		TeamProject teamPro = teamProDAO.findTeamProject(teamId);
		if (teamPro.getRequestMemberCnt() != 0) {
			throw new ExistingMemberException("가입한 팀원이 있어 폐쇄 할 수 없습니다.");
		}else {
			return teamProDAO.removeAll(userId, teamId);
		}
	}
		
}
