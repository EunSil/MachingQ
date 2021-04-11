package impl;

import java.util.*;
import model.*;

public interface TutorDAOImpl {
	public List<Tutor> getTutorList(String subject);						// 전체 튜터 정보를 획득
	public int insertTutor(Tutor tutor);					// 튜터 정보 추가
	public int updateTutor(Tutor tutor);					// 튜터 정보 수정
	public int deleteTutor(String student_number);				// 튜터 정보 삭제	
	public Tutor getTutorByCode(String student_number);			// 튜터 정보를 동아리코드로 찾음

}
