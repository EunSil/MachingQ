package controller.study;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Tutor;
import model.service.TutorTuteeManager;

public class RegisterTutorController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(RegisterTutorController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String student_number = UserSessionUtils.getLoginUserId(request.getSession());
    	
    	Tutor tutor = new Tutor(student_number, request.getParameter("subject"),
    			request.getParameter("grade"), request.getParameter("year"));		
    	
    	log.debug("Create Tutor : {}", tutor);
    		
		try {
			TutorTuteeManager manager = TutorTuteeManager.getInstance();
			manager.createTutor(tutor);
	    	
	        return "redirect:/study/list";	// ���� �� Ŀ�´�Ƽ ����Ʈ ȭ������ redirect
	        
		} catch (Exception e) {		// ���� �߻� �� �Է� form���� forwarding
            request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("tutor", tutor);
			return "/study/register_tutor.jsp";
		}
    }
}
