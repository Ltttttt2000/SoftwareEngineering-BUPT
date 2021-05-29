package com.iot.g89;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Live {
    private String liveId = "None";
    private String instructId;

    private Date date;
    private String description;
    private int number = 0;

    private final static String filePath = "./core/src/csv/Live.csv";
    public final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");

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
        try {
            this.date = sdf.parse(para[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.description = para[3];
        this.number = Integer.parseInt(para[4]);
    }

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }

    public String getInstructorId() {
        return instructId;
    }

    public void setInstructId(String instructId) {
        this.instructId = instructId;
    }

    public String getDate() {
        return sdf.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
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

    public boolean duplicateChecking(){
        ArrayList<String[]> allList = FileUtils.readCSV(filePath, new String[]{"*"});
        for(String[] para : allList){
            if(para[1].equals(instructId) && para[2].equals(sdf.format(date)))
                return true;
        }
        return false;
    }

    /**
     * insertToCSV
     *
     * @return true success; false duplicate;
     */
    public int insertToCSV(){
        if(duplicateChecking())
            return -1;
        String[] para = new String[5];
        para[0] = this.getLiveId();
        para[1] = this.getInstructorId();
        para[2] = this.getDate();
        para[3] = this.getDescription();
        para[4] = this.getNumber() + "";

        ArrayList<String[]> paraList = new ArrayList<>();
        paraList.add(para);
        FileUtils.insertCSV(filePath,paraList);
        return 1;
    }


    public String toString(){
        return "id\t\t" + this.getLiveId() + "\n" +
                "level\t" + this.getDescription() + "\n";
    }
}
