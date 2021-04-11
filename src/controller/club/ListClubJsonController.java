package controller.club;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import controller.DispatcherServlet;
import model.Club;
import model.service.ClubManager;

public class ListClubJsonController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
        
		ClubManager manager = ClubManager.getInstance();
		List<Club> clubList = manager.findClubList();
		
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonString = mapper.writeValueAsString(clubList);
       	logger.debug("communityList in JSON : {}", jsonString);

    	response.setContentType("application/json;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	out.println(jsonString);       	
    	
       	return null;
    }
}
