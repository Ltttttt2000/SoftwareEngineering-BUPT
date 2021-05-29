package com.iot.g89;

import java.util.ArrayList;

public class Administrator extends User{
	
	public Administrator(String userId) {
		super(userId);
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
