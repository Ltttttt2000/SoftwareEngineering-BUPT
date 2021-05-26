package fxml;

import com.iot.g89.GUIDriver;
import com.iot.g89.SceneTransform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class CourseBookingPageController implements Initializable {
    @FXML
    private VBox instructorVBox;

    private GUIDriver driver;
    private Scene thisScene;
    private Scene lastScene;

    private String userId;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(String userId, Scene thisScene, Scene lastScene, GUIDriver driver) {
        this.driver = driver;
        this.userId = userId;
        this.thisScene = thisScene;
        this.lastScene = lastScene;
        listInstructors();
    }

    private void listInstructors(){
        String[] instructors = getAllInstructorsId();
        for(String i:instructors){
            instructorVBox.getChildren().add(drawInstructorButton(i));
        }
    }

    private String[] getAllInstructorsId() {
        String[] strs = {"I1001", "I1002", "I1003", "I1004", "I1005", "I1006"};
        return strs;
    }

    public void showBookingList(){
        System.out.println("yes");
    }

    public Button drawInstructorButton(String userId){
        StackPane imagePane = new StackPane();

        Image normalBorder = new Image("file:core/src/imgs/normalborder.png", 60, 60, false, false);
        Image JuniorBorder = new Image("file:core/src/imgs/juniorborder.png", 60, 60, false, false);
        Image seniorBorder = new Image("file:core/src/imgs/seniorborder.png", 60, 60, false, false);

        Image imageMale = new Image("file:core/src/imgs/instructorHeadPortraitMale.png", 60, 60, false, false);
        Image imageFemale = new Image("file:core/src/imgs/instructorHeadPortraitFemale.png", 60, 60, false, false);

        ImageView imageHead= new ImageView();
        ImageView imageBorder= new ImageView();

        Button button = new Button();
        BorderPane buttonPane = new BorderPane();
        BorderPane centerPane = new BorderPane();

        Label userIdLabel = new Label("ID: " + userId);

        Random r = new Random();

        String sex = driver.getSex(userId);
        String age = driver.getAge(userId);
        String userType = driver.getUsertype(userId);
        String userLevel = driver.getUserLevel(userId);

        Label levelLabel = new Label(userType + " Level: " + userLevel);

        // bigger labels
        button.setStyle("-fx-background-color: white;" +
                "-fx-border-style: hidden hidden solid hidden;" +
                "-fx-font-family: Arial;");
        userIdLabel.setStyle("-fx-font-size: 18px;-fx-font-family: 'Arial Black';");
        levelLabel.setStyle("-fx-text-fill: gray");

        // Add nodes into buttonPane
        centerPane.setTop(userIdLabel);
        centerPane.setLeft(levelLabel);
        centerPane.setPadding(new Insets(0,5,0,5));

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

        // Layout in Button
        BorderPane.setAlignment(centerPane, Pos.CENTER);

        BorderPane.setAlignment(userIdLabel, Pos.CENTER_LEFT);
        BorderPane.setAlignment(levelLabel, Pos.BOTTOM_LEFT);

        // set size
        centerPane.setPrefSize(100, 60);
        buttonPane.setPrefSize(233, 70);

        button.setMaxWidth(233);
        button.setMinHeight(70);

        // add buttonPane into button
        button.setGraphic(buttonPane);

        button.setOnAction(e ->{
            showBookingList();
        });

        return button;
    }

    // for back button
    public void backToLastScene(){
        SceneTransform.ToScene(lastScene);
        instructorVBox.getChildren().clear();
    }
}
