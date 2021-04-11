package controller.teampro;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.TeamProject;
import model.service.TeamProjectrManager;

public class ListMyPageTeamProjectController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
    	String stuId = UserSessionUtils.getLoginUserId(request.getSession());	
    	TeamProjectrManager manager = TeamProjectrManager.getInstance();
		List<TeamProject> teamProList = manager.findMyTeamProjectList(stuId);
		
		// teamProList 객체를 request에 저장하여 마이페이지 리스트 화면으로 이동(forwarding)
		request.setAttribute("teamProList", teamProList);
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));	
		
		return "/teamProject/myPageList.jsp";        
    }
}
