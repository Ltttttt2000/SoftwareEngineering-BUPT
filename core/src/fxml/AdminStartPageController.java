package fxml;

import com.iot.g89.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminStartPageController implements Initializable {
    private GUIDriver driver;
    private String curUserId;
    private Scene thisScene;
    private Scene lastScene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(String curUserId, Scene thisScene, Scene lastScene, GUIDriver driver) {
        this.driver = driver;
        this.curUserId = curUserId;
        this.thisScene = thisScene;
        this.lastScene = lastScene;
    }

    public void showClient(ActionEvent event) {
        Stage primaryStage = new Stage();
        List list = new List(primaryStage, driver);
        ArrayList<Object> objects = driver.select("Client");
        ArrayList<User> users = new ArrayList<User>();
        for(Object o:objects)
            users.add((User) o);

        list.toUserListPage(users);
    }

    public void showBannedClient(ActionEvent event) {
        Stage primaryStage = new Stage();
        List list = new List(primaryStage, driver);
        ArrayList<Object> objects = driver.select("Client LoginLicense=false");
        ArrayList<User> users = new ArrayList<User>();
        for(Object o:objects)
            users.add((User) o);

        list.toUserListPage(users);
    }

    public void showUserAskForUnban(ActionEvent event) {
        Stage primaryStage = new Stage();
        List list = new List(primaryStage, driver);
        ArrayList<Object> objects = driver.select("Ban");
        ArrayList<User> users = new ArrayList<User>();
        for(Object o:objects)
            users.add((User) o);

        list.toUserListPage(users);
    }

    public void showInstructor(ActionEvent event) {
        Stage primaryStage = new Stage();
        List list = new List(primaryStage, driver);
        ArrayList<Object> objects = driver.select("Instructor");
        ArrayList<User> users = new ArrayList<User>();
        for(Object o:objects)
            users.add((User) o);

        list.toUserListPage(users);
    }

    public void showBannedInstructor(ActionEvent event) {
        Stage primaryStage = new Stage();
        List list = new List(primaryStage, driver);
        ArrayList<Object> objects = driver.select("Instructor LoginLicense=false");
        ArrayList<User> users = new ArrayList<User>();
        for(Object o:objects)
            users.add((User) o);

        list.toUserListPage(users);
    }

    public void showVideo(ActionEvent event) {
        Stage primaryStage = new Stage();
        List list = new List(primaryStage, driver);
        ArrayList<Object> objects = driver.select("Video");
        ArrayList<Video> videos = new ArrayList<Video>();
        for(Object o:objects)
            videos.add((Video) o);

        list.toVideoListPage(videos);
    }

    public void showLiveCourse(ActionEvent event) {

    }

    public void showFutureLiveCourse(ActionEvent event) {

    }

    public void logOut(ActionEvent event){
        SceneTransform.ToScene(lastScene);
        driver.logout();
    }
}
