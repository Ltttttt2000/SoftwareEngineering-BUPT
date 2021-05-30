package fxml;

import com.iot.g89.GUIDriver;
import com.iot.g89.SceneTransform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class NewCoursePageController implements Initializable {
    private GUIDriver driver;
    private String userId;
    private Scene thisScene;
    private Scene lastScene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(String userId, Scene thisScene, Scene lastScene, GUIDriver driver) {
        this.driver = driver;
        this.userId = userId;
        this.thisScene = thisScene;
        this.lastScene = lastScene;
    }

    public void goToUploadCourse(ActionEvent event) {
        SceneTransform.ToInstructorUploadCourse(userId, thisScene);
    }

    public void goToPublishLive(ActionEvent event) {
        SceneTransform.ToPublishLivePage(userId, thisScene);
    }

    // for back button
    public void backToLastScene() {
        SceneTransform.ToScene(lastScene);
    }
}
