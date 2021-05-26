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
 	public int instructorMoney = 0;
	 
	 /**
 	 * Instantiates a new instructor.
 	 *
 	 * @param userid the userid
 	 */
 	public Instructor(String userid) {
		super(userid);
		this.instructorMoney = Integer.parseInt(selectList.get(entry)[14]);
	}

	public Instructor(String[] para) {
 		super(para);
		this.instructorMoney = Integer.parseInt(selectList.get(entry)[14]);
	}
	 
	 /**
 	 * Sets the resume.
 	 *
 	 * @param resume the new resume
 	 */
	 public void setResume(String resume) {
		 this.resume = resume;
		 String[] attrs = new String[] {"resume"};
		 String[] values = new String[] {String.valueOf(this.resume)};
		 FileUtils.updateCSV4(userFilePath, this.userid, attrs, values);
	 }
	 
	 /**
 	 * Gets the resume.
 	 *
 	 * @return the resume
 	 */
	 public String getResume() {
		 return this.resume;
	 }
	 
	 /**
 	 * Sets the instructor money.
 	 *
 	 * @param instructorMoney the new instructor money
 	 */
	 public void setInstructorMoney(int instructorMoney) {
		 this.instructorMoney = instructorMoney;
		 String[] attrs = new String[] {"instructorMoney"};
		 String[] values = new String[] {String.valueOf(this.instructorMoney)};
		 FileUtils.updateCSV4(userFilePath, this.userid, attrs, values);
	 }
	 
	 /**
 	 * Gets the instructor money.
 	 *
 	 * @return the instructor money
 	 */
	 public int getInstructorMoney() {
		 return this.instructorMoney;
	 }

	 /**
 	 * Upload video.
 	 */
 	//Todo!!!
	 public void uploadVideo() {
		 
		 
	 }
	 
	 /**
 	 * Delete video.
 	 *
 	 * @param video the video
 	 */
	 public void deleteVideo(Video video) {
		 FileUtils.deleteCSV(video.getVideoId(), "./videoCSV.csv");
		 FileUtils.delete("./Video/"+ video.getVideoId()+".mp4");
	 }
	 
	 /**
 	 * Play video.
 	 *
 	 * @param video the video
 	 */
 	//Todo!!!!
	 public void playVideo(Video video) {
		 
	 }
	 
	 /**
 	 * Edits the video.
 	 *
 	 * @param video the video
 	 */
 	//Todo!!!
	 public void editVideo(Video video) {
		 
		 
	 }

	 /**
 	 * List this insrtuctor's supreme member.
 	 *
 	 * @return the array list of these supre members object
 	 */
 	ArrayList<Client> listMySupremeMember(){
		 String[] readAll = {"*"}; 
		 ArrayList<String[]> clientInstructorList = new ArrayList<String[]>();
		 clientInstructorList = FileUtils.readCSV("./clientInstructor.csv", readAll);
		 int i;
		 ArrayList<Client> supreClients = new ArrayList<Client>();
		 //every line in the clientInstructor file
		 for(i=0; i<clientInstructorList.size();i++) {
			 //find entries buying this instructor
			 if(clientInstructorList.get(i)[2].equals(this.userid)) {
				 //存在重复client，see!!!!!!
				 supreClients.add(new Client(clientInstructorList.get(i)[1]));
			 }
			 
		 }
		return supreClients;
		 
	 }
	 
	 /**
 	 * List the video uploaded by this instructor.
 	 *
 	 * @return the array list of this instructor's video object
 	 */
 	ArrayList<Video> listMyVideo(){
		 String[] readAll = {"*"}; 
		 ArrayList<String[]> videoList = new ArrayList<String[]>();
		 videoList = FileUtils.readCSV("./videoCSV.csv", readAll);
		 int i;
		 ArrayList<Video> myVideos = new ArrayList<Video>();
		 //every line in the clientInstructor file
		 for(i=0; i<videoList.size();i++) {
			 //find video entries made by this instructor
			 if(videoList.get(i)[4].equals(this.userid)) {
				 myVideos.add(new Video(videoList.get(i)[0]));
			 }
		 }
		 return myVideos;
		 
	 }
	 
 	
	 /**
 	 * Check my member.
 	 *
 	 * @param member the member
 	 */
	 public void checkMyMember(Client member) {
		
			 System.out.println("Id:"+ member.getId()+"\n"+
			 					"Sex"+ member.getSex() +"\n"+
					 			"age:"+ member.getAge() +"\n"+
					 			"height"+ member.getHeight() +"\n"+
					 			"weight"+ member.getWeight() +"\n"+
					 			"chest"+ member.getChest() +"\n"+
					 			"waist"+ member.getWaist() +"\n"+
					 			"hip"+ member.getHip() +"\n");
	 }
		 
	 
}
