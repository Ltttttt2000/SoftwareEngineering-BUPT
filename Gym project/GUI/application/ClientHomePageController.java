package application;

import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public class ClientHomePageController implements Initializable {
	@FXML
	private AnchorPane headPane;
	@FXML
	private TextField userIDTF;
	@FXML
	private ToggleButton homePageButton;
	@FXML
	private ToggleButton myInfoButton;
	@FXML
	private ToggleButton storeButton;
	@FXML
	private ToggleButton myCourseButton;
	
	@FXML
	private AnchorPane homePagePane;
	@FXML
	private Button publicCoursesButton;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void showHomePagePane(ActionEvent event) {
		System.out.println("Button Clicked!");
		if(homePageButton.isSelected())
			homePagePane.setVisible(true);
		else
			homePagePane.setVisible(false);
	}
	
}
