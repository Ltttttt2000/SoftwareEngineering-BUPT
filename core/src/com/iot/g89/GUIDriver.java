package com.iot.g89;

public class GUIDriver {

    private GymUtils gymUtils;

    public GUIDriver(){
        this.gymUtils = new GymUtils();
    }

    /**
     *
     * @param userID
     * @param password
     * @param type
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
}
