package Main;

/**
 * Created by Merike on 09-Oct-16.
 */
public class Entry {
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
