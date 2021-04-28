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
        String[] strs = {"1001", "1002", "1003", "1004", "1005", "1006", "1007"};
        return strs;
    }

    private String[] getAllvideosId() {
        String[] strs = {"1001", "1002", "1003", "1004", "1005", "1006", "1007"};
        return strs;
    }

    public Button drawInstructorButton(String userId){
        Button button = new Button();
        BorderPane buttonPane = new BorderPane();
        GridPane centerPane = new GridPane();

        Label userIdLabel = new Label("ID: " + userId);

        Label ageTextLabel = new Label("Age: ");
        Label sexTextLabel = new Label("Sex: ");
        Label userTypeTextLabel = new Label("User Type: ");
        Label userLevelTextLabel = new Label("User Level: ");

//        Label priceLabel = new Label(driver.getInstructorMoney(userId) + "£");
//
//        Label ageLabel = new Label(driver.getAge(userId));
//        Label sexLabel = new Label(driver.getSex(userId));
//        Label userTypeLabel = new Label(driver.getUsertype(userId));
//        Label userLevelLabel = new Label(driver.getUserLevel(userId));

        Label priceLabel = new Label("3000" + "£");

        Label ageLabel = new Label("18");
        Label sexLabel = new Label("Male");
        Label userTypeLabel = new Label("Instructor");
        Label userLevelLabel = new Label("Super");

        //col and row for centerPane
        ColumnConstraints col = new ColumnConstraints(10, 100, Double.MAX_VALUE);
        RowConstraints row = new RowConstraints(10, 30, Double.MAX_VALUE);

        // bigger labels
        userIdLabel.setStyle("-fx-font-size: 24px;");
        priceLabel.setStyle("-fx-font-size: 24px;");

        // add labels into centerPane
        centerPane.add(ageTextLabel, 0, 0);
        centerPane.add(sexTextLabel, 0, 1);
        centerPane.add(userTypeTextLabel, 2, 0);
        centerPane.add(userLevelTextLabel, 2, 1);

        // add labels with changeable value
        centerPane.add(ageLabel, 1, 0);
        centerPane.add(sexLabel, 1, 1);
        centerPane.add(userTypeLabel, 3, 0);
        centerPane.add(userLevelLabel, 3, 1);

        // make those labels aligned to the right
        GridPane.setHalignment(ageTextLabel, HPos.RIGHT);
        GridPane.setHalignment(sexTextLabel, HPos.RIGHT);
        GridPane.setHalignment(userTypeTextLabel, HPos.RIGHT);
        GridPane.setHalignment(userLevelTextLabel, HPos.RIGHT);

        // make the GridPane a better look
        centerPane.getColumnConstraints().setAll(col, col, col, col);
        centerPane.getRowConstraints().setAll(row, row);

        // Add nodes into buttonPane
        buttonPane.setLeft(userIdLabel);
        buttonPane.setCenter(centerPane);
        buttonPane.setRight(priceLabel);

        // Layout in Button
        centerPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(userIdLabel, Pos.CENTER_LEFT);
        BorderPane.setAlignment(priceLabel, Pos.CENTER_RIGHT);

        // set size
        centerPane.setPrefSize(464, 76);
        buttonPane.setPrefSize(750, 84);

        button.setMaxWidth(750);
        button.setMinHeight(84);

        // add buttonPane into button
        button.setGraphic(buttonPane);

        button.setOnAction(e ->{
            SceneTransform.ToUserInfoPage(userId, thisScene);
        });

        return button;
    }

    public Button drawVideoButton(String videoId){
        Image image= new Image("file:core/src/imgs/videoCover.jpg");

        Button button = new Button();
        BorderPane buttonPane = new BorderPane();
        GridPane centerPane = new GridPane();
        BorderPane imagePane = new BorderPane();

        Label videoIdLabel = new Label("ID: " + videoId);

        Label videoTypeTextLabel = new Label("Video Type: ");
        Label SportTypeTextLabel = new Label("Sport Type: ");

        Label priceLabel = new Label("30" + "£");
        Label videoTypeLabel = new Label("public");
        Label sportTypeLabel = new Label("Yoga");

        //col and row for centerPane
        ColumnConstraints col = new ColumnConstraints(10, 100, Double.MAX_VALUE);
        RowConstraints row = new RowConstraints(10, 30, Double.MAX_VALUE);

        // bigger labels
        videoIdLabel.setStyle("-fx-font-size: 24px;");
        priceLabel.setStyle("-fx-font-size: 24px;");

        // add labels into centerPane
        centerPane.add(videoTypeTextLabel, 0, 0);
        centerPane.add(SportTypeTextLabel, 0, 1);

        // add labels with changeable value
        centerPane.add(videoTypeLabel, 1, 0);
        centerPane.add(sportTypeLabel, 1, 1);

        // make those labels aligned to the right
        GridPane.setHalignment(videoTypeTextLabel, HPos.RIGHT);
        GridPane.setHalignment(SportTypeTextLabel, HPos.RIGHT);

        // make the GridPane a better look
        centerPane.getColumnConstraints().setAll(col, col);
        centerPane.getRowConstraints().setAll(row, row);

        imagePane.setRight(videoIdLabel);
        imagePane.setCenter(new ImageView(image));

        // Add nodes into buttonPane
        buttonPane.setLeft(imagePane);
        buttonPane.setCenter(centerPane);
        buttonPane.setRight(priceLabel);
        buttonPane.setPadding(new Insets(10));

        // Layout in Button
        BorderPane.setAlignment(videoIdLabel, Pos.CENTER);
        centerPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(imagePane, Pos.CENTER_LEFT);
        BorderPane.setAlignment(priceLabel, Pos.CENTER_RIGHT);

        // set size
        centerPane.setPrefSize(464, 76);
        buttonPane.setPrefSize(750, 84);

        button.setMaxWidth(750);
        button.setMinHeight(84);

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
