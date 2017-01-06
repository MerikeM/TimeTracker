package Main;

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
        Time oldTotal = totalTime;
        entries.add(entry);
        totalTime=Time.addTimes(totalTime,entry.getTime());
        if ((int)totalTime.getTimeInHours()/10>(int)oldTotal.getTimeInHours()/10){
            int border = ((int)totalTime.getTimeInHours()/10)*10;
            String message = "Palju õnne! Projekt " + name + " ületas " + border + " tunni piiri!";
            AlertBox.display("Eesmärk täidetud", message);
        }
    }

    //lisab uue sissekande seda andmebaasi lisamata ja arvutab uue koguaja
    public void newEntryWithoutDb(Entry entry){
        entries.addWithoutDb(entry);
        totalTime=Time.addTimes(totalTime,entry.getTime());
    }


    //kustutab sissekande ja arvutab uue koguaja
    public void deleteEntry(Entry entry){
        totalTime=Time.subtractTimes(totalTime, entry.getTime());
        entries.deleteEntry(entry);
    }

    public Time getTotalTime() {
        return totalTime;
    }

    public String getName() {
        return name;
    }
}
