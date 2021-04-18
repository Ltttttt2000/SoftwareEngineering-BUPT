package com.iot.g89;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		SceneTransform st = new SceneTransform(primaryStage);
		SceneTransform.ToRegisterAndLoginPage();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
