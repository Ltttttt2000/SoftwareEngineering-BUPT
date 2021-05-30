package fxml;

import com.iot.g89.GUIDriver;
import com.iot.g89.Instructor;
import com.iot.g89.Live;
import com.iot.g89.SceneTransform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseBookingPageController implements Initializable {
    @FXML
    private VBox instructorVBox;
    @FXML
    private VBox liveVBox;
    @FXML
    private Label note;

    private ArrayList<Object> bookedLives = new ArrayList<Object>();

    private GUIDriver driver;
    private Scene thisScene;
    private Scene lastScene;

    private String curUserId;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(String userId, Scene thisScene, Scene lastScene, GUIDriver driver) {
        this.driver = driver;
        this.curUserId = userId;
        this.thisScene = thisScene;
        this.lastScene = lastScene;
        listInstructors();
        note.setText("");
        liveVBox.getChildren().clear();
    }

    private void listInstructors(){
        ArrayList<Object> instructors = driver.select("Instructor", curUserId);
        instructorVBox.getChildren().clear();
        for(Object i:instructors){
            instructorVBox.getChildren().add(drawInstructorButton((Instructor) i));
        }
    }

    public void listLives(String instructorId){
        ArrayList<Object> lives = driver.select("Live InstructorId=" + instructorId);
        bookedLives = driver.select("Live", curUserId);
        liveVBox.getChildren().clear();
        for(Object l:lives){
            if(liveContains(bookedLives, l))
                liveVBox.getChildren().add(drawLiveButton((Live) l, true));
            else
                liveVBox.getChildren().add(drawLiveButton((Live) l, false));
        }
    }

    public Button drawInstructorButton(Instructor user){
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

        String userId = user.getUserId();
        String sex = user.getSex();
        String age = String.valueOf(user.getAge());
        String userType = driver.getUsertype(userId);
        String userLevel = user.getUserLevel();

        Label userIdLabel = new Label("ID: " + userId);
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
            listLives(userId);
        });

        return button;
    }

    public Button drawLiveButton(Live live, Boolean booked) {
        ImageView stackImg = new ImageView("file:core/src/imgs/tickImg.png");

        Button button = new Button();

        BorderPane buttonPane = new BorderPane();
        BorderPane leftPane = new BorderPane();
        BorderPane rightPane = new BorderPane();

        String[] dateTime = Live.sdf.format(live.getDate()).split(" ");
        String liveId = live.getLiveId();
        String number = String.valueOf(live.getNumber());

        Label dateLabel = new Label(dateTime[0]);
        Label timeLabel = new Label("Time: " + dateTime[1] + ":00");
        Label liveIdLabel = new Label("Live ID: " + liveId);
        Label numberLabel = new Label(number + "/20");

        String style = "-fx-font-family: Arial; -fx-text-fill: white; -fx-font-size: 12px;";

        button.setStyle("-fx-background-color: rgba(0,0,0,0.3);");
        button.setTooltip(new Tooltip(live.getDescription()));
        dateLabel.setStyle("-fx-font-family: 'Arial Black'; -fx-text-fill: white; -fx-font-size: 18px;");
        timeLabel.setStyle(style);
        liveIdLabel.setStyle(style);
        numberLabel.setStyle(style);

        // Layout in button
        leftPane.setTop(dateLabel);
        leftPane.setBottom(timeLabel);

        rightPane.setLeft(stackImg);
        rightPane.setRight(numberLabel);

        buttonPane.setLeft(leftPane);
        buttonPane.setCenter(liveIdLabel);
        buttonPane.setRight(rightPane);

        BorderPane.setAlignment(liveIdLabel, Pos.BOTTOM_CENTER);

        button.setPadding(new Insets(10));
        button.setGraphic(buttonPane);

        // Button and pane size
        rightPane.setPrefSize(55, 42);
        leftPane.setPrefSize(122, 42);
        buttonPane.setPrefSize(285, 42);
        button.setPrefSize(305, 62);

        stackImg.setVisible(booked);

        button.setOnAction(e ->{
            switch(driver.purchaseOrReserve(liveId)){
                case -1:
                    note.setText("No such instructor/video/live"); break;
                case -2:
                    driver.rescind(liveId);
                    stackImg.setVisible(false);
                    note.setText("Class reservation cancelled successfully");
                    break;
                case -3:
                    break;
                case -4:
                    note.setText("You are not his/her student"); break;
                case -5:
                    note.setText("The class you selected is full !"); break;
                case 1:
                    stackImg.setVisible(true);
                    note.setText("Successful booking of class");
                    break;
            }
        });


        return button;
    }

    // for back button
    public void backToLastScene(){
        SceneTransform.ToScene(lastScene);
        instructorVBox.getChildren().clear();
    }

    public boolean liveContains(ArrayList<Object> list, Object obj) {
        Live l1;
        Live l2 = (Live) obj;
        for(Object o:list){
            l1 = (Live) o;
            if(l1.getLiveId().equals(l2.getLiveId()))
                return true;
        }
        return false;
    }

    public ArrayList<Object> subListByDate(ArrayList<Object> list) {
        // Sublist
        return list;
    }
}
