package controller.club;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import controller.study.UpdateTutorController;
import controller.user.UserSessionUtils;
import model.service.ClubManager;
import model.service.TutorTuteeManager;
import model.service.UserManager;
import model.Club;
import model.Tutor;

public class ClubApplyController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UpdateTutorController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String student_number = request.getParameter("student_number");
		String club_code = request.getParameter("club_code");

	if (request.getMethod().equals("GET")) {	
		// GET request: 회원정보 수정 form 요청	
		// 원래는 UpdateUserFormController가 처리하던 작업을 여기서 수행

		log.debug("UpdateForm Request : {}", student_number);
		
		//UserManager manager = UserManager.getInstance();
		ClubManager manager = ClubManager.getInstance();
		Club club = manager.findClub(club_code);	// 수정하려는 사용자 정보 검색
		request.setAttribute("club", club);			

		HttpSession session = request.getSession();
		String preId = UserSessionUtils.getLoginUserId(session);
		if (UserSessionUtils.isLoginUser(preId, session) ||
			UserSessionUtils.isLoginUser("admin", session)) {
			// 현재 로그인한 사용자가 수정 대상 사용자이거나 관리자인 경우 -> 수정 가능
			manager.updateMatching(student_number);
			request.setAttribute("student_number", student_number);
			return "/club/applyList";   // 검색한 사용자 정보를 update form으로 전송     
		}    
		
		// else (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
		request.setAttribute("updateFailed", true);
		request.setAttribute("exception", 
				new IllegalStateException("동아리 승인은 관리자와 부장만 가능합니다."));            
		return "/club/applyList.jsp";	// 사용자 보기 화면으로 이동 (forwarding)
    }	
	// POST request (회원정보가 parameter로 전송됨)
	Club club = new Club(
			request.getParameter("club_code"),
    		request.getParameter("club_name"),
    		request.getParameter("president_code"), request.getParameter("president_phone"),
    		request.getParameter("professor_code"), request.getParameter("professor_phone"),
    		request.getParameter("club_place"), request.getParameter("club_time"),
    		request.getParameter("activity"), request.getParameter("purpose"),
    		request.getParameter("category"),  request.getParameter("description")); // 수정하려는 커뮤니티 정보 검색
	request.setAttribute("club", club);
	
	log.debug("Update Club : {}", club);
	
	ClubManager manager = ClubManager.getInstance();
	manager.updateMatching(student_number);			
    return "redirect:/club/view";
}

}
