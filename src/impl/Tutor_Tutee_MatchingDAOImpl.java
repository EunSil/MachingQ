package impl;

import model.*;
import java.util.*;

public interface Tutor_Tutee_MatchingDAOImpl {
	public List<Tutor> getTutorMatchingList (String subject, String time);						// ÀüÃ¼ Æ©Æ¼ Á¤º¸¸¦ È¹µæ
	public int insertMatching (String tutor_number, String tutee_number);					// tutee, tutor project_code ºÎ¿©
}
