
package com.iot.g89;
/**
 * 
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author DongWanqi
 *
 */
public class User{

	protected String userId = "None";
	//private final SimpleStringProperty Userid;
	protected String password = "None";
	//private final SimpleStringProperty Password;
	protected String userLevel = "Normal";
	protected String sex = "None";
	//private final SimpleStringProperty Sex;
	protected String phoneNumber = "None";
	//private final SimpleStringProperty PhoneNumber;
	protected Boolean loginLicense = true;
	protected double rechargeAmount = 0;      //the total money in the account
	//private final SimpleDoubleProperty RechargeAmount;
	protected String resume = "None";  //for instructor
	
	//physicalInfo
	protected int age = 0;
	//private final SimpleIntegerProperty Age;
	protected double height = 0;
	protected double weight = 0;
	protected double chest = 0;
	protected double waist = 0;
	protected double hip = 0;

	protected String userFilePath;
	protected int entry = 0;
	
	String[] fileHeaders = new String[14];
	
	ArrayList<String[]> userInfoList;
	
	//initial value written in the file
	                              //userid, password, userType, sex, phoneNo,loginLic,rechargeMoney,resume, 
	String[] userInfo = new String[] {"None", "None", "Normal", "None","None","TRUE", "0", "None",   
			                          "0","0","0","0","0","0"}; //6 physical Info,the last is InstructorMoney
	
	//the arrayList content read from the file
	ArrayList<String[]> selectList = new ArrayList<String[]>();
	String[] readAll = {"*"}; // 为了给readCSV传入一个读全部的 *
 	
//	PhysicalInfo physicalInfo;
	
	/**
	 * Instantiates a new user.
	 *
	 * @param userId the userid
	 */
	public User(String userId){
		userInfo[0] = this.userId;
//		this.Userid = new SimpleStringProperty(userid);
//		this.Password = new SimpleStringProperty("None");
//		this.Sex = new SimpleStringProperty("None");
//		this.PhoneNumber = new SimpleStringProperty("None");
//		this.RechargeAmount = new SimpleDoubleProperty(0);
//		this.Age = new SimpleIntegerProperty(0);
		userFilePath = "./core/src/csv/"+ this.getClass().getSimpleName() + ".csv";
		
		fileHeaders[0] = "userId";
		fileHeaders[1] = "password";
		fileHeaders[2] = "userLevel";
		fileHeaders[3] = "sex";
		fileHeaders[4] = "phoneNumber";
		fileHeaders[5] = "loginLicense";
		fileHeaders[6] = "rechargeAmount";
		fileHeaders[7] = "resume";
		
			//phisicalInfo
			fileHeaders[8] = "age";
			fileHeaders[9] = "height"; 
			fileHeaders[10] = "weight";
			fileHeaders[11] = "chest"; 
			fileHeaders[12] = "waist"; 
			fileHeaders[13] = "hip";
			
		
	
		if(this.getClass().getSimpleName().equals("Instructor")) {
			fileHeaders = Arrays.copyOf(fileHeaders, fileHeaders.length + 1);
			fileHeaders[14] = "instructorMoney";
			userInfo = Arrays.copyOf(userInfo, userInfo.length + 1);
			userInfo[14] = "0";
		}
			
		
		File check = new File(userFilePath);
		//file not exist
		if(!check.exists()) {
			FileUtils.createCSV(userFilePath, fileHeaders);
			userInfoList = new ArrayList<String[]>();
			userInfoList.add(userInfo);
			FileUtils.insertCSV(userFilePath, userInfoList);
		}
		else { //file exists
			//read all the content in the file
			
			selectList = FileUtils.readCSV(userFilePath, readAll);
			
			//find the entry of this user in the file
			if (selectList.size() == 0) {
				//System.out.println("File is empty!");
			}
			else {
				
				while(entry+1<selectList.size() ||entry+1==selectList.size()) {
					if(selectList.get(entry)[0].equals(userId))
						break;
					else
						entry++;
				}
				
			
				//cannot find in file
				if (selectList.size() >= entry + 1 && selectList.size() != 0) {   //old user login-in, read the information into this user from the file

					this.userId = userId;
					this.password = selectList.get(entry)[1];
					//userInfo[1] = this.password;
					this.userLevel = selectList.get(entry)[2];
					//userInfo[2] = this.userType;
					this.sex = selectList.get(entry)[3];
					//userInfo[3] = this.sex;
					this.phoneNumber = selectList.get(entry)[4];
					//userInfo[4] = this.phoneNumber;
					this.loginLicense = Boolean.valueOf(selectList.get(entry)[5]);
					//userInfo[5] = String.valueOf(this.loginLicense);
					this.rechargeAmount = Double.parseDouble(selectList.get(entry)[6]);
					//userInfo[6] = String.valueOf(this.rechargeAmount);
					this.resume = selectList.get(entry)[7];
					//userInfo[7] = this.resume;

					this.age = Integer.parseInt(selectList.get(entry)[8]);
					//userPhysical[1] = Integer.toString(this.age);
					this.height = Double.parseDouble(selectList.get(entry)[9]);
					//userPhysical[2] = Double.toString(this.height);
					this.weight = Double.parseDouble(selectList.get(entry)[10]);
					//userPhysical[3] = Double.toString(this.weight);
					this.chest = Double.parseDouble(selectList.get(entry)[11]);
					//userPhysical[4] = Double.toString(this.chest);
					this.waist = Double.parseDouble(selectList.get(entry)[12]);
					//userPhysical[5] = Double.toString(this.waist);
					this.hip = Double.parseDouble(selectList.get(entry)[13]);
					//userPhysical[6] = Double.toString(this.hip);

				}
			}
		}
	}

