package com.iot.g89;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;

/**
 * This class is the
 */
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
     * @return -1 no user; -2 wrong password; -3 banned; 1 success
     */
    public int login(String userID, String password, String type){

        gymUtils.initialize(userID);

        if(gymUtils.user == null){
            return -1;
        }

        if(gymUtils.user.passwordCheck(password)){
            if(gymUtils.user.loginLicense)
                return 1;
            else
                return -3;
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

        String userId = GymUtils.findLastIDPlus1(type) + "";

        if(type.equals("Instructor")){
            parameters = Arrays.copyOf(parameters, parameters.length + 1);
            parameters[parameters.length - 1] = "0";
            userId = "I" + userId;
        }else
            userId = "C" + userId;

        parameters[0] = userId;

        ArrayList<String[]> userInfoList = new ArrayList<String[]>();
        userInfoList.add(parameters);

        FileUtils.insertCSV("./core/src/csv/" + type + ".csv", userInfoList);

        return userId;
    }

    /**
     * change password
     *
     * @param oldPassword old password
     * @param newPassword new password
     * @return -1 wrong old password; -2 old password is same as the new ones; 1 success
     */
    public int changePassword (String oldPassword, String newPassword){
        return changePassword(oldPassword, newPassword, gymUtils.user);
    }

    /**
     * change password
     *
     * @param oldPassword old password
     * @param newPassword new password
     * @param user The owner of the password
     * @return -1 wrong old password; -2 old password is same as the new ones; 1 success
     */
    public int changePassword (String oldPassword, String newPassword, User user) {
        if(!user.passwordCheck(oldPassword))
            return -1;
        else if(oldPassword.equals(newPassword))
            return -2;
        else {
            user.setAndPushPassword(newPassword);
            return 1;
        }
    }

    /**
     * change password
     *
     * @param oldPassword old password
     * @param newPassword new password
     * @param id The id of the owner of the password
     * @return -1 wrong old password; -2 old password is same as the new ones; 1 success
     */
    public int changePassword (String oldPassword, String newPassword, String id) {
        User user = null;
        if(id.charAt(0) == 'C'){
            user = new Client(id);
        }else{
            user = new Instructor(id);
        }
        if(!(user.getUserId().equals("None")))
            return changePassword(oldPassword, newPassword, user);
        return -1;
    }

    /**
     * change basic information
     *
     * @param parameters parameters
     * @return true
     */
    public boolean changeBasicInfo(String[] parameters) {
        return changeBasicInfo(parameters, gymUtils.user);
    }

    /**
     * change basic information
     *
     * @param parameters parameters
     * @param user The owner of the info
     * @return true
     */
    public boolean changeBasicInfo(String[] parameters, User user) {
        user.setAndPushSex(parameters[3]);
        user.setAndPushPhone(parameters[4]);
        user.setAndPushResume(parameters[7]);
        user.setAndPushAge(Integer.parseInt(parameters[8]));
        user.setAndPushHeight(Double.parseDouble(parameters[9]));
        user.setAndPushWeight(Double.parseDouble(parameters[10]));
        user.setAndPushChest(Double.parseDouble(parameters[11]));
        user.setAndPushWaist(Double.parseDouble(parameters[12]));
        user.setAndPushHip(Double.parseDouble(parameters[13]));

        return true;
    }

    /**
     * change basic information
     *
     * @param parameters parameters
     * @param id user's id
     * @return true
     */
    public boolean changeBasicInfo(String[] parameters, String id) {
        User user = null;
        if(id.charAt(0) == 'C'){
            user = new Client(id);
        }else{
            user = new Instructor(id);
        }
        if(!(user.getUserId().equals("None")))
            return changeBasicInfo(parameters, user);
        return false;
    }

    public String getUserId(){return gymUtils.user.getUserId();}

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
        if(!(user.getUserId().equals("None")))
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
        if(!(user.getUserId().equals("None")))
            return user.getPhoneNumber();
        return null;
    }

    public String getRechargeAccount(String id){
        User user = null;
        if(id.charAt(0) == 'C'){
            user = new Client(id);
        }else{
            user = new Instructor(id);
        }
        if(!(user.getUserId().equals("None")))
            return String.format("%.2f", user.getRechargeAmount());
        return null;
    }

    public String getAge(String id){
        User user = null;
        if(id.charAt(0) == 'C'){
            user = new Client(id);
        }else{
            user = new Instructor(id);
        }
        if(!(user.getUserId().equals("None")))
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
        if(!(user.getUserId().equals("None")))
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
        if(!(user.getUserId().equals("None")))
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
        if(!(user.getUserId().equals("None")))
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
        if(!(user.getUserId().equals("None")))
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
        if(!(user.getUserId().equals("None")))
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
        if(!(user.getUserId().equals("None")))
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
        if(!(user.getUserId().equals("None")))
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
        if(!(user.getUserId().equals("None")))
            return user.getUserLevel();
        return null;
    }

    public String getUsertype(String id){
        return GymUtils.typeById(id);
    }

    public String getInstructorMoney(String id){
        Instructor instructor = new Instructor(id);
        if(!(instructor.getUserId().equals("None")))
            return String.valueOf(instructor.getInstructorMoney());
        return null;
    }

    /**
     * select support = !=
     * remember to use the acronym for the hump in str
     *
     * @param str sql
     * @return ArrayList<Object>
     */
    public ArrayList<Object> select(String str){
        return GymUtils.select(str);
    }

    /**
     * select
     * matryoshka doll edition
     *
     * @param str sql
     * @param originList ArrayList<Object> to be filter
     * @return ArrayList<Object>
     */
    public ArrayList<Object> select(String str, ArrayList<Object> originList) {
        return GymUtils.select(str,originList);
    }

    /**
     * aggregation
     * use for purchaseXX.csv
     *
     * @param type what (type like "Live")
     * @param Id whose (Id like "C1001")
     * @return ArrayList<Object>
     */
    public ArrayList<Object> select(String type, String Id){
        return GymUtils.select(type,Id);
    }

    /**
     * recharge
     *
     * @param money money
     */
    public void recharge(double money){
        Client client = (Client) gymUtils.user;
        client.recharge(money);
    }

    /**
     * <ul>
     * <li>purchase instructor or video</li>
     * <li>reserve a live</li>
     * </ul>
     *
     * @param Id  instructor/video/live id
     * @return
     * <ul>
     * <li>-1 no such instructor/video/live</li>
     * <li>-2 repeat purchasing</li>
     * <li>-3 money problem</li>
     * <li>-4 not the student</li>
     * <li>-5 live is full</li>
     * <li>1 success</li>
     * <ul/>
     */
    public int purchaseOrReserve(String Id){
        Client client = (Client) gymUtils.user;
        return client.purchaseOrReserve(Id);
    }

    /**
     * publish live
     *
     * @return -1 duplicate; -2 time traveller; 1 success
     */
    public int publishLive(String[] para){
        Instructor instructor = (Instructor) gymUtils.user;
        return instructor.publishLive(para);
    }

    /**
     * delete
     *
     * @param Id client/instructor/admin/video/live Id
     * @return false not found; true success;
     */
    public boolean delete(String Id){
        return GymUtils.delete(Id);
    }

    /**
     * auto ban
     *
     * @param Id client/instructor/admin Id
     * @return true ban; false unban
     */
    public boolean ban(String Id){
        User user = (User) GymUtils.constructByID(Id);
        assert user != null;
        if(user.getLoginLicense()){
            user.banThisAccount();
            return true;
        }
        else {
            user.unbanThisAccount();
            return false;
        }
    }

    /**
     * ban
     *
     * @param Id client/instructor/admin Id
     * @param ban true for ban; false for unban
     */
    public void ban(String Id, boolean ban){
        User user = (User) GymUtils.constructByID(Id);
        assert user != null;
        if(ban)
            user.banThisAccount();
        else
            user.unbanThisAccount();
    }

    /**
     * apply for unban
     */
    public void applyForUnban(){
        gymUtils.user.applyForUnban();
    }

    /**
     * rescind
     *
     * @param Id  instructor/video/live id
     * @return false not found; true success;
     */
    public boolean rescind(String Id){
        Client client = (Client) gymUtils.user;
        return client.rescind(Id);
    }
}