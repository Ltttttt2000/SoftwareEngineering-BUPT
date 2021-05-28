package fxml;

import com.iot.g89.FileUtils;
import com.iot.g89.GUIDriver;
import com.iot.g89.GymUtils;
import com.iot.g89.SceneTransform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InstructorUploadCourseController implements Initializable {
    @FXML
    private javafx.scene.control.Button Choose;

    @FXML
    private javafx.scene.control.Label VideoPath;

    @FXML
    private javafx.scene.control.TextField VideoName;

    @FXML
    private ChoiceBox<String> courseType;

    @FXML
    private javafx.scene.control.TextField priceValue;

    @FXML
    private TextArea Detail;

    private File file;

    Stage stage;

    // init user ID
    private String userId;

    private GUIDriver driver;
    private Scene thisScene;
    private Scene lastScene;

    public InstructorUploadCourseController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courseType.getItems().addAll("Strength", "Relax", "HIT", "Basic Ability", "Yoga");
    }


    public void initData(String userId, Scene lastScene, GUIDriver driver){
        this.driver = driver;
        this.lastScene = lastScene;
        this.userId = userId;
    }

    public void backToLastScene(ActionEvent event){
        SceneTransform.ToScene(lastScene);
    }

    public void selectFile(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter( "*.mp4"),
//                new FileChooser.ExtensionFilter("*.avi"),
//                new FileChooser.ExtensionFilter("*.rmvb")
//        );
        file = fileChooser.showOpenDialog(stage);

        VideoPath.setText(file.getAbsolutePath());
    }

    public void uploadFile(ActionEvent event) throws IOException {
        GymUtils hehe = new GymUtils();
        String VideoId = "V" + Integer.toString(hehe.findLastVideoIDPlus1("Video"));
        String UserId = userId;
        String name = VideoName.getText();
        String type = courseType.getValue();
        String CoursePrice = priceValue.getText();
        String courseDetail = Detail.getText();
        String FileType = FileUtils.getFileSuffix(file);

        ArrayList<String[]> entry = new ArrayList<String[]>();
        String[] data = {VideoId, name, type, CoursePrice, UserId, courseDetail, "", FileType};
        entry.add(data);
        FileUtils.insertCSV("./core/src/csv/Video.csv", entry);

        FileUtils.uploadFile("./core/src/video/", file.getPath(), VideoId + "." + FileType);
    }



}
