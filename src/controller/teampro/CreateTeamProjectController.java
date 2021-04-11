package controller.teampro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.ClassDTO;
import model.TeamProject;
import model.service.TeamProjectrManager;

public class CreateTeamProjectController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CreateTeamProjectController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
    	try {
    		String stuId = UserSessionUtils.getLoginUserId(request.getSession());	
    		TeamProjectrManager manager = TeamProjectrManager.getInstance();
    		
    		ClassDTO c = new ClassDTO();
        	c.setClassId(manager.findClass(
    						Integer.parseInt(request.getParameter("courseId")),
		    				Integer.parseInt(request.getParameter("year")),
		    				Integer.parseInt(request.getParameter("semester")) + 1,
		    				Integer.parseInt(request.getParameter("division")) + 1,
		    				Integer.parseInt(request.getParameter("profId"))
		    				));
    		
        	String[] value = request.getParameterValues("day");
        	String day = String.join("/", value);
        	String[] value2 = request.getParameterValues("place");
        	String place = String.join("/", value2);
    		TeamProject teamPro = new TeamProject(
    				0,
    				Integer.parseInt(request.getParameter("teamMemberCnt")),
    				c,
    				day,
    				place,
    				request.getParameter("keyword"),
    				stuId
    				);
    		
    		// 팀프로젝트 생성
    		manager.create(teamPro, stuId);
    		log.debug("Create TeamProject : {}", teamPro);
    		
    		return "redirect:/teamProject/list";
    	} catch(Exception e) {
    		request.setAttribute("creationFailed", true);
    		request.setAttribute("exception", e);
    		return "/teamProject/create/form";
    	}	
    }
}
