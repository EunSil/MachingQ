package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Review;
import model.service.ReviewManager;

public class RegisterReviewController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterReviewController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
      	try {
      		System.out.println(request.getParameter("classId"));
      		int classId = Integer.valueOf(request.getParameter("classId"));
      	}catch(Exception e) {
      		System.out.print("classId를 찾을 수 없습니다");
      		return "redirect:/review/list";	
      	}
    	Review review = new Review(
    			//지금 폼에서 얻어오는 것들이랑 dto에 넣어야 할 파라미터가 안맞음. 외래키 이용 등등 문제. + 폼 내용 인식이 null로 돼. trim()은 공백 때문에 넣어본 거.
    		0, //revid
    		Integer.valueOf(request.getParameter("semester")), //openSemester
			request.getParameter("lecturetotal"),
			request.getParameter("dproject"),
			request.getParameter("dteamproject"),
			request.getParameter("dcreditRate"),
			request.getParameter("dAttendance"),
			request.getParameter("dnumberOfExam"),
			request.getParameter("totalReview"),
			Integer.valueOf(request.getParameter("classId"))
			//9595
			);
		
        log.debug("Create Review : {}", review);

		try {
			ReviewManager manager = ReviewManager.getInstance();
			manager.create(review);
			manager.registerKeywordMatching(review);
			
	        return "redirect:/review/list";		// 성공 시 사용자 리스트 화면으로 redirect
	        
		} catch (Exception e) {		
			e.printStackTrace();
//            request.setAttribute("registerFailed", true);
//			request.setAttribute("exception", e);
//			request.setAttribute("review", review);
			return "/review/registerForm.jsp";
		}
      	//return "redirect:/review/list";	
    }
}
