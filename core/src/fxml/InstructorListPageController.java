package fxml;

import com.iot.g89.GUIDriver;
import com.iot.g89.Instructor;
import com.iot.g89.SceneTransform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class InstructorListPageController implements Initializable {
    @FXML
    private ScrollPane videoPane;
    @FXML
    private ScrollPane instructorPane;

    @FXML
    private VBox instructorListVBox;

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
    }

    private void listInstructors(){
        ArrayList<Object> list = driver.select("Instructor *");
        for(Object i:list){
            instructorListVBox.getChildren().add(drawInstructorButton((Instructor) i));
        }
    }

//    private String[] getAllInstructorsId() {
//        String[] strs = {"I1001", "I1002", "I1003", "I1004", "I1005", "I1006"};
//        return strs;
//    }

    public Button drawInstructorButton(Instructor user){
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

        String userId = user.getUserid();
        String price = String.valueOf(user.getInstructorMoney());
        String sex = user.getSex();
        String age = String.valueOf(user.getAge());
        String userType = user.getUserLevel();
        String userLevel = user.getUserLevel();

        Label userIdLabel = new Label("ID: " + userId);
        Label priceLabel = new Label(price + "£");
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

    public void showInstructorStore(ActionEvent event){
        videoPane.setVisible(false);
        instructorPane.setVisible(true);
    }

    // for back button
    public void backToLastScene(){
        SceneTransform.ToScene(lastScene);
        instructorListVBox.getChildren().clear();
    }
}
