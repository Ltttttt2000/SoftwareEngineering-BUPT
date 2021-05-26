package com.iot.g89;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Video {
	String videoId;
	private final SimpleStringProperty VideoId;
	String videoType;
	private final SimpleStringProperty VideoType;
	String detail;
	double price;
	private final SimpleDoubleProperty Price;
	Instructor author;
	private final SimpleStringProperty Author;
	Client specificClient;
	
    public Video(String videoId) {
    	this.VideoId = new SimpleStringProperty(videoId);
    	this.videoId = videoId;
    	this.VideoType = new SimpleStringProperty("Default");
    	this.Price = new SimpleDoubleProperty(0.0);
    	this.Author = new SimpleStringProperty("Somebody");
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

	}

	public void setDetail(String detail) {

	}

	public void setPrice(String price) {

	}

	public void setAuthor(Instructor author) {

	}

	public void setSpecificClient(Client specificClient) {

	}

	public void playVideo() {

	}

}
