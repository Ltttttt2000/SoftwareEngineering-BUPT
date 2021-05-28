package fxml;

import com.iot.g89.GUIDriver;
import com.iot.g89.SceneTransform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.net.URL;
import java.util.ResourceBundle;

public class MyTeachingPgaeController implements Initializable {

    private String userId;
    private GUIDriver driver;
    private Scene thisScene;
    private Scene lastScene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(String curUserId, Scene thisScene, Scene lastScene, GUIDriver driver) {
        this.driver = driver;
        this.userId = curUserId;
        this.thisScene = thisScene;
        this.lastScene = lastScene;
    }

    public void goToInstructorSelfCourseListPage(ActionEvent event){
        SceneTransform.ToInstructorSelfCourseListPage(userId, driver, thisScene);
    }


    // for back button
    public void backToLastScene(){
        SceneTransform.ToScene(lastScene);
    }
}
