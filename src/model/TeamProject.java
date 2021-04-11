package model;

/**
 * 팀플 관리를 위해 필요한 도메인 클래스. TEAMPROJECTTABLE 테이블과 대응됨
 */
public class TeamProject {
	private int		teamId;
	private int	teamMemberCnt;
	private ClassDTO c;
	private String tMeetSchedule;
	private String tMeetPlace;
	private int requestMemberCnt;
	private String tKeyword;
	private String stuId;
	
	public TeamProject() { }		// 기본 생성자
	
	public TeamProject(int teamId, int teamMemberCnt, ClassDTO c, String tMeetSchedule, String tMeetPlace, String tKeyword, String stuId) {
		this.teamId = teamId;
		this.teamMemberCnt = teamMemberCnt;
		this.c = c;
		this.tMeetSchedule = tMeetSchedule;
		this.tMeetPlace = tMeetPlace;
		this.tKeyword = tKeyword;
		this.stuId = stuId;
	}
	
	public TeamProject(int teamId, int teamMemberCnt, String tMeetSchedule, String tMeetPlace, String tKeyword) {
		this.teamId = teamId;
		this.teamMemberCnt = teamMemberCnt;
		this.tMeetSchedule = tMeetSchedule;
		this.tMeetPlace = tMeetPlace;
		this.tKeyword = tKeyword;
	}
	
	public TeamProject(int teamId, int teamMemberCnt) {
		this.teamId = teamId;
		this.teamMemberCnt = teamMemberCnt;
	}
	
	public void update(TeamProject updateTeamProject) {
        this.teamMemberCnt = updateTeamProject.teamMemberCnt;
        this.c = updateTeamProject.c;
    }
	
	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getTeamMemberCnt() {
		return teamMemberCnt;
	}

	public void setTeamMemberCnt(int teamMemberCnt) {
		this.teamMemberCnt = teamMemberCnt;
	}

	public ClassDTO getC() {
		return c;
	}

	public void setC(ClassDTO c) {
		this.c = c;
	}

	public String gettMeetSchedule() {
		return tMeetSchedule;
	}

	public void settMeetSchedule(String tMeetSchedule) {
		this.tMeetSchedule = tMeetSchedule;
	}

	public String gettMeetPlace() {
		return tMeetPlace;
	}

	public void settMeetPlace(String tMeetPlace) {
		this.tMeetPlace = tMeetPlace;
	}

	public int getRequestMemberCnt() {
		return requestMemberCnt;
	}

	public void setRequestMemberCnt(int requestMemberCnt) {
		this.requestMemberCnt = requestMemberCnt;
	}

	public String gettKeyword() {
		return tKeyword;
	}

	public void settKeyword(String tKeyword) {
		this.tKeyword = tKeyword;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	@Override
	public String toString() {
		return "TeamProject [teamId=" + teamId + ", teamMemberCnt=" + teamMemberCnt + ", class=" + c + "]";
	}	
}
