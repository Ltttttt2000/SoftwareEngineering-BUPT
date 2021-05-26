package com.iot.g89;

import java.io.IOException;

import fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneTransform {
	private static Stage primaryStage;

	private static Scene sceneClientStartPage;
	private static Scene sceneRegisterAndLoginPage;
	private static Scene sceneUserInfoPage;
	private static Scene sceneUserSecurityEditPage;
	private static Scene sceneSorePage;
	private static Scene sceneCoursesListPage;
	private static Scene sceneCourseBookingPage;
	
	private static ClientStartPageController clientStartPageController;
	private static RegisterAndLoginPageController registerAndLoginPageController;
	private static UserInfoPageController userInfoPageController;
	private static UserSecurityEditPageController userSecurityEditPageController;
	private static StorePageController storePageController;
	private static CoursesListPageController coursesListPageController;
	private static CourseBookingPageController courseBookingPageController;

	private static GUIDriver driver = new GUIDriver();
	
	public SceneTransform(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
//			rootClientStartPage = FXMLLoader.load(getClass().getResource("/fxml/ClientStartPageScene.fxml"));
//			rootRegisterAndLoginPage = FXMLLoader.load(getClass().getResource("/fxml/RegisterAndLoginPageScene.fxml"));
//			rootUserInfoPage = FXMLLoader.load(getClass().getResource("/fxml/UserInfoPageScene.fxml"));
			
			FXMLLoader loaderClientStartPage = new FXMLLoader(getClass().getResource("/fxml/ClientStartPageScene.fxml"));
			FXMLLoader loaderRegisterAndLoginPage = new FXMLLoader(getClass().getResource("/fxml/RegisterAndLoginPageScene.fxml"));
			FXMLLoader loaderUserInfoPage = new FXMLLoader(getClass().getResource("/fxml/UserInfoPageScene.fxml"));
			FXMLLoader loaderUserSecurityEditPage = new FXMLLoader(getClass().getResource("/fxml/UserSecurityEditPageScene.fxml"));
			FXMLLoader loaderStorePage = new FXMLLoader(getClass().getResource("/fxml/StorePageScene.fxml"));
			FXMLLoader loaderCoursesListPage = new FXMLLoader(getClass().getResource("/fxml/CoursesListPageScene.fxml"));
			FXMLLoader loaderCourseBookingPage = new FXMLLoader(getClass().getResource("/fxml/CourseBookingPageScene.fxml"));

			sceneClientStartPage = new Scene(loaderClientStartPage.load());
			sceneRegisterAndLoginPage = new Scene(loaderRegisterAndLoginPage.load());
			sceneUserInfoPage = new Scene(loaderUserInfoPage.load());
			sceneUserSecurityEditPage = new Scene(loaderUserSecurityEditPage.load());
			sceneSorePage = new Scene(loaderStorePage.load());
			sceneCoursesListPage = new Scene(loaderCoursesListPage.load());
			sceneCourseBookingPage = new Scene(loaderCourseBookingPage.load());
			
			clientStartPageController = loaderClientStartPage.getController();
			registerAndLoginPageController = loaderRegisterAndLoginPage.getController();
			userInfoPageController = loaderUserInfoPage.getController();
			userSecurityEditPageController = loaderUserSecurityEditPage.getController();
			storePageController = loaderStorePage.getController();
			coursesListPageController = loaderCoursesListPage.getController();
			courseBookingPageController = loaderCourseBookingPage.getController();
			
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
	
	public static void ToUserInfoPage(String userId, Scene lastScene) {
		userInfoPageController.initData(userId, sceneUserInfoPage, lastScene, driver);
		ToScene(sceneUserInfoPage);
	}

	public static void ToUserSecurityEditPage(String userId, Scene lastScene) {
		userSecurityEditPageController.initData(userId, sceneUserSecurityEditPage, lastScene, driver);
		ToScene(sceneUserSecurityEditPage);
	}

	// Temporary
	public static void ToStorePage(String userId) {
		storePageController.initData(userId, sceneSorePage, sceneSorePage, driver);
		ToScene(sceneSorePage);
	}

	public static void ToStorePage(String userId, Scene lastScene) {
		storePageController.initData(userId, sceneSorePage, lastScene, driver);
		ToScene(sceneSorePage);
	}

	public static void ToCoursesListPage(String userId, String windowName, Scene lastScene) {
		coursesListPageController.initData(userId, windowName, sceneCoursesListPage, lastScene, driver);
		ToScene(sceneCoursesListPage);
	}

	public static void ToCourseBookingPage(String userId, Scene lastScene) {
		courseBookingPageController.initData(userId, sceneCourseBookingPage, lastScene, driver);
		ToScene(sceneCourseBookingPage);
	}

	// Temporary
	public static void ToCourseBookingPage(String userId) {
		courseBookingPageController.initData(userId, sceneCourseBookingPage, sceneCourseBookingPage, driver);
		ToScene(sceneCourseBookingPage);
	}
	
	public static void ToScene(Scene scene) {
		primaryStage.setScene(scene);
		primaryStage.setTitle("GymSystem");
		primaryStage.show();
	}
}
