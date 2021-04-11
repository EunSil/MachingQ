package controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.ClassDTO;
import model.Review;
import model.service.ReviewManager;
import model.service.TeamProjectrManager;

public class ListViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
    	System.out.println("지금 ListViewController : execute");
    	ReviewManager manager = ReviewManager.getInstance();
		TeamProjectrManager tManager = new TeamProjectrManager();  //@이거 때문에 teamProjectmanager생성자 public으로 바꿨음
		
		//선택한 과목의 정보를 나타내기 위해 classDto 가져옴.
    	int classId = Integer.valueOf(request.getParameter("classId"));
    	ClassDTO choosedClass = (ClassDTO)tManager.findClass(classId);
    	
    	//선택한 과목이름에 대한 강의평을 나타내기 위해 교과명으로 강의평 검색
    	String courseTitle = request.getParameter("courseTitle");
    	List<Review> reviewList = manager.SearchReviewListForName(courseTitle);
    	
    	
    	request.setAttribute("curUserId", 
    			UserSessionUtils.getLoginUserId(request.getSession()));	
		request.setAttribute("choosedClass", choosedClass);	
		request.setAttribute("reviewList", reviewList);
		//System.out.println(choosedClass);
		// 사용자 리스트 화면으로 이동(forwarding)
		return "/review/view.jsp";        
	}

}
