package fxml;

import java.net.URL;
import java.util.*;

import com.iot.g89.GUIDriver;
import com.iot.g89.SceneTransform;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * <p>Boundary.</p>
 * <p>InstructorStartPageController class.</p>
 *
 * @version 0.5
 * @author medon1013, S0range
 */

public class InstructorStartPageController implements Initializable {

    @FXML
    private Label userIdLabel;

    // init user ID
    private String userId;

    private GUIDriver driver;
    private Scene thisScene;
    private Scene lastScene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(String userId, Scene thisScene, Scene lastScene, GUIDriver driver) {
        this.driver = driver;
        this.userId = userId;
        userIdLabel.setText(userId);
        this.thisScene = thisScene;
        this.lastScene = lastScene;
    }

    public void logOut(ActionEvent event){
        SceneTransform.ToScene(lastScene);
        driver.logout();
    }

    public void showMyInfo(ActionEvent event) {
        SceneTransform.ToUserInfoPage(userId, thisScene);
    }

    public void showNewCourse(ActionEvent event) {
        SceneTransform.ToNewCoursePage(userId, thisScene);
    }

    public void showCommunity(ActionEvent event) {
        SceneTransform.ToCommunitytPage(userId, thisScene);
    }

    public void goToMyTeachingPage(ActionEvent event) {
        SceneTransform.ToMyTeachingPage(userId, thisScene);
    }


}
