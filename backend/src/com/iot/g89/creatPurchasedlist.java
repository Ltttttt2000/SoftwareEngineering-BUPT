/**
 * 
 */
package com.iot.g89;

import java.util.ArrayList;

/**
 * @author hy
 *
 */
public class creatPurchasedlist {
	public static void main(String[] args) {
		String[] fileHeaders = {"purchasedId","clientId","instructorId"};
		String filePath = "./clientInstructor.csv";
		FileUtils.createCSV(filePath, fileHeaders);
		ArrayList<String[]> purchase = new ArrayList<String[]>();
		String[] info = {"001","1001","2001"};
		purchase.add(info);
		FileUtils.insertCSV(filePath, purchase);
		
		
		String[] fileHeaders1 = {"purchasedId","clientId","videoId"};
		String filePath1 = "./clientVideo.csv";
		FileUtils.createCSV(filePath1, fileHeaders1);
		ArrayList<String[]> purchase1 = new ArrayList<String[]>();
		String[] info1 = {"001","1001","4001"};
		purchase1.add(info1);
		FileUtils.insertCSV(filePath1, purchase1);
		
		String[] fileHeaders2 = {"assignId","clientId","instructorId","videoId"};
		String filePath2 = "./AssignVideo.csv";
		FileUtils.createCSV(filePath2, fileHeaders2);
		ArrayList<String[]> purchase2 = new ArrayList<String[]>();
		String[] info2 = {"001","1001","2001","4001"};
		purchase2.add(info2);
		FileUtils.insertCSV(filePath2, purchase2);
	}

}
