package controller.teampro;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.TeamProjectrManager;
import model.ClassDTO;
import model.ClassRemove;
import model.TeamProject;

public class ViewTeamProjectController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
    	String courseId = request.getParameter("courseId");
    	String profId = request.getParameter("profId");
    	
    	if(courseId != null && profId != null) {
	    	ClassRemove cr = new ClassRemove();
			cr.setCourseId(Integer.parseInt(courseId));
			cr.setCourseTitle(request.getParameter("courseTitle"));
			cr.setProfId(Integer.parseInt(profId));
			cr.setProfName(request.getParameter("profName"));
			
			TeamProjectrManager manager = TeamProjectrManager.getInstance();
			List<TeamProject> teamProList = manager.findTeamProjectList(Integer.parseInt(courseId), Integer.parseInt(profId));
			List<ClassDTO> openedSemester = manager.findSemestertList(Integer.parseInt(courseId), Integer.parseInt(profId));
			for(int i = 1; i < openedSemester.size(); i++) {
				ClassDTO c = openedSemester.get(i - 1);
				if(c.getYear() == openedSemester.get(i).getYear() && c.getSemester() == openedSemester.get(i).getSemester()) {
					openedSemester.remove(i);
					i--;
				}
			}
			
			request.setAttribute("openedSemester", openedSemester);	
			request.setAttribute("classRemove", cr);
			request.setAttribute("teamProList", teamProList);				
    	}else {
    		return "redirect:/teamProject/list";
    	}
    	
    	request.setAttribute("curUserId", 
    			UserSessionUtils.getLoginUserId(request.getSession()));	
		return "/teamProject/view.jsp";	
    }
}
