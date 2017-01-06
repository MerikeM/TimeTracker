package Main;

import java.util.ArrayList;

/**
 * Created by Merike on 29-Oct-16.
 */
public class EntryList {
    ArrayList<Entry> entryList = new ArrayList<Entry>();

    //lisab uue sissekande ja lisab selle andmebaasi
    public void add(Entry entry){
        entryList.add(entry);
        Database db = new Database();
        db.newEntry(entry);
        db.closeConnection();
    }

    //lisab uue sissekande seda andmebaasi lisamata
    public void addWithoutDb(Entry entry) {
        entryList.add(entry);
    }

    //kustutab sissekande
    public void deleteEntry(Entry entry){
        entryList.remove(entry);
        int id = entry.getEntryID();
        Database db = new Database();
        db.deleteEntry(id);
        db.closeConnection();
    }
}
