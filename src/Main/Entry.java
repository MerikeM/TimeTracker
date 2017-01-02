package Main;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Merike on 09-Oct-16.
 */
public class Entry {
    SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");

    private Time time;
    private Date date;
    private String projectName;
    private static int nextID=0;
    private int entryID;

    public Entry(Time t, Date d, String p){
        this.time = t;
        this.date = d;
        this.projectName = p;
        this.entryID = nextID;
        nextID++;
    }

    public Entry(Time t, Date d, String p, int id){
        this.time = t;
        this.date = d;
        this.projectName = p;
        this.entryID = id;
        if (id>=nextID){
            nextID = id+1;
        }
    }

    public Time getTime() {
        return time;
    }

    public String getDateString() {
        return df.format(date);
    }

    public Date getDate() {
        return date;
    }

    public String getTimeString(){
        return time.toString();
    }

    public String getProjectName(){
        return projectName;
    }

    public int getEntryID() {
        return entryID;
    }

}
