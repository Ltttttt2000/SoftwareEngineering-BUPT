package com.iot.g89;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			Parent root = FXMLLoader.load(getClass().getResource("/fxml/UserInfoPageScene.fxml"));
//			Parent root = FXMLLoader.load(getClass().getResource("/fxml/RegisterAndLoginPageScene.fxml"));
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/ClientStartPageScene.fxml"));
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("GymSystem");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
