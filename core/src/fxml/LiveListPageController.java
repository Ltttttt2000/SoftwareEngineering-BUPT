package fxml;

import com.iot.g89.GUIDriver;
import com.iot.g89.Instructor;
import com.iot.g89.Live;
import com.iot.g89.SceneTransform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LiveListPageController implements Initializable {
    @FXML
    private Label userIdLabel;
    @FXML
    private VBox liveVBox;
    @FXML
    private Label liveIdLabel;
    @FXML
    private TextArea descriptionTA;
    @FXML
    private Button deleteButton;

    private String chosenLive = "";

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

        userIdLabel.setText(userId);
        liveIdLabel.setText("");
        liveVBox.getChildren().clear();
        deleteButton.setDisable(true);

        listLives(userId);
    }

    public void listLives(String instructorId){
        ArrayList<Object> lives = driver.select("Live InstructorId=" + instructorId);
//        System.out.println(lives);
        liveVBox.getChildren().clear();
        for(Object l:lives){
            liveVBox.getChildren().add(drawLiveButton((Live) l));
        }
    }

    public Button drawLiveButton(Live live) {
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

        button.setOnAction(e ->{
            this.liveIdLabel.setText(liveId);
            descriptionTA.setText(live.getDescription());
            chosenLive = liveId;
            deleteButton.setDisable(false);
        });

        return button;
    }

    public void deleteLive(ActionEvent event) {
        if(driver.delete(chosenLive)) {
            listLives(curUserId);
            descriptionTA.setText("");
            liveIdLabel.setText("");

            deleteButton.setDisable(true);
        }
        else{
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setHeaderText("Live not found!");
            alert1.setContentText("Please choose another!");

            alert1.showAndWait();
        }
    }

    // for back button
    public void backToLastScene() {
        SceneTransform.ToScene(lastScene);
        liveIdLabel.setText("");
    }
}
