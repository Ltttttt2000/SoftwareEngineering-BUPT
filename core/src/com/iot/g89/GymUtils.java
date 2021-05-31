package com.iot.g89;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * 
 *
 * @version 0.5
 * @author ly129
 */
public class GymUtils {

    public User user = null;

    /**
     * initialize the programme, construct a user and hold it
     *
     * @param userID user Id
     */
    public void initialize (String userID) {
        User user = (User) constructByID(userID);
        if(!(user.userId.equals("None"))){
            this.user = user;
        }
    }

    /**
     * find last Id plus one
     *
     * @param type String Client/Instructor/Admin/Video/Live
     * @return int
     */
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

    /**
     * return a object constructed by Id
     *
     * @param Id client/instructor/admin/video/live Id
     * @return object
     */
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

    /**
     * return the type of the Id
     *
     * @param Id client/instructor/admin/video/live Id
     * @return String
     */
    public static String typeById(String Id){
        char type = Id.charAt(0);
        switch (type){
            case 'C' :
                return "Client";
            case 'I' :
                return "Instructor";
            case 'A' :
                return "Administrator";
            case 'V' :
                return "Video";
            case 'L' :
                return "Live";
        }
        return "None";
    }

    /**
     * Use for select
     *
     * @param type String Client/Instructor/Admin/Video/Live
     * @return ArrayList<Object>
     */
    private static ArrayList<Object> selectAll(String type){
        ArrayList<Object> returnList = new ArrayList<Object>();

        String filePath = "./core/src/csv/"+ type + ".csv";
        String[] readAll = {"*"};

        ArrayList<String[]> allList = FileUtils.readCSV(filePath, readAll);
        for (String[] para : allList){
            Object o = GymUtils.constructByID(para[0]);
            returnList.add(o);
        }
        if(type.equals("Ban"))
            Collections.reverse(returnList);
        if(type.equals("Live")){
            ArrayList<Live> liveList = new ArrayList<Live>();
            for(Object o : returnList){
                Live live = (Live) o;
                liveList.add(live);
            }
            Collections.sort(liveList);
            returnList = new ArrayList<Object>(liveList);
        }
        return returnList;
    }

    /**
     * select support = !=
     * remember to use the acronym for the hump in str
     *
     * @param str sql
     * @return ArrayList<Object>
     */
    public static ArrayList<Object> select(String str){
        String[] para = str.split("( )+");
        return select(str,selectAll(para[0]));
    }

    /**
     * select
     * matryoshka doll edition
     *
     * @param str sql
     * @param originList ArrayList<Object> to be filter
     * @return ArrayList<Object>
     */
    public static ArrayList<Object> select(String str, ArrayList<Object> originList) {
        ArrayList<Object> returnList = new ArrayList<>(originList);
        Collections.copy(returnList, originList);

        String[] para = str.split("( )+");
        if(para.length != 1) {
            for (int i = 1; i < para.length; i++) {
                String para1 = para[i];

                if (para1.equals("Filter")){
                    ArrayList<Live> liveList = new ArrayList<>();
                    for(Object o : returnList){
                        Live live = (Live) o;
                        if(live.getDate().after(new Date())){
                            liveList.add(live);
                        }
                        returnList = new ArrayList<Object>(liveList);
                    }
                }else if (Pattern.matches("[A-Za-z0-9.]+=[A-Za-z0-9.]+", para1)) {
                    String[] para2 = para1.split("=");
                    Iterator<Object> iterator = returnList.iterator();
                    while (iterator.hasNext()) {
                        try {
                            Object o = iterator.next();
                            Method m = o.getClass().getMethod("get" + para2[0], null);
                            if (!(m.invoke(o).toString().equals(para2[1])))
                                iterator.remove();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if (Pattern.matches("[A-Za-z0-9.]+!=[A-Za-z0-9.]+", para1)) {
                    String[] para2 = para1.split("!=");
                    Iterator<Object> iterator = returnList.iterator();
                    while (iterator.hasNext()) {
                        try {
                            Object o = iterator.next();
                            Method m = o.getClass().getMethod("get" + para2[0], null);
                            if (m.invoke(o).toString().equals(para2[1]))
                                iterator.remove();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return returnList;
    }

    /**
     * aggregation
     * use for purchaseXX.csv
     *
     * @param type what (type like "Live")
     * @param Id whose (Id like "C1001")
     * @return ArrayList<Object>
     */
    public static ArrayList<Object> select(String type, String Id){
        ArrayList<Object> returnList = new ArrayList<>();

        String filePath = "./core/src/csv/Purchase" + type + ".csv";
        if(type.equals("Client"))
            filePath = "./core/src/csv/PurchaseInstructor.csv";
        String[] readAll = {"*"};
        ArrayList<String[]> allList = FileUtils.readCSV(filePath, readAll);
        type = "com.iot.g89." + type;

        ArrayList<String> idList = new ArrayList<>();

//        aggregate by client, return instructor/live/video
        if(Id.charAt(0) == 'C'){
            for(String[] para : allList){
                if(para[1].equals(Id))
                    idList.add(para[0]);
            }
            for(String IdR : idList){
                try {
                    Class<?> clazz = Class.forName(type);
                    Constructor<?> constructor = clazz.getConstructor(String.class);
                    Object o = constructor.newInstance(IdR);
                    returnList.add(o);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//        aggregate by instructor, return client
        }else{
            for(String[] para : allList){
                if(para[0].equals(Id))
                    idList.add(para[1]);
            }
            for(String IdR : idList) {
                Client c = new Client(IdR);
                returnList.add(c);
            }
        }
        return returnList;
    }

    /**
     * delete
     *
     * @param Id client/instructor/admin/video/live Id
     * @return false not found; true success;
     */
    public static boolean delete(String Id){
        String type = GymUtils.typeById(Id);
        String pathRoot = "./core/src/csv/";
        if(GymUtils.typeById(Id).equals("Client")){
            FileUtils.deleteCSV(Id,pathRoot + "Ban.csv");
            Client client = new Client(Id);
            ArrayList<Object> liveList = select("Live",Id);
            for(Object o : liveList){
                Live live = (Live) o;
                client.rescind(live.getLiveId());
            }
            FileUtils.deleteCSVAll(Id,pathRoot + "PurchaseInstructor.csv",1);
            FileUtils.deleteCSVAll(Id,pathRoot + "PurchaseVideo.csv",1);

        } else if(GymUtils.typeById(Id).equals("Instructor")){
            FileUtils.deleteCSV(Id,pathRoot + "Ban.csv");
            ArrayList<Object> liveList = select("Live InstructorId=" +Id);
            for(Object o : liveList){
                Live live = (Live) o;
                delete(live.getLiveId());
            }
            FileUtils.deleteCSVAll(Id,pathRoot + "PurchaseInstructor.csv",0);

        } else if(GymUtils.typeById(Id).equals("Live"))
            FileUtils.deleteCSVAll(Id,pathRoot + "PurchaseLive.csv",0);

         else if(GymUtils.typeById(Id).equals("Video"))
            FileUtils.deleteCSVAll(Id,pathRoot + "PurchaseVideo.csv",0);

        return FileUtils.deleteCSV(Id,pathRoot + type + ".csv");
    }
}