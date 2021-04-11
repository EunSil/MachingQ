package controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.ProfDTO;
import model.Review;
import model.StudentDTO;
import model.service.ReviewManager;
import model.service.UserManager;

public class MatchProfController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(MatchProfController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 수정 form 요청	
    		// 원래는 UpdateUserFormController가 처리하던 작업을 여기서 수행
    		String updateId = UserSessionUtils.getLoginUserId(request.getSession());

    		log.debug("UpdateForm Request : {}", updateId);
    		
    		UserManager manager = UserManager.getInstance();
			StudentDTO user = manager.findUser(updateId);	// 수정하려는 사용자 정보 검색
			request.setAttribute("user", user);
			request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));

			HttpSession session = request.getSession();
			if (UserSessionUtils.isLoginUser(updateId, session) ||
				UserSessionUtils.isLoginUser("admin", session)) {
				// 현재 로그인한 사용자가 수정 대상 사용자이거나 관리자인 경우 -> 수정 가능
				
				return "/review/matchingForm.jsp";   // 검색한 사용자 정보를 update form으로 전송     
			}    
			
			// else (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("타인의 정보는 수정할 수 없습니다."));            
			return "/review/list.jsp";	// 사용자 보기 화면으로 이동 (forwarding)
	    }	
    	// POST request (회원정보가 parameter로 전송됨)
    	StudentDTO updateUser = new StudentDTO(
    			request.getParameter("stuId"),
    			request.getParameter("stuName"),
    			request.getParameter("password"),
    			Integer.parseInt(request.getParameter("grade")),
    			request.getParameter("dProject"),
    			request.getParameter("dTeamProject"),
    			request.getParameter("dCreditRate"),
    			request.getParameter("dAttendance"),
    			request.getParameter("dNumberOfExam"));
    	
    	log.debug("Update User : {}", updateUser);
    	request.setAttribute("keyId", request.getParameter("dKeyword"));
    	request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));
		UserManager manager = UserManager.getInstance();
		manager.update(updateUser);			
		ReviewManager revMan = ReviewManager.getInstance();
		
		
		List<Review> recommendedReview = revMan.getReviewMatching(updateUser);
		ProfDTO recommendedProf = revMan.getProfMatching(Integer.valueOf(request.getParameter("dKeyword")));
		//System.out.println(recommendedReview.toString());
		request.setAttribute("reviewList", recommendedReview);
		request.setAttribute("prof", recommendedProf);
		
		return "/review/matchedlist.jsp";
    }
}
