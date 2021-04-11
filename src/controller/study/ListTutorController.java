package controller.study;

import java.util.List;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.Tutor;
import model.service.TutorTuteeManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.TutorTuteeManager;

public class ListTutorController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
    	TutorTuteeManager manager = TutorTuteeManager.getInstance();
		List<Tutor> tutorList = manager.findTutorList();
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));
		
		// commList ��ü�� request�� �����Ͽ� Ŀ�´�Ƽ ����Ʈ ȭ������ �̵�(forwarding)
		request.setAttribute("tutorList", tutorList);				
		return "/study/list.jsp";        
    }

}
