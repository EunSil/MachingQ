package controller.study;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.DeleteUserController;
import controller.user.UserSessionUtils;
import model.StudentDTO;
import model.Tutor;
import model.service.TutorTuteeManager;
import model.service.UserManager;

public class DeleteTutorController implements Controller  {

	private static final Logger log = LoggerFactory.getLogger(DeleteTutorController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String deleteId = request.getParameter("student_number");
		System.out.println("삭제할 아이디: " + deleteId);
    	log.debug("Delete User : {}", deleteId);

    	TutorTuteeManager manager = TutorTuteeManager.getInstance();		
		HttpSession session = request.getSession();	
	
		if ((UserSessionUtils.isLoginUser("admin", session) && 	// 로그인한 사용자가 관리자이고 	
			 !deleteId.equals("admin"))							// 삭제 대상이 일반 사용자인 경우, 
			   || 												// 또는 
			(!UserSessionUtils.isLoginUser("admin", session) &&  // 로그인한 사용자가 관리자가 아니고 
			  UserSessionUtils.isLoginUser(deleteId, session))) { // 로그인한 사용자가 삭제 대상인 경우 (자기 자신을 삭제)
				
			manager.removeTutor(deleteId);	// 로그인한 사용자가 관리자 	
			return "redirect:/study/list";		// 사용자 리스트로 이동								// 로그인한 사용자는 이미 삭제됨
			
		}
		
		request.setAttribute("updateFailed", true);
		request.setAttribute("exception", 
				new IllegalStateException("튜터 정보는 본인과 관리자 외에 삭제할 수 없습니다."));            
		return "/study/view";
	}

}
