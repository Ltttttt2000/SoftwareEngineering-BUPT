package com.iot.g89;

import java.util.ArrayList;
import java.util.Arrays;

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
     * @param type type
     * @return -1 no user; -2 wrong password; 1 success login
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
        parameters[0] = userid;

        if(type.equals("Instructor")){
            parameters = Arrays.copyOf(parameters, parameters.length + 1);
            parameters[parameters.length - 1] = "0";
        }

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

    public String getSex(){return gymUtils.user.getSex();}
    public String getPhone(){return gymUtils.user.getPhone();}
    public String getRechargeAccount(){return String.format("%.2f", gymUtils.user.getRechargeAccount());}
    public String getAge(){return gymUtils.user.getAge() + "";}
    public String getHeight(){return gymUtils.user.getHeight() + "";}
    public String getWeight(){return String.format("%.2f", gymUtils.user.getWeight());}
    public String getChest(){return String.format("%.1f", gymUtils.user.getChest());}
    public String getWaist(){return String.format("%.1f", gymUtils.user.getWaist());}
    public String getHip(){return String.format("%.1f", gymUtils.user.getHip());}
    public String getLoginLicense(){return String.valueOf(gymUtils.user.getLoginLicense());}
    public String getResume(){return gymUtils.user.getResume();}
//    public String getResume(){return gymUtils.user.get}
    public String getUserLevel(){return gymUtils.user.getUserLevel();}
    public String getUsertype(){return gymUtils.user.getClass().getSimpleName();}
}