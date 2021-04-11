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

public class SearchReviewController implements Controller {
	// private static final int countPerPage = 100;	// 한 화면에 출력할 사용자 수
	private static final Logger log = (Logger) LoggerFactory.getLogger(SearchReviewController.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }

    	//검색 키워드를 받아옴. 교수명 / 교과명
    	String keyword = request.getParameter("searchKeyword");
    	if(keyword.equals("")) keyword = "";
    	log.debug("지금 SearchReviewController : execute, 키워드 : " + keyword);
    	
    	ReviewManager manager = ReviewManager.getInstance();
    	List<ClassDTO> classList = manager.SearchClassListForName(keyword);
    	
    	
    	//교수명으로 수업 검색하는 것. 두 리스트를 겹치지 않고 어떻게 합쳐야 할 지 모르겠음. contain 에러
//		List<ClassDTO> profClassList = manager.SearchClassListForProf(keyword);
//		for (ClassDTO c : profClassList) {
//			if (!classList.contains(c)) {
//	        	classList.add(c);
//	        }
//		}
//		classList.addAll(classList);
		// List<User> userList = manager.findUserList(currentPage, countPerPage);

		// userList 객체와 현재 로그인한 사용자 ID를 request에 저장하여 전달
//		if(classList == null)
//			request.setAttribute("classList", profClassList);	
//		else if(profClassList == null)
    	
    	
    	
		request.setAttribute("classList", classList);
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));	
		//System.out.println(classList);
		
//		System.out.println(profClassList);
		// 사용자 리스트 화면으로 이동(forwarding)
		return "/review/searchedList.jsp";        
    }
}

