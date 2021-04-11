package controller.study;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Tutor;
import model.Tutee;
import model.service.TutorTuteeManager;
import model.Tutor_Tutee_Matching;

public class ViewStudyController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
		Tutor tutor = new Tutor();
		Tutor_Tutee_Matching matchingDTO = new Tutor_Tutee_Matching();
		TutorTuteeManager manager = TutorTuteeManager.getInstance();
		
		String student_number = request.getParameter("student_number");
		System.out.println("ㅁㅈㅁㅈ: " + student_number);
		String curUserId = request.getParameter("curUserId");
		
		tutor = manager.findTutor(student_number);		// 커뮤니티 정보 검색			
		List<Tutee> tuteeList = manager.findYesList(tutor);
		List<Tutee> tuteeList2 = manager.findNoList(tutor);
		System.out.println(tuteeList);
		System.out.println(tuteeList2);
		request.setAttribute("tutor", tutor);	// 커뮤니티 정보 저장				
		request.setAttribute("tuteeList", tuteeList);
		request.setAttribute("tuteeList2", tuteeList2);
		request.setAttribute("curUserId", curUserId);
		
		return "/study/view.jsp";				// 커뮤니티 보기 화면으로 이동
    }
}
