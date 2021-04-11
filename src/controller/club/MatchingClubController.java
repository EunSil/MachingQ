package controller.club;

import model.dao.ClubDAO;
import model.Club;
import model.service.ClubManager;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import controller.study.RegisterTuteeController;
import controller.user.UserSessionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatchingClubController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(RegisterTuteeController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

    	String activity = request.getParameter("activity").toString();
    	String purpose = request.getParameter("purpose").toString();
    	String[] category = request.getParameterValues("category");
    	request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));	
    	
    	ClubManager manager = ClubManager.getInstance();
		List<Club> clubList = manager.getMatchingList(activity, purpose, category);
		
		log.debug(activity + " " + purpose);
		// commList ��ü�� request�� �����Ͽ� Ŀ�´�Ƽ ����Ʈ ȭ������ �̵�(forwarding)
		request.setAttribute("clubList", clubList);
		return "/club/matching_result.jsp";   
	}

}
