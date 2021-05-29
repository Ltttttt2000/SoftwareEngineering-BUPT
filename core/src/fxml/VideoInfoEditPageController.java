package fxml;

import com.iot.g89.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class VideoInfoEditPageController implements Initializable {
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

    // init user ID
    private String userId;

    private GUIDriver driver;
    private Scene thisScene;
    private Scene lastScene;
    private Video video;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courseType.getItems().addAll("Strength", "Relax", "HIT", "Basic Ability", "Yoga");
        SpecificClient.getItems().addAll("Normal", "Member", "SupremeMember");
    }


    public void initData(String userId, Scene lastScene, GUIDriver driver, Video video){
        this.driver = driver;
        this.lastScene = lastScene;
        this.userId = userId;
        this.video = video;

        VideoName.setText(video.getVideoName());
        priceValue.setText(Double.toString(video.getVideoPrice()));
        Detail.setText(video.getVideoDetail());
        courseType.getSelectionModel().select(video.getVideoType());
        SpecificClient.getSelectionModel().select(video.getSpecificClient());
    }

    public void confirmUpdate(ActionEvent event){
        String[] attrs = {"videoName", "videoType", "videoPrice", "videoDetail", "specificClient"};
        String[] values = {VideoName.getText(), courseType.getValue(), priceValue.getText(),
                Detail.getText(), SpecificClient.getValue()};
        FileUtils.updateCSV4("./core/src/csv/Video.csv", video.getVideoId(), attrs, values);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Updated course information successfully!");

        alert.showAndWait();
    }

    public void deleteVideo(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("You confirm that you want to delete the course:");
        alert.setContentText(video.getVideoName());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            String filename = "./core/src/video/" + video.getVideoId() + "." + video.getFileType();
            if(GymUtils.delete(video.getVideoId()) && FileUtils.deleteFile(filename)){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Delete the success");
                alert1.setHeaderText(null);
                alert1.setContentText("You have successfully deleted the course" + video.getVideoName());

                alert1.showAndWait();
            }
            else{
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Error Dialog");
                alert2.setHeaderText("Delete course failed");
                alert2.setContentText("There was an error deleting the course " + video.getVideoName());

                alert2.showAndWait();
            }
        }
        else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    // for back button
    public void backToLastScene(){
        SceneTransform.ToScene(lastScene);
    }
}
