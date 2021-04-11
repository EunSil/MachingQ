package model.service;

import model.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class TeamProjectAnalysis {
	//비즈니스 메소드. 추천 알고리즘. 
	//static 하래서 했다
	public static List<TeamProject> recommendTeamProject(List<TeamProject> teamProList, TeamProject teamPro) throws Exception{
		List<TeamProject> reco = new ArrayList<TeamProject>();
		List<Integer> scores = new ArrayList<Integer>();
		for(int i = 0; i < teamProList.size(); i++) {
			int score = 0;
			
			if(teamPro.gettKeyword().equals(teamProList.get(i).gettKeyword()))
				score += 7;
	
			if(teamPro.gettMeetPlace().equals(teamProList.get(i).gettMeetPlace())) {
				score += 7;
			}else {
				String[] mp1 = teamPro.gettMeetPlace().split("/");
				String[] mp2 = teamProList.get(i).gettMeetPlace().split("/");
				if(mp1.length == 1 && mp2.length == 1)
					score += 1;
				else
					score += 4;
			}
			
			if(teamPro.gettMeetSchedule().equals(teamProList.get(i).gettMeetSchedule())) {
				score += 7;
			}else {
				String[] ms1 = teamPro.gettMeetSchedule().split("/");
				String ms2 = teamProList.get(i).gettMeetSchedule();
				int ms1Cnt = ms1.length == 1? 0: ms1.length;
				int s = 7 - ms1Cnt;
				int isSame = 0;
				for(int j = 0; j < ms1.length; j++) {
					if(ms2.indexOf(ms1[j]) != -1) {
						isSame++;
					}
				}
				s = s - (ms2.split("/").length - isSame);
			}
			scores.add(score);
		}
		if(scores.size() != 0) {
			int max = Collections.max(scores);
			for(int i = 0; i < teamProList.size(); i++) {
				if(scores.get(i) == max) {
					reco.add(teamProList.get(i));
				}
			}
		}
		
		return reco;
	}
	

}
