package controller.teampro;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.TeamProjectrManager;
import model.Comment;
import model.TeamProject;
import model.TeamProjectMatching;

public class ListCommentTeamProjectController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
    	String stuId = UserSessionUtils.getLoginUserId(request.getSession());
    	String teamId;
    	if(request.getParameter("teamId") == null) {
    		teamId = TeamPorjectSessionUtils.getTeamId(request.getSession());
    	}else {
    		teamId = request.getParameter("teamId");
    	}
    	
    	if(teamId != null) {
	    	TeamProjectrManager manager = TeamProjectrManager.getInstance();
			List<Comment> teamCommList = manager.findMyTeamProjectCommentList(Integer.parseInt(teamId), stuId);
			
			TeamProject teamPro = manager.findTeamProject(Integer.parseInt(teamId));
			List<TeamProjectMatching> teamProMatList = manager.findWithTeamProject(Integer.parseInt(teamId));
	    	
			request.setAttribute("stuId", stuId);
			request.setAttribute("teamProMatList", teamProMatList);
			request.setAttribute("teamPro", teamPro);
			request.setAttribute("teamCommList", teamCommList);		
			request.setAttribute("teamId", teamId);	
    	}else {
    		return "redirect:/teamProject/list";
    	}
    	
    	request.setAttribute("curUserId", 
    			UserSessionUtils.getLoginUserId(request.getSession()));	
    	
		return "/teamProject/community.jsp";        
    }
}
