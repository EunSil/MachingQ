package controller.teampro;

import javax.servlet.http.HttpSession;

public class TeamPorjectSessionUtils {
    public static final String TEAM_SESSION_KEY = "teamId";
    
    public static String getTeamId(HttpSession session) {
    	String teamId = (String)session.getAttribute(TEAM_SESSION_KEY);
        return teamId;
    }
   
}
