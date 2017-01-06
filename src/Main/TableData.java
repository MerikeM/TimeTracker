package Main;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Merike on 29-Oct-16.
 */
public class TableData {
    SimpleStringProperty project;
    SimpleStringProperty date;
    SimpleStringProperty time;
    SimpleIntegerProperty id;

    public TableData(String p, String d, String t, int i){
        project = new SimpleStringProperty(p);
        date = new SimpleStringProperty(d);
        time = new SimpleStringProperty(t);
        id = new SimpleIntegerProperty(i);
    }

    public String getProject() {
        return project.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getTime() {
        return time.get();
    }

    public int getId() {
        return id.get();
    }

    //tagastab aja objektina
    public Time getTimeObject(){
        String time = getTime();
        int hmPlace = 0;
        int msPlace = 0;
        int count = 0;
        int hours, min, sec;

        //leia koolonite põhjal tundide, minutite, sekundite vahekohad
        for (int i = 0; i < time.length(); i++) {
            if (time.charAt(i) == ':' && count == 0){
                hmPlace = i;
                count++;
            }
            if (time.charAt(i) == ':' && count ==1){
                msPlace = i;
            }
        }

        //eralda koolonite asukohtade põhjal tunnid, minutid, sekundid ja muuda need int-ideks
        hours = Integer.parseInt(time.substring(0,hmPlace));
        min = Integer.parseInt(time.substring(hmPlace + 1, msPlace));
        sec = Integer.parseInt(time.substring(msPlace + 1));

        return new Time (hours, min, sec);
    }

    public int getDay (){
        String date = getDate();
        return Integer.parseInt(date.substring(8));
    }

    public int getMonth (){
        String date = getDate();
        return Integer.parseInt((date.substring(5,7)));
    }

    public int getYear () {
        String date = getDate();
        return Integer.parseInt((date.substring(0,4)));
    }
}
