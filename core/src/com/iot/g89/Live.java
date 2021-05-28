package com.iot.g89;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Live {
    private String liveId = "None";
    private String instructId;

    private Date date;
    //Morning Afternoon Evening
    private String time;
    private String description;
    private int number = 0;

    private final String filePath = "./core/src/csv/Live.csv";
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Live (String id){
        ArrayList<String[]> selectList = FileUtils.readCSV(filePath, new String[] {"*"});

        if(selectList.size() != 0) {
            for(String[] para : selectList){
                if(para[0].equals(id)){
                    this.liveId = para[0];
                    this.instructId = para[1];
                    try {
                        this.date = sdf.parse(para[2]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    this.time = para[3];
                    this.description = para[4];
                    this.number = Integer.parseInt(para[5]);
                    break;
                }
            }
        }
    }

    public Live(String[] para){
        this.liveId = para[0];
        this.instructId = para[1];
        try {
            this.date = sdf.parse(para[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public void setAndPushNumber(int number){
        this.number = number;
        String[] attrs = new String[] {"number"};
        String[] values = new String[] {String.valueOf(this.number)};
        FileUtils.updateCSV4(filePath, this.liveId, attrs, values);
    }

    public void insertToCSV(){
        String[] para = new String[6];
        para[0] = this.getLiveId();
        para[1] = this.getInstructId();
        para[2] = sdf.format(this.getDate());
        para[3] = this.getTime();
        para[4] = this.getDescription();
        para[5] = this.getNumber() + "";

        ArrayList<String[]> paraList = new ArrayList<>();
        paraList.add(para);
        FileUtils.insertCSV(filePath,paraList);
    }


    public String toString(){
        return "id\t\t" + this.getLiveId() + "\n" +
                "level\t" + this.getDescription() + "\n";
    }
}
