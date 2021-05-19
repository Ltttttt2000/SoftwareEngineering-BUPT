
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

	protected String userid = "None";
	protected String password = "None";
	protected String userLevel = "Normal";
	protected String sex = "None";
	protected String phoneNumber = "None";
	protected Boolean loginLicense = true;
	protected double rechargeAmount = 0;      //the total money in the account
	protected String resume = "None";  //for instructor
	
	//physicalInfo
	protected int age = 0;
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
	 * @param userid the userid
	 */
	public User(String userid){
		userInfo[0] = this.userid;
		userFilePath = "./core/src/csv/"+ this.getClass().getSimpleName() + ".csv";
		
		fileHeaders[0] = "userid";
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
					if(selectList.get(entry)[0].equals(userid))
						break;
					else
						entry++;
				}
				
			
				//cannot find in file
				if (selectList.size() >= entry + 1 && selectList.size() != 0) {   //old user login-in, read the information into this user from the file

					this.userid = userid;
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

		this.password = parameters[1];
		this.userLevel = parameters[2];
		this.sex = parameters[3];
		this.phoneNumber = parameters[4];
		this.resume = parameters[7];

		this.age = Integer.parseInt(parameters[8]);
		this.height = Double.parseDouble(parameters[9]);
		this.weight = Double.parseDouble(parameters[10]);
		this.chest = Double.parseDouble(parameters[11]);
		this.waist = Double.parseDouble(parameters[12]);
		this.hip = Double.parseDouble(parameters[13]);

	}
	
	 /**
 	 * Sets the password.
 	 *
 	 * @param password, the new password
 	 */
 	public void setPassword(String password) {
		 this.password = password;
		 //userInfo[1] = this.password;
		 String[] attrs = new String[] {"password"};
		 String[] values = new String[] {this.password};
		 FileUtils.updateCSV4(userFilePath, this.userid, attrs, values);
		 
	 }
	 
	 public void setUserLevel(String userLevel) {
		 this.userLevel = userLevel;
		 //userInfo[2] = this.userType;
		 String[] attrs = new String[] {"userLevel"};
		 String[] values = new String[] {this.userLevel};
		 FileUtils.updateCSV4(userFilePath, this.userid, attrs, values);
	 }
	 
	 //新加，记得要在子类新加一下
	 public void setSex(String sex) {
		 this.sex = sex;
		 //userInfo[3] = this.sex;
		 String[] attrs = new String[] {"sex"};
		 String[] values = new String[] {this.sex};
		 FileUtils.updateCSV4(userFilePath, this.userid, attrs, values);
		 
	 }

	public void setPhone(String phone) {
		 this.phoneNumber = phone;
		 //userInfo[4] = this.phoneNumber;
		 String[] attrs = new String[] {"phoneNumber"};
		 String[] values = new String[] {this.phoneNumber};
		 FileUtils.updateCSV4(userFilePath, this.userid, attrs, values);
		 
	 }

	public void setResume(String resume) {
		 this.resume = resume;
		 //userInfo[7] = this.resume; 
		 String[] attrs = new String[] {"resume"};
		 String[] values = new String[] {this.resume};
		 FileUtils.updateCSV4(userFilePath, this.userid, attrs, values);
	 }
	 
	 
	 //	TODO !!! useless??
/*	 void setPhysicalInfo(PhysicalInfo physicalInfo) {

	 }
 */
	 /**
 	 * Ban this account.
 	 */
	 public void banThisAccount() {
		 this.loginLicense = false;
		 String[] attrs = new String[] {"loginLicense"};
		 String[] values = new String[] {String.valueOf(this.loginLicense)};
		 FileUtils.updateCSV4(userFilePath, this.userid, attrs, values);
	 }
	 
	 /**
 	 * Unban this account.
 	 */
	 public void unbanThisAccount() {
		 this.loginLicense = true;
		 String[] attrs = new String[] {"loginLicense"};
		 String[] values = new String[] {String.valueOf(this.loginLicense)};
		 FileUtils.updateCSV4(userFilePath, this.userid, attrs, values);
	 }

	public String getId() {
		return this.userid;
		 
	 }
	public String getPassword() {
		return this.password;
		 
	 }
	public String getUserLevel(){
		return this.userLevel;
		 
	 }
	public String getPhone() {
		return this.phoneNumber;
	 }
	public double getRechargeAccount(){
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

	public int getInstructorMoney(){return 0;}

	public void setAge(int age) {
		this.age = age;
		//userPhysical[1] = Integer.toString(this.age);
		 String[] attrs = new String[] {"age"};
		 String[] values = new String[] {String.valueOf(this.age)};
		 FileUtils.updateCSV4(userFilePath, this.userid, attrs, values);
		
	}

	public void setHeight(double height) {
		 this.height = height;
		 //userPhysical[2] = Double.toString(this.height);
		 String[] attrs = new String[] {"height"};
		 String[] values = new String[] {String.valueOf(this.height)};
		 FileUtils.updateCSV4(userFilePath, this.userid, attrs, values);
	 }

	public void setWeight(double weight) {
		 this.weight = weight;
		 //userPhysical[3] = Double.toString(this.weight);
		 String[] attrs = new String[] {"weight"};
		 String[] values = new String[] {String.valueOf(this.weight)};
		 FileUtils.updateCSV4(userFilePath,  this.userid, attrs, values);
	 }


	public void setChest(double chest) {
		 this.chest = chest; 
		 //userPhysical[4] = Double.toString(this.chest);
		 String[] attrs = new String[] {"chest"};
		 String[] values = new String[] {String.valueOf(this.chest)};
		 FileUtils.updateCSV4(userFilePath, this.userid, attrs, values);
			
	 }

	public void setWaist(double waist) {
		 this.waist = waist;
		 //userPhysical[5] = Double.toString(this.waist);
		 String[] attrs = new String[] {"waist"};
		 String[] values = new String[] {String.valueOf(this.waist)};
		 FileUtils.updateCSV4(userFilePath, this.userid, attrs, values);
			
	 }

	public void setHip(double hip) {
		 this.hip = hip;
		 //userPhysical[6] = Double.toString(this.hip);
		 String[] attrs = new String[] {"hip"};
		 String[] values = new String[] {String.valueOf(this.hip)};
		 FileUtils.updateCSV4(userFilePath, this.userid, attrs, values);
	 }
	 

	public boolean passwordCheck(String password){
 		return this.getPassword().equals(password);
	 }

	 /*
	 void insertToCSV(){

		userInfoList = new ArrayList<String[]>();
		userInfoList.add(userInfo);
		FileUtils.insertCSV(userFilePath, userInfoList);
	 }
     */

	/**
	 * Prints the user physical info.
	 */
	public String toString() {
		return "age:"+ this.getAge() +"\n"+
				"height"+ this.getHeight() +"\n"+
				"weight"+ this.getWeight() +"\n"+
				"chest"+ this.getChest() +"\n"+
				"waist"+ this.getWaist() +"\n"+
				"hip"+ this.getHip() +"\n";
	}


	//TODO !!!useless??
	/* PhysicalInfo getUserPhysicalInfo() {
		 physicalInfo = new PhysicalInfo(this.userid);
		 return this.physicalInfo;
		
	 }*/
 	
}
