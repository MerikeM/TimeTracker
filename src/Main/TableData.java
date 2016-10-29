package Main;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Merike on 29-Oct-16.
 */
public class TableData {
    private SimpleStringProperty project;
    private SimpleStringProperty date;
    private SimpleStringProperty time;

    public TableData(String p, String d, String t){
        this.project = new SimpleStringProperty(p);
        this.date = new SimpleStringProperty(d);
        this.time = new SimpleStringProperty(t);
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
}
