package com.iot.g89;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import  com.csvreader.CsvWriter;

public class FileUtils {
	/**
	 * 
	 * Create a CSV file, that is, add the header
	 * @param filePath
	 * 			The path and name of the file to create
	 * @param headers
	 * 			The header of the CSV file that was created
	 */
	public static void createCSV(String filePath, String[] headers) {
		try {
			CsvWriter csvWriter = new CsvWriter(filePath,',', Charset.forName("GBK"));
			csvWriter.writeRecord(headers);
			
			csvWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Appends a data entry to an existing CSV file. The data entry can be multiple
	 * 
	 * @param filePath
	 * 			The path and name of the file to which the data entry was added
	 * @param csvList
	 * 			The data entry to insert
	 */
	public static void insertCSV(String filePath,  ArrayList<String[]> csvList) {
		try {
            
            BufferedWriter out = new BufferedWriter(new 

                    OutputStreamWriter(new FileOutputStream(filePath,true),"GBK"),1024);
            
         // Create a CSV write object
            CsvWriter csvWriter = new CsvWriter(out,',');
 
            int row = 0;
            String[] content = {""};
            
            for(row = 0; row < csvList.size(); row++) {
            	content = csvList.get(row);
            	csvWriter.writeRecord(content);
            }
            
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Writes the data to a CSV file and overwrites the previous data
	 * @param filePath
	 * 			The path to the CSV file to write to
	 * @param csvList
	 * 			The data to be written
	 */
	public static void writeCSV(String filePath, ArrayList<String[]> csvList) {
		
		try {
			CsvWriter csvWriter = new CsvWriter(filePath,',', Charset.forName("GBK"));
			
			for(String[] item : csvList) {
				csvWriter.writeRecord(item);
			}
			
			csvWriter.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * Read data from a CSV file
	 * 
	 * @param filePath
	 * 			The path and name of the file to read the data from
	 * @param cloum
	 * 			For which columns of data to read, "*" represents all reads
	 * @return
	 * 		The data entry read from the CSV file
	 */
	public static ArrayList<String[]> readCSV(String filePath, String[] cloum) {
		ArrayList<String[]> csvList = new ArrayList<String[]>();
		
        try {
            // Create a CSV read object
            CsvReader csvReader = new CsvReader(filePath, ',' , Charset.forName("GBK"));

            // Read the header
            csvReader.readHeaders();
            
            //Read all the data for each row
            if(cloum[0].equals("*")) {
            	while(csvReader.readRecord()) {
            		csvList.add(csvReader.getValues());
            	}
            }
            else {
            	String[] item = {""};
            	int i;
            	while(csvReader.readRecord()) {
            		for(i = 0; i < cloum.length; i++) {
            			//Read a column of data in this row
            			item[i] = csvReader.get(cloum[i]);
            		}
            		csvList.add(item);
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		return csvList;
	}
	
	/**
	 * Read data from a CSV file with headers
	 * 
	 * @param filePath
	 * 			The path and name of the file to read the data from
	 * @param cloum
	 * 			For which columns of data to read, "*" represents all reads
	 * @return
	 * 		The data entry read from the CSV file with headers
	 */
	public static ArrayList<String[]> readCSVWithHeader(String filePath, String[] cloum) {
		ArrayList<String[]> csvList = new ArrayList<String[]>();
		
        try {
            // Create a CSV read object
            CsvReader csvReader = new CsvReader(filePath, ',' , Charset.forName("GBK"));
            
            //Read all the data for each row
            if(cloum[0].equals("*")) {
            	while(csvReader.readRecord()) {
            		csvList.add(csvReader.getValues());
            	}
            }
            else {
            	String[] item = {""};
            	int i;
            	while(csvReader.readRecord()) {
            		for(i = 0; i < cloum.length; i++) {
            			//Read a column of data in this row
            			item[i] = csvReader.get(cloum[i]);
            		}
            		csvList.add(item);
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		return csvList;
	}
	
	
	/**
	 * Delete files or folders
	 * 
	 * @param fileName
	 * 			The name of the file or folder that was deleted
	 * @return
	 * 		Whether the file or folder was deleted successfully
	 */
	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			return false;
		} else {
			if (file.isFile()) {
				return deleteFile(fileName);
			} 
			else {
				return deleteDirectory(fileName);
			}
		}
	}
	
	public static boolean deleteDirectory(String dir) {
		// If dir does not end with a file delimiter, the file delimiter is automatically added
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		
		File dirFile = new File(dir);
		
		// If the file corresponding to dir does not exist or is not a directory, exit
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		
		boolean flag = true;
		
		// Delete all files in the folder (including subdirectories)
		File[] files = dirFile.listFiles();
		
		for (int i = 0; i < files.length; i++) {
			// Delete subfile
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
			// Delete subdirectories
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}
 
		if (!flag) {
			return false;
		}
 
		// Delete current directory
		if (dirFile.delete()) {
			return true;
		} 
		else {
			return false;
		}
	}
	
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Modify the name of a specific file
	 * @param oldName
	 * 			The original name of the file to be modified
	 * @param newName
	 * 			New file name
	 * @return
	 * 		Did you successfully rename the file
	 */
	public static boolean renameFile(String oldName, String newName) {
		
		try {
			File src = new File(oldName);
			File des = new File(newName);
			
			if(des.exists()) {
				return false;
			}
			else {
				return src.renameTo(des);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	/**
	 * Batch modification of different values for different entries in the CSV file
	 * 
	 * @param filePath
	 * 			The path to the file to be modified
	 * @param key
	 * 			The primary key value of the entry to be modified
	 * @param attrs
	 * 			The name of the property to be modified
	 * @param values
	 * 			The modified value
	 */
	public static void updateCSV1(String filePath, String[] key, 
				ArrayList<String[]> attrs, ArrayList<String[]> values) {
		ArrayList<String[]> csvList = new ArrayList<String[]>();
		
		String[] cloum = {"*"};
		
		csvList = readCSVWithHeader(filePath, cloum);
		
		int i = 0;
		
		for(i = 0; i < key.length; i++) {
			updateCSV2(csvList, key[i], attrs.get(i), values.get(i));
		}
		
		writeCSV(filePath, csvList);
		
	}
	
	/**
	 * Modify a specific individual entry in a CSV file. But do not write to the file.
	 * 
	 * @param csvList
	 * 			The result of a CSV read containing all the data (including the header)
	 * @param key
	 * 			The primary key value of the entry to be modified
	 * @param attrs
	 * 			The name of the property to be modified
	 * @param values
	 * 			The modified value
	 */
	public static void updateCSV2(ArrayList<String[]> csvList, String key, String[] attrs, String[] values) {
		String[] newItem = {""};
		
		ArrayList<Integer> index = getAttrIndex(csvList.get(0), attrs);
		int j = 0;
		
		for(j = 1; j < csvList.size(); j++) {
			String[] item = csvList.get(j);
			if(item[0].equals(key)) {
				newItem = replaceItem(index, item, values);
				csvList.set(j, newItem);
			}
		}
	}
	
	/**
	 * If an entry in CSV is modified in bulk, 
	 * the properties of the modified entry and the modified value are the same
	 * @param filePath
	 * 			The path to the file to be modified
	 * @param key
	 * 			The primary key value of the entry to be modified
	 * @param attrs
	 * 			The name of the property to be modified
	 * @param values
	 * 			The modified value
	 */
	public static void updateCSV3(String filePath, String[] key, String[] attrs, String[] values) {
		ArrayList<String[]> csvList = new ArrayList<String[]>();
		
		String[] cloum = {"*"};
		
		csvList = readCSVWithHeader(filePath, cloum);
		
		int i = 0;
		
		for(i = 0; i < key.length; i++) {
			updateCSV2(csvList, key[i], attrs, values);
		}
		
		writeCSV(filePath, csvList);
	}
	
	/**
	 * 
	 * Modify a specific individual entry in a CSV file.
	 * 
	 * @param filePath
	 * 			The path to the file to be modified
	 * @param key
	 * 			The primary key value of the entry to be modified
	 * @param attrs
	 * 			The name of the property to be modified
	 * @param values
	 * 			The modified value
	 */
	public static void updateCSV4(String filePath, String key, String[] attrs, String[] values) {
		ArrayList<String[]> csvList = new ArrayList<String[]>();
		
		String[] cloum = {"*"};
		
		csvList = readCSVWithHeader(filePath, cloum);
		
		updateCSV2(csvList, key, attrs, values);
		
		writeCSV(filePath, csvList);
		
		
	}
	
	/**
	 * Deletes a specific data entry
	 * @param key
	 * 			The value of the primary key
	 * @param filePath
	 * 			The path to the CSV file
	 */
	public static void deleteCSV(String key, String filePath) {
		ArrayList<String[]> csvList = new ArrayList<String[]>();
		
		String[] cloum = {"*"};
		
		csvList = readCSVWithHeader(filePath, cloum);
		
		int i = 0;
		for(i = 0; i < csvList.size(); i++) {
			if(csvList.get(i)[0].equals(key)) {
				csvList.remove(i);
			}
		}
		
		writeCSV(filePath, csvList);
	}
	
	public static ArrayList<Integer> getAttrIndex(String[] header,String[] attrs) {
		int i = 0;
		int j = 0;
		ArrayList<Integer> index = new ArrayList<Integer>();
		
		for(i = 0; i < attrs.length; i++) {
			for(j = 0; j < header.length; j++) {
				if(header[j].equals(attrs[i])) {
					index.add(j);
					break;
				}
			}
		}
		
		return index;
	}
	
	public static String[] replaceItem(ArrayList<Integer> index, String[] oldItem, String[] newItem) { 
		
		int j = 0;
		
		for(int i : index) {
			oldItem[i] = newItem[j];
			j++;
		}
		
		return oldItem;
	}
	
	/**
	 * Uploads the local file to the specified location locally
	 * @param dir
	 * 			The destination directory for the upload
	 * @param filePath
	 * 			Locally, the path and name of the file to be uploaded
	 * @param fileName
	 * 			The file name after uploading
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static void uploadFile(String dir, String filePath, String fileName) 
			throws FileNotFoundException, IOException{
		
		try {
			File file = new File(filePath);
			FileInputStream in = new FileInputStream(file);
			File fileLocation = new File(dir);
			
			if(!fileLocation.exists()) {
				boolean isCreated = fileLocation.mkdir();
				if(!isCreated) {
					return;
				}
			}
			
			File uploadFile = new File(dir, fileName);
			OutputStream out = new FileOutputStream(uploadFile);
			byte[] buffer = new byte[1024*1024];
			
			int length;
			
			while((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			
			in.close();
			out.close();
		}catch(FileNotFoundException ex) {
			System.out.println("Failed upload.");
			ex.printStackTrace();
		}catch(IOException ex) {
			System.out.println("Failed upload.");
			ex.printStackTrace();
		}
	}
	
}
