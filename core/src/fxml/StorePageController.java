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
    private ScrollPane videoPane;
    @FXML
    private ScrollPane instructorPane;

    @FXML
    private VBox instructorListVBox;
    @FXML
    private VBox videoListVBox;


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

        listInstructors();
        listvideos();
    }

    private void listInstructors(){
        String[] instructors = getAllInstructorsId();
        for(String i:instructors){
            instructorListVBox.getChildren().add(drawInstructorButton(i));
        }
    }

    private void listvideos(){
        String[] videos = getAllvideosId();
        for(String v:videos){
            videoListVBox.getChildren().add(drawVideoButton(v));
        }
    }

    private String[] getAllInstructorsId() {
        String[] strs = {"I1001", "I1002", "I1003", "I1004", "I1005", "I1006"};
        return strs;
    }

    private String[] getAllvideosId() {
        String[] strs = {"1001", "1002", "1003", "1004", "1005", "1006", "1007"};
        return strs;
    }

    public Button drawInstructorButton(String userId){
        StackPane imagePane = new StackPane();

        Image normalBorder = new Image("file:core/src/imgs/normalborder.png", 90, 90, false, false);
        Image JuniorBorder = new Image("file:core/src/imgs/juniorborder.png", 90, 90, false, false);
        Image seniorBorder = new Image("file:core/src/imgs/seniorborder.png", 90, 90, false, false);

        Image imageMale = new Image("file:core/src/imgs/instructorHeadPortraitMale.png", 90, 90, false, false);
        Image imageFemale = new Image("file:core/src/imgs/instructorHeadPortraitFemale.png", 90, 90, false, false);

        ImageView imageHead= new ImageView();
        ImageView imageBorder= new ImageView();

        Button button = new Button();
        BorderPane buttonPane = new BorderPane();
        BorderPane centerPane = new BorderPane();

        Label userIdLabel = new Label("ID: " + userId);
        Label priceLabel = new Label(driver.getInstructorMoney(userId) + "£");

        Random r = new Random();

        String sex = driver.getSex(userId);
        String age = driver.getAge(userId);
        String userType = driver.getUsertype(userId);
        String userLevel = driver.getUserLevel(userId);

        Label infoLabel = new Label("Age: " + age + " | Sex: " + sex);
        Label levelLabel = new Label(userType + " Level: " + userLevel);

        // bigger labels
        button.setStyle("-fx-background-color: white;" +
                "-fx-border-style: hidden hidden solid hidden;" +
                "-fx-font-family: Arial;");
        userIdLabel.setStyle("-fx-font-size: 24px;-fx-font-family: 'Arial Black';");
        priceLabel.setStyle("-fx-font-size: 24px;");
        levelLabel.setStyle("-fx-text-fill: gray");
        infoLabel.setStyle("-fx-text-fill: gray");

        // Add nodes into buttonPane
        centerPane.setTop(userIdLabel);
        centerPane.setLeft(levelLabel);
        centerPane.setRight(infoLabel);
        centerPane.setPadding(new Insets(0,20,0,10));

        if(sex.equals("Female"))
            imageHead.setImage(imageFemale);
        else
            imageHead.setImage(imageMale);

        if(userLevel.equals("Junior"))
            imageBorder.setImage(JuniorBorder);
        else if(userLevel.equals("Senior"))
            imageBorder.setImage(seniorBorder);
        else // if(userLevel.equals("Normal"))
            imageBorder.setImage(normalBorder);

        imagePane.getChildren().addAll(imageHead, imageBorder);
        buttonPane.setLeft(imagePane);
        buttonPane.setCenter(centerPane);
        buttonPane.setRight(priceLabel);
        buttonPane.setPadding(new Insets(20));

        // Layout in Button
        BorderPane.setAlignment(centerPane, Pos.CENTER);

        BorderPane.setAlignment(userIdLabel, Pos.CENTER_LEFT);
        BorderPane.setAlignment(levelLabel, Pos.BOTTOM_LEFT);
        BorderPane.setAlignment(infoLabel, Pos.BOTTOM_LEFT);

        BorderPane.setAlignment(priceLabel, Pos.CENTER_RIGHT);

        // set size
        centerPane.setPrefSize(464, 90);
        buttonPane.setPrefSize(750, 100);

        button.setMaxWidth(750);
        button.setMinHeight(100);

        // add buttonPane into button
        button.setGraphic(buttonPane);

        button.setOnAction(e ->{
            SceneTransform.ToUserInfoPage(userId, thisScene);
        });

        return button;
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

    public void showVideoStore(ActionEvent event){
        instructorPane.setVisible(false);
        videoPane.setVisible(true);
    }

    public void showInstructorStore(ActionEvent event){
        videoPane.setVisible(false);
        instructorPane.setVisible(true);
    }

    // for back button
    public void backToLastScene(){
        SceneTransform.ToScene(lastScene);
        videoListVBox.getChildren().clear();
        instructorListVBox.getChildren().clear();
    }
}
