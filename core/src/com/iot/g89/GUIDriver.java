package com.iot.g89;

import java.util.*;

/**
 * <p>Controller.</p>
 * <p>GUI driver.</p>
 * <p>The logged in user instance is saved here</p>
 *
 * @version 0.5
 * @author ly129
 */
public class GUIDriver {

    private User userLogin = null;

    /**
     * Initialize the programme, construct a user and hold it.
     *
     * @param userID user Id
     */
    public void initialize (String userID) {
        User user = (User) GymUtils.constructByID(userID);
        assert user != null;
        if(!(user.userId.equals("None"))){
            this.userLogin = user;
        }
    }

    /**
     * Login.
     *
     * @param userID userID
     * @param password password
     * @return -1 no user; -2 wrong password; -3 banned; 1 success
     */
    public int login(String userID, String password){

        initialize(userID);

        if(userLogin == null){
            return -1;
        }

        if(userLogin.passwordCheck(password)){
            if(userLogin.loginLicense)
                return 1;
            else
                return -3;
        }else{
            userLogin = null;
            return -2;
        }
    }

    /**
     * Logout.
     */
    public void logout(){
        userLogin = null;
    }

    /**
     * Register a new user.
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
        return changePassword(oldPassword, newPassword, userLogin);
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
        return changeBasicInfo(parameters, userLogin);
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

    public String getUserId(){return userLogin.getUserId();}

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
    public String getInstructorMoney(){return String.valueOf(userLogin.getInstructorMoney());}

    public String getSex(String id){
        User user = (User) GymUtils.constructByID(id);
        assert user != null;
        if(!(user.getUserId().equals("None")))
            return user.getSex();
        return null;
    }

    public String getPhone(String id){
        User user = (User) GymUtils.constructByID(id);
        assert user != null;
        if(!(user.getUserId().equals("None")))
            return user.getPhoneNumber();
        return null;
    }

    public String getRechargeAccount(String id){
        User user = (User) GymUtils.constructByID(id);
        assert user != null;
        if(!(user.getUserId().equals("None")))
            return String.format("%.2f", user.getRechargeAmount());
        return null;
    }

    public String getAge(String id){
        User user = (User) GymUtils.constructByID(id);
        assert user != null;
        if(!(user.getUserId().equals("None")))
            return user.getAge() + "";
        return null;
    }

    public String getHeight(String id){
        User user = (User) GymUtils.constructByID(id);
        assert user != null;
        if(!(user.getUserId().equals("None")))
            return user.getHeight() + "";
        return null;
    }

    public String getWeight(String id){
        User user = (User) GymUtils.constructByID(id);
        assert user != null;
        if(!(user.getUserId().equals("None")))
            return String.format("%.2f", user.getWeight());
        return null;
    }

    public String getChest(String id){
        User user = (User) GymUtils.constructByID(id);
        assert user != null;
        if(!(user.getUserId().equals("None")))
            return String.format("%.1f", user.getChest());
        return null;
    }

    public String getWaist(String id){
        User user = (User) GymUtils.constructByID(id);
        assert user != null;
        if(!(user.getUserId().equals("None")))
            return String.format("%.1f", user.getWaist());
        return null;
    }

    public String getHip(String id){
        User user = (User) GymUtils.constructByID(id);
        assert user != null;
        if(!(user.getUserId().equals("None")))
            return String.format("%.1f", user.getHip());
        return null;
    }

    public String getLoginLicense(String id){
        User user = (User) GymUtils.constructByID(id);
        assert user != null;
        if(!(user.getUserId().equals("None")))
            return String.valueOf(user.getLoginLicense());
        return null;
    }

    public String getResume(String id){
        User user = (User) GymUtils.constructByID(id);
        assert user != null;
        if(!(user.getUserId().equals("None")))
            return user.getResume();
        return null;
    }

    public String getUserLevel(String id){
        User user = (User) GymUtils.constructByID(id);
        assert user != null;
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
     * <p>Select.</p>
     * <p>Support = != Filter.</p>
     * <p>Remember to use the acronym for the hump in str.</p>
     *
     * @param str sql
     * @return ArrayList
     */
    public ArrayList<Object> select(String str){
        return GymUtils.select(str);
    }

    /**
     * <p>Select.</p>
     * <p>Support = != Filter.</p>
     * <p>Remember to use the acronym for the hump in str.</p>
     * <p>Matryoshka doll edition.</p>
     *
     * @param str sql
     * @param originList ArrayList<Object> to be filter
     * @return ArrayList
     */
    public ArrayList<Object> select(String str, ArrayList<Object> originList) {
        return GymUtils.select(str,originList);
    }

    /**
     * <p>Aggregation.</p>
     * <p>Use for purchaseXX.csv.</p>
     *
     * @param type what (type like "Live")
     * @param Id whose (Id like "C1001")
     * @return ArrayList
     */
    public ArrayList<Object> select(String type, String Id){
        return GymUtils.select(type,Id);
    }

    /**
     * <p>Recharge.</p>
     *
     * @param money money
     */
    public void recharge(double money){
        Client client = (Client) userLogin;
        client.recharge(money);
    }

    /**
     * <ul>
     * <li>Purchase instructor or video.</li>
     * <li>Reserve a live.</li>
     * </ul>
     *
     * @param Id instructor/video/live id
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
        Client client = (Client) userLogin;
        return client.purchaseOrReserve(Id);
    }

    /**
     * Publish a live.
     *
     * @param para live information
     * @return -1 duplicate; -2 time traveller; 1 success
     */
    public int publishLive(String[] para){
        Instructor instructor = (Instructor) userLogin;
        return instructor.publishLive(para);
    }

    /**
     * Delete.
     *
     * @param Id client/instructor/admin/video/live Id
     * @return false not found; true success;
     */
    public boolean delete(String Id){
        return GymUtils.delete(Id);
    }

    /**
     * Auto ban.
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
     * Ban.
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
     * Apply for unban, Id will be added in the Ban.csv.
     */
    public void applyForUnban(){
        userLogin.applyForUnban();
    }

    /**
     * Rescind.
     *
     * @param Id  instructor/video/live id
     * @return false not found; true success;
     */
    public boolean rescind(String Id){
        Client client = (Client) userLogin;
        return client.rescind(Id);
    }
}