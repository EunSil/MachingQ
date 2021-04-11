package controller.study;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Tutee;
import model.Tutor;
import model.service.TutorTuteeManager;

public class RegisterTuteeController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(RegisterTuteeController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String student_number = UserSessionUtils.getLoginUserId(request.getSession());
    	
    	String grade = request.getParameter("grade").toString();
    	String subject = request.getParameter("subject").toString();
    	
    	Tutee tutee = new Tutee(student_number, subject, grade);		
    	
    	log.debug("Create Tutee : {}", tutee);
    	
    	TutorTuteeManager manager = TutorTuteeManager.getInstance();
    	manager.createTutee(tutee);
		List<Tutor> tutorList = manager.matchingList(grade, subject);
		request.setAttribute("tutorList", tutorList);
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));
		return "/study/matching_result.jsp";
		/*try {
			manager.createTutee(tutee);
	    	
	        return "/study/matching_result.jsp";	// ���� �� Ŀ�´�Ƽ ����Ʈ ȭ������ redirect
	        
		} catch (Exception e) {		// ���� �߻� �� �Է� form���� forwarding
            request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("tutee", tutee);
			return "/study/register_tutee.jsp";
		}*/
    }

}
