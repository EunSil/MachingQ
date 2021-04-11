package impl;

import java.util.*;
import model.*;

public interface ClubDAOImpl {
	
	public List<Club> getClubList();				// 전체 동아리 정보를 획득
	public int insertClub(Club club);			// 동아리 정보 추가
	public int updateClub(Club club);			// 동아리 정보 수정
	public int deleteClub(String club_code);				// 동아리 정보 삭제
	public Club getClubByName(String club_name);		// 동아리 정보를 동아리명으로 찾음
	public Club getClubByCode(String club_code);		// 동아리 정보를 동아리코드로 찾음
	
}