	public User(String[] parameters){

		userFilePath = "./core/src/csv/"+ this.getClass().getSimpleName() + ".csv";

		this.userId = parameters[0];
		this.password = parameters[1];
		this.userLevel = parameters[2];
		this.sex = parameters[3];
		this.phoneNumber = parameters[4];
		this.loginLicense = Boolean.valueOf(parameters[5]);
		this.rechargeAmount = Double.parseDouble(parameters[6]);
		this.resume = parameters[7];

//		this.Userid = new SimpleStringProperty(parameters[0]);
//		this.Password = new SimpleStringProperty(parameters[1]);
//		this.Sex = new SimpleStringProperty(parameters[3]);
//		this.PhoneNumber = new SimpleStringProperty(parameters[4]);


		this.age = Integer.parseInt(parameters[8]);
		this.height = Double.parseDouble(parameters[9]);
		this.weight = Double.parseDouble(parameters[10]);
		this.chest = Double.parseDouble(parameters[11]);
		this.waist = Double.parseDouble(parameters[12]);
		this.hip = Double.parseDouble(parameters[13]);

//		this.RechargeAmount = new SimpleDoubleProperty(Double.parseDouble(parameters[6]));
//		this.Age = new SimpleIntegerProperty(Integer.parseInt(parameters[8]));

	}

	 /**
 	 * Ban this account.
 	 */
	 public void banThisAccount() {
		 this.loginLicense = false;
		 String[] attrs = new String[] {"loginLicense"};
		 String[] values = new String[] {String.valueOf(this.loginLicense)};
		 FileUtils.updateCSV4(userFilePath, this.userId, attrs, values);
	 }
	 
