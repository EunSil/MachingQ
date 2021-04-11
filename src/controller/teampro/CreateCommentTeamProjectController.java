package controller.teampro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Comment;
import model.StudentDTO;
import model.service.TeamProjectrManager;

public class CreateCommentTeamProjectController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
    	try {
    		String stuId = UserSessionUtils.getLoginUserId(request.getSession());
    		
    		String teamId = request.getParameter("teamId");
    		if(teamId != null) {
	    		StudentDTO s = new StudentDTO();
	    		s.setStuId(stuId);
	    		Comment comm = new Comment(Integer.parseInt(teamId), s, request.getParameter("comment_content"), null);
	        	
	    		// 댓글 추가
	    		TeamProjectrManager manager = TeamProjectrManager.getInstance();
	    		manager.createComment(comm);
    		}else {
    			return "redirect:/teamProject/list";
    		}
    		
    		HttpSession session = request.getSession();
            session.setAttribute(TeamPorjectSessionUtils.TEAM_SESSION_KEY, teamId);
    		
    		return "redirect:/teamProject/comment/view";
    	} catch(Exception e) {
    		System.out.println(e);
    		return "/teamProject/comment/view";
    	}
    }
}
