package fxml;

import com.iot.g89.GUIDriver;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

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
        button.setPrefSize(768, 84);

        return button;
    }
}
