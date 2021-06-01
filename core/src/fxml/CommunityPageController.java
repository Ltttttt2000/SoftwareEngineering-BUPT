package fxml;

import com.iot.g89.GUIDriver;
import com.iot.g89.SceneTransform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <p>Boundary.</p>
 * <p>CommunityPageController class.</p>
 *
 * @version 0.5
 * @author S0range
 */

public class CommunityPageController implements Initializable {
    @FXML
    private Label userIdLabel;
    @FXML
    private Label userLevelLabel;

    private GUIDriver driver;
    private String userId;
    private Scene thisScene;
    private Scene lastScene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void initData(String userId, Scene thisScene, Scene lastScene, GUIDriver driver) {
        this.driver = driver;
        this.userId = userId;
        this.thisScene = thisScene;
        this.lastScene = lastScene;

        userIdLabel.setText("User ID: " + userId);
        userLevelLabel.setText("User Level: " + driver.getUserLevel(userId));
    }

    // for back button
    public void backToLastScene() {
        SceneTransform.ToScene(lastScene);
    }
}
