package controller.club;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.Club;
import model.service.ClubManager;

public class ListClubController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
    	ClubManager manager = ClubManager.getInstance();
		List<Club> clubList = manager.findClubList();
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));	
		
		// commList 객체를 request에 저장하여 커뮤니티 리스트 화면으로 이동(forwarding)
		request.setAttribute("clubList", clubList);				
		return "/club/list.jsp";        
    }
}
