package fxml;

import com.iot.g89.GUIDriver;
import com.iot.g89.SceneTransform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CoursesListPageController implements Initializable {
    @FXML
    private ScrollPane videoPane;
    @FXML
    private VBox videoListVBox;
    @FXML
    private Label windowNameLabel;
    @FXML
    private ChoiceBox<String> sportTypeCB;
    @FXML
    private TextField videoIdTF;

    private String[] userIds;
    private GUIDriver driver;
    private Scene thisScene;
    private Scene lastScene;

    private String curUserId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sportTypeCB.getItems().addAll("All", "Strength", "Relax", "HIT", "Basic Ability", "Yoga");
        sportTypeCB.getSelectionModel().select(0);
    }

    public void initData(String curUserId, String windowName, Scene thisScene, Scene lastScene, GUIDriver driver) {
        this.driver = driver;
        this.curUserId = curUserId;
        this.thisScene = thisScene;
        this.lastScene = lastScene;
        windowNameLabel.setText(windowName);
        switch(windowName) {
            case "Public Courses":
                break;
            case "Paid Courses":
                // yeah
                break;
            case "History Courses":
                // something
                break;
            case "Courses Store":
                // something else
                break;
            case "Purchased Courses":
                // Something choose someone's paid courses
                break;
            case "Private Courses":
                // own courses
                break;
        }
        listVideos();
    }

    private void listVideos(){
        String[] videos = getAllvideosId();
        for(String v:videos){
            videoListVBox.getChildren().add(drawVideoButton(v));
        }
    }

    private String[] getAllvideosId() {
        String[] strs = {"1001", "1002", "1003", "1004", "1005", "1006", "1007"};
        return strs;
    }

    public Button drawVideoButton(String videoId){
        ImageView image= new ImageView("file:core/src/imgs/videoCover.jpg");

        Button button = new Button();
        BorderPane buttonPane = new BorderPane();
        BorderPane centerPane = new BorderPane();
        BorderPane imagePane = new BorderPane();

        Label videoIdLabel = new Label("ID: " + videoId);

        Label priceLabel = new Label("30" + "£");
        Label videoTypeLabel = new Label("Video Type: " + "public" + " | Author: " + "橙色大呲花");
        Label sportTypeLabel = new Label("Sport Type: " + "Yoga");

        // bigger labels
        button.setStyle("-fx-background-color: white;" +
                "-fx-border-style: hidden hidden solid hidden;" +
                "-fx-font-family: Arial;");
        videoIdLabel.setStyle("-fx-font-size: 24px;-fx-font-family: 'Arial Black';");
        priceLabel.setStyle("-fx-font-size: 24px;");
        videoTypeLabel.setStyle("-fx-text-fill: gray");
        sportTypeLabel.setStyle("-fx-text-fill: gray");

        centerPane.setLeft(sportTypeLabel);
        centerPane.setTop(videoIdLabel);
        centerPane.setRight(videoTypeLabel);
        centerPane.setPadding(new Insets(0,20,0,10));
        imagePane.setCenter(image);

        // Add nodes into buttonPane
        buttonPane.setLeft(imagePane);
        buttonPane.setCenter(centerPane);
        buttonPane.setRight(priceLabel);
        buttonPane.setPadding(new Insets(20));

        // Layout in Button
        BorderPane.setAlignment(videoIdLabel, Pos.TOP_LEFT);
        BorderPane.setAlignment(sportTypeLabel, Pos.BOTTOM_LEFT);
        BorderPane.setAlignment(videoTypeLabel, Pos.BOTTOM_RIGHT);

        BorderPane.setAlignment(centerPane, Pos.CENTER);

        BorderPane.setAlignment(imagePane, Pos.CENTER_LEFT);
        BorderPane.setAlignment(priceLabel, Pos.CENTER_RIGHT);

        // set size
        centerPane.setPrefSize(464, 90);
        buttonPane.setPrefSize(750, 100);

        button.setMaxWidth(750);
        button.setMinHeight(100);

        // add buttonPane into button
        button.setGraphic(buttonPane);

        button.setOnAction(e ->{
            System.out.println("Video " + videoId);
        });

        return button;
    }

    // for back button
    public void backToLastScene(){
        SceneTransform.ToScene(lastScene);
        videoListVBox.getChildren().clear();
    }
}
