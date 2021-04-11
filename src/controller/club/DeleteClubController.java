package controller.club;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.study.DeleteTutorController;
import controller.user.DeleteUserController;
import controller.user.UserSessionUtils;
import model.StudentDTO;
import model.Tutor;
import model.Club;
import model.service.ClubManager;
import model.service.TutorTuteeManager;
import model.service.UserManager;

public class DeleteClubController implements Controller {
private static final Logger log = LoggerFactory.getLogger(DeleteTutorController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String deleteId = request.getParameter("club_code");
		String preId = request.getParameter("president_code");
    	log.debug("Delete User : {}", deleteId);

    	ClubManager manager = ClubManager.getInstance();		
		HttpSession session = request.getSession();	
	
		if ((UserSessionUtils.isLoginUser("admin", session)) 	// 로그인한 사용자가 관리자	
			   || 												// 또는 
			(!UserSessionUtils.isLoginUser("admin", session) &&  // 로그인한 사용자가 관리자가 아니고 
			  (UserSessionUtils.isLoginUser(preId, session)))){ 
				manager.remove(deleteId);	
				return "redirect:/club/list";
			  }
		
		request.setAttribute("updateFailed", true);
		request.setAttribute("exception", 
				new IllegalStateException("동아리는 부장과 관리자 외에 수정할 수 없습니다."));            
		return "/club/view";

	}
}
