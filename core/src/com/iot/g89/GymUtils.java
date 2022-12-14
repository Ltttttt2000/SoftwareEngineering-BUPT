package com.iot.g89;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * <p>Controller.</p>
 * <p>Contains some static method.</p>
 *
 * @version 0.5
 * @author ly129
 */
public class GymUtils {

    /**
     * Find last Id plus one.
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
     * Return a object constructed by Id.
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
     * Return the type of the Id.
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
     * Use for select.
     *
     * @param type String Client/Instructor/Admin/Video/Live
     * @return ArrayList
     */
    private static ArrayList<Object> selectAll(String type){
        ArrayList<Object> returnList = new ArrayList<>();

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
            ArrayList<Live> liveList = new ArrayList<>();
            for(Object o : returnList){
                Live live = (Live) o;
                liveList.add(live);
            }
            Collections.sort(liveList);
            returnList = new ArrayList<>(liveList);
        }
        return returnList;
    }

    /**
     * <p>Select.</p>
     * <p>Support = != Filter</p>
     * <P>Remember to use the acronym for the hump in str.</p>
     *
     * @param str sql
     * @return ArrayList
     */
    public static ArrayList<Object> select(String str){
        String[] para = str.split("( )+");
        return select(str,selectAll(para[0]));
    }

    /**
     * <p>Select.</p>
     * <p>Matryoshka doll edition.</p>
     *
     * @param str sql
     * @param originList ArrayList<Object> to be filter
     * @return ArrayList
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
                        returnList = new ArrayList<>(liveList);
                    }
                }else if (Pattern.matches("[A-Za-z0-9._]+=[A-Za-z0-9._]+", para1)) {
                    String[] para2 = para1.split("=");
                    Iterator<Object> iterator = returnList.iterator();
                    while (iterator.hasNext()) {
                        try {
                            Object o = iterator.next();
                            Method m = o.getClass().getMethod("get" + para2[0]);
                            if (!(m.invoke(o).toString().equals(para2[1])))
                                iterator.remove();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if (Pattern.matches("[A-Za-z0-9._]+!=[A-Za-z0-9._]+", para1)) {
                    String[] para2 = para1.split("!=");
                    Iterator<Object> iterator = returnList.iterator();
                    while (iterator.hasNext()) {
                        try {
                            Object o = iterator.next();
                            Method m = o.getClass().getMethod("get" + para2[0]);
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
     * <p>Aggregation.</p>
     * <p>Use for purchaseXX.csv.</p>
     *
     * @param type what (type like "Live")
     * @param Id whose (Id like "C1001")
     * @return ArrayList
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
     * Delete.
     *
     * @param Id client/instructor/admin/video/live Id
     * @return false not found; true success;
     */
    public static boolean delete(String Id){
        String type = GymUtils.typeById(Id);
        String pathRoot = "./core/src/csv/";
        switch (GymUtils.typeById(Id)) {
            case "Client": {
                FileUtils.deleteCSV(Id, pathRoot + "Ban.csv");
                Client client = new Client(Id);
                ArrayList<Object> liveList = select("Live", Id);
                for (Object o : liveList) {
                    Live live = (Live) o;
                    client.rescind(live.getLiveId());
                }
                FileUtils.deleteCSVAll(Id, pathRoot + "PurchaseInstructor.csv", 1);
                FileUtils.deleteCSVAll(Id, pathRoot + "PurchaseVideo.csv", 1);

                break;
            }
            case "Instructor": {
                FileUtils.deleteCSV(Id, pathRoot + "Ban.csv");
                ArrayList<Object> liveList = select("Live InstructorId=" + Id);
                for (Object o : liveList) {
                    Live live = (Live) o;
                    delete(live.getLiveId());
                }
                FileUtils.deleteCSVAll(Id, pathRoot + "PurchaseInstructor.csv", 0);

                break;
            }
            case "Live":
                FileUtils.deleteCSVAll(Id, pathRoot + "PurchaseLive.csv", 0);
                break;
            case "Video":
                FileUtils.deleteCSVAll(Id, pathRoot + "PurchaseVideo.csv", 0);
                break;
        }

        return FileUtils.deleteCSV(Id,pathRoot + type + ".csv");
    }
}