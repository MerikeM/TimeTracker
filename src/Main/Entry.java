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



    public Entry(Time t, Date d, String p){
        this.time = t;
        this.date = d;
        this.project = p;
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
}
