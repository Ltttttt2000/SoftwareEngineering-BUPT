package com.iot.g89;

import java.util.ArrayList;

public class Administrator extends User{
	
	public Administrator(String userId) {
		super(userId);
	}
	
	/**
	 * List all the clients information.
	 * 
	 * @return 
	 * 		All client objects in Arraylist
	 */
	public ArrayList<Client> listAllClient(){
		ArrayList<Client> allClients = new ArrayList<Client>();
		String filePath = "./Client.csv";
		String[] attributes = {"userId"};
		
		ArrayList<String[]> clientsInfo = new ArrayList<String[]>();
		clientsInfo = FileUtils.readCSV(filePath, attributes);
		int i = 0;
		
		while(i < clientsInfo.size()) {
			Client client = new Client(clientsInfo.get(i)[0]);    //get userId, build Client
			allClients.add(client);		
			i++;
		}
		System.out.println(allClients);
		return allClients;
	}

	/**
	 * List all the clients information.
	 * 
	 * @return 
	 * 		All client objects in arraylist
	 */
	public ArrayList<Instructor> listAllInstructor(){
		ArrayList<Instructor> allInstructor = new ArrayList<Instructor>();
		String filePath = "./Instructor.csv";
		String[] attributes = {"userId"};
		
		ArrayList<String[]> instructorsInfo = new ArrayList<String[]>();
		instructorsInfo = FileUtils.readCSV(filePath, attributes);
		int i = 0;
		while(i < instructorsInfo.size()) {
			Instructor instructor = new Instructor(instructorsInfo.get(i)[0]);
			allInstructor.add(instructor);		
			i++;
		}
		System.out.println(allInstructor);
		return allInstructor;
	}
	
	/**
	 * List all the users information.
	 * 
	 * @return 
	 * 		All user objects in arraylist
	 */
	public ArrayList<User> listAllUser(){
		ArrayList<User> allUser = new ArrayList<User>();
		String[] usersID = {"userId"};
		//all admin
		String adminPath = "./Administrator.csv";
		ArrayList<String[]> adminInfo = new ArrayList<String[]>();
		adminInfo = FileUtils.readCSV(adminPath, usersID);
		int k = 0;
		for(k=0;k<adminInfo.size();k++) {
			while(k < adminInfo.size()) {
				Administrator admin = new Administrator(adminInfo.get(k)[0]);
				allUser.add(admin);	
			}
		}
		//all Clients
		String clientPath = "./Client.csv";
		ArrayList<String[]> clientsInfo = new ArrayList<String[]>();
		clientsInfo = FileUtils.readCSV(clientPath, usersID);
		int i = 0;
		while(i < clientsInfo.size()) {
			Client client = new Client(clientsInfo.get(i)[0]);
			allUser.add(client);	
			i++;
		}
		
		String instruPath = "./Instructor.csv";
		ArrayList<String[]> instructorsInfo = new ArrayList<String[]>();
		instructorsInfo = FileUtils.readCSV(instruPath, usersID);
		int j = 0;
		for(j=0;j<instructorsInfo.size();j++) {
			Instructor instructor = new Instructor(instructorsInfo.get(j)[0]);
			allUser.add(instructor);	
		}
		

		System.out.println(allUser);
		return allUser;
	}
	
	/**
	 * List all the videos information.
	 * 
	 * @return 
	 * 		All videos objects in arraylist.
	 */
	public ArrayList<Video> listAllVideo(){
		ArrayList<Video> allVideo = new ArrayList<Video>();
		String filePath = "./videoCSVs.csv";
		String[] videoid = {"videoId"};
		ArrayList<String[]> videoInfo = new ArrayList<String[]>();
		videoInfo = FileUtils.readCSV(filePath, videoid);

		int i = 0;
		while(i < videoInfo.size()) {
			Video video = new Video(videoInfo.get(i)[0]);
			allVideo.add(video);	
			i++;
		}
		System.out.println(allVideo);
		return allVideo;
	}
	
	/**
	 * Delete a user.
	 * @param userDel
	 * 		The user need to be deleted.
	 */
	public void deleteUser(User userDel) {
		String userId = userDel.userId;
		String typeOfUser = userDel.getClass().getSimpleName();
		String path = "./" + typeOfUser + ".csv";
		FileUtils.deleteCSV(userId, path);
	}
	
	/**
	 * Ban a user so that the user cannot do any operations.
	 * @param userBan
	 * 		The User need to be banned.
	 */
	public void banUser(User userBan) {
		userBan.banThisAccount();
	}
	
	/**
	 * Delete the particular video.
	 * @param videoDel
	 * 		The video need to be deleted.
	 */
	public void deleteVideo(Video videoDel) {
		String videoid = videoDel.getVideoId();
		//delete from videoSCV
		String path = "./videoCSVs.csv";
		FileUtils.deleteCSV(videoid, path);
		//delete video
		FileUtils.delete(videoid+".mp4");
	}
}
