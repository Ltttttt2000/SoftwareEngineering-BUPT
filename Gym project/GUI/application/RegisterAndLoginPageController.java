package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
		// TODO Auto-generated method stub
		
	}
	
	// when you move your mouse on the login part
	public void ShowLoginPageMove(MouseEvent event) {
		int delay = 150;				// background must be slower to catch the button
		if(lmpX == 0) {delay = 0;}		// no need
		
		ChangePosition(loginMovePane, lmpX, 0, 0, 150, "fastToSlow");
		ChangePosition(registerMovePane, rmpX, 192, delay, 150, "slowToFast");
		
		ChangePosition(loginReshapePane, lrpX, 96);
		ChangePosition(registerReshapePane, rrpX, 96);
		
		ChangeShape(loginReshapePane, lrpSize, 1.5);
		ChangeShape(registerReshapePane, rrpSize, 0.5);
		
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
		if(rmpX == 0) {delay = 0;}		// no need
		
		ChangePosition(loginMovePane, lmpX, -192, delay, 150, "slowToFast");
		ChangePosition(registerMovePane, rmpX, 0, 0, 150, "fastToSlow");
		
		ChangePosition(loginReshapePane, lrpX, -96);
		ChangePosition(registerReshapePane, rrpX, -96);
		
		ChangeShape(loginReshapePane, lrpSize, 0.5);
		ChangeShape(registerReshapePane, rrpSize, 1.5);
		
		lmpX = -192;
		rmpX = 0;
		lrpSize = 0.5;
		rrpSize = 1.5;
		lrpX = -96;
		rrpX = -96;
	}
	
	// when you click your mouse on the login part
	public void ShowLoginPageClick(ActionEvent event) {
		int delay = 150;				// background must be slower to catch the button
		if(lmpX == 0) {delay = 0;}		// no need
		
		ChangePosition(loginMovePane, lmpX, 0, 0, 150, "fastToSlow");
		ChangePosition(registerMovePane, rmpX, 384, delay, 150, "slowToFast");
		
		ChangePosition(loginReshapePane, lrpX, 192);
		ChangePosition(registerReshapePane, rrpX, 192);
		
		ChangeShape(loginReshapePane, lrpSize, 2);
		ChangeShape(registerReshapePane, rrpSize, 0);
		
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
		int delay = 151;				// background must be slower to catch the button
		if(rmpX == 0) {delay = 0;}		// no need
		
		ChangePosition(loginMovePane, lmpX, -384, delay, 150, "slowToFast");
		ChangePosition(registerMovePane, rmpX, 0, 0, 150, "fastToSlow");
		
		ChangePosition(loginReshapePane, lrpX, -192);
		ChangePosition(registerReshapePane, rrpX, -192);
		
		ChangeShape(loginReshapePane, lrpSize, 0);
		ChangeShape(registerReshapePane, rrpSize, 2);
		
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
		ChangePosition(loginMovePane, lmpX, 0);
		ChangePosition(registerMovePane, rmpX, 0);
		
		ChangePosition(loginReshapePane, lrpX, 0);
		ChangePosition(registerReshapePane, rrpX, 0);
		
		ChangeShape(loginReshapePane, lrpSize, 1);
		ChangeShape(registerReshapePane, rrpSize, 1);
		
		lmpX = 0;
		rmpX = 0;
		lrpSize = 1;
		rrpSize = 1;
		lrpX = 0;
		rrpX = 0;
	}
	
	public void ChangePosition(Node n, double xStart, double xEnd) {
		ChangePosition(n, xStart, xEnd, 0, 300, "normal");
	}
	
	public void ChangePosition(Node n, double xStart, double xEnd, double time) {ChangePosition(n, xStart, xEnd, 0, time, "normal");}
	
	public void ChangePosition(Node n, double xStart, double xEnd, int delay) {ChangePosition(n, xStart, xEnd, delay, 300, "normal");}
	
	public void ChangePosition(Node n, double xStart, double xEnd, String speed) {ChangePosition(n, xStart, xEnd, 0, 300, speed);}
	
	public void ChangePosition(Node n, double xStart, double xEnd, int delay, double time, String speed) {
		if(speed == "normal")
			ChangePosition(n, xStart, xEnd, delay, time, 0.5, 0, 0.5, 1);
		else if(speed == "fastToSlow")
			ChangePosition(n, xStart, xEnd, delay, time, 0.5, 0, 1, 1);
		else if(speed == "slowToFast")
			ChangePosition(n, xStart, xEnd, delay, time, 0, 0, 0.5, 1);
	}
	
	public void ChangePosition(Node n, double xStart, double xEnd, int delay, double time, double s1, double s2, double s3, double s4) {
		// reshape
		TranslateTransition tt = new TranslateTransition();
			
		tt.setDuration(Duration.millis(time));
		tt.setNode(n);
		
		tt.setFromX(xStart);
		tt.setToX(xEnd);
		if(delay != 0)
			tt.setDelay(Duration.millis(delay));
		
		tt.setInterpolator(Interpolator.SPLINE(s1, s2, s3, s4));
		
		// play
		tt.play();
	}
	public void ChangeShape(Node n,double sizeStart, double sizeEnd){
		// reshape
		ScaleTransition st = new ScaleTransition();
		
		st.setDuration(Duration.millis(300));
		st.setNode(n);
		
		st.setFromX(sizeStart);
		st.setToX(sizeEnd);
		
		st.setInterpolator(Interpolator.SPLINE(0.5, 0, 0.5, 1));
		
		// play
		st.play();
	}
	
	
	/*
	 * login page function
	 * */
	
	public void MouseEnteredLogin(MouseEvent event) {
		ChangePosition(keyImg, 0, 142, 0);
	}
	public void MouseExitLogin(MouseEvent event) {
		ChangePosition(keyImg, 142, 0, 0);
	}
	
	public void backToBeginPane(ActionEvent event) {
		loginPane.setVisible(false);
	}
	
	public void loginSuccess(ActionEvent event) {
		
	}

}
