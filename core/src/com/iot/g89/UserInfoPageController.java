package com.iot.g89;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class UserInfoPageController implements Initializable {
	@FXML
	private Label userIdLabel;
	
	// basic info
	@FXML
	private GridPane infoShowingPane;
	
	@FXML
	private Label sexLabel;
	@FXML
	private Label phoneLabel;
	@FXML
	private Label balanceLabel;
	@FXML
	private Label ageLabel;
	@FXML
	private Label heightLabel;
	@FXML
	private Label weightLabel;
	@FXML
	private Label chestLabel;
	@FXML
	private Label waistLabel;
	@FXML
	private Label hipLabel;
	@FXML
	private Label loginLicenseLabel;
	@FXML
	private TextArea resumeTextArea;
	
	@FXML
	private Button securityInfoEditButton;
	@FXML
	private Button basicInfoEditButton;
	
	
	// user type label
	@FXML
	private Label adminLabel;
	@FXML
	private Label instructorLabel;
	
	// client type label
	@FXML
	private Label clientTypeLabelNormal;
	@FXML
	private Label clientTypeLabelMember;
	@FXML
	private Label clientTypeLabelSupreme;
	
	// basic info edit
	@FXML
	private GridPane infoEditPane;
	
	@FXML
	private ChoiceBox<String> sexCB;
	@FXML
	private TextField phoneTF;
	@FXML
	private Label balanceLabel1;
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
	private Label loginLicenseLabel1;
	
	@FXML
	private Button restoreButton;
	@FXML
	private Button saveButton;
	
	private String resume;

	private GUIDriver driver;
	private Scene thisScene;
	private Scene lastScene;
	
	// test value
	private String userId;
	private String userType = "client";
	private String clientType = "supreme";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		sexCB.getItems().addAll("Unknown", "Male", "Female");
	}
	
	public void initData(String userId, Scene thisScene, Scene lastScene, GUIDriver driver) {
		this.driver = driver;
		this.userId = userId;
		getBasicInof(userId);
		this.thisScene = thisScene;
		this.lastScene = lastScene;
	}
	
	public void getBasicInof(String userId) {
		userIdLabel.setText(userId);
		
		// get basic info
		sexLabel.setText(getSex(userId));
		phoneLabel.setText(getPhone(userId));
		balanceLabel.setText(getrechargeAccount(userId));
		balanceLabel1.setText(getrechargeAccount(userId));
		ageLabel.setText(getAge(userId));
		heightLabel.setText(getHeight(userId));
		weightLabel.setText(getWeight(userId));
		chestLabel.setText(getChest(userId));
		waistLabel.setText(getWaist(userId));
		hipLabel.setText(getHip(userId));
		
		if(getLoginLicense(userId)) {
			loginLicenseLabel.setText("Pass");
			loginLicenseLabel1.setText("Pass");
			loginLicenseLabel.setTextFill(Paint.valueOf("green"));
		}else {
			loginLicenseLabel.setText("Forbid");
			loginLicenseLabel1.setText("Forbid");
			loginLicenseLabel.setTextFill(Paint.valueOf("red"));
		}
		
		resume = getResume(userId);
		resumeTextArea.setText(resume);
		
		// judge user's type
		String userType = getUserType(userId);
		if(userType == "administrator") {
			showLabel(adminLabel);
		}
		else if(userType == "instructor") {
			showLabel(instructorLabel);
		}
		else { // if(userType == "client")
			String clientType = getClientType(userId);
			if(clientType == "normal") {
				showLabel(clientTypeLabelNormal);
			}
			else if(clientType == "member") {
				showLabel(clientTypeLabelMember);
			}
			else { // if(clientType == "supreme")
				showLabel(clientTypeLabelSupreme);
			}
		}
	}
	
	// only show the chosen label
	public void showLabel(Label l) {
		// hide all labels
		adminLabel.setVisible(false);
		instructorLabel.setVisible(false);
		clientTypeLabelNormal.setVisible(false);
		clientTypeLabelMember.setVisible(false);
		clientTypeLabelSupreme.setVisible(false);
		
		// only show the chosen one
		l.setVisible(true);
	}
	
	// restore the original basic info in TextFields
	public void restoreBasicInfo() {
		sexCB.getSelectionModel().select(sexLabel.getText());
		phoneTF.setText(phoneLabel.getText());
		ageTF.setText(ageLabel.getText());
		heightTF.setText(heightLabel.getText());
		weightTF.setText(weightLabel.getText());
		chestTF.setText(chestLabel.getText());
		waistTF.setText(waistLabel.getText());
		hipTF.setText(hipLabel.getText());
		resumeTextArea.setText(resume);
	}
	
	// show the basic info edit pane
	public void showEditPane(Boolean b) {
		infoShowingPane.setVisible(!b);
		securityInfoEditButton.setDisable(b);
		basicInfoEditButton.setVisible(!b);
		
		infoEditPane.setVisible(b);
		saveButton.setVisible(b);
		restoreButton.setVisible(b);
		resumeTextArea.setEditable(b);
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
	
	//---------------------------Button's action function------------------------------------

	// for security info edit button
	public void securityInfoEdit(ActionEvent event){
		SceneTransform.ToUserSecurityEditPage(userId, thisScene);
	}

	// for edit button
	public void basicInfoEdit(ActionEvent event) {
		showEditPane(true);
		restoreBasicInfo();
	}
	
	// for save button
	public void basicInfoSave(ActionEvent event) {
		changeTextFieldColor();
		
		if(allRightInput())	{
			showEditPane(false);
		}
	}
	
	// for restore button
	public void restoreByButton(ActionEvent event) {
		restoreBasicInfo();
		changeTextFieldColor();
	}

	// for back button
	public void backToLastScene(){
		SceneTransform.ToScene(lastScene);
	}
	
	
	
	
	// test function
	private String getUserType(String userId) {
		// TODO Auto-generated method stub
		return userType;
	}

	private String getClientType(String userId) {
		// TODO Auto-generated method stub
		return clientType;
	}

	private String getResume(String userId) {
		// TODO Auto-generated method stub
		return "I am fat!\r\n"
				+ "Yes\r\n"
				+ "I need GYM\r\n"
				+ "I want to lose weight\r\n"
				+ "\r\n"
				+ "Of course,\r\n"
				+ "Female instructor please\r\n"
				+ "\r\n"
				+ "";
	}

	private boolean getLoginLicense(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	private String getHip(String userId) {
		// TODO Auto-generated method stub
		return "87.0";
	}

	private String getWaist(String userId) {
		// TODO Auto-generated method stub
		return "90.0";
	}

	private String getChest(String userId) {
		// TODO Auto-generated method stub
		return "95.0";
	}

	private String getWeight(String userId) {
		// TODO Auto-generated method stub
		return "80.00";
	}

	private String getHeight(String userId) {
		// TODO Auto-generated method stub
		return "180";
	}

	private String getAge(String userId) {
		// TODO Auto-generated method stub
		return "18";
	}

	private String getrechargeAccount(String userId) {
		// TODO Auto-generated method stub
		return "3000.00";
	}

	private String getPhone(String userId) {
		// TODO Auto-generated method stub
		return "18600090821";
	}

	private String getSex(String userId) {
		// TODO Auto-generated method stub
		return "Male";
	}
	
	

}
