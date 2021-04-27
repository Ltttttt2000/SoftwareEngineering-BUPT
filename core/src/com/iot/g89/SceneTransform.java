package com.iot.g89;

import java.io.IOException;

import fxml.ClientStartPageController;
import fxml.RegisterAndLoginPageController;
import fxml.UserInfoPageController;
import fxml.UserSecurityEditPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneTransform {
	private static Stage primaryStage;

	private static Scene sceneClientStartPage;
	private static Scene sceneRegisterAndLoginPage;
	private static Scene sceneUserInfoPage;
	private static Scene sceneUserSecurityEditPage;
	
	private static ClientStartPageController clientStartPageController;
	private static RegisterAndLoginPageController registerAndLoginPageController;
	private static UserInfoPageController userInfoPageController;
	private static UserSecurityEditPageController userSecurityEditPageController;

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

			sceneClientStartPage = new Scene(loaderClientStartPage.load());
			sceneRegisterAndLoginPage = new Scene(loaderRegisterAndLoginPage.load());
			sceneUserInfoPage = new Scene(loaderUserInfoPage.load());
			sceneUserSecurityEditPage = new Scene(loaderUserSecurityEditPage.load());
			
			clientStartPageController = loaderClientStartPage.getController();
			registerAndLoginPageController = loaderRegisterAndLoginPage.getController();
			userInfoPageController = loaderUserInfoPage.getController();
			userSecurityEditPageController = loaderUserSecurityEditPage.getController();
			
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
	
	public static void ToScene(Scene scene) {
		primaryStage.setScene(scene);
		primaryStage.setTitle("GymSystem");
		primaryStage.show();
	}
}
