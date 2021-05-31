package fxml;

import com.iot.g89.GUIDriver;
import com.iot.g89.GUIUtils;
import com.iot.g89.SceneTransform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserSecurityEditPageController implements Initializable {
    private String userId;

    @FXML
    private Label userIdLabel;
    @FXML
    private TextField oldPasswordTF;
    @FXML
    private PasswordField newPasswordPF;
    @FXML
    private PasswordField reNewPasswordPF;

    @FXML
    private Label rule1;
    @FXML
    private Label rule2;
    @FXML
    private Label rule3;
    @FXML
    private Label rule4;

    // rules label color
    private String redColor = "-fx-background-color: rgba(255,0,0,0.5);";
    private String noColor = "-fx-background-color: transparent;";

    private GUIDriver driver;
    private Scene thisScene;
    private Scene lastScene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void initData(String userId, Scene thisScene, Scene lastScene, GUIDriver driver){
        this.driver = driver;
        this.userId = userId;
        userIdLabel.setText(userId);
        this.thisScene = thisScene;
        this.lastScene = lastScene;

        oldPasswordTF.setText("");
        newPasswordPF.setText("");
        reNewPasswordPF.setText("");
    }

    public void savePassword(ActionEvent event){
        String oldPassword = oldPasswordTF.getText();
        String newPassword = newPasswordPF.getText();

        int i = GUIUtils.isPassword(newPassword);

        resetColor();

        // if the new password is legal
        switch(i){
            case -1: rule1.setStyle(rule1.getStyle() + redColor);
                break;
            case -2: rule2.setStyle(rule1.getStyle() + redColor);
                break;
            case -3: rule3.setStyle(rule1.getStyle() + redColor);
                break;
        }

        if(i != 1){
            GUIUtils.checkTextField(newPasswordPF, false);
        }
        // check if the Re-enter password is same with new password
        else if(!newPassword.equals(reNewPasswordPF.getText())){
            GUIUtils.checkTextField(reNewPasswordPF, false);
        }
        // check if the old password is correct
        else{
            switch(driver.changePassword(oldPassword, newPassword, userId)){
                // wrong old password
                case -1:
                    GUIUtils.checkTextField(oldPasswordTF, false);
                    break;
                // new password is same as the old password
                case -2:
                    GUIUtils.checkTextField(newPasswordPF, false);
                    rule4.setStyle(rule1.getStyle() + redColor);
                    break;
                //success
                case 1:
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Password Saved");
                    alert.setHeaderText("Password changed!");
                    alert.setContentText("You have successfully changed the Password!");

                    alert.showAndWait();
                    backToLastScene();
                    break;
            }
        }
    }

    private void resetColor(){
        rule1.setStyle(rule1.getStyle() + noColor);
        rule2.setStyle(rule2.getStyle() + noColor);
        rule3.setStyle(rule3.getStyle() + noColor);
        rule4.setStyle(rule4.getStyle() + noColor);

        GUIUtils.checkTextField(oldPasswordTF, true);
        GUIUtils.checkTextField(newPasswordPF, true);
        GUIUtils.checkTextField(reNewPasswordPF, true);
    }

    public void backToLastScene(){
        SceneTransform.ToScene(lastScene);
    }
}
