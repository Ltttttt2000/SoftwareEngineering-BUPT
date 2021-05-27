package com.iot.g89;

import java.util.ArrayList;

public class PurchaseInstructor {

    public static ArrayList<Object> aggregate(String userId){
        ArrayList<Object> returnList = new ArrayList<>();

        String filePath = "./core/src/csv/PurchaseInstructor.csv";
        String[] readAll = {"*"};
        ArrayList<String[]> allList = FileUtils.readCSV(filePath, readAll);

        ArrayList<String> idList = new ArrayList<>();

//        aggregate by client, return instructor
        if(userId.charAt(0) == 'C'){
            for(String[] para : allList){
                if(para[1].equals(userId))
                    idList.add(para[0]);
                }
            for(String Id : idList){
                Instructor i = new Instructor(Id);
                returnList.add(i);
            }
//        aggregate by instructor, return client
        }else{
            for(String[] para : allList){
                if(para[0].equals(userId))
                    idList.add(para[1]);
            }
            for(String Id : idList) {
                Client c = new Client(Id);
                returnList.add(c);
            }
        }
        return returnList;
    }


}
