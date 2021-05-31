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
    private static Scene sceneLivePage;

    private static VideoListPageController videoListPageController;
    private static UserListPageController userListPageController;
    private static LiveCourseListPageController liveCourseListPageController;

    private static GUIDriver driver;

    public List(Stage primaryStage, GUIDriver driver) {
        this.driver = driver;
        this.primaryStage = primaryStage;
        try {
            FXMLLoader loaderSceneVideoPage = new FXMLLoader(getClass().getResource("/fxml/VideoListPageScene.fxml"));
            FXMLLoader loaderSceneUserPage = new FXMLLoader(getClass().getResource("/fxml/UserListPageScene.fxml"));
            FXMLLoader loaderSceneLivePage = new FXMLLoader(getClass().getResource("/fxml/LiveCourseListPageScene.fxml"));

            sceneVideoPage = new Scene(loaderSceneVideoPage.load());
            sceneUserPage = new Scene(loaderSceneUserPage.load());
            sceneLivePage = new Scene(loaderSceneLivePage.load());


            videoListPageController = loaderSceneVideoPage.getController();
            userListPageController = loaderSceneUserPage.getController();
            liveCourseListPageController = loaderSceneLivePage.getController();

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

    public static void toLiveListPage(ArrayList<Live> lives){
        liveCourseListPageController.initData(lives, sceneLivePage, driver);
        toScene(sceneLivePage, "Live");
    }

    public static void toScene(Scene scene, String title){
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void closeStage(){
        primaryStage.close();
    }
}
