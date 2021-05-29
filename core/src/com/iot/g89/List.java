package com.iot.g89;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import fxml.*;

public class List {
    private static Stage primaryStage;
    private static Scene sceneVideoPage;
    private static Scene sceneUserPage;
    private static Scene sceneUserInfoPage;
//    private static Scene sceneVideoInfoPage;

    private static VideoListPageController videoListPageController;
    private static UserListPageController userListPageController;
    private static UserInfoPageController userInfoPageController;
//    private static VideoInfoPageController videoInfoPageController;

    private static GUIDriver driver;

    public List(Stage primaryStage, GUIDriver driver) {
        this.driver = driver;
        this.primaryStage = primaryStage;
        try {
            FXMLLoader loaderSceneVideoPage = new FXMLLoader(getClass().getResource("/fxml/VideoListPageScene.fxml"));
            FXMLLoader loaderSceneUserPage = new FXMLLoader(getClass().getResource("/fxml/UserListPageScene.fxml"));
            FXMLLoader loaderSceneUserInfoPage = new FXMLLoader(getClass().getResource("/fxml/UserInfoPageScene.fxml"));
//            FXMLLoader loaderSceneVideoInfoPage = new FXMLLoader(getClass().getResource("/fxml/VideoInfoPageScene.fxml"));

            sceneVideoPage = new Scene(loaderSceneVideoPage.load());
            sceneUserPage = new Scene(loaderSceneUserPage.load());
            sceneUserInfoPage = new Scene(loaderSceneUserInfoPage.load());
//            sceneVideoInfoPage = new Scene(loaderSceneVideoInfoPage.load());


            videoListPageController = loaderSceneVideoPage.getController();
            userListPageController = loaderSceneUserPage.getController();
            userInfoPageController = loaderSceneUserInfoPage.getController();
//            videoInfoPageController = loaderSceneVideoInfoPage.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void toVideoListPage(ArrayList<Video> videos){
        videoListPageController.initData(videos, sceneVideoPage);
        toScene(sceneVideoPage, "Video");
    }

    public static void toUserListPage(ArrayList<User> users){
        userListPageController.initData(users, sceneUserPage);
        toScene(sceneUserPage, "User");
    }

//    public static void ToVideoInfoPage(String userId, Scene lastScene, Video video) {
//		videoInfoPageController.initData(userId, sceneVideoInfoPage, lastScene, driver, video);
//		toScene(sceneVideoInfoPage);
//	}

    public static void ToUserInfoPage(String userId, Scene lastScene) {
        userInfoPageController.initData(userId, sceneUserInfoPage, lastScene, driver);
        toScene(sceneUserInfoPage, "User");
    }

    public static void toScene(Scene scene, String title){
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
