package Main;



import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Merike on 09-Oct-16.
 */
public class Entry {
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    private Time time;
    private Date date;
    private String project;
    private static int nextID=0;
    private int entryID;



    public Entry(Time t, Date d, String p){
        this.time = t;
        this.date = d;
        this.project = p;
        this.entryID = nextID;
        nextID++;
    }

    public Time getTime() {
        return time;
    }

    public Date getDate() {
        return date;
    }

    public String getDateString() {
        return df.format(date);
    }

    public String getTimeString(){
        return time.toString();
    }

    public int getEntryID() {
        return entryID;
    }
}
