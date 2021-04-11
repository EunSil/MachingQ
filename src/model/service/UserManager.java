package model.service;

import java.sql.SQLException;
import java.util.List;

import model.StudentDTO;
import model.dao.StudentDAO;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class UserManager {
	private static UserManager userMan = new UserManager();
	private StudentDAO userDAO;

	private UserManager() {
		try {
			userDAO = new StudentDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static UserManager getInstance() {
		return userMan;
	}
	
	public int create(StudentDTO user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getStuId()) == true) {
			throw new ExistingUserException(user.getStuId() + "는 존재하는 아이디입니다.");
		}
		return userDAO.create(user);
	}

	public int update(StudentDTO user) throws SQLException {
		return userDAO.update(user);
	}	

	public int remove(String userId) throws SQLException {
		return userDAO.remove(userId);
	}

	public StudentDTO findUser(String userId)
		throws SQLException, UserNotFoundException {
		StudentDTO user = userDAO.findUser(userId);
		if (user == null) {
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
		}
		return user;
	}

	public List<StudentDTO> findUserList() throws SQLException {
			return userDAO.findUserList();
	}
	
	public List<StudentDTO> findUserList(int currentPage, int countPerPage)
		throws SQLException {
		return userDAO.findUserList(currentPage, countPerPage);
	}

	public boolean login(String userId, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		StudentDTO user = findUser(userId);

		if (!user.matchStuPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}
	
	public StudentDAO getUserDAO() {
		return this.userDAO;
	}
}