	 /**
 	 * Unban this account.
 	 */
	 public void unbanThisAccount() {
		 this.loginLicense = true;
		 String[] attrs = new String[] {"loginLicense"};
		 String[] values = new String[] {String.valueOf(this.loginLicense)};
		 FileUtils.updateCSV4(userFilePath, this.userId, attrs, values);
	 }



//	public SimpleStringProperty useridProperty() {
//		return Userid;
//	}

//	public SimpleStringProperty passwordProperty() {
//		return Password;
//	}



//	public SimpleStringProperty sexProperty() {
//		return Sex;
//	}



//	public SimpleStringProperty phoneNumberProperty() {
//		return PhoneNumber;
//	}



//	public SimpleDoubleProperty rechargeAmountProperty() {
//		return RechargeAmount;
//	}

//	public SimpleIntegerProperty ageProperty() {
//		return Age;
//	}


	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return this.password;
		 
	 }
	public String getUserLevel(){
		return this.userLevel;
		 
	 }
	public String getPhoneNumber() {
		return this.phoneNumber;
	 }
	public double getRechargeAmount(){
 		return this.rechargeAmount;
	 }
	public Boolean getLoginLicense(){
		return this.loginLicense ;
	 }
	public String getSex() {
    	 return this.sex;
     }

	public int getAge() {
 		return this.age;
 	}

	public double getHeight() {
 		return this.height; 
 	}

	public double getWeight() {
 		return this.weight;
 	}

	public double getChest() {
 		return this.chest;
 	}

	public double getWaist() {
 		return this.waist;
 	}

	public double getHip() {
 		return this.hip;
 	}

	public String getResume(){return this.resume;}

	public double getInstructorMoney(){return 0;}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setLoginLicense(Boolean loginLicense) {
		this.loginLicense = loginLicense;
	}

	public void setRechargeAmount(double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setChest(double chest) {
		this.chest = chest;
	}

	public void setWaist(double waist) {
		this.waist = waist;
	}

	public void setHip(double hip) {
		this.hip = hip;
	}

	public void setAndPushPassword(String password) {
		this.password = password;
		//userInfo[1] = this.password;
		String[] attrs = new String[] {"password"};
		String[] values = new String[] {this.password};
		FileUtils.updateCSV4(userFilePath, this.userId, attrs, values);

	}

	public void setAndPushUserLevel(String userLevel) {
		this.userLevel = userLevel;
		//userInfo[2] = this.userType;
		String[] attrs = new String[] {"userLevel"};
		String[] values = new String[] {this.userLevel};
		FileUtils.updateCSV4(userFilePath, this.userId, attrs, values);
	}

	//新加，记得要在子类新加一下
	public void setAndPushSex(String sex) {
		this.sex = sex;
		//userInfo[3] = this.sex;
		String[] attrs = new String[] {"sex"};
		String[] values = new String[] {this.sex};
		FileUtils.updateCSV4(userFilePath, this.userId, attrs, values);

	}

	public void setAndPushPhone(String phone) {
		this.phoneNumber = phone;
		//userInfo[4] = this.phoneNumber;
		String[] attrs = new String[] {"phoneNumber"};
		String[] values = new String[] {this.phoneNumber};
		FileUtils.updateCSV4(userFilePath, this.userId, attrs, values);

	}

	public void setAndPushResume(String resume) {
		this.resume = resume;
		//userInfo[7] = this.resume;
		String[] attrs = new String[] {"resume"};
		String[] values = new String[] {this.resume};
		FileUtils.updateCSV4(userFilePath, this.userId, attrs, values);
	}

	public void setAndPushRechargeAmount(double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
		String[] attrs = new String[] {"rechargeAmount"};
		String[] values = new String[] {String.format("%.2f",this.rechargeAmount)};
		FileUtils.updateCSV4(userFilePath, this.userId, attrs, values);
	}

	public void setAndPushAge(int age) {
		this.age = age;
		//userPhysical[1] = Integer.toString(this.age);
		 String[] attrs = new String[] {"age"};
		 String[] values = new String[] {String.valueOf(this.age)};
		 FileUtils.updateCSV4(userFilePath, this.userId, attrs, values);
		
	}

	public void setAndPushHeight(double height) {
		 this.height = height;
		 //userPhysical[2] = Double.toString(this.height);
		 String[] attrs = new String[] {"height"};
		 String[] values = new String[] {String.valueOf(this.height)};
		 FileUtils.updateCSV4(userFilePath, this.userId, attrs, values);
	 }

	public void setAndPushWeight(double weight) {
		 this.weight = weight;
		 //userPhysical[3] = Double.toString(this.weight);
		 String[] attrs = new String[] {"weight"};
		 String[] values = new String[] {String.valueOf(this.weight)};
		 FileUtils.updateCSV4(userFilePath,  this.userId, attrs, values);
	 }


	public void setAndPushChest(double chest) {
		 this.chest = chest; 
		 //userPhysical[4] = Double.toString(this.chest);
		 String[] attrs = new String[] {"chest"};
		 String[] values = new String[] {String.valueOf(this.chest)};
		 FileUtils.updateCSV4(userFilePath, this.userId, attrs, values);
			
	 }

	public void setAndPushWaist(double waist) {
		 this.waist = waist;
		 //userPhysical[5] = Double.toString(this.waist);
		 String[] attrs = new String[] {"waist"};
		 String[] values = new String[] {String.valueOf(this.waist)};
		 FileUtils.updateCSV4(userFilePath, this.userId, attrs, values);
			
	 }

	public void setAndPushHip(double hip) {
		 this.hip = hip;
		 //userPhysical[6] = Double.toString(this.hip);
		 String[] attrs = new String[] {"hip"};
		 String[] values = new String[] {String.valueOf(this.hip)};
		 FileUtils.updateCSV4(userFilePath, this.userId, attrs, values);
	 }
	 

	public boolean passwordCheck(String password){
 		return this.getPassword().equals(password);
	 }

	public String toString() {
		return "id\t\t" + this.getUserId() + "\n" +
				"level\t" + this.getUserLevel() + "\n" +
				"sex\t\t" + this.getSex() + "\n";
	}
}
