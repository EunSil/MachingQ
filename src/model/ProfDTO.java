package model;

// 교수정보를 저장하기 위한 DTO
public class ProfDTO {
	private String profId;		// 교수코드
	private String profName;		// 교수명
	
	public ProfDTO(String profId, String profName) {
		this.profId = profId;
		this.profName = profName;
	}
	
	public String getProfId() {
		return profId;
	}
	public void setProfId(String profId) {
		this.profId = profId;
	}
	public String getProfName() {
		return profName;
	}
	public void setProfName(String profName) {
		this.profName = profName;
	}	
	
}
