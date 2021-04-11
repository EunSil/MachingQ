package controller.teampro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.StudentDTO;
import model.TeamProjectMatching;
import model.service.TeamProjectrManager;

public class CreateTeamProjectMatchingController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CreateTeamProjectMatchingController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
    	try {
    		TeamProjectrManager manager = TeamProjectrManager.getInstance();
    		
    		String stuId = UserSessionUtils.getLoginUserId(request.getSession());	
    		String teamId = request.getParameter("teamId");        	   		
        	StudentDTO s = new StudentDTO();
        	s.setStuId(stuId);
        	
        	TeamProjectMatching tm = null;
        	if(teamId != null) {
        		tm = new TeamProjectMatching(Integer.parseInt(teamId), s, null);
        		//팀프로젝트 신청
        		manager.createMatching(tm);
        	}
        	
        	log.debug("Create TeamProjectMatching : {}", tm);
    		return "redirect:/teamProject/list";
    	} catch(Exception e) {
    		System.out.println(e);
    		return "redirect:/teamProject/list";
    	}   	
    }
}
