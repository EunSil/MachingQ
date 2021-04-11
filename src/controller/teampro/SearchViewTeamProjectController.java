package controller.teampro;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.TeamProjectrManager;
import model.ClassRemove;

public class SearchViewTeamProjectController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
    	List<ClassRemove> classList = null;
    	TeamProjectrManager manager = TeamProjectrManager.getInstance();
		String courseTitle = request.getParameter("courseTitle");
		
		if(courseTitle != null)
			classList = manager.SearchClassListForTitle(courseTitle.replaceAll(" ", ""));
		else 
			return "redirect:/teamProject/list";
		
		request.setAttribute("classList", classList);
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));	
		
		return "/teamProject/searchList.jsp";				
    }
}
