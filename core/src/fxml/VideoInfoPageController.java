package fxml;

import com.iot.g89.GUIDriver;
import com.iot.g89.SceneTransform;
import com.iot.g89.Video;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class VideoInfoPageController implements Initializable {
    @FXML
    private Label VideoName;


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


    public void initData(String userId, Scene lastScene, GUIDriver driver, Video video, Scene thisScene){
        this.driver = driver;
        this.lastScene = lastScene;
        this.userId = userId;
        this.video = video;
        this.thisScene = thisScene;
        getBasicInfo(video);
    }

    public void getBasicInfo(Video video){
        VideoName.setText(video.getVideoName());
        VideoId.setText(video.getVideoId());
        VideoType.setText(video.getVideoType());
        VideoUploader.setText(video.getVideoUploader());
        Detail.setText(video.getVideoDetail());

        String UserType = driver.getUsertype(userId);

        if(UserType.equals("Administrator")){
            PurchaseVideoButton.setVisible(false);
            basicInfoEditButton.setVisible(true);
        }
        else if(UserType.equals("Instructor")){
            if(video.getVideoUploader().equals(userId)){
                System.out.println(userId);
                PurchaseVideoButton.setVisible(false);
                basicInfoEditButton.setVisible(true);
            }
            else{
                basicInfoEditButton.setVisible(false);
            }
        }
        else{
            basicInfoEditButton.setVisible(false);
        }

        String SpecificClient = video.getSpecificClient();
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

    public void basicInfoEdit(ActionEvent event){
        SceneTransform.ToVideoInfoEditPage(userId, thisScene, driver, video);
    }

    public void videoPlay(ActionEvent event){
        String FilePath = "./core/src/video/" + video.getVideoId() + "." + video.getFileType();
        video.playVideo(FilePath);
    }

    public void Purchase(ActionEvent event){
        if(driver.purchaseOrReserve(video.getVideoId()) == 1){
            backToLastScene();
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

    public void backToLastScene(){
        SceneTransform.ToScene(lastScene);
    }


}
