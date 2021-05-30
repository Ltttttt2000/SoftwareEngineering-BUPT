package fxml;

import com.iot.g89.GUIDriver;
import com.iot.g89.SceneTransform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class StorePageController  implements Initializable {
    @FXML
    private Button instructorStoreButton;
    @FXML
    private Button coursesStoreButton;

    private String[] userIds;
    private GUIDriver driver;
    private Scene thisScene;
    private Scene lastScene;

    private String curUserId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(String curUserId, Scene thisScene, Scene lastScene, GUIDriver driver) {
        this.driver = driver;
        this.curUserId = curUserId;
        this.thisScene = thisScene;
        this.lastScene = lastScene;
    }

    public void goToInstructorStore(ActionEvent event) {
        SceneTransform.ToInstructorListPage(curUserId, "Instructors Store", thisScene);
    }

    public void goToCoursesStore(ActionEvent event) {
        SceneTransform.ToCoursesListPage(curUserId, "Courses Store", thisScene);
    }

    public void goToRechargePage(ActionEvent event) {
        SceneTransform.ToRechargePage(curUserId, thisScene);
    }

    // for back button
    public void backToLastScene(){
        SceneTransform.ToScene(lastScene);
    }
}
