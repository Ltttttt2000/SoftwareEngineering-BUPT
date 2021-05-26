package com.iot.g89;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Video {
	private String videoId;
	private final SimpleStringProperty VideoId;
	private String videoType;
	private final SimpleStringProperty VideoType;
	private String detail;

	private String videoName;
	private final SimpleStringProperty VideoName;

	public String getVideoName() {
		return videoName;
	}

	public SimpleStringProperty videoNameProperty() {
		return VideoName;
	}

	private double price;
	private final SimpleDoubleProperty Price;
	private Instructor author;
	private final SimpleStringProperty Author;
	Client specificClient;


	
    public Video(String videoId) {
    	this.VideoId = new SimpleStringProperty(videoId);
    	this.videoId = videoId;
    	this.VideoType = new SimpleStringProperty("Default");
    	this.Price = new SimpleDoubleProperty(0.0);
    	this.Author = new SimpleStringProperty("Somebody");
    	this.VideoName = new SimpleStringProperty("Stay fit and be strong");
    }

	public SimpleStringProperty videoIdProperty() {
		return VideoId;
	}

	public SimpleStringProperty videoTypeProperty() {
		return VideoType;
	}

	public SimpleDoubleProperty priceProperty() {
		return Price;
	}

	public SimpleStringProperty authorProperty() {
		return Author;
	}

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

	public Instructor getAuthor() {
		return author;

	}

	public Client getSpecificClient() {
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

	public void setAuthor(Instructor author) {
		this.author = author;
	}

	public void setSpecificClient(Client specificClient) {
		this.specificClient = specificClient;
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
