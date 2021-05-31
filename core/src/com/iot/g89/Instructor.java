package com.iot.g89;

import java.text.ParseException;
import java.util.Date;


/**
 * <p>Entity.</p>
 * <p>Instructor class.</p>
 *
 * @version 0.5
 * @author ly129, LiuTong
 */
public class Instructor extends User{
	
	 /** The instructor money. */
 	public double instructorMoney = 0.0;

	/**
	 * <p>Instantiate a instructor. The function auto collect information from csv file.</p>
	 * <p>If there is no such Id in the CSV, the function will return a instructor with Id equals to "none".</p>
	 *
	 * @param userId ClientId
	 */
 	public Instructor(String userId) {
		super(userId);
		this.instructorMoney = Double.parseDouble(selectList.get(entry)[14]);
	}

	/**
	 * Mainly use for the reflection.
	 *
	 * @param para para
	 */
	public Instructor(String[] para) {
 		super(para);
		this.instructorMoney = Double.parseDouble(para[14]);
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
	 * Publish a live.
	 *
	 * @param para live information
	 * @return -1 duplicate; -2 time traveller; 1 success
	 */
	public int publishLive(String[] para){
		try {
			if(Live.sdf.parse(para[2]).before(new Date()))
				return -2;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		para[0] = "L" + GymUtils.findLastIDPlus1("Live");
		para[1] = this.getUserId();
	 	para[4] = "0";
	 	return new Live(para).insertToCSV();
	}
}
