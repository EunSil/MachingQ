package controller.teampro;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.ClassDTO;
import model.TeamProject;
import model.service.TeamProjectrManager;
import model.service.TeamProjectAnalysis;

public class SelectTeamProjectController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
    	
    	try {
    		String stuId = UserSessionUtils.getLoginUserId(request.getSession());	
    		ClassDTO c = new ClassDTO(request.getParameter("courseTitle").replaceAll(" ", ""), Integer.parseInt(request.getParameter("year")), Integer.parseInt(request.getParameter("semester")) + 1, Integer.parseInt(request.getParameter("division")) + 1, request.getParameter("profName"));
        	TeamProjectrManager manager = TeamProjectrManager.getInstance();
        	c.setClassId(manager.findClass(
    						c.getCourseTitle(),
		    				c.getYear(),
		    				c.getSemester(),
		    				c.getDivision(),
		    				c.getProfName()
		    				));
        	String[] value = request.getParameterValues("day");
        	String day = String.join("/", value);
        	String[] value2 = request.getParameterValues("place");
        	String place = String.join("/", value2);
        	boolean[] valueChecked = new boolean[] {false, false, false, false, false, false, false};
        	boolean[] value2Checked = new boolean[] {false, false};
        	
        	for(int i = 0; i < value.length; i++) {
        		switch(value[i]) {
        			case "��": 
        				valueChecked[0] = true;
        				break;
        			case "ȭ": 
        				valueChecked[1] = true;
        				break;
        			case "��": 
        				valueChecked[2] = true;
        				break;
        			case "��": 
        				valueChecked[3] = true;
        				break;
        			case "��": 
        				valueChecked[4] = true;
        				break;
        			case "��": 
        				valueChecked[5] = true;
        				break;
        			case "��": 
        				valueChecked[6] = true;
        				break;
        		}
        	}
        	for(int i = 0; i < value2.length; i++) {
        		if(value2[i].equals("�б�"))
        			value2Checked[0] = true;
        		else if(value2[i].equals("��������"))
        			value2Checked[1] = true;
        	}
        	
    		TeamProject teamPro = new TeamProject(
    				0,
    				0,
    				c,
    				day,
    				place,
    				request.getParameter("keyword"),
    				stuId);
    		
    		List<TeamProject> teamProList = manager.findTeamProjectList(c.getClassId());
    		
    		// ���� ��û�� ���� ��ȭ���� ������ �ʰ� �ϱ� ���ؼ� 
    		for(int i = 0; i < teamProList.size(); i++) {
                if(teamProList.get(i).getRequestMemberCnt() == teamProList.get(i).getTeamMemberCnt()) {
                   teamProList.remove(i);
                   i--;
                }
             }
    		
    		List<TeamProject> recommendList = TeamProjectAnalysis.recommendTeamProject(teamProList, teamPro);
    		request.setAttribute("selectionSuccessed", true);
    		request.setAttribute("teamPro", teamPro);
    		request.setAttribute("valChecked", valueChecked);
    		request.setAttribute("val2Checked", value2Checked);
    		request.setAttribute("recommendList", recommendList);	
    		request.setAttribute("curUserId", 
    				UserSessionUtils.getLoginUserId(request.getSession()));	
    		
    		return "/teamProject/select";
    	} catch(Exception e) {
    		System.out.println(e);
    		request.setAttribute("curUserId", 
    				UserSessionUtils.getLoginUserId(request.getSession()));	
    		request.setAttribute("selectionFailed", true);
    		request.setAttribute("exception", e);
    		return "/teamProject/select";
    	}
    	
    }
}
