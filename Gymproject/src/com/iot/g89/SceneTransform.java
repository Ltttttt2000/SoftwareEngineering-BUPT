package com.iot.g89;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneTransform {
	private static Stage primaryStage;
	private static Parent rootClientStartPage;
	private static Parent rootRegisterAndLoginPage;
	private static Parent rootUserInfoPage;
	
	private static ClientStartPageController clientStartPageController;
	private static RegisterAndLoginPageController RegisterAndLoginPageController;
	private static UserInfoPageController UserInfoPageController;
	
	
	public SceneTransform(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
//			rootClientStartPage = FXMLLoader.load(getClass().getResource("/fxml/ClientStartPageScene.fxml"));
//			rootRegisterAndLoginPage = FXMLLoader.load(getClass().getResource("/fxml/RegisterAndLoginPageScene.fxml"));
//			rootUserInfoPage = FXMLLoader.load(getClass().getResource("/fxml/UserInfoPageScene.fxml"));
			
			FXMLLoader loaderClientStartPage = new FXMLLoader(getClass().getResource("/fxml/ClientStartPageScene.fxml"));
			FXMLLoader loaderRegisterAndLoginPage = new FXMLLoader(getClass().getResource("/fxml/RegisterAndLoginPageScene.fxml"));
			FXMLLoader loaderUserInfoPage = new FXMLLoader(getClass().getResource("/fxml/UserInfoPageScene.fxml"));
			
			rootClientStartPage = loaderClientStartPage.load();
			rootRegisterAndLoginPage = loaderRegisterAndLoginPage.load();
			rootUserInfoPage = loaderUserInfoPage.load();
			
			clientStartPageController = loaderClientStartPage.getController();
			RegisterAndLoginPageController = loaderRegisterAndLoginPage.getController();
			UserInfoPageController = loaderUserInfoPage.getController();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void ToRegisterAndLoginPage() {
		ToScene(rootRegisterAndLoginPage);
	}
	
	public static void ToClientStartPage(String userId) {
		clientStartPageController.initData(userId);
		ToScene(rootClientStartPage);
	}
	
	public static void ToUserInfoPage(String userId) {
		UserInfoPageController.initData(userId);
		ToScene(rootUserInfoPage);
	}
	
	public static void ToScene(Parent root) {
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("GymSystem");
		primaryStage.show();
	}
}
