package com.iot.g89;

import javax.swing.*;
import java.util.ArrayList;

public class Client extends User{
              // Normal, Member, SupremeMember
	//

	public Client(String userid) {super(userid);}
	public Client(String[] para) {super(para);}
	
	/**
	 * Recharge money into user's account. Decide whether the user can have a upgrade based on the amount of money recharged.
	 * 
	 * @param money
	 * 			The money the user recharge
	 */
	public void recharge(double money) {
		if(money < 0) {
			System.out.println("error");
		}else {
			setAndPushRechargeAmount(this.getRechargeAmount() + money);
			if(money >= 500 && this.getUserLevel().equals("Normal"))
				this.setAndPushUserLevel("Member");
			if(money >= 1000 && !this.getUserLevel().equals("SupremeMember"))
				this.setAndPushUserLevel("SupremeMember");
		}
	}
	/**
	 * Consume money from user's account.
	 * 
	 * @param money
	 * 			The money the user need to pay
	 */
	public int consume(double money) {
		if(this.getUserLevel().equals("Member"))
			money *= 0.8;
		if(this.getUserLevel().equals("SupremeMember"))
			money *= 0.5;

		double balance = this.getRechargeAmount() - money;
		if(balance < 0) {
			return -1;
		}else {
			setAndPushRechargeAmount(balance);
			return 1;
		}
	}

	/**
	 * <ul>
	 * <li>purchase instructor or video</li>
	 * <li>reserve a live</li>
	 * </ul>
	 *
	 * @param Id
	 * @return -1 no instructor/video; -2 repeat purchasing -3 money; 1 success
	 */
	public int purchaseOrReserve(String Id) {

		String filePath = "./core/src/csv/";
		String[] insertString = {Id,userid};
		ArrayList<String[]> insertList = new ArrayList<>();
		insertList.add(insertString);

		double money = 0;
		if(Id.charAt(0) == 'I'){
			Instructor i = new Instructor(Id);
			if(i.getUserid().equals("None"))
				return  -1;
			money = i.getInstructorMoney();
			filePath = filePath + "PurchaseInstructor.csv";
		}else if(Id.charAt(0) == 'V'){
			Video v = new Video(Id);
			if(v.getVideoId().equals("None"))
				return  -1;
			money = 0;
			filePath = filePath + "PurchaseVideo.csv";
		}else {
			filePath = filePath + "Reservation";
		}

		ArrayList<String[]> IdList = FileUtils.readCSV(filePath,new String[]{"*"});
		for(String[] IdR : IdList){
			if(IdR[0].equals(Id) && IdR[1].equals(this.getUserid()))
				return -2;
		}

		int w = consume(money);
		if(w != 1)
			return -3;

		FileUtils.insertCSV(filePath, insertList);
		return 1;
	}
}

