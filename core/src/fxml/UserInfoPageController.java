package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import com.iot.g89.GUIDriver;
import com.iot.g89.GUIUtils;
import com.iot.g89.SceneTransform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class UserInfoPageController implements Initializable {
	@FXML
	private Button backButton;

	@FXML
	private Label userIdLabel;
	
	// basic info
	@FXML
	private GridPane infoShowingPane;
	@FXML
	private BorderPane priceTagPane;

	@FXML
	private Label priceTagLabel;
	
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
	private TextArea resumeTA;
	
	@FXML
	private Button securityInfoEditButton;
	@FXML
	private Button basicInfoEditButton;
	
	
	// user type label
	@FXML
	private Label adminLabel;
	@FXML
	private Label instructorTypeLabelNormal;
	@FXML
	private Label instructorTypeLabelJunior;
	@FXML
	private Label instructorTypeLabelSenior;


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

	@FXML
	private Button payButton;
	
	private String resume;

	private GUIDriver driver;
	private Scene thisScene;
	private Scene lastScene;

	private String userId;
	
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
		if(userId.equals(driver.getUserId()) || driver.getUsertype().equals("Administrator")) {
			basicInfoEditButton.setDisable(false);
			securityInfoEditButton.setDisable(false);
			payButton.setVisible(false);
		}
		else{
			basicInfoEditButton.setDisable(true);
			securityInfoEditButton.setDisable(true);
			if(driver.getUsertype(userId).equals("Instructor") && driver.getUsertype().equals("Client"))
				payButton.setVisible(true);
		}
	}
	
	private void getBasicInof(String userId) {
		userIdLabel.setText(userId);
		
		// get basic info
		sexLabel.setText(driver.getSex(userId));
		phoneLabel.setText(driver.getPhone(userId));
		balanceLabel.setText(driver.getRechargeAccount(userId));
		balanceLabel1.setText(driver.getRechargeAccount(userId));
		ageLabel.setText(driver.getAge(userId));
		heightLabel.setText(driver.getHeight(userId));
		weightLabel.setText(driver.getWeight(userId));
		chestLabel.setText(driver.getChest(userId));
		waistLabel.setText(driver.getWaist(userId));
		hipLabel.setText(driver.getHip(userId));
		
		if(driver.getLoginLicense(userId).equals("true")) {
			loginLicenseLabel.setText("Pass");
			loginLicenseLabel1.setText("Pass");
			loginLicenseLabel.setTextFill(Paint.valueOf("green"));
		}else {
			loginLicenseLabel.setText("Forbid");
			loginLicenseLabel1.setText("Forbid");
			loginLicenseLabel.setTextFill(Paint.valueOf("red"));
		}
		
		resume = driver.getResume(userId);
		resumeTA.setText(resume);

		priceTagPane.setVisible(false);
		// judge user's type
		String userType = driver.getUsertype(userId);
		if(userType.equals("Administrator")) {
			showLabel(adminLabel);
		}
		else if(userType.equals("Instructor")) {
			String instructorType = driver.getUserLevel(userId);
			if(instructorType.equals("Normal")) {
				showLabel(instructorTypeLabelNormal);
			}
			else if(instructorType.equals("Junior")) {
				showLabel(instructorTypeLabelJunior);
			}
			else { // if(instructorType.equals("Senior"))
				showLabel(instructorTypeLabelSenior);
			}

			priceTagPane.setVisible(true);
			priceTagLabel.setText(driver.getInstructorMoney(userId));
		}
		else { // if(userType.equals("client"))
			String clientType = driver.getUserLevel(userId);
			if(clientType.equals("Normal")) {
				showLabel(clientTypeLabelNormal);
			}
			else if(clientType.equals("Member")) {
				showLabel(clientTypeLabelMember);
			}
			else { // if(clientType.equals("SupremeMember"))
				showLabel(clientTypeLabelSupreme);
			}
		}
	}
	
	// only show the chosen label
	private void showLabel(Label l) {
		// hide all labels
		adminLabel.setVisible(false);
		instructorTypeLabelNormal.setVisible(false);
		instructorTypeLabelJunior.setVisible(false);
		instructorTypeLabelSenior.setVisible(false);
		clientTypeLabelNormal.setVisible(false);
		clientTypeLabelMember.setVisible(false);
		clientTypeLabelSupreme.setVisible(false);
		
		// only show the chosen one
		l.setVisible(true);
	}
	
	// restore the original basic info in TextFields
	private void restoreBasicInfo() {
		sexCB.getSelectionModel().select(sexLabel.getText());
		phoneTF.setText(phoneLabel.getText());
		ageTF.setText(ageLabel.getText());
		heightTF.setText(heightLabel.getText());
		weightTF.setText(weightLabel.getText());
		chestTF.setText(chestLabel.getText());
		waistTF.setText(waistLabel.getText());
		hipTF.setText(hipLabel.getText());
		resumeTA.setText(resume);
	}
	
	// show the basic info edit pane
	private void showEditPane(Boolean b) {
		infoShowingPane.setVisible(!b);
		securityInfoEditButton.setDisable(b);
		backButton.setDisable(b);
		basicInfoEditButton.setVisible(!b);
		
		infoEditPane.setVisible(b);
		saveButton.setVisible(b);
		restoreButton.setVisible(b);
		resumeTA.setEditable(b);
	}
	
	private void changeTextFieldColor() {
		GUIUtils.checkTextField(phoneTF, GUIUtils.isNum(phoneTF.getText()));
		GUIUtils.checkTextField(ageTF, GUIUtils.isNum(ageTF.getText()));
		GUIUtils.checkTextField(heightTF, GUIUtils.isNumeric(heightTF.getText()));
		GUIUtils.checkTextField(weightTF, GUIUtils.isNumeric(weightTF.getText()));
		GUIUtils.checkTextField(chestTF, GUIUtils.isNumeric(chestTF.getText()));
		GUIUtils.checkTextField(waistTF, GUIUtils.isNumeric(waistTF.getText()));
		GUIUtils.checkTextField(hipTF, GUIUtils.isNumeric(hipTF.getText()));
	}
	
	private Boolean allRightInput() {
		if(GUIUtils.isNum(phoneTF.getText())
				&& GUIUtils.isNum(ageTF.getText())
				&& GUIUtils.isNumeric(heightTF.getText())
				&& GUIUtils.isNumeric(weightTF.getText())
				&& GUIUtils.isNumeric(chestTF.getText())
				&& GUIUtils.isNumeric(waistTF.getText())
				&& GUIUtils.isNumeric(hipTF.getText()))
			return true;
		else
			return false;
	}

	private String[] gatherInfo(){
		String[] user = new String[14];
		user[0] = "";
		user[1] = "";
		user[2] = "";
		user[3] = sexCB.getValue();
		user[4] = phoneTF.getText();
		user[5] = "";
		user[6] = "";
		user[7] = resumeTA.getText();
		user[8] = ageTF.getText();
		user[9] = heightTF.getText();
		user[10] = weightTF.getText();
		user[11] = chestTF.getText();
		user[12] = waistTF.getText();
		user[13] = hipTF.getText();
		return user;
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
			String[] user = gatherInfo();

			driver.changeBasicInfo(user, userId);

			getBasicInof(userId);
			showEditPane(false);
		}
	}
	
	// for restore button
	public void restoreByButton(ActionEvent event) {
		restoreBasicInfo();
		changeTextFieldColor();
	}

	// for purchase
	public void buy(ActionEvent event) {
//		driver.purchaseorR...(userId);
	}

	// for back button
	public void backToLastScene(){
		SceneTransform.ToScene(lastScene);
	}

}
