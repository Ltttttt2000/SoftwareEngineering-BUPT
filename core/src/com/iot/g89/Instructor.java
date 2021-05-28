/*
 * 
 */
package com.iot.g89;

import java.util.ArrayList;


/**
 * The Class Instructor.
 */
public class Instructor extends User{
	
	 /** The instructor money. */
 	public double instructorMoney = 0;
	 
	 /**
 	 * Instantiates a new instructor.
 	 *
 	 * @param userId the userId
 	 */
 	public Instructor(String userId) {
		super(userId);
		this.instructorMoney = Double.parseDouble(selectList.get(entry)[14]);
	}

	public Instructor(String[] para) {
 		super(para);
		this.instructorMoney = Double.parseDouble(para[14]);
	}
	 
	 /**
 	 * Sets the resume.
 	 *
 	 * @param resume the new resume
 	 */
	 public void setAndPushResume(String resume) {
		 this.resume = resume;
		 String[] attrs = new String[] {"resume"};
		 String[] values = new String[] {String.valueOf(this.resume)};
		 FileUtils.updateCSV4(userFilePath, this.userId, attrs, values);
	 }

	 public void setAndPushInstructorMoney(double instructorMoney) {
		 this.instructorMoney = instructorMoney;
		 String[] attrs = new String[] {"instructorMoney"};
		 String[] values = new String[] {String.format("%.2f",this.instructorMoney)};
		 FileUtils.updateCSV4(userFilePath, this.userId, attrs, values);
	 }
	 public double getInstructorMoney() {
		 return this.instructorMoney;
	 }

	 public void setInstructorMoney(double instructorMoney) {
		this.instructorMoney = instructorMoney;
	}

	/**
	 * publish live
	 *
	 * @return true success; false duplicate;
	 */
	public boolean publishLive(String[] para){
	 	para[0] = "L" + GymUtils.findLastIDPlus1("Live");
	 	para[1] = this.getUserId();
	 	para[5] = "0";
	 	return new Live(para).insertToCSV();
	}
}
