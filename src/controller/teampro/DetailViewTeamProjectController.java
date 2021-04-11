package controller.teampro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.TeamProjectrManager;
import model.TeamProject;

public class DetailViewTeamProjectController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
    	TeamProject teamPro = null;
		TeamProjectrManager manager = TeamProjectrManager.getInstance();
		
		String stuId = UserSessionUtils.getLoginUserId(request.getSession());	
		String teamId = request.getParameter("teamId");
		boolean isJoined = false;
		
		if(teamId != null) {
			teamPro = manager.findTeamProject(Integer.parseInt(teamId));
			isJoined = manager.isMyTeamProject(Integer.parseInt(teamId), stuId);
		}else {
			return "redirect:/teamProject/list";
		}
		
		request.setAttribute("isJoined", !isJoined); // 해당 팀프로젝트에 가입했는지 여부 저장
		request.setAttribute("teamPro", teamPro);	
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));	
		
		return "/teamProject/detailView.jsp";		
    }
}
