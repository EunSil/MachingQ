package controller.club;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.ClubManager;

public class JoinClubController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String club_code = request.getParameter("club_code");
		String usrId = UserSessionUtils.getLoginUserId(request.getSession());
		String curUserId = request.getParameter("curUserId");
		
		request.setAttribute("curUserId", curUserId);
		
		ClubManager manager = ClubManager.getInstance();
		manager.createMatching(club_code, usrId);
		return "/club/list.jsp";
	}

}
