package controller.club;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import controller.DispatcherServlet;
import model.service.ClubManager;
import model.Club;

public class ViewClubJsonController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
		Club club = null;
		ClubManager manager = ClubManager.getInstance();
		String clubId = request.getParameter("commId");
		club = manager.findClub(clubId);		// 커뮤니티 상세 정보 검색 (회원들의 정보도 함께 검색됨)		
				
		// Jackson을 이용하여 결과 객체들을 JSON 텍스트로 변환
		ObjectMapper mapper = new ObjectMapper();	
    	String jsonString = mapper.writeValueAsString(club);
       	logger.debug("clubList in JSON : {}", jsonString);

       	// JSON 텍스트를 포함하는 response message 생성
    	response.setContentType("application/json;charset=utf-8");	
    	PrintWriter out = response.getWriter();
    	out.println(jsonString);       	
    	
		return null;	// uri 대신 null을 리턴 -> DispatcherServlet에서 요청 처리 종료 
    }
}
