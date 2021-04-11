package controller.review;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.*;
import model.service.ReviewManager;
import model.service.UserManager;
import model.service.UserNotFoundException;

public class ListReviewController implements Controller {
	// private static final int countPerPage = 100;	// 한 화면에 출력할 사용자 수
	private static final Logger log = (Logger) LoggerFactory.getLogger(ListReviewController.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
    	log.debug("지금 ListReviewController : execute");
    	UserManager umanager = UserManager.getInstance();
    	ReviewManager manager = ReviewManager.getInstance();
		List<Review> reviewList = manager.findReviewList();
		UserManager usermanager = UserManager.getInstance();
		List<StudentDTO> userList = usermanager.findUserList();
		
		String stuId = request.getParameter("stuId");
		
    	StudentDTO user = null;
		try {
			user = umanager.findUser(stuId);	// 사용자 정보 검색
		} catch (UserNotFoundException e) {				
	        //return "redirect:/review/list";
		}	
		request.setAttribute("user", user);	
		// List<User> userList = manager.findUserList(currentPage, countPerPage);

		//최근 강의평 모두 전달.
		request.setAttribute("reviewList", reviewList);				
		//System.out.println(reviewList);
		
		//로그인
		request.setAttribute("userList", userList);	
		request.setAttribute("curUserId", 
		UserSessionUtils.getLoginUserId(request.getSession()));	
		// 사용자 리스트 화면으로 이동(forwarding)
		return "/review/list.jsp";        
    }
}
