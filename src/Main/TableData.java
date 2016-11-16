package Main;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Merike on 29-Oct-16.
 */
public class TableData {
    private SimpleStringProperty project;
    private SimpleStringProperty date;
    private SimpleStringProperty time;
    private SimpleIntegerProperty id;

    public TableData(String p, String d, String t, int id){
        this.project = new SimpleStringProperty(p);
        this.date = new SimpleStringProperty(d);
        this.time = new SimpleStringProperty(t);
        this.id = new SimpleIntegerProperty(id);
    }

    public String getProject() {
        return project.get();
    }

    public SimpleStringProperty projectProperty() {
        return project;
    }

    public void setProject(String project) {
        this.project.set(project);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public Time getTimeObject(){
        String time = getTime();
        int hPlace = 0;
        int mPlace = 0;
        int sPlace = 0;
        int hours, min, sec;

        //leia tähtede h, m ja s asukohad
        for (int i = 0; i < time.length(); i++) {
            if (time.charAt(i) == 'h'){
                hPlace = i;
            }
            if (time.charAt(i) == 'm'){
                mPlace = i;
            }
            if (time.charAt(i) == 's'){
                sPlace = i;
            }
        }

        //eralda tähtede auskohtade põhjal tunnid, minutid, sekundid ja muuda need int-ideks
        hours = Integer.parseInt(time.substring(0,hPlace));
        min = Integer.parseInt(time.substring(hPlace + 2, mPlace));
        sec = Integer.parseInt(time.substring(mPlace + 4, sPlace));

        return new Time (hours, min, sec);
    }

    public int getDay (){
        String date = getDate();
        return Integer.parseInt(date.substring(0,2));
    }

    public int getMonth (){
        String date = getDate();
        return Integer.parseInt((date.substring(3,5)));
    }

    public int getYear () {
        String date = getDate();
        return Integer.parseInt((date.substring(6)));
    }
}
