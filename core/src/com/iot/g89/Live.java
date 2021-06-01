package com.iot.g89;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>Entity.</p>
 * <p>Live class.</p>
 *
 * @version 0.5
 * @author Medon, ly129
 */
public class Live implements Comparable<Live>{
    private String liveId = "None";
    private String instructorId;

    private Date date;
    private String description;
    private int number = 0;

    private final static String filePath = "./core/src/csv/Live.csv";
    public final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");

    /**
     * <p>Instantiate a live. The function auto collect information from csv file.</p>
     * <p>If there is no such Id in the CSV, the function will return a live with Id equals to "none".</p>
     *
     * @param id ClientId
     */
    public Live (String id){
        ArrayList<String[]> selectList = FileUtils.readCSV(filePath, new String[] {"*"});

        if(selectList.size() != 0) {
            for(String[] para : selectList){
                if(para[0].equals(id)){
                    this.liveId = para[0];
                    this.instructorId = para[1];
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

    /**
     * Mainly use for the reflection.
     *
     * @param para para
     */
    public Live(String[] para){
        this.liveId = para[0];
        this.instructorId = para[1];
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
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public Date getDate() {
        return date;
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

    /**
     * Check duplicate.
     *
     * @return true yep; false nop
     */
    public boolean duplicateChecking(){
        ArrayList<String[]> allList = FileUtils.readCSV(filePath, new String[]{"*"});
        for(String[] para : allList){
            if(para[1].equals(instructorId) && para[2].equals(sdf.format(date)))
                return true;
        }
        return false;
    }

    /**
     * Insert to CSV.
     *
     * @return -1 duplicate; 1 success
     */
    public int insertToCSV(){
        if(duplicateChecking())
            return -1;
        String[] para = new String[5];
        para[0] = this.getLiveId();
        para[1] = this.getInstructorId();
        para[2] = sdf.format(this.getDate());
        para[3] = this.getDescription();
        para[4] = this.getNumber() + "";

        ArrayList<String[]> paraList = new ArrayList<>();
        paraList.add(para);
        FileUtils.insertCSV(filePath,paraList);
        return 1;
    }


    public String toString(){
        return "id\t\t" + this.getLiveId() + "\n" +
                "time\t" + sdf.format(this.getDate()) + "\n" +
                "what\t" + this.getDescription() + "\n";
    }

    public boolean equals(Object o){
        if(!o.getClass().equals(this.getClass()))
            return false;
        Live live = (Live) o;
        return this.getLiveId().equals(live.getLiveId());
    }

    @Override
    public int compareTo(Live live) {
        return date.compareTo(live.date);
    }
}
