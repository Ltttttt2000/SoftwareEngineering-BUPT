package fxml;

import com.iot.g89.GUIDriver;
import com.iot.g89.GUIUtils;
import com.iot.g89.SceneTransform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RechargePageController implements Initializable {
    @FXML
    private Button recharge100;
    @FXML
    private Button recharge300;
    @FXML
    private Button recharge500;
    @FXML
    private Button recharge700;
    @FXML
    private Button recharge1000;
    @FXML
    private Button rechargeInput;

    @FXML
    private Label userIdLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private TextField amountTF;
    @FXML
    private Label illegalLabel;

    @FXML
    private BorderPane manualInputPane;

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
        userIdLabel.setText(userId);
        this.thisScene = thisScene;
        this.lastScene = lastScene;

        showInputPane(false);
    }

    public void showInputPane(Boolean b) {
        recharge100.setDisable(b);
        recharge300.setDisable(b);
        recharge500.setDisable(b);
        recharge700.setDisable(b);
        recharge1000.setDisable(b);
        rechargeInput.setDisable(b);
        manualInputPane.setVisible(b);
        amountTF.setText("");
        GUIUtils.checkTextField(amountTF, true);
        illegalLabel.setVisible(false);
    }

    public void showInputPane(ActionEvent event) {
        showInputPane(true);
    }

    public void recharge1(ActionEvent event) { recharge(100); }
    public void recharge3(ActionEvent event) { recharge(300); }
    public void recharge5(ActionEvent event) { recharge(500); }
    public void recharge7(ActionEvent event) { recharge(700); }
    public void recharge10(ActionEvent event) { recharge(1000); }
    public void rechargeI(ActionEvent event) {
        String amount = amountTF.getText();
        if(GUIUtils.isNumeric(amount)) {
            recharge(Double.valueOf(amount));
            GUIUtils.checkTextField(amountTF, true);
            illegalLabel.setVisible(false);
        }
        else{
            GUIUtils.checkTextField(amountTF, false);
            illegalLabel.setVisible(true);
        }

    }

    public void cancelInput(ActionEvent event) {
        showInputPane(false);
    }

    public void recharge(double amount) {
        // recharge money
        driver.recharge(amount);
        backToLastScene();
    }

    // for back button
    public void backToLastScene() {
        SceneTransform.ToScene(lastScene);
    }
}
