package com.iot.g89;

import javax.swing.*;
import java.util.ArrayList;

public class Client extends User{
              // Normal, Member, SupremeMember
	//

	public Client(String userid) {
		super(userid);
	}
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
		double balance = this.getRechargeAmount() - money;
		if(balance < 0) {
			return -1;
		}else {
			setAndPushRechargeAmount(balance);
			return 1;
		}
	}

	//related to video class
	/**
	 * This method is for every user to list all public videos.
	 * 
	 * @return ArrayList<Video>
	 * 			all the Videos
	 */
	public ArrayList<Video> listPublicVideo(){
		ArrayList<Video> allPublicVideo = new ArrayList<Video>();
		String filePath = "./videoCSVs.csv";
		String[] idAndType = {"*"};
		ArrayList<String[]> videoInfo = new ArrayList<String[]>();
		videoInfo = FileUtils.readCSV(filePath, idAndType);

		int i = 0;
		while(i < videoInfo.size()) {
			if(videoInfo.get(i)[1].equals("public")) {  //the second element is videoType = public
				Video video = new Video(videoInfo.get(i)[0]);
				allPublicVideo.add(video);	
			}
				i++;
		}
		System.out.println(allPublicVideo);
		return allPublicVideo;
	}
	
	/**
	 * This method is only for member client to list all paid videos.
	 * 
	 * @return ArrayList<Video>
	 * 			all the Videos need to be paid
	 */
	public ArrayList<Video> listPaidVideo(){
		ArrayList<Video> allPaidVideo = new ArrayList<Video>();
		String filePath = "./videoCSVs.csv";
		String[] idAndType = {"videoId","videoType"};
		ArrayList<String[]> videoInfo = new ArrayList<String[]>();
		videoInfo = FileUtils.readCSV(filePath, idAndType);

		int i = 0;
		while(i<videoInfo.size()) {
			if(videoInfo.get(i)[1].equals("paid")) {  //the second element is videoType = public
				Video video = new Video(videoInfo.get(i)[0]);
				allPaidVideo.add(video);	
			}
			i++;
		}
		System.out.println(allPaidVideo);
		return allPaidVideo;
	}
	
	/**
	 * This method is only for member client to list all purchased videos.
	 * 
	 * @return ArrayList<Video>
	 * 			all the Videos the client purchased
	 */
	public ArrayList<Video> listPurchasedVideo(){
		ArrayList<Video> purcharedVideo = new ArrayList<Video>();
		String filePath = "./clientVideo.csv";
		String[] info = {"*"};
		ArrayList<String[]> videoInfo = new ArrayList<String[]>();
		videoInfo = FileUtils.readCSV(filePath, info);

		int i = 0;
		while(i<videoInfo.size()) {
			if(videoInfo.get(i)[1].equals(this.userid)) {
				Video video = new Video(videoInfo.get(i)[2]);   //videoid
				purcharedVideo.add(video);	
			}
			i++;
		}
		System.out.println(purcharedVideo);
		return purcharedVideo;
		
	}
	/**
	 * This method is only for SupremeMember to list all private videos provided by instructor.
	 * 
	 * @return ArrayList<Video>
	 * 			all the Videos private
	 */
	public ArrayList<Video> listPrivateVideo(){
		ArrayList<Video> privateVideo = new ArrayList<Video>();
		String filePath = "./AssignVideo.csv";
		String[] info = {"*"};
		ArrayList<String[]> videoInfo = new ArrayList<String[]>();
		videoInfo = FileUtils.readCSV(filePath, info);

		int i = 0;
		while(i < videoInfo.size()) {
			if(videoInfo.get(i)[1].equals(this.userid)) {  //the second element is client's userid
				Video video = new Video(videoInfo.get(i)[3]);   //videoid
				privateVideo.add(video);	
			}
			i++;
		}
		System.out.println(privateVideo);
		return privateVideo;
	}

	/**
	 * This method is for every user to play a particular video.
	 * 
	 * @param video
	 * 			the video want to play
	 */
	public void playVideo(Video video) { 
		//the function in video class
	}

	/**
	 * This method is only for member client and SupremeMember client to buy a particular video.
	 * 
	 * @param VideoBuy
	 * 			the video want to buy
	 */
	public void buyVideo(Video VideoBuy) {
		if(this.userLevel.equals("Normal")) {
			System.out.println("Wrong clientType");
		}else {
			double money = VideoBuy.getPrice();
			consume(money);
			
			String filePath = "./clientVideo.csv";	
			String[] attrs = {"*"};
			//identify purchasedId
			ArrayList<String[]> info = new ArrayList<String[]>();
			info = FileUtils.readCSV(filePath, attrs);
			int i = 0;
			String purchasedid = null;
			while(i < info.size()) {  //the second element is client's userid
				purchasedid = info.get(i)[0];
				i++;
			}
			int id = Integer.parseInt(purchasedid) + 1;
			String newPurchasedId = String.valueOf(id);
			ArrayList<String[]> csvList = new ArrayList<String[]>();
			String[] addInfo = {newPurchasedId,userid,VideoBuy.getVideoId()};
			csvList.add(addInfo);
			FileUtils.insertCSV(filePath, csvList);
		}
	}
	


	
	//related to instructor
	
	/**
	 * This method is only for SupremeMember client to list all instructors.
	 * 
	 * @return ArrayList<Instructor>
	 * 			all the instructor
	 */
	public ArrayList<Instructor> listAllInstructor(){
		ArrayList<Instructor> allInstructor = new ArrayList<Instructor>();
		String filePath = "./Instructor.csv";
		String[] attributes = {"userid"};
		
		ArrayList<String[]> instructorsInfo = new ArrayList<String[]>();
		instructorsInfo = FileUtils.readCSV(filePath, attributes);
		int i = 0;
		while(i<instructorsInfo.size()) {
			Instructor instructor = new Instructor(instructorsInfo.get(i)[0]);
			allInstructor.add(instructor);		
			i++;
		}
		System.out.println(allInstructor);
		return allInstructor;
	}
	
	/**
	 * This method is only for SupremeMember client to list personal instructors.
	 * 
	 * @return ArrayList<Instructor>
	 * 			all the instructor
	 */
	public ArrayList<Instructor> listMyInstructor(){
		ArrayList<Instructor> myInstructor = new ArrayList<Instructor>();
		String filePath = "./clientInstructor.csv";
		String[] attrs = {"*"};
		ArrayList<String[]> info = new ArrayList<String[]>();
		info = FileUtils.readCSV(filePath, attrs);

		int i = 0;
		for(i = 0; i < info.size(); i++) {
			if(info.get(i)[1].equals(this.userid)) {
				Instructor instructor = new Instructor(info.get(i)[2]);  //instructorid
				myInstructor.add(instructor);	
			}
		}
		System.out.println(myInstructor);
		return myInstructor;
	}

	/**
	 * <ul>
	 * <li>purchase instructor or video</li>
	 * <li>reserve a live</li>
	 * </ul>
	 *
	 * @param Id
	 * @return -1 wrong usertype; -2 money; 1 success
	 */
	public int purchaseOrReserve(String Id) {

		String filePath = "./core/src/csv/";
		String[] insertString = {Id,userid};
		ArrayList<String[]> insertList = new ArrayList<>();
		insertList.add(insertString);

		if(this.userLevel.equals("Normal")) {
			return -1;
		}else {

			double money = 0;
			if(Id.charAt(0) == 'I'){
				Instructor i = new Instructor(Id);
				money = i.getInstructorMoney();
				filePath = filePath + "PurchaseInstructor.csv";
			}else if(Id.charAt(0) == 'V'){
				Video v = new Video(Id);
				money = 0;
				filePath = filePath + "PurchaseVideo.csv";
			}else
				filePath = filePath + "Reservation";

			int w = consume(money);
			if(w != 1)
				return -2;

			FileUtils.insertCSV(filePath, insertList);
			return 1;
		}
	}
	
	/**
	 * This method is only for SupremeMember client to check a particular instructor.
	 * 
	 * @param instructor
	 * 			the instructor want to check
	 */
	public void checkInstructor(Instructor instructor) {
		String userid = instructor.userid;
		String filePath = "./Instructor.csv";
		ArrayList<String[]> instructorsInfo = new ArrayList<String[]>();
		instructorsInfo = FileUtils.readCSV(filePath, fileHeaders);
		int i = 0;
		while(i<instructorsInfo.size()) {  //the second element is client's userid
			if(instructorsInfo.get(i)[0] .equals(userid)) {
				//display function
			}
			i++;
		}
		//System.out.println(instructorsInfo);
	}
	
}

