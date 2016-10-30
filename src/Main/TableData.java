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

    public double getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }
}
