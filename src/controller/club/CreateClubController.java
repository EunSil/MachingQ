package controller.club;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.*;
import model.service.ClubManager;

public class CreateClubController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CreateClubController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Club club = new Club(
    		request.getParameter("club_code"), request.getParameter("club_name"),
			request.getParameter("president_code"), request.getParameter("president_phone"),
			request.getParameter("professor_code"), request.getParameter("professor_phone"),
			request.getParameter("club_place"), request.getParameter("club_time"),
			request.getParameter("activity"), request.getParameter("purpose"),
			request.getParameter("category"), request.getParameter("description"));		
    	
    	log.debug("Create Club : {}", club);
    		
		try {
			ClubManager manager = ClubManager.getInstance();
			manager.createClub(club);
			
	    	
	        return "redirect:/club/list";	// 성공 시 커뮤니티 리스트 화면으로 redirect
	        
		} catch (Exception e) {		// 예외 발생 시 입력 form으로 forwarding
            request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("club", club);
			return "/club/register_club.jsp";
		}
    }
}
