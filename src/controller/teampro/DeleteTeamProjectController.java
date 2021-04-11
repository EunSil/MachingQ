package controller.teampro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.TeamProjectrManager;

public class DeleteTeamProjectController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	String myId = request.getParameter("myId");
		String openId = request.getParameter("openId");
		String teamId = request.getParameter("teamId");
		
    	try {
			TeamProjectrManager manager = TeamProjectrManager.getInstance();
			
			if(myId != null && teamId != null) {
				if(!myId.equals(openId))
					manager.remove(myId, Integer.parseInt(teamId));
				else {
					manager.removeAll(myId, Integer.parseInt(teamId));
				}
			}
	        
			return "redirect:/teamProject/myPage/list";	
    	}catch(Exception e) {
    		System.out.println(e);
    		request.setAttribute("deleteFailed", true);
    		request.setAttribute("exception", e);
    		
    		return "/teamProject/comment/view";
    	}	
	}
}
