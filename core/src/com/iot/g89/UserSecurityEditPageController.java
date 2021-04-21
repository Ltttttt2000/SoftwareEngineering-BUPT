package com.iot.g89;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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

    private GUIDriver driver;
    private Scene thisScene;
    private Scene lastScene;

    private String password = "666666";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void initData(String userId, Scene thisScene, Scene lastScene, GUIDriver driver){
        this.driver = driver;
        this.userId = userId;
        userIdLabel.setText(userId);
        this.thisScene = thisScene;
        this.lastScene = lastScene;
    }

    public void backToLastScene(ActionEvent event){
        SceneTransform.ToScene(lastScene);
    }

    public void savePassword(ActionEvent event){
        String oldPassword = getPassword(userId);
        String newPassword = newPasswordPF.getText();

        // check if the old password is correct
        if(!oldPassword.equals(oldPasswordTF.getText())){
            GuiUtils.checkTextField(oldPasswordTF, false);
            GuiUtils.checkTextField(reNewPasswordPF, true);
            newPasswordPF.setText("");
            reNewPasswordPF.setText("");
        }else{
            // check if the Re-enter password is same with new password
            if(!newPassword.equals(reNewPasswordPF.getText())){
                GuiUtils.checkTextField(reNewPasswordPF, false);
            }else{
                GuiUtils.checkTextField(reNewPasswordPF, true);
            }
            GuiUtils.checkTextField(oldPasswordTF, true);
        }



    }

    private String getPassword(String userId) {
        return password;
    }
}
