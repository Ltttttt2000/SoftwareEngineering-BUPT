package com.iot.g89;

import java.util.ArrayList;

public class Live {
    private String liveId = "None";
    private String instructId;

    //Morning Afternoon Evening
    private String time;
    private String description;
    private int number;

    private final String filePath = "./core/src/csv/Live.csv";

    public Live (String id){
        ArrayList<String[]> selectList = FileUtils.readCSV(filePath, new String[] {"*"});

        if(selectList.size() != 0) {
            for(String[] para : selectList){
                if(para[0].equals(id)){
                    this.liveId = para[0];
                    this.instructId = para[1];
                    this.time = para[2];
                    this.description = para[3];
                    this.number = Integer.parseInt(para[4]);
                    break;
                }
            }
        }
    }

    public Live(String[] para){
        this.liveId = para[0];
        this.instructId = para[1];
        this.time = para[2];
        this.description = para[3];
        this.number = Integer.parseInt(para[4]);
    }

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }

    public String getInstructId() {
        return instructId;
    }

    public void setInstructId(String instructId) {
        this.instructId = instructId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String toString(){
        return "id\t\t" + this.getLiveId() + "\n" +
                "level\t" + this.getDescription() + "\n";
    }
}
