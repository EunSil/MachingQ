package controller.club;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.ClubManager;
import model.Club;

public class ViewClubController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
    	Club club = null;
    	ClubManager manager = ClubManager.getInstance();
		String clubId = request.getParameter("club_code");
		String curUserId = request.getParameter("curUserId");
		System.out.println(curUserId);
		club = manager.findClub(clubId);		// 커뮤니티 정보 검색			
		
		request.setAttribute("club", club);	// 커뮤니티 정보 저장
		request.setAttribute("curUserId", curUserId);
		return "/club/view.jsp";				// 커뮤니티 보기 화면으로 이동
    }
}
