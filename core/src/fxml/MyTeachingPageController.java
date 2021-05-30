package fxml;

import com.iot.g89.GUIDriver;
import com.iot.g89.List;
import com.iot.g89.SceneTransform;
import com.iot.g89.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyTeachingPageController implements Initializable {

    private String curUserId;
    private GUIDriver driver;
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

    public void goToInstructorSelfCourseListPage(ActionEvent event) {
        SceneTransform.ToInstructorSelfCourseListPage(curUserId, thisScene);
    }

    public void goToLiveList(ActionEvent event) {
        SceneTransform.ToLiveListPage(curUserId, thisScene);
    }

    public void goToClientList(ActionEvent event) {
        Stage primaryStage = new Stage();
        List list = new List(primaryStage, driver);
        ArrayList<Object> objects = driver.select("Client", curUserId);
        ArrayList<User> users = new ArrayList<User>();
        for(Object o:objects)
            users.add((User) o);

        list.toUserListPage(users);
    }

    // for back button
    public void backToLastScene(){
        SceneTransform.ToScene(lastScene);
    }
}
