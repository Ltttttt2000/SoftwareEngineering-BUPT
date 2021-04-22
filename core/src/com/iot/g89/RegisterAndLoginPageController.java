package com.iot.g89;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterAndLoginPageController implements Initializable {
	@FXML
	private AnchorPane beginPane;		// the pane has two button
	
	@FXML
	private AnchorPane loginPane;		// login background (right part) which is covered by other pane
	@FXML
	private AnchorPane registerPane;	// register background (left part) which is covered by other pane
	
	@FXML
	private Pane loginMovePane;		// login background (left part) which can move away
	@FXML
	private Pane registerMovePane;	// register background (right part) which can move away
	
	@FXML
	private BorderPane loginReshapePane;		// pane has login button
	@FXML
	private BorderPane registerReshapePane;		// pane has register button

	// login pane's nodes
	@FXML
	private ImageView keyImg;
	
	@FXML
	private TextField userIdTF;
	@FXML
	private PasswordField userPasswordPF;
	@FXML
	private ChoiceBox<String> typeCB;

	// register pane's nodes
	@FXML
	private BorderPane passwordSettingPane;
	@FXML
	private BorderPane basicInfoPane;

	@FXML
	private PasswordField registerPasswordPF;
	@FXML
	private PasswordField registerConfirmPasswordPF;
	@FXML
	private ChoiceBox<String> registerUserTypeCB;
	@FXML
	private Button nextButton;
	@FXML
	private GridPane rulePane;
	@FXML
	private  Label rule1;
	@FXML
	private  Label rule2;
	@FXML
	private  Label rule3;

	@FXML
	private ChoiceBox<String> sexCB;
	@FXML
	private TextField ageTF;
	@FXML
	private TextField heightTF;
	@FXML
	private TextField weightTF;
	@FXML
	private TextField chestTF;
	@FXML
	private TextField waistTF;
	@FXML
	private TextField hipTF;
	@FXML
	private TextArea resumeTA;
	@FXML
	private TextField phoneTF;

	private GUIDriver driver;
	private Scene thisScene;
	
	// current buttons' size
	private double lrpSize = 1;
	private double rrpSize = 1;
	
	// current buttons' position
	private double lrpX = 0;
	private double rrpX = 0;
	
	//current backgrounds' position
	private double lmpX = 0;
	private double rmpX = 0;

	// rules label color
	private String redColor = "-fx-background-color: rgba(255,0,0,0.5);";
	private String noColor = "-fx-background-color: transparent;";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		typeCB.getItems().addAll("Administrator", "Instructor", "Client");
		typeCB.getSelectionModel().select("Client");
		registerUserTypeCB.getItems().addAll("User Type", "Instructor", "Client");
		registerUserTypeCB.getSelectionModel().select(0);
		sexCB.getItems().addAll("Unknown", "Male", "Female");
		sexCB.getSelectionModel().select(0);
	}

	public void initData(Scene thisScene, GUIDriver driver){
		this.driver = driver;
		this.thisScene = thisScene;
	}

	//--------------------------------------begin page function-----------------------------------------------

	// when you move your mouse on the login part
	public void ShowLoginPageMove(MouseEvent event) {
		int delay = 150;				// background must be slower to catch the button
		int time = 150;
		String movingMode[] = {"slowToFast", "fastToSlow"};		// no need
		if(lmpX == 0) {
			delay = 0; 
			time = 300;
			movingMode[0] = "normal";
			movingMode[1] = "normal";
		}
		
		GuiUtils.ChangePosition(loginMovePane, lmpX, 0, 0, time, movingMode[1]);
		GuiUtils.ChangePosition(registerMovePane, rmpX, 192, delay, time, movingMode[0]);
		
		GuiUtils.ChangePosition(loginReshapePane, lrpX, 96);
		GuiUtils.ChangePosition(registerReshapePane, rrpX, 96);
		
		GuiUtils.ChangeSize(loginReshapePane, lrpSize, 1.5);
		GuiUtils.ChangeSize(registerReshapePane, rrpSize, 0.5);
		
		lmpX = 0;
		rmpX = 192;
		
		lrpSize = 1.5;
		rrpSize = 0.5;
		lrpX = 96;
		rrpX = 96;
	}
	
	// when you move your mouse on the register part
	public void ShowRegisterPageMove(MouseEvent event) {
		int delay = 150;				// background must be slower to catch the button
		int time = 150;
		String movingMode[] = {"slowToFast", "fastToSlow"};		// no need
		if(rmpX == 0) {
			delay = 0; 
			time = 300;
			movingMode[0] = "normal";
			movingMode[1] = "normal";
		}
		
		GuiUtils.ChangePosition(loginMovePane, lmpX, -192, delay, time, movingMode[0]);
		GuiUtils.ChangePosition(registerMovePane, rmpX, 0, 0, time, movingMode[1]);
		
		GuiUtils.ChangePosition(loginReshapePane, lrpX, -96);
		GuiUtils.ChangePosition(registerReshapePane, rrpX, -96);
		
		GuiUtils.ChangeSize(loginReshapePane, lrpSize, 0.5);
		GuiUtils.ChangeSize(registerReshapePane, rrpSize, 1.5);
		
		lmpX = -192;
		rmpX = 0;
		lrpSize = 0.5;
		rrpSize = 1.5;
		lrpX = -96;
		rrpX = -96;
	}
	
	// when you click your mouse on the login part
	public void ShowLoginPageClick(ActionEvent event) {
		GuiUtils.ChangePosition(loginMovePane, lmpX, 0);
		GuiUtils.ChangePosition(registerMovePane, rmpX, 384);
		
		GuiUtils.ChangePosition(loginReshapePane, lrpX, 192);
		GuiUtils.ChangePosition(registerReshapePane, rrpX, 192);
		
		GuiUtils.ChangeSize(loginReshapePane, lrpSize, 2);
		GuiUtils.ChangeSize(registerReshapePane, rrpSize, 0);
		
		lmpX = 0;
		rmpX = 384;
		lrpSize = 2;
		rrpSize = 0;
		lrpX = 192;
		rrpX = 192;
		
		PauseTransition pause = new PauseTransition(Duration.millis(300));
		pause.setOnFinished(e -> {
			loginPane.setVisible(true);}
		);
		pause.play();
	}
	
	// when you click your mouse on the register part
	public void ShowRegisterPageClick(ActionEvent event) {
		GuiUtils.ChangePosition(loginMovePane, lmpX, -384);
		GuiUtils.ChangePosition(registerMovePane, rmpX, 0);
		
		GuiUtils.ChangePosition(loginReshapePane, lrpX, -192);
		GuiUtils.ChangePosition(registerReshapePane, rrpX, -192);
		
		GuiUtils.ChangeSize(loginReshapePane, lrpSize, 0);
		GuiUtils.ChangeSize(registerReshapePane, rrpSize, 2);
		
		lmpX = -384;
		rmpX = 0;
		lrpSize = 0;
		rrpSize = 2;
		lrpX = -192;
		rrpX = -192;
		
		PauseTransition pause = new PauseTransition(Duration.millis(300));
		pause.setOnFinished(e -> {
			registerPane.setVisible(true);}
		);
		pause.play();
		
	}
	
	public void SceneRestore(MouseEvent event) {
		GuiUtils.ChangePosition(loginMovePane, lmpX, 0);
		GuiUtils.ChangePosition(registerMovePane, rmpX, 0);
		
		GuiUtils.ChangePosition(loginReshapePane, lrpX, 0);
		GuiUtils.ChangePosition(registerReshapePane, rrpX, 0);
		
		GuiUtils.ChangeSize(loginReshapePane, lrpSize, 1);
		GuiUtils.ChangeSize(registerReshapePane, rrpSize, 1);
		
		lmpX = 0;
		rmpX = 0;
		lrpSize = 1;
		rrpSize = 1;
		lrpX = 0;
		rrpX = 0;
	}
	
	//------------------------------------login page function----------------------------------------------
	
	public void MouseEnteredLogin(MouseEvent event) {
		GuiUtils.ChangePosition(keyImg, 0, 142, 0);
	}
	public void MouseExitLogin(MouseEvent event) {
		GuiUtils.ChangePosition(keyImg, 142, 0, 0);
	}

	/**
	 * For back button. Back to the beginning pane and reset all the nodes
	 * @param event
	 */
	public void backToBeginPane(ActionEvent event) {
		loginPane.setVisible(false);
		registerPane.setVisible(false);

		// for login pane
		userIdTF.setText("");
		userPasswordPF.setText("");

		// for register pane
		registerPasswordPF.setText("");
		registerConfirmPasswordPF.setText("");
		registerUserTypeCB.getSelectionModel().select(0);
		ageTF.setText("");
		heightTF.setText("");
		weightTF.setText("");
		chestTF.setText("");
		waistTF.setText("");
		hipTF.setText("");
		resumeTA.setText("");
		phoneTF.setText("");
		resetRegisterPane(true);
	}

	// login page function
	public void loginSuccess(ActionEvent event) {
		String userId = userIdTF.getText();
		String password = userPasswordPF.getText();
		String type = typeCB.getValue();

		int x = driver.login(userId, password, type);

		GuiUtils.checkTextField(userIdTF, true);
		GuiUtils.checkTextField(userPasswordPF, true);

		if(x == 1){
			SceneTransform.ToClientStartPage(userId, thisScene);
			userPasswordPF.setText("");
		}else if (x == -1)
			GuiUtils.checkTextField(userIdTF, false);
		else
			GuiUtils.checkTextField(userPasswordPF, false);
	}

	//-------------------------------------register page function----------------------------------------------
	public void completePasswordSet(ActionEvent event){
		String password = registerPasswordPF.getText();
		String confirmPassword = registerConfirmPasswordPF.getText();

		int i = GuiUtils.isPassword(password);

		resetColor();

		// empty password
		if(i != 1){
			switch(i) {
				case -1: rule1.setStyle(rule1.getStyle() + redColor);
					break;
				case -2: rule2.setStyle(rule1.getStyle() + redColor);
					break;
				case -3: rule3.setStyle(rule1.getStyle() + redColor);
					break;
			}
			GuiUtils.checkTextField(registerPasswordPF, false);
		}
		// different confirm password
		else if(!password.equals(confirmPassword)){
			GuiUtils.checkTextField(registerConfirmPasswordPF, false);
		}
		// no choice of user type
		else if(registerUserTypeCB.getValue().equals("User Type")){
			GuiUtils.checkTextField(registerUserTypeCB, false);
		}
		// success
		else{
			// cannot change the password after press the nest button
			resetRegisterPane(false);
		}
	}

	public void finishRegister(ActionEvent event){
		ageTF.setText((ageTF.getText().equals("")) ? "18" : ageTF.getText());
		heightTF.setText((heightTF.getText().equals("")) ? "170" : heightTF.getText());
		weightTF.setText((weightTF.getText().equals("")) ? "70" : weightTF.getText());
		chestTF.setText((chestTF.getText().equals("")) ? "86.7" : chestTF.getText());
		waistTF.setText((waistTF.getText().equals("")) ? "57.8" : waistTF.getText());
		hipTF.setText((hipTF.getText().equals("")) ? "92.1" : hipTF.getText());

		changeTextFieldColor();

		if(allRightInput()) {
			String[] user = new String[14];
//			0 userid,	1 password,	2 userType,	3 sex,		4 phoneNumber,	5 loginLicense,	6 rechargeAmount,
//			7 resume,	8 age,		9 height,	10 weight,	11 chest,		12 waist,		13 hip;
			user[0] = "";
			user[1] = registerPasswordPF.getText();
			user[2] = registerUserTypeCB.getValue();
			user[3] = sexCB.getValue();
			user[4] = phoneTF.getText();
			user[5] = "true";
			user[6] = "0.00";
			user[7] = resumeTA.getText();
			user[8] = ageTF.getText();
			user[9] = heightTF.getText();
			user[10] = weightTF.getText();
			user[11] = chestTF.getText();
			user[12] = waistTF.getText();
			user[13] = hipTF.getText();

			for (int i = 1; i < 14; i++) {
				if (user[i].equals("")) {
					user[i] = "None";
				}
			}

			backToBeginPane(event);
		}
	}

	public void resetPassword(ActionEvent event){
		resetRegisterPane(true);
	}

	/**
	 *
	 * @param reset
	 * 			ture: reset the register pane to init form;
	 *			false: move the password setting pane and show the basic info pane.
	 */
	private void resetRegisterPane(Boolean reset){
		resetColor();
		registerPasswordPF.setDisable(!reset);
		registerConfirmPasswordPF.setDisable(!reset);
		registerUserTypeCB.setDisable(!reset);
		nextButton.setDisable(!reset);
		if(reset)
			GuiUtils.ChangePosition(passwordSettingPane, -240, 0, 300.0);
		else
			GuiUtils.ChangePosition(passwordSettingPane, 0, -240, 300.0);

		basicInfoPane.setVisible(!reset);
		rulePane.setVisible(reset);
		nextButton.setDisable(!reset);
	}

	public void changeTextFieldColor() {
		GuiUtils.checkTextField(phoneTF, GuiUtils.isNum(phoneTF.getText()));
		GuiUtils.checkTextField(ageTF, GuiUtils.isNum(ageTF.getText()));
		GuiUtils.checkTextField(heightTF, GuiUtils.isNumeric(heightTF.getText()));
		GuiUtils.checkTextField(weightTF, GuiUtils.isNumeric(weightTF.getText()));
		GuiUtils.checkTextField(chestTF, GuiUtils.isNumeric(chestTF.getText()));
		GuiUtils.checkTextField(waistTF, GuiUtils.isNumeric(waistTF.getText()));
		GuiUtils.checkTextField(hipTF, GuiUtils.isNumeric(hipTF.getText()));
	}

	public Boolean allRightInput() {
		if(GuiUtils.isNum(phoneTF.getText())
				&& GuiUtils.isNum(ageTF.getText())
				&& GuiUtils.isNumeric(heightTF.getText())
				&& GuiUtils.isNumeric(weightTF.getText())
				&& GuiUtils.isNumeric(chestTF.getText())
				&& GuiUtils.isNumeric(waistTF.getText())
				&& GuiUtils.isNumeric(hipTF.getText()))
			return true;
		else
			return false;
	}

	/**
	 * reset all nodes' color to white
	 */
	private void resetColor(){
		rule1.setStyle(rule1.getStyle() + noColor);
		rule2.setStyle(rule1.getStyle() + noColor);
		rule3.setStyle(rule1.getStyle() + noColor);

		GuiUtils.checkTextField(registerPasswordPF, true);
		GuiUtils.checkTextField(registerConfirmPasswordPF, true);
		GuiUtils.checkTextField(registerUserTypeCB, true);

		GuiUtils.checkTextField(phoneTF, true);
		GuiUtils.checkTextField(ageTF, true);
		GuiUtils.checkTextField(heightTF, true);
		GuiUtils.checkTextField(weightTF, true);
		GuiUtils.checkTextField(chestTF, true);
		GuiUtils.checkTextField(waistTF, true);
		GuiUtils.checkTextField(hipTF, true);
	}


}
