package com.iot.g89;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class GuiUtils {

	//---------------------------------------input check---------------------------------------------

	// if the inputs are all number with at most one "." among
	public static Boolean isNumeric(String str) {
		return str.matches("[0-9]+.?[0-9]*");
	}
	
	//  if the inputs are all number (phoneNumber)
	public static Boolean isNum(String str) {
		return str.matches("[0-9]*");
	}
	
	// if the input is false then change the color of the TextField
	public static Boolean checkTextField (Node tf, Boolean b) {
		String wrongStyle = "-fx-background-color: #ffa1a1;";
		String rightStyle = "-fx-background-color: white;";
		
		if(b)
			tf.setStyle(tf.getStyle() + rightStyle);
		else
			tf.setStyle(tf.getStyle() + wrongStyle);
		
		return b;
	}

	//-----------------------------animations-----------------------------------------------

	// change node's position
	public static void ChangePosition(Node n, double xStart, double xEnd) {
		ChangePosition(n, xStart, xEnd, 0, 300, "normal");
	}
	public static void ChangePosition(Node n, double xStart, double xEnd, double time) {
		ChangePosition(n, xStart, xEnd, 0, time, "normal");
	}
	public static void ChangePosition(Node n, double xStart, double xEnd, int delay) {
		ChangePosition(n, xStart, xEnd, delay, 300, "normal");
	}
	public static void ChangePosition(Node n, double xStart, double xEnd, String speed) {
		ChangePosition(n, xStart, xEnd, 0, 300, speed);
	}
	public static void ChangePosition(Node n, double xStart, double xEnd, int delay, double time, String speed) {
		if(speed == "normal")
			ChangePosition(n, xStart, xEnd, delay, time, 0.5, 0, 0.5, 1);
		else if(speed == "fastToSlow")
			ChangePosition(n, xStart, xEnd, delay, time, 0.5, 0, 1, 1);
		else if(speed == "slowToFast")
			ChangePosition(n, xStart, xEnd, delay, time, 0, 0, 0.5, 1);
	}
	public static void ChangePosition(Node n, double xStart, double xEnd, int delay, double time, double s1, double s2, double s3, double s4) {
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
	
	// change node's size
	public static void ChangeSize(Node n,double sizeStart, double sizeEnd) {
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
}
