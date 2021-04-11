package controller;

import java.util.HashMap;
import java.util.Map;

import controller.club.*;
import controller.review.*;
import controller.study.*;
import controller.teampro.*;
import controller.user.DeleteUserController;
import controller.user.LoginController;
import controller.user.LogoutController;
import controller.user.RegisterUserController;
import controller.user.UpdateUserController;
import controller.user.ViewUserController;


public class RequestMapping {
    
    // 占쏙옙 占쏙옙청 uri占쏙옙 占쏙옙占쏙옙 controller 占쏙옙체占쏙옙 占쏙옙占쏙옙占쏙옙 HashMap 占쏙옙占쏙옙
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
//    	// 占쏙옙 uri占쏙옙 占쏙옙占쏙옙占실댐옙 controller 占쏙옙체占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/view", new ViewUserController());
        mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
        mappings.put("/user/register", new RegisterUserController());
//
//        // 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙청占쏙옙 占쏙옙占쏙옙 占쏙옙청 처占쏙옙 占쏙옙占쏙옙
////      mappings.put("/user/update/form", new UpdateUserFormController());
        mappings.put("/user/update/form", new UpdateUserController());
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/delete", new DeleteUserController());
//        
        mappings.put("/club/list", new ListClubController());
        mappings.put("/club/view", new ViewClubController());
        mappings.put("/club/register/form", new ForwardController("/club/register_club.jsp"));
        mappings.put("/club/register", new CreateClubController());
        mappings.put("/club/update/form", new UpdateClubController());
        mappings.put("/club/update", new UpdateClubController());
        mappings.put("/club/matching/form", new ForwardController("/club/matching_club.jsp"));
        mappings.put("/club/matching", new MatchingClubController());
        mappings.put("/club/matchingLegister", new JoinClubController());
        mappings.put("/club/delete", new DeleteClubController());
        mappings.put("/club/apply", new ClubApplyController());
        mappings.put("/club/applyList", new ClubApplyListController());
        mappings.put("/club/deny", new ClubDenyController());
        
        //횈짤횇횒횈짤횈쩌 째체쨌횄 request URI횄횩째징
        mappings.put("/study/tutor/register/form", new ForwardController("/study/register_tutor.jsp"));
        mappings.put("/study/tutor/register", new RegisterTutorController());
        mappings.put("/study/tutee/register/form", new ForwardController("/study/register_tutee.jsp"));
        mappings.put("/study/tutee/register", new RegisterTuteeController());
        mappings.put("/study/matching/form", new ForwardController("/study/matching_study.jsp"));
        mappings.put("/study/matching", new TutorMatchingController());
        mappings.put("/study/list", new ListTutorController());
        mappings.put("/study/join", new StudyJoinController());
        mappings.put("/study/view", new ViewStudyController());
        mappings.put("/study/tutor/update/form", new UpdateTutorController());
        mappings.put("/study/tutor/update", new UpdateTutorController()); 
        mappings.put("/study/tutor/delete", new DeleteTutorController());
        mappings.put("/study/apply", new StudyApplyController());
        mappings.put("/study/deny", new StudyDenyController());
//        logger.info("Initialized Request Mapping!");
    	
        mappings.put("/teamProject/list", new ListTeamProjectController());
	   	mappings.put("/teamProject/search/form", new ForwardController("/teamProject/search.jsp"));
	   	mappings.put("/teamProject/search/view", new SearchViewTeamProjectController());
	   	mappings.put("/teamProject/create/form", new CreateTeamProjectFormController());
	   	mappings.put("/teamProject/create", new CreateTeamProjectController());
	   	mappings.put("/teamProject/view", new ViewTeamProjectController());
	   	mappings.put("/teamProject/detail/view", new DetailViewTeamProjectController());
	   	mappings.put("/teamProject/matching/create", new CreateTeamProjectMatchingController());
	   	mappings.put("/teamProject/myPage/list", new ListMyPageTeamProjectController());
	   	mappings.put("/teamProject/comment/view", new ListCommentTeamProjectController());
	   	mappings.put("/teamProject/comment/create", new CreateCommentTeamProjectController());
	   	mappings.put("/teamProject/select", new ForwardController("/teamProject/selectForm.jsp"));
	   	mappings.put("/teamProject/recommend", new SelectTeamProjectController());
	   	mappings.put("/teamProject/delete", new DeleteTeamProjectController());
    
	   	// 占쏙옙占쏙옙 占쏙옙占쏙옙 request URI 占쌩곤옙
	   	mappings.put("/review/register/form", new ForwardController("/review/registerForm.jsp"));
	   	mappings.put("/review/matching/form", new ForwardController("/review/matchingForm.jsp"));
        mappings.put("/review/register", new RegisterReviewController());
        mappings.put("/review/list", new ListReviewController());
        mappings.put("/review/search", new SearchReviewController());
        mappings.put("/review/view", new ListViewController());
        mappings.put("/review/match", new MatchProfController());
    }

    public Controller findController(String uri) {	
    	// 占쌍억옙占쏙옙 uri占쏙옙 占쏙옙占쏙옙占실댐옙 controller 占쏙옙체占쏙옙 찾占쏙옙 占쏙옙환
        return mappings.get(uri);
    }
}
