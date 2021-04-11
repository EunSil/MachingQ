package controller.study;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.TutorTuteeManager;
import model.Tutee;
import model.Tutor;

public class StudyJoinController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String tutor_number = request.getParameter("student_number");
		String usrId = UserSessionUtils.getLoginUserId(request.getSession());
		
		TutorTuteeManager manager = TutorTuteeManager.getInstance();
		Tutee tutee = manager.findTutee(usrId);
		Tutor tutor = manager.findTutor(tutor_number);

		manager.createMatching(tutor_number, usrId);
		
		request.setAttribute("joinFailed", true);
		String msg = (tutee.getProject_code() != null) 
				   ? "이미 가입한 스터디가 있습니다."	
				   : "신청완료";	
		request.setAttribute("exception", new IllegalStateException(msg));
		return "/study/matching_result.jsp";
	}

}
