package com.iot.g89;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
            Class clazz = Class.forName(type);
            Constructor constructor = clazz.getConstructor(String.class);
            Object o = constructor.newInstance(userID);
            User user = (User) o;
            if(!(user.userid.equals("None"))){
                this.user = user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}