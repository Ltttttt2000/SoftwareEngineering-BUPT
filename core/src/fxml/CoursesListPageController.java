package fxml;

import com.iot.g89.GUIDriver;
import com.iot.g89.SceneTransform;
import com.iot.g89.Video;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
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
import java.util.ArrayList;
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
    private ChoiceBox<String> priceRangeCB;
    @FXML
    private TextField searchingTF;

    private String selection = "Video";
    private String typeSelection = "";
    private String priceSelection = "";
    private String searching = "";

    private ArrayList<Object> list = new ArrayList<Object>();

    private GUIDriver driver;
    private Scene thisScene;
    private Scene lastScene;

    private String curUserId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sportTypeCB.getItems().addAll("All", "Strength", "Relax", "HIT", "Basic Ability", "Yoga");
        priceRangeCB.getItems().addAll("All", "Free (0£)", "Paid");
        sportTypeCB.getSelectionModel().select(0);
        priceRangeCB.getSelectionModel().select(0);

        sportTypeCB.getSelectionModel().selectedItemProperty().addListener(selectType);
        priceRangeCB.getSelectionModel().selectedItemProperty().addListener(selectPrice);
    }

    public void initData(String curUserId, String windowName, Scene thisScene, Scene lastScene, GUIDriver driver) {
        this.driver = driver;
        this.curUserId = curUserId;
        this.thisScene = thisScene;
        this.lastScene = lastScene;
        windowNameLabel.setText(windowName);
        switch(windowName) {
            case "Public Courses":
                list = driver.select(selection + " VideoPrice=0.00");
                break;
            case "Paid Courses":
                list = driver.select(selection + " VideoPrice!=0.00");
                break;
            case "History Courses":
                // something
                break;
            case "Courses Store":
                list = driver.select(selection);
                break;
            case "Purchased Courses":
                list = driver.select(selection, curUserId);
                break;
            case "Private Courses":
                list = driver.select(selection + "SpecificClient=" + curUserId);
                break;
        }
        listVideos();
    }

    private void listVideos(){
        ArrayList<Object> videos = driver.select(selection + " " + typeSelection + " " + priceSelection + " " + searching, this.list);
        videoListVBox.getChildren().clear();
        for(Object v:videos){
            videoListVBox.getChildren().add(drawVideoButton((Video) v));
        }

        System.out.println(selection + " " + typeSelection + " " + priceSelection + " " + searching);
    }

    public Button drawVideoButton(Video video){
        ImageView image= new ImageView("file:core/src/imgs/videoCover.jpg");

        Button button = new Button();
        BorderPane buttonPane = new BorderPane();
        BorderPane centerPane = new BorderPane();
        BorderPane imagePane = new BorderPane();

        String videoName = video.getVideoName();
        String price = String.valueOf(video.getVideoPrice());
        String videoId = video.getVideoId();
        String author = video.getVideoUploader();
        String type = video.getVideoType();

        Label videoIdLabel = new Label(videoName);

        Label priceLabel = new Label(price + "£");
        Label videoTypeLabel = new Label("Video ID: " + videoId + " | Author: " + author);
        Label sportTypeLabel = new Label("Sport Type: " + type);

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

    // Select video types
    public ChangeListener<String> selectType = new ChangeListener<String>(){
        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            if(t1.equals("All"))
                typeSelection = "";
            else
                typeSelection = "VideoType=" + t1;
            listVideos();
        }
    };

    // Select prices
    public ChangeListener<String> selectPrice = new ChangeListener<String>(){
        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            if(t1.equals("All"))
                priceSelection = "";
            else if(t1.equals("Free (0£)"))
                priceSelection = "VideoPrice=0.0";
            else
                priceSelection = "VideoPrice!=0.0";

            listVideos();
        }
    };

    public void searchSomething(ActionEvent event){
        String str = searchingTF.getText();

        if(str.matches("^\\s*$"))
            searching = "";
        else if(str.matches("I+[0-9]]") && str.length() == 5)
            searching = "Author=" + str;
        else if(str.matches("V+[0-9]") && str.length() == 5)
            searching = "VideoId=" + str;
        else
            searching = "VideoName=" + str;
        listVideos();
    }

    // for back button
    public void backToLastScene(){
        SceneTransform.ToScene(lastScene);
        videoListVBox.getChildren().clear();
    }
}
