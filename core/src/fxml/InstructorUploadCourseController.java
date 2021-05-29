package fxml;

import com.iot.g89.FileUtils;
import com.iot.g89.GUIDriver;
import com.iot.g89.GymUtils;
import com.iot.g89.SceneTransform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
import java.util.Optional;
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

    @FXML
    private ChoiceBox<String> SpecificClient;

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
        SpecificClient.getItems().addAll("Normal", "Member", "SupremeMember");
        courseType.getSelectionModel().select("Strength");
        SpecificClient.getSelectionModel().select("Normal");
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

    public void confirmUpload(ActionEvent event) throws IOException {
        if(file == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("You did not select the file");
            alert.setContentText("Please select a file!");

            alert.showAndWait();
        }
        else if(VideoName.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("You did not fill in the name of the video");
            alert.setContentText("Please fill in the name of the video!");

            alert.showAndWait();
        }
        else if(priceValue.getText().equals("") || !isNumeric(priceValue.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("The price is incorrectly filled");
            alert.setContentText("Please fill in the price in correct form!");

            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Do you want to upload the file:");
            alert.setContentText(file.getAbsolutePath());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                uploadFile();
            }
        }
    }


    public void uploadFile() throws IOException {
        String VideoId = "V" + Integer.toString(GymUtils.findLastIDPlus1("Video"));
        String UserId = userId;
        String name = VideoName.getText();
        String type = courseType.getValue();
        String CoursePrice = priceValue.getText();
        String courseDetail = Detail.getText();
        String FileType = FileUtils.getFileSuffix(file);
        String ClientType = SpecificClient.getValue();
        if(ClientType.equals("")){
            ClientType = "Normal";
        }

        ArrayList<String[]> entry = new ArrayList<String[]>();
        String[] data = {VideoId, name, type, CoursePrice, UserId, courseDetail, ClientType, FileType};
        entry.add(data);
        FileUtils.insertCSV("./core/src/csv/Video.csv", entry);

        FileUtils.uploadFile("./core/src/video/", file.getPath(), VideoId + "." + FileType);
    }

    public boolean isNumeric(String str){
         for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
         }
        return true;
    }



}
