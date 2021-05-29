package com.iot.g89;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 *
 * @version 0.1
 * @author ly129
 */
public class GymUtils {

    public User user = null;

    public void initialize (String userID, String type) {

        type = "com.iot.g89." + type;

        try {
            Class<?> clazz = Class.forName(type);
            Constructor<?> constructor = clazz.getConstructor(String.class);
            Object o = constructor.newInstance(userID);
            User user = (User) o;
            if(!(user.userId.equals("None"))){
                this.user = user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int findLastIDPlus1(String type) {

        String filePath = "./core/src/csv/"+ type + ".csv";
        String lowerCaseType = Character.toLowerCase(type.charAt(0)) + type.substring(1);
        String key;
        if(type.equals("Client") || type.equals("Instructor")){ key = "userId"; }
        else{ key = lowerCaseType + "Id"; }

        ArrayList<String[]> IDList = FileUtils.readCSV(filePath, new String[]{key});
        if(IDList.size() == 0)
            return 1001;
        String[] last = IDList.get(IDList.size() - 1);
        return Integer.parseInt(last[0].substring(1)) + 1;
    }

    public static Object constructByID(String Id){
        char type = Id.charAt(0);
        switch (type){
            case 'C' :
                return new Client(Id);
            case 'I' :
                return new Instructor(Id);
            case 'A' :
                return new Administrator(Id);
            case 'V' :
                return new Video(Id);
            case 'L' :
                return new Live(Id);
        }
        return null;
    }

    public static String typeById(String Id){
        char type = Id.charAt(0);
        switch (type){
            case 'C' :
                return "Client";
            case 'I' :
                return "Instructor";
            case 'A' :
                return  "Administrator";
            case 'V' :
                return "Video";
            case 'L' :
                return "Live";
        }
        return "None";
    }

    public static boolean delete(String Id){
        String type = GymUtils.typeById(Id);
        String path = "./core/src/csv/" + type + ".csv";
        return FileUtils.deleteCSV(Id,path);
    }
}