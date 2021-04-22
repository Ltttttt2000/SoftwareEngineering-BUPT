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
}