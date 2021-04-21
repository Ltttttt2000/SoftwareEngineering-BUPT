package com.iot.g89;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

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
	
	@FXML
	private ImageView keyImg;
	
	@FXML
	private TextField userIdTF;
	@FXML
	private PasswordField userPasswordPF;
	@FXML
	private ChoiceBox<String> typeCB;

	private GUIDriver driver;
	private Scene thisScene;
	
	// current buttons' size
	private double lrpSize = 1;
	private double rrpSize = 1;
	
	// current buttons'position
	private double lrpX = 0;
	private double rrpX = 0;
	
	//current backgrounds' position
	private double lmpX = 0;
	private double rmpX = 0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		typeCB.getItems().addAll("Administrator", "Instructor", "Client");
	}

	public void initData(Scene thisScene, GUIDriver driver){
		this.driver = driver;
		this.thisScene = thisScene;
	}
	
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
	
	/*
	 * login page function
	 * */
	
	public void MouseEnteredLogin(MouseEvent event) {
		GuiUtils.ChangePosition(keyImg, 0, 142, 0);
	}
	public void MouseExitLogin(MouseEvent event) {
		GuiUtils.ChangePosition(keyImg, 142, 0, 0);
	}
	
	public void backToBeginPane(ActionEvent event) {
		loginPane.setVisible(false);
		registerPane.setVisible(false);
	}
	
	public void loginSuccess(ActionEvent event) {
		String userId = userIdTF.getText();
		String password = userPasswordPF.getText();
		String type = typeCB.getValue();

		int x = driver.login(userId, password, type);

		GuiUtils.checkTextField(userIdTF, true);
		GuiUtils.checkTextField(userPasswordPF, true);

		if(x == 1)
			SceneTransform.ToClientStartPage(userId, thisScene);
		else if (x == -1)
			GuiUtils.checkTextField(userIdTF, false);
		else
			GuiUtils.checkTextField(userPasswordPF, false);

	}

}
