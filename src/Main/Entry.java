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



    public Entry(Time t, Date d){
        time = t;
        date = d;
    }

    public void setTime(Time t){
        time = t;
    }

    public void setDate(Date d){
        date = d;
    }

    public Time getTime(){
        return time;
    }

    public Date getDate(){
        return date;
    }

}
