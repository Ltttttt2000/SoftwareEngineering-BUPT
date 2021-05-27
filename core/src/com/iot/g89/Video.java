package com.iot.g89;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Video {
	private String videoId;
	//private final SimpleStringProperty VideoId;
	private String videoType;
	//private final SimpleStringProperty VideoType;
	private String detail;

	private String videoName;
	//private final SimpleStringProperty VideoName;



	private double price;
	//private final SimpleDoubleProperty Price;
	private String author;
	//private final SimpleStringProperty Author;
	private String specificClient;


	
    public Video(String videoId) {
    	//this.VideoId = new SimpleStringProperty(videoId);
    	this.videoId = videoId;
    	//this.VideoType = new SimpleStringProperty("Default");
		this.videoType = "Default";
    	//this.Price = new SimpleDoubleProperty(0.0);
		this.price = 0;
    	//this.Author = new SimpleStringProperty("Somebody");

		//this.author = new Instructor("Somebody");
		this.author = "Somebody";

		//this.VideoName = new SimpleStringProperty();
    	this.videoName = "Stay fit and be strong";
    }

    public String getVideoName() {
		return videoName;
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

	public String getVideoType(){
		return videoType;

	}

	public String getDetail() {
		return detail;

	}

	public double getPrice() {
		return price;
	}

	public String getAuthor() {
		return author;
	}

//	public String getAuthor() {
//		return author;
//	}

	public String getSpecificClient() {
		return specificClient;

	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setPrice(String price) {
		this.price = Double.parseDouble(price);
	}

//	public void setAuthor(Instructor author) {
//		this.author = author;
//	}

	public void setSpecificClient(Client specificClient) {
		this.specificClient = specificClient.getUserid();
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

}
