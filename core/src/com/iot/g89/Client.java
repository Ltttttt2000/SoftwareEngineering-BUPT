package com.iot.g89;

import java.util.ArrayList;

public class Client extends User{
              // Normal, Member, SupremeMember
	//

	public Client(String userId) {super(userId);}
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
	public double consume(double money) {
		if(this.getUserLevel().equals("Member"))
			money *= 0.8;
		if(this.getUserLevel().equals("SupremeMember"))
			money *= 0.5;

		double balance = this.getRechargeAmount() - money;
		if(balance < 0) {
			return -1;
		}else {
			setAndPushRechargeAmount(balance);
			return money;
		}
	}

	public boolean checkSource(String Id){
		String type = GymUtils.typeById(Id);
		String filePath = "./core/src/csv/Purchase" + type + ".csv";
		ArrayList<String[]> IdList = FileUtils.readCSV(filePath,new String[]{"*"});
		for(String[] IdR : IdList){
			if(IdR[0].equals(Id) && IdR[1].equals(this.getUserId()))
				return true;
		}
		return false;
	}

	/**
	 * <ul>
	 * <li>purchase instructor or video</li>
	 * <li>reserve a live</li>
	 * </ul>
	 *
	 * @param Id  instructor/video/live id
	 * @return
	 * <ul>
	 * <li>-1 no instructor/video</li>
	 * <li>-2 repeat purchasing</li>
	 * <li>-3 money problem</li>
	 * <li>-4 not the student</li>
	 * <li>-5 live is full</li>
	 * <li>1 success</li>
	 * <ul/>
	 */
	public int purchaseOrReserve(String Id) {

		String filePath = "./core/src/csv/Purchase";
		String[] insertString = {Id, userId};
		ArrayList<String[]> insertList = new ArrayList<>();
		insertList.add(insertString);

		double money = 0;
		if(Id.charAt(0) == 'I'){
			Instructor i = new Instructor(Id);
			if(i.getUserId().equals("None"))
				return -1;
			money = i.getInstructorMoney();
			filePath = filePath + "Instructor.csv";
		}else if(Id.charAt(0) == 'V'){
			Video v = new Video(Id);
			if(v.getVideoId().equals("None"))
				return -1;
			money = v.getVideoPrice();
			filePath = filePath + "Video.csv";
		}else {
			Live l = new Live(Id);
			if(l.getLiveId().equals("None"))
				return -1;
			else if(!this.checkSource(new Instructor(l.getInstructId()).getUserId()))
				return -4;
			else if(l.getNumber() >= 20)
				return -5;
			filePath = filePath + "Live.csv";
		}

		if(this.checkSource(Id))
			return -2;

		double w = consume(money);
		if(w < 0)
			return -3;

		//finally...
		w *= 0.7;
		if(GymUtils.typeById(Id).equals("Instructor")){
			Instructor instructor = new Instructor(Id);
			instructor.setAndPushRechargeAmount(instructor.getRechargeAmount() + w);
		}else if(GymUtils.typeById(Id).equals("Video")){
			Instructor instructor = new Instructor(new Video(Id).getVideoUploader());
			instructor.setAndPushRechargeAmount(instructor.getRechargeAmount() + w);
		}else{
			Live live = new Live(Id);
			live.setAndPushNumber(live.getNumber() + 1);
		}

		FileUtils.insertCSV(filePath, insertList);
		return 1;
	}
}

