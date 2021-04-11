package controller.study;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.TutorTuteeManager;
import model.service.UserManager;
import model.Tutor;
import model.StudentDTO;


public class UpdateTutorController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UpdateTutorController.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String curUserId = request.getParameter("curUserId");
		request.setAttribute("curUserId", curUserId);
		if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 수정 form 요청	
    		// 원래는 UpdateUserFormController가 처리하던 작업을 여기서 수행
    		String updateId = request.getParameter("stuId");

    		log.debug("UpdateForm Request : {}", updateId);
    		
    		TutorTuteeManager manager = TutorTuteeManager.getInstance();
			Tutor tutor = manager.findTutor(updateId);	// 수정하려는 사용자 정보 검색
			request.setAttribute("tutor", tutor);			

			HttpSession session = request.getSession();
			if (UserSessionUtils.isLoginUser(updateId, session) ||
				UserSessionUtils.isLoginUser("admin", session)) {
				// 현재 로그인한 사용자가 수정 대상 사용자이거나 관리자인 경우 -> 수정 가능
				
				return "/study/tutorUpdateForm.jsp";   // 검색한 사용자 정보를 update form으로 전송     
			}    
			
			// else (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("타인의 정보는 수정할 수 없습니다."));            
			return "/study/view.jsp";	// 사용자 보기 화면으로 이동 (forwarding)
	    }	
    	// POST request (회원정보가 parameter로 전송됨)
    	Tutor updateUser = new Tutor(
    			request.getParameter("stuId"),
    			request.getParameter("subject"),
    			request.getParameter("grade"),
    			request.getParameter("year"));
    	
    	log.debug("Update User : {}", updateUser);

    	TutorTuteeManager manager = TutorTuteeManager.getInstance();
		manager.updateTutor(updateUser);			
        return "redirect:/study/list";
	}

}
