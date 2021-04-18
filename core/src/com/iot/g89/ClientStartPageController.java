package com.iot.g89;

import java.net.URL;
import java.util.*;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ClientStartPageController implements Initializable {
	@FXML
	private Label userIdLabel;
	
	@FXML
	private AnchorPane backPane2;
	@FXML
	private AnchorPane backPane3;
	
	// discoverButton and buttons behind it
	@FXML
	private Button discoverButton;
	
	@FXML
	private Button publicCourseButton;
	@FXML
	private Button paidCourseButton;
	@FXML
	private Button historyButton;
	@FXML
	private Button communityButton;
	
	// myCourseButton and buttons behind it
	@FXML
	private Button myCourseButton;
	
	@FXML
	private Button purchasedCourseButton;
	@FXML
	private Button privateCourseButton;
	@FXML
	private Button myInstructorButton;
	@FXML
	private Button favoritesButton;
	
	// init user ID
	private String userId;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void initData(String userId) {
		this.userId = userId;
		userIdLabel.setText(userId);
	}
	
	public void showBackPane2(ActionEvent event) {
		System.out.println("Button Clicked!");
		backPane2.setVisible(true);
		
		backButtonMove(paidCourseButton, "right", "up", 0);
		backButtonMove(communityButton, "right", "down", 1);
		backButtonMove(historyButton, "left", "down", 2);
		backButtonMove(publicCourseButton, "left", "up", 3);
	}
	public void showBackPane3(ActionEvent event) {
		backPane3.setVisible(true);
		
		backButtonMove(privateCourseButton, "right", "up", 0);
		backButtonMove(favoritesButton, "right", "down", 1);
		backButtonMove(myInstructorButton, "left", "down", 2);
		backButtonMove(purchasedCourseButton, "left", "up", 3);
	}
	
	public void closeAllPane(ActionEvent event) {
		System.out.println("out of the pane");
		backPane2.setVisible(false);
		backPane3.setVisible(false);
	}
	
	public void showMyInfo(ActionEvent event) {
		SceneTransform.ToUserInfoPage(userId);
	}
	
	public void backButtonMove(Node n, String direX, String direY, int delay) {
		// add animation to four buttons
		TranslateTransition tt = new TranslateTransition();
		tt.setDuration(Duration.millis(300));
		tt.setNode(n);
		
		//start point
		tt.setFromX(0);
		tt.setFromY(0);
		
		if(direX == "right")	// go right
			tt.setToX(10.0);
		else					// go left
			tt.setToX(-10.0);
		
		if(direY == "up")		// go up
			tt.setToY(-10.0);
		else					// go down
			tt.setToY(10.0);
		
		// go back
		tt.setAutoReverse(true);
		tt.setCycleCount(2);
		
		// set delay
		tt.setDelay(Duration.millis(delay* 150));
		
	/*------------------------------------------------------*/
		// size change
		ScaleTransition st = new ScaleTransition();
		st.setDuration(Duration.millis(300));
		st.setNode(n);
		
		st.setFromX(1);
		st.setFromY(1);
		st.setToX(1.114);
		st.setToY(1.114);
		
		// go back
		st.setAutoReverse(true);
		st.setCycleCount(2);
		
		// set delay
		st.setDelay(Duration.millis(delay* 150));
		
		// play
		tt.play();
		st.play();
	}
}