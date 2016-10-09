package Main;
import java.util.ArrayList;

/**
 * Created by Merike on 09-Oct-16.
 */
public class Project {
    private ArrayList<Entry> entries;
    private Time totalTime;

    public Project(){
        entries = new ArrayList<Entry>();
        totalTime = new Time (0,0,0);
    }

    public void newEntry(Entry e){
        entries.add(e);
        totalTime=Time.addTimes(totalTime,e.getTime());
    }

    public Time getTotalTime(){
        return totalTime;
    }
}
