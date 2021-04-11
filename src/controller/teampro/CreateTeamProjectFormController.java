package controller.teampro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.ClassRemove;

public class CreateTeamProjectFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ClassRemove cr = new ClassRemove();
		cr.setCourseId(Integer.parseInt(request.getParameter("courseId")));
		cr.setCourseTitle(request.getParameter("courseTitle"));
		cr.setProfId(Integer.parseInt(request.getParameter("profId")));
		cr.setProfName(request.getParameter("profName"));
		
		request.setAttribute("classRemove", cr);
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));	
		
		return "/teamProject/createForm.jsp";			
	}
}
