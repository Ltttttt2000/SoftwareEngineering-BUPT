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

    @FXML
    private Label rule1;
    @FXML
    private Label rule2;
    @FXML
    private Label rule3;

    // rules label color
    private String redColor = "-fx-background-color: rgba(255,0,0,0.5);";
    private String noColor = "-fx-background-color: transparent;";

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
        String oldPassword = oldPasswordTF.getText();
        String newPassword = newPasswordPF.getText();

        int i = GuiUtils.isPassword(newPassword);

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
            GuiUtils.checkTextField(newPasswordPF, false);
        }
        // check if the Re-enter password is same with new password
        else if(!newPassword.equals(reNewPasswordPF.getText())){
            GuiUtils.checkTextField(reNewPasswordPF, false);
        }
        // check if the old password is correct
        else if(!resetPassword(userId, oldPassword, newPassword)){
            GuiUtils.checkTextField(oldPasswordTF, false);
            GuiUtils.checkTextField(reNewPasswordPF, true);
        }
        // Success
        else{
            SceneTransform.ToRegisterAndLoginPage();
        }
    }

    private void resetColor(){
        rule1.setStyle(rule1.getStyle() + noColor);
        rule2.setStyle(rule1.getStyle() + noColor);
        rule3.setStyle(rule1.getStyle() + noColor);

        GuiUtils.checkTextField(oldPasswordTF, true);
        GuiUtils.checkTextField(newPasswordPF, true);
        GuiUtils.checkTextField(reNewPasswordPF, true);
    }

    private Boolean resetPassword(String userId, String oldPW, String newPW) {
        if(oldPW.equals(password))
            return true;
        else
            return false;
    }
}
