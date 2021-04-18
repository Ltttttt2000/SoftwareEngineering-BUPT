package com.iot.g89;

public class TestGUIDriver {
    public static void main(String[] args){

        GUIDriver guiDriver = new GUIDriver();

        System.out.println(guiDriver.login("1003", "122123", "Client"));
        System.out.println(guiDriver.login("1001", "122123", "Client"));
        System.out.println(guiDriver.login("1001", "123123", "Client"));

    }
}
