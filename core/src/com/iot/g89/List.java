package com.iot.g89;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import fxml.*;

public class List {
    private static Stage primaryStage;
    private static Scene sceneVideoPage;
    private static Scene sceneUserPage;
    private static VideoListPageController videoListPageController;
    private static UserListPageController userListPageController;
    public List(Stage primaryStage) {
        this.primaryStage = primaryStage;
        try {
            FXMLLoader loaderSceneVideoPage = new FXMLLoader(getClass().getResource("/fxml/VideoListPageScene.fxml"));
            FXMLLoader loaderSceneUserPage = new FXMLLoader(getClass().getResource("/fxml/UserListPageScene.fxml"));


            sceneVideoPage = new Scene(loaderSceneVideoPage.load());
            sceneUserPage = new Scene(loaderSceneUserPage.load());

            videoListPageController = loaderSceneVideoPage.getController();
            userListPageController = loaderSceneUserPage.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toListPage(Video[] videos){
        videoListPageController.initData(videos);
//        toVideoScene(sceneVideoPage);
        toScene(sceneVideoPage, "Video");
    }

    public void toListPage(User[] users){
        userListPageController.initData(users);
//        toUserScene(sceneUserPage);
        toScene(sceneUserPage, "User");
    }

//    public static void toVideoScene(Scene scene){
//        primaryStage.setTitle("VIDEOS");
//        primaryStage.setScene(sceneVideoPage);
//        primaryStage.show();
//    }
//
//
//    public static void toUserScene(Scene scene){
//        primaryStage.setTitle("USERS");
//        primaryStage.setScene(sceneUserPage);
//        primaryStage.show();
//    }

    public static void toScene(Scene scene, String title){
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
