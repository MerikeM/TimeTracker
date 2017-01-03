package Main;

import java.util.ArrayList;

import static Main.Main.entryView;

/**
 * Created by Merike on 29-Oct-16.
 */
public class EntryList {

    ArrayList<Entry> entryList;

    public EntryList(){
        this.entryList=new ArrayList<Entry>();
    }

    //lisab uue sissekande ja lisab selle andmebaasi
    public void add(Entry entry){
        entryList.add(entry);
        Database db = new Database();
        db.newEntry(entry);
        db.closeConnection();
        entryView.data.add(new TableData(entry.getProjectName(), entry.getDateString(), entry.getTimeString(), entry.getEntryID()));
    }

    //lisab uue sissekande seda andmebaasi lisamata
    public void addWithoutDb(Entry entry){
        entryList.add(entry);
        entryView.data.add(new TableData(entry.getProjectName(), entry.getDateString(), entry.getTimeString(), entry.getEntryID()));
    }

    //kustutab sissekande
    public void deleteEntry(Entry entry){
        entryList.remove(entry);
        int id = entry.getEntryID();
        Database db = new Database();
        db.deleteEntry(id);
        db.closeConnection();
    }

    //väljastab sissekande nr i aja
    public String getTime(int i){
        Entry currentEntry = entryList.get(i);
        Time currentTime = currentEntry.getTime();
        String timeAsString = currentTime.toString();
        return timeAsString;
    }

    //väljastab sissekande nr i kuupäeva
    public String getDate(int i){
        Entry currentEntry = entryList.get(i);
        String dateAsString = currentEntry.getDateString();
        return dateAsString;
    }

}
