package com.iot.g89;

import java.io.IOException;

import fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneTransform {
	private static Stage primaryStage;

	private static Scene sceneClientStartPage;
	private static Scene sceneInstructorStartPage;
	private static Scene sceneAdminStartPage;
	private static Scene sceneRegisterAndLoginPage;
	private static Scene sceneUserInfoPage;
	private static Scene sceneUserSecurityEditPage;
	private static Scene sceneSorePage;
	private static Scene sceneCoursesListPage;
	private static Scene sceneInstructorListPage;
	private static Scene sceneCourseBookingPage;
	private static Scene sceneInstructorUploadCourse;
	private static Scene sceneInstructorSelfCourseListPage;
	private static Scene sceneMyTeachingPage;
	private static Scene sceneRechargePage;
	private static Scene sceneVideoInfoPage;
	private static Scene sceneVideoInfoEditPage;
	private static Scene scenePublishLivePage;
	private static Scene sceneNewCoursePage;
	private static Scene sceneLiveListPage;
	private static Scene sceneCommunityPage;

	private static ClientStartPageController clientStartPageController;
	private static InstructorStartPageController instructorStartPageController;
	private static AdminStartPageController adminStartPageController;
	private static RegisterAndLoginPageController registerAndLoginPageController;
	private static UserInfoPageController userInfoPageController;
	private static UserSecurityEditPageController userSecurityEditPageController;
	private static StorePageController storePageController;
	private static CoursesListPageController coursesListPageController;
	private static InstructorListPageController instructorListPageController;
	private static CourseBookingPageController courseBookingPageController;
	private static InstructorUploadCourseController instructorUploadCourseController;
	private static InstructorSelfCourseListPageController instructorSelfCourseListPageController;
	private static MyTeachingPageController myTeachingPageController;
	private static RechargePageController rechargePageController;
	private static VideoInfoPageController videoInfoPageController;
	private static VideoInfoEditPageController videoInfoEditPageController;
	private static PublishLivePageController publishLivePageController;
	private static NewCoursePageController newCoursePageController;
	private static LiveListPageController liveListPageController;
	private static CommunityPageController communityPageController;

	private static GUIDriver driver = new GUIDriver();
	
	public SceneTransform(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
//			rootClientStartPage = FXMLLoader.load(getClass().getResource("/fxml/ClientStartPageScene.fxml"));
//			rootRegisterAndLoginPage = FXMLLoader.load(getClass().getResource("/fxml/RegisterAndLoginPageScene.fxml"));
//			rootUserInfoPage = FXMLLoader.load(getClass().getResource("/fxml/UserInfoPageScene.fxml"));
			
			FXMLLoader loaderClientStartPage = new FXMLLoader(getClass().getResource("/fxml/ClientStartPageScene.fxml"));
			FXMLLoader loaderInstructorStartPage = new FXMLLoader(getClass().getResource("/fxml/InstructorStartPageScene.fxml"));
			FXMLLoader loaderAdminStartPage = new FXMLLoader(getClass().getResource("/fxml/AdminStartPageScene.fxml"));
			FXMLLoader loaderRegisterAndLoginPage = new FXMLLoader(getClass().getResource("/fxml/RegisterAndLoginPageScene.fxml"));
			FXMLLoader loaderUserInfoPage = new FXMLLoader(getClass().getResource("/fxml/UserInfoPageScene.fxml"));
			FXMLLoader loaderUserSecurityEditPage = new FXMLLoader(getClass().getResource("/fxml/UserSecurityEditPageScene.fxml"));
			FXMLLoader loaderStorePage = new FXMLLoader(getClass().getResource("/fxml/StorePageScene.fxml"));
			FXMLLoader loaderCoursesListPage = new FXMLLoader(getClass().getResource("/fxml/CoursesListPageScene.fxml"));
			FXMLLoader loaderInstructorListPage = new FXMLLoader(getClass().getResource("/fxml/InstructorListPageScene.fxml"));
			FXMLLoader loaderCourseBookingPage = new FXMLLoader(getClass().getResource("/fxml/CourseBookingPageScene.fxml"));
			FXMLLoader loaderInstructorUploadCourse = new FXMLLoader(getClass().getResource("/fxml/InstructorUploadCourseScene.fxml"));
			FXMLLoader loaderInstructorSelfCourseListPage = new FXMLLoader(getClass().getResource("/fxml/InstructorSelfCourseListPageScene.fxml"));
			FXMLLoader loaderMyTeachingPage = new FXMLLoader(getClass().getResource("/fxml/MyTeachingPageScene.fxml"));
			FXMLLoader loaderRechargePage = new FXMLLoader(getClass().getResource("/fxml/RechargePageScene.fxml"));
			FXMLLoader loaderVideoInfoPage = new FXMLLoader(getClass().getResource("/fxml/VideoInfoPageScene.fxml"));
			FXMLLoader loaderVideoInfoEditPage = new FXMLLoader(getClass().getResource("/fxml/VideoInfoEditPageScene.fxml"));
			FXMLLoader loaderPublishLivePage = new FXMLLoader(getClass().getResource("/fxml/PublishLivePageScene.fxml"));
			FXMLLoader loaderNewCoursePage = new FXMLLoader(getClass().getResource("/fxml/NewCoursePageScene.fxml"));
			FXMLLoader loaderLiveListPage = new FXMLLoader(getClass().getResource("/fxml/LiveListPageScene.fxml"));
			FXMLLoader loaderCommunityPage = new FXMLLoader(getClass().getResource("/fxml/CommunityPageScene.fxml"));

			sceneClientStartPage = new Scene(loaderClientStartPage.load());
			sceneInstructorStartPage = new Scene(loaderInstructorStartPage.load());
			sceneAdminStartPage = new Scene(loaderAdminStartPage.load());
			sceneRegisterAndLoginPage = new Scene(loaderRegisterAndLoginPage.load());
			sceneUserInfoPage = new Scene(loaderUserInfoPage.load());
			sceneUserSecurityEditPage = new Scene(loaderUserSecurityEditPage.load());
			sceneSorePage = new Scene(loaderStorePage.load());
			sceneCoursesListPage = new Scene(loaderCoursesListPage.load());
			sceneInstructorListPage = new Scene(loaderInstructorListPage.load());
			sceneCourseBookingPage = new Scene(loaderCourseBookingPage.load());
			sceneInstructorUploadCourse = new Scene(loaderInstructorUploadCourse.load());
			sceneInstructorSelfCourseListPage = new Scene(loaderInstructorSelfCourseListPage.load());
			sceneMyTeachingPage = new Scene(loaderMyTeachingPage.load());
			sceneRechargePage = new Scene(loaderRechargePage.load());
			sceneVideoInfoPage = new Scene(loaderVideoInfoPage.load());
			sceneVideoInfoEditPage = new Scene(loaderVideoInfoEditPage.load());
			scenePublishLivePage = new Scene(loaderPublishLivePage.load());
			sceneNewCoursePage = new Scene(loaderNewCoursePage.load());
			sceneLiveListPage = new Scene(loaderLiveListPage.load());
			sceneCommunityPage = new Scene(loaderCommunityPage.load());

			clientStartPageController = loaderClientStartPage.getController();
			instructorStartPageController = loaderInstructorStartPage.getController();
			adminStartPageController = loaderAdminStartPage.getController();
			registerAndLoginPageController = loaderRegisterAndLoginPage.getController();
			userInfoPageController = loaderUserInfoPage.getController();
			userSecurityEditPageController = loaderUserSecurityEditPage.getController();
			storePageController = loaderStorePage.getController();
			coursesListPageController = loaderCoursesListPage.getController();
			instructorListPageController = loaderInstructorListPage.getController();
			courseBookingPageController = loaderCourseBookingPage.getController();
			instructorUploadCourseController = loaderInstructorUploadCourse.getController();
			instructorSelfCourseListPageController = loaderInstructorSelfCourseListPage.getController();
			myTeachingPageController = loaderMyTeachingPage.getController();
			rechargePageController = loaderRechargePage.getController();
			videoInfoPageController = loaderVideoInfoPage.getController();
			videoInfoEditPageController = loaderVideoInfoEditPage.getController();
			publishLivePageController = loaderPublishLivePage.getController();
			newCoursePageController = loaderNewCoursePage.getController();
			liveListPageController = loaderLiveListPage.getController();
			communityPageController = loaderCommunityPage.getController();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void ToRegisterAndLoginPage() {
		registerAndLoginPageController.initData(sceneRegisterAndLoginPage, driver);
		ToScene(sceneRegisterAndLoginPage);
	}
	
	public static void ToClientStartPage(String userId, Scene lastScene) {
		clientStartPageController.initData(userId, sceneClientStartPage, lastScene, driver);
		ToScene(sceneClientStartPage);
	}

	public static void ToInstructorStartPage(String userId, Scene lastScene) {
		instructorStartPageController.initData(userId, sceneInstructorStartPage, lastScene, driver);
		ToScene(sceneInstructorStartPage);
	}

	public static void ToAdminStartPage(String userId, Scene lastScene) {
		adminStartPageController.initData(userId, sceneAdminStartPage, lastScene, driver);
		ToScene(sceneAdminStartPage);
	}
	
	public static void ToUserInfoPage(String userId, Scene lastScene) {
		userInfoPageController.initData(userId, sceneUserInfoPage, lastScene, driver);
		ToScene(sceneUserInfoPage);
	}

	// For Administrator and Instructors
	public static void ToUserInfoPage(String userId) {
		switch (driver.getUsertype()){
			case "Administrator":
				userInfoPageController.initData(userId, sceneUserInfoPage, sceneAdminStartPage, driver);
				break;
			case "Instructor":
				userInfoPageController.initData(userId, sceneUserInfoPage, sceneMyTeachingPage, driver);
				break;
		}
		ToScene(sceneUserInfoPage);
	}

	public static void ToUserSecurityEditPage(String userId, Scene lastScene) {
		userSecurityEditPageController.initData(userId, sceneUserSecurityEditPage, lastScene, driver);
		ToScene(sceneUserSecurityEditPage);
	}

	public static void ToStorePage(String userId, Scene lastScene) {
		storePageController.initData(userId, sceneSorePage, lastScene, driver);
		ToScene(sceneSorePage);
	}

	public static void ToCoursesListPage(String userId, String windowName, Scene lastScene) {
		coursesListPageController.initData(userId, windowName, sceneCoursesListPage, lastScene, driver);
		ToScene(sceneCoursesListPage);
	}

	public static void ToInstructorListPage(String userId, String windowName, Scene lastScene) {
		instructorListPageController.initData(userId, windowName, sceneInstructorListPage, lastScene, driver);
		ToScene(sceneInstructorListPage);
	}

	public static void ToCourseBookingPage(String userId, Scene lastScene) {
		courseBookingPageController.initData(userId, sceneCourseBookingPage, lastScene, driver);
		ToScene(sceneCourseBookingPage);
	}

	public static void ToInstructorUploadCourse(String userId, Scene lastScene){
		instructorUploadCourseController.initData(userId, lastScene, driver);
		ToScene(sceneInstructorUploadCourse);
	}

	public static void ToInstructorSelfCourseListPage(String userId, Scene lastScene){
		instructorSelfCourseListPageController.initData(userId, lastScene, driver, sceneInstructorSelfCourseListPage);
		ToScene(sceneInstructorSelfCourseListPage);
	}

	public static void ToMyTeachingPage(String userId, Scene lastScene){
		myTeachingPageController.initData(userId, sceneMyTeachingPage, lastScene, driver);
		ToScene(sceneMyTeachingPage);
	}

	public static void ToRechargePage(String userId, Scene lastScene){
		rechargePageController.initData(userId, sceneMyTeachingPage, lastScene, driver);
		ToScene(sceneRechargePage);
	}

	public static void ToVideoInfoPage(String userId, Scene lastScene, Video video){
		videoInfoPageController.initData(userId, lastScene, driver, video, sceneVideoInfoPage);
		ToScene(sceneVideoInfoPage);
	}

	// For Administrator
	public static void ToVideoInfoPage(String userId, Video video){
		videoInfoPageController.initData(userId, sceneAdminStartPage, driver, video, sceneVideoInfoPage);
		ToScene(sceneVideoInfoPage);
	}

	public static void ToVideoInfoEditPage(String userId, Scene last, GUIDriver driver, Video video){
		videoInfoEditPageController.initData(userId, last, driver, video);
		ToScene(sceneVideoInfoEditPage);
	}

	public static void ToPublishLivePage(String userId, Scene lastScene){
		publishLivePageController.initData(userId, scenePublishLivePage, lastScene, driver);
		ToScene(scenePublishLivePage);
	}

	public static void ToNewCoursePage(String userId, Scene lastScene){
		newCoursePageController.initData(userId, sceneNewCoursePage, lastScene, driver);
		ToScene(sceneNewCoursePage);
	}

	public static void ToLiveListPage(String userId, Scene lastScene){
		liveListPageController.initData(userId, sceneLiveListPage, lastScene, driver);
		ToScene(sceneLiveListPage);
	}

	public static void ToCommunitytPage(String userId, Scene lastScene){
		communityPageController.initData(userId, sceneCommunityPage, lastScene, driver);
		ToScene(sceneCommunityPage);
	}

	// Temporary
	public static void ToPublishLivePage(String userId){
		publishLivePageController.initData(userId, scenePublishLivePage, scenePublishLivePage, driver);
		ToScene(scenePublishLivePage);
	}
	// Temporary
	public static void ToAdminStartPage(String userId) {
		adminStartPageController.initData(userId, sceneAdminStartPage, sceneAdminStartPage, driver);
		ToScene(sceneAdminStartPage);
	}
	// Temporary
	public static void ToCourseBookingPage(String userId) {
		courseBookingPageController.initData(userId, sceneCourseBookingPage, sceneCourseBookingPage, driver);
		ToScene(sceneCourseBookingPage);
	}
	// Temporary
	public static void ToStorePage(String userId) {
		storePageController.initData(userId, sceneSorePage, sceneSorePage, driver);
		ToScene(sceneSorePage);
	}
	
	public static void ToScene(Scene scene) {
		primaryStage.setScene(scene);
		primaryStage.setTitle("GymSystem");
		primaryStage.show();
	}
}
