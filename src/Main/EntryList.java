package Main;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Merike on 29-Oct-16.
 */
public class EntryList {

    ArrayList<Entry> entryList;

    public EntryList(){
        this.entryList=new ArrayList<Entry>();
    }

    //lisab uue sissekande
    public void add(Entry entry){
        entryList.add(entry);
    }

    //kustutab sissekande
    public void deleteEntry(Entry entry){
        entryList.remove(entry);
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
