package com.iot.g89;

import java.util.ArrayList;

public class Video {
	private String videoId = "None";
	//private final SimpleStringProperty VideoId;
	private String videoName = "Stay fit and be strong";
	//private final SimpleStringProperty VideoName;
	private String videoType = "Default";
	//private final SimpleStringProperty VideoType

	private double videoPrice;
	//private final SimpleDoubleProperty Price;
	private String videoUploader;
	//private final SimpleStringProperty Author;
	private String videoDetail;
	private String specificClient;
	private String fileType;

	private final String filePath = "./core/src/csv/Video.csv";
	
    public Video(String id) {
		ArrayList<String[]> selectList = FileUtils.readCSV(filePath, new String[] {"*"});

		if(selectList.size() != 0) {
			for (String[] para : selectList) {
				if (para[0].equals(id)) {
					//this.VideoId = new SimpleStringProperty(videoId);
					this.videoId = para[0];
					//this.VideoName = new SimpleStringProperty();
					this.videoName = para[1];
					//this.VideoType = new SimpleStringProperty("Default");
					this.videoType = para[2];
					//this.Price = new SimpleDoubleProperty(0.0);
					this.videoPrice = Double.parseDouble(para[3]);
					//this.Author = new SimpleStringProperty("Somebody");
					this.videoUploader = para[4];
					this.videoDetail = para[5];
					this.specificClient = para[6];
					this.fileType = para[7];
					break;
				}
			}
		}
    }

    public Video(String[] para){
		this.videoId = para[0];
		//this.VideoName = new SimpleStringProperty();
		this.videoName = para[1];
		//this.VideoType = new SimpleStringProperty("Default");
		this.videoType = para[2];
		//this.Price = new SimpleDoubleProperty(0.0);
		this.videoPrice = Double.parseDouble(para[3]);
		//this.Author = new SimpleStringProperty("Somebody");
		this.videoUploader = para[4];
		this.videoDetail = para[5];
		this.specificClient = para[6];
		this.fileType = para[7];
	}


//	public String videoNameProperty() {
//		return VideoName.get();
//	}

//	public SimpleStringProperty videoIdProperty() {
//		return VideoId;
//	}
//
//	public SimpleStringProperty videoTypeProperty() {
//		return VideoType;
//	}
//
//	public SimpleDoubleProperty priceProperty() {
//		return Price;
//	}
//
//	public SimpleStringProperty authorProperty() {
//		return Author;
//	}


	public String getVideoId() {
		return videoId;
	}

	public String getVideoName() {
		return videoName;
	}

	public String getVideoType() {
		return videoType;
	}

	public double getVideoPrice() {
		return videoPrice;
	}

	public String getVideoUploader() {
		return videoUploader;
	}

	public String getVideoDetail() {
		return videoDetail;
	}

	public String getSpecificClient() {
		return specificClient;
	}

	public String getFileType() {
		return fileType;
	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	public void setVideoDetail(String videoDetail) {
		this.videoDetail = videoDetail;
	}

	public void setVideoPrice(String videoPrice) {
		this.videoPrice = Double.parseDouble(videoPrice);
	}

//	public void setAuthor(Instructor author) {
//		this.author = author;
//	}

	public void setSpecificClient(Client specificClient) {
		this.specificClient = specificClient.getUserId();
	}

	public void playVideo(String file) {
		Runtime rn = Runtime.getRuntime();
		Process p = null;
		try{
			p = rn.exec("cmd /c start "+file);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error exec!");
		}
	}

	public String toString(){
    	return "id\t\t" + this.getVideoId() + "\n" +
				"level\t" + this.getVideoName() + "\n";
	}

}
