package controller.club;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Club;
import model.StudentDTO;
import model.ClubMatching;
import model.service.ClubManager;

public class ClubApplyListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Club club = new Club();
		ClubMatching matchingDTO = new ClubMatching();
		ClubManager manager = ClubManager.getInstance();
		
		String cCode = request.getParameter("club_code");
		String curUserId = request.getParameter("curUserId");
		System.out.println("ㅁㅈㅁㅈ: " + cCode);
		
		club = manager.findClub(cCode);		// 커뮤니티 정보 검색			
		List<String> stuList = manager.findYesList(cCode);
		List<String> stuList2 = manager.findNoList(cCode);
		
		System.out.println(stuList);
		System.out.println(stuList2);
		
		request.setAttribute("club", club);	// 커뮤니티 정보 저장				
		request.setAttribute("stuList", stuList);
		request.setAttribute("stuList2", stuList2);
		request.setAttribute("curUserId", curUserId);
		
		return "/club/applyList.jsp";
	}

}
