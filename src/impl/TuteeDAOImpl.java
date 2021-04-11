package impl;

import java.util.*;
import model.*;

public interface TuteeDAOImpl {
	public List<Tutee> getTuteeList(String project_code);						// 전체 튜티 정보를 획득
	public int insertTutee(Tutee tutee);					// 튜티 정보 추가
	public int updateTutee(Tutee tutee);					// 튜티 정보 수정
	public int deleteTutee(String student_number);				// 튜티 정보 삭제	
	public Tutee getTuteeByCode(String student_number);		// 튜티 정보를 동아리코드로 찾음
	List<Tutee> getTuteeList();
}
