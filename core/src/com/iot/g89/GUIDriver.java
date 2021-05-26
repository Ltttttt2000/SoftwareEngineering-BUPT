package com.iot.g89;

import com.sun.javafx.collections.ArrayListenerHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GUIDriver {

    private GymUtils gymUtils;

    public GUIDriver(){
        this.gymUtils = new GymUtils();
    }

    /**
     * login
     *
     * @param userID userID
     * @param password password
     * @return -1 no user; -2 wrong password; 1 success
     */
    public int login(String userID, String password, String type){

        gymUtils.initialize(userID, type);

        if(gymUtils.user == null){
            return -1;
        }

        if(gymUtils.user.passwordCheck(password)){
            return 1;
        }else{
            gymUtils.user = null;
            return -2;
        }
    }

    /**
     * logout
     */
    public void logout(){

        gymUtils.user = null;
    }

    /**
     * register a new user
     *
     * @param parameters parameters
     * @param type type
     * @return userID
     */
    public String registerUser(String[] parameters, String type){

        String userid = gymUtils.findLastIDPlus1(type) + "";

        if(type.equals("Instructor")){
            parameters = Arrays.copyOf(parameters, parameters.length + 1);
            parameters[parameters.length - 1] = "0";
            userid = "I" + userid;
        }else
            userid = "C" + userid;

        parameters[0] = userid;

        ArrayList<String[]> userInfoList = new ArrayList<String[]>();
        userInfoList.add(parameters);

        FileUtils.insertCSV("./core/src/csv/" + type + ".csv", userInfoList);

        return userid;
    }

    /**
     * change password
     *
     * @param oldPassword old password
     * @param newPassword new password
     * @return -1 wrong old password; -2 old password is same as the new ones; 1 success
     */
    public int changePassword (String oldPassword, String newPassword){

        if(!gymUtils.user.passwordCheck(oldPassword))
            return -1;
        else if(oldPassword.equals(newPassword))
            return -2;
        else {
            gymUtils.user.setPassword(newPassword);
            return 1;
        }
    }

    /**
     * change basic information
     *
     * @param parameters parameters
     * @return true
     */
    public boolean changeBasicInfo(String[] parameters){

        User user = gymUtils.user;

        user.setSex(parameters[3]);
        user.setPhone(parameters[4]);
        user.setResume(parameters[7]);
        user.setAge(Integer.parseInt(parameters[8]));
        user.setHeight(Double.parseDouble(parameters[9]));
        user.setWeight(Double.parseDouble(parameters[10]));
        user.setChest(Double.parseDouble(parameters[11]));
        user.setWaist(Double.parseDouble(parameters[12]));
        user.setHip(Double.parseDouble(parameters[13]));

        return true;

    }

    public String getUserId(){return gymUtils.user.getId();}

    public String getSex(){return getSex(getUserId());}
    public String getPhone(){return getPhone(getUserId());}
    public String getRechargeAccount(){return getRechargeAccount(getUserId());}
    public String getAge(){return getAge(getUserId());}
    public String getHeight(){return getHeight(getUserId());}
    public String getWeight(){return getWeight(getUserId());}
    public String getChest(){return getChest(getUserId());}
    public String getWaist(){return getWaist(getUserId());}
    public String getHip(){return getHip(getUserId());}
    public String getLoginLicense(){return getLoginLicense(getUserId());}
    public String getResume(){return getResume(getUserId());}
    public String getUserLevel(){return getUserLevel(getUserId());}
    public String getUsertype(){return getUsertype(getUserId());}
    // for instructor only
    public String getInstructorMoney(){return String.valueOf(gymUtils.user.getInstructorMoney());}

    public String getSex(String id){
        User user = null;
        if(id.charAt(0) == 'C'){
            user = new Client(id);
        }else{
            user = new Instructor(id);
        }
        if(!(user.getId().equals("None")))
            return user.getSex();
        return null;
    }

    public String getPhone(String id){
        User user = null;
        if(id.charAt(0) == 'C'){
            user = new Client(id);
        }else{
            user = new Instructor(id);
        }
        if(!(user.getId().equals("None")))
            return user.getPhone();
        return null;
    }

    public String getRechargeAccount(String id){
        User user = null;
        if(id.charAt(0) == 'C'){
            user = new Client(id);
        }else{
            user = new Instructor(id);
        }
        if(!(user.getId().equals("None")))
            return String.format("%.2f", user.getRechargeAccount());
        return null;
    }

    public String getAge(String id){
        User user = null;
        if(id.charAt(0) == 'C'){
            user = new Client(id);
        }else{
            user = new Instructor(id);
        }
        if(!(user.getId().equals("None")))
            return user.getAge() + "";
        return null;
    }

    public String getHeight(String id){
        User user = null;
        if(id.charAt(0) == 'C'){
            user = new Client(id);
        }else{
            user = new Instructor(id);
        }
        if(!(user.getId().equals("None")))
            return user.getHeight() + "";
        return null;
    }

    public String getWeight(String id){
        User user = null;
        if(id.charAt(0) == 'C'){
            user = new Client(id);
        }else{
            user = new Instructor(id);
        }
        if(!(user.getId().equals("None")))
            return String.format("%.2f", user.getWeight());
        return null;
    }

    public String getChest(String id){
        User user = null;
        if(id.charAt(0) == 'C'){
            user = new Client(id);
        }else{
            user = new Instructor(id);
        }
        if(!(user.getId().equals("None")))
            return String.format("%.1f", user.getChest());
        return null;
    }

    public String getWaist(String id){
        User user = null;
        if(id.charAt(0) == 'C'){
            user = new Client(id);
        }else{
            user = new Instructor(id);
        }
        if(!(user.getId().equals("None")))
            return String.format("%.1f", user.getWaist());
        return null;
    }

    public String getHip(String id){
        User user = null;
        if(id.charAt(0) == 'C'){
            user = new Client(id);
        }else{
            user = new Instructor(id);
        }
        if(!(user.getId().equals("None")))
            return String.format("%.1f", user.getHip());
        return null;
    }

    public String getLoginLicense(String id){
        User user = null;
        if(id.charAt(0) == 'C'){
            user = new Client(id);
        }else{
            user = new Instructor(id);
        }
        if(!(user.getId().equals("None")))
            return String.valueOf(user.getLoginLicense());
        return null;
    }

    public String getResume(String id){
        User user = null;
        if(id.charAt(0) == 'C'){
            user = new Client(id);
        }else{
            user = new Instructor(id);
        }
        if(!(user.getId().equals("None")))
            return user.getResume();
        return null;
    }

    public String getUserLevel(String id){
        User user = null;
        if(id.charAt(0) == 'C'){
            user = new Client(id);
        }else{
            user = new Instructor(id);
        }
        if(!(user.getId().equals("None")))
            return user.getUserLevel();
        return null;
    }

    public String getUsertype(String id){
        User user = null;
        if(id.charAt(0) == 'C'){
            user = new Client(id);
        }else{
            user = new Instructor(id);
        }
        if(!(user.getId().equals("None")))
            return user.getClass().getSimpleName();
        return null;
    }

    public String getInstructorMoney(String id){
        Instructor instructor = new Instructor(id);
        if(!(instructor.getId().equals("None")))
            return String.valueOf(instructor.getInstructorMoney());
        return null;
    }

    private ArrayList<Object> selectAll(String type){
        ArrayList<Object> returnList = new ArrayList<Object>();

        String userFilePath = "./core/src/csv/"+ type + ".csv";
        type = "com.iot.g89." + type;
        String[] readAll = {"*"};

        ArrayList<String[]> allList = FileUtils.readCSV(userFilePath, readAll);
        for (String[] para : allList){
            try {
                Class<?> clazz = Class.forName(type);
                Constructor<?> constructor = clazz.getConstructor(String[].class);
                Object o = constructor.newInstance(new Object[]{para});
                returnList.add(o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  returnList;
    }

    public ArrayList<Object> select(String str){
        String[] para = str.split("( )+");
        return select(str,selectAll(para[0]));
    }

    /**
     * select函数
     * 全选用*
     * 属性记得用首字母大写的驼峰命名法
     *
     * @param str
     * @param originList
     * @return
     */
    public ArrayList<Object> select(String str, ArrayList<Object> originList){

        String[] para = str.split("( )+");
        if(para[1].equals("*"))
            return originList;

        String[] para2 = para[1].split("=");

        Iterator<Object> iterator = originList.iterator();
        while(iterator.hasNext()){
            try {
                Object o = iterator.next();
                Method m = o.getClass().getMethod("get" + para2[0], null);
                if(!(m.invoke(o).equals(para2[1])))
                    iterator.remove();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return originList;
    }
}