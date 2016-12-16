package Main;

import static Main.Main.timerView;

/**
 * Created by Merike on 09-Oct-16.
 */
public class Project {
    EntryList entries = new EntryList();
    private Time totalTime;
    private String name;

    public Project(String name){
        this.name=name;
        this.entries = new EntryList();
        this.totalTime = new Time (0,0,0);
    }

    //lisab uue sissekande, lisab selle andmebaasi ja arvutab uue koguaja
    public void newEntry(Entry entry){
        entries.add(entry);
        totalTime=Time.addTimes(totalTime,entry.getTime());
        timerView.updateTotalTimes();
    }

    //lisab uue sissekande seda andmebaasi lisamata ja arvutab uue koguaja
    public void newEntryWithoutDb(Entry entry){
        entries.addWithoutDb(entry);
        totalTime=Time.addTimes(totalTime,entry.getTime());
        timerView.updateTotalTimes();
    }


    //kustutab sissekande ja arvutab uue koguaja
    public void deleteEntry(Entry entry){
        totalTime=Time.subtractTimes(totalTime, entry.getTime());
        entries.deleteEntry(entry);
        timerView.updateTotalTimes();
    }

    public Time getTotalTime() {
        return totalTime;
    }

    public String getName() {
        return name;
    }
}
