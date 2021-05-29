package fxml;

import com.iot.g89.GUIDriver;
import com.iot.g89.Video;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class VideoInfoPageController implements Initializable {
    @FXML
    private Label VideoName;

    @FXML
    private Label SpecificClient;

    @FXML
    private Label VideoId;

    @FXML
    private Label VideoType;

    @FXML
    private Label VideoUploader;

    @FXML
    private TextArea Detail;

    @FXML
    private Button PurchaseVideoButton;

    @FXML
    private Button VideoPlayButton;

    @FXML
    private Button basicInfoEditButton;

    @FXML
    private Label VideoTypeLabelNormal;

    @FXML
    private Label VideoTypeLabelMember;

    @FXML
    private Label VideoTypeLabelSupreme;
    // init user ID
    private String userId;

    private GUIDriver driver;
    private Scene thisScene;
    private Scene lastScene;
    private Video video;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void initData(String userId, Scene lastScene, GUIDriver driver, Video video){
        this.driver = driver;
        this.lastScene = lastScene;
        this.userId = userId;
        this.video = video;
    }

    public void getBasicInfo(Video video){
        VideoName.setText(video.getVideoName());
        SpecificClient.setText(video.getSpecificClient());
        VideoId.setText(video.getVideoId());
        VideoType.setText(video.getVideoType());
        VideoUploader.setText(video.getVideoUploader());
        Detail.setText(video.getVideoDetail());

        String UserType = driver.getUsertype(userId);

        if(UserType.equals("Administrator")){
            PurchaseVideoButton.setVisible(false);
        }
        else if(UserType.equals("Instructor")){
            if(VideoUploader.equals(userId)){
                PurchaseVideoButton.setVisible(false);
            }
            else{
                basicInfoEditButton.setVisible(false);
            }
        }
        else{
            basicInfoEditButton.setVisible(false);
        }

        if(SpecificClient.equals("Normal")){
            showLabel(VideoTypeLabelNormal);
        }
        else if(SpecificClient.equals("Member")){
            showLabel(VideoTypeLabelMember);
        }
        else{
            showLabel(VideoTypeLabelSupreme);
        }
    }

    // only show the chosen label
    private void showLabel(Label l) {
        // hide all labels
        VideoTypeLabelMember.setVisible(false);
        VideoTypeLabelNormal.setVisible(false);
        VideoTypeLabelSupreme.setVisible(false);

        // only show the chosen one
        l.setVisible(true);
    }


}
