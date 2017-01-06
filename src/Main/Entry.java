package Main;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Merike on 09-Oct-16.
 */
public class Entry {
    SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");

    Time time;
    Date date;
    String projectName;
    static int nextID=0;
    int entryID;

    //konstruktor automaatse ID puhuks
    public Entry(Time t, Date d, String p){
        time = t;
        date = d;
        projectName = p;
        entryID = nextID;
        nextID++;
    }

    //konstruktor, kui ID on juba olemas
    public Entry(Time t, Date d, String p, int id){
        time = t;
        date = d;
        projectName = p;
        entryID = id;
        if (id>=nextID){
            nextID = id+1;
        }
    }

    public Time getTime() {
        return time;
    }

    //tagastab sissekande aja stringina kujul hh:mm:ss
    public String getTimeString(){
        return time.toString();
    }

    public Date getDate() {
        return date;
    }

    //tagastab kuupäeva stringina kujul yyyy.MM.dd
    public String getDateString() {
        return df.format(date);
    }

    public String getProjectName(){
        return projectName;
    }

    public int getEntryID() {
        return entryID;
    }
}