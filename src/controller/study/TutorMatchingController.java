package controller.study;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.Tutee;
import model.Tutor;
import model.service.TutorTuteeManager;
import model.dao.Tutor_Tutee_MatchingDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorMatchingController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(RegisterTuteeController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	
    	/*String student_number = UserSessionUtils.getLoginUserId(request.getSession());*/
    	String grade = request.getParameter("grade").toString();
    	String subject = request.getParameter("subject").toString();
    	
    	TutorTuteeManager manager = TutorTuteeManager.getInstance();
		List<Tutor> tutorList = manager.matchingList(grade, subject);
		
		System.out.println(tutorList);
		
		log.debug(grade + " " + subject);
		// commList ��ü�� request�� �����Ͽ� Ŀ�´�Ƽ ����Ʈ ȭ������ �̵�(forwarding)
			request.setAttribute("tutorList", tutorList);
			return "/study/matching_result.jsp";   
    }
}
