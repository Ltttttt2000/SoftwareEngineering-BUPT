package fxml;

import com.iot.g89.GUIDriver;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StorePageController  implements Initializable {


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
    }


    public Button drawButton(String userId){
        Button button = new Button();
        BorderPane buttonPane = new BorderPane();

        Label userIdLabel = new Label("ID: " + userId);

        Label ageTextLabel = new Label("Age: ");
        Label sexTextLabel = new Label("Sex: ");
        Label userTypeTextLabel = new Label("User Type: ");
        Label userLevelTextLabel = new Label("User Level: ");

        Label ageLabel = new Label();
        Label sexLabel = new Label();
        Label userTypeLabel = new Label();
        Label userLevelLabel = new Label();

        buttonPane.setLeft(userIdLabel);


        buttonPane.setPrefSize(768, 84);
        button.setPrefSize(768, 84);

        button.setGraphic(buttonPane);

        return button;
    }
}
