package Main;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Merike on 30-Oct-16.
 */
public class AddEntryWindow {
    static ComboBox<String> projectDropdown;
    static DatePicker datePicker;
    static TextField hourField, minField, secField;
    static Stage window;

    public static void addEntry(){
        String projectName;
        Project project;
        Date date;
        Time time;
        Entry entry;

        projectName = projectDropdown.getValue();
        try {
            project = ProjectList.findProjectByName(projectName);
        } catch (IllegalArgumentException e){
            AlertBox.display("Viga", "Vali mõni projekt");
            project = new Project("UserForgotToChooseTheProject");
        }

        LocalDate localDate = datePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        date = Date.from(instant);

        time = findEnteredTime();

        if (!time.toString().equals(new Time(0,0,0).toString()) && !project.getName().equals(new Project("UserForgotToChooseTheProject").getName())) {
            entry = new Entry(time, date, projectName);
            project.newEntry(entry);
            EntryView.data.add(new TableData(entry.getProjectString(), entry.getDateString(), entry.getTimeString(), entry.getEntryID()));
            TimerView.totalTimeLabel.setText(Main.projectList.getTotalTimesAsString());

            window.close();
        }
    }

    private static Time findEnteredTime(){
        int hours, minutes, seconds;
        Time time;

        if(hourField.getText().trim().equals("")){
            hours = 0;
        } else {
            try {
                hours = Integer.parseInt(hourField.getText().trim());
            } catch (NumberFormatException e) {
                AlertBox.display("Viga", "Viga aja sisestamisel. Lubatud ainult numbrid");
                time = new Time(0, 0, 0);
                return time;
            }
        }

        if(minField.getText().trim().equals("")){
            minutes = 0;
        } else {
            try {
                minutes = Integer.parseInt(minField.getText().trim());
            } catch (NumberFormatException e) {
                AlertBox.display("Viga", "Viga aja sisestamisel. Lubatud ainult numbrid");
                time = new Time(0, 0, 0);
                return time;
            }
        }

        if(secField.getText().trim().equals("")){
            seconds = 0;
        } else {
            try {
                seconds = Integer.parseInt(secField.getText().trim());
            } catch (NumberFormatException e) {
                AlertBox.display("Viga", "Viga aja sisestamisel. Lubatud ainult numbrid");
                time = new Time(0, 0, 0);
                return time;
            }
        }

        time = new Time(hours, minutes, seconds);
        return time;

    }

    public static void display(){
        window = new Stage();
        window.setHeight(250);
        window.setWidth(400);

        Label projectLabel = new Label("Projekt: ");
        Label timeLabel = new Label("Aeg: ");
        Label dateLabel = new Label("Kuupäev: ");

        projectDropdown = new ComboBox<String>();
        int arrayLength = Main.projectList.getProjectList().size();
        for(int i=0; i<arrayLength; i++){
            Project p = Main.projectList.getProject(i);
            String projectName = p.toString();
            projectDropdown.getItems().add(projectName);
        }

        HBox timeField = new HBox();
        hourField = new TextField();
        hourField.setPrefWidth(30);
        hourField.setPromptText("H");
        minField = new TextField();
        minField.setPrefWidth(30);
        minField.setPromptText("M");
        secField = new TextField();
        secField.setPrefWidth(30);
        secField.setPromptText("S");
        Label timeSeparator1 = new Label(" : ");
        Label timeSeparator2 = new Label(" : ");
        timeField.getChildren().addAll(hourField, timeSeparator1, minField, timeSeparator2, secField);

        datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());

        Button okButton = new Button("OK");
        Button cancelButton = new Button("Tühista");
        
        GridPane grid = new GridPane();
        grid.add(projectLabel, 0, 0);
        grid.add(projectDropdown, 1, 0);
        grid.add(timeLabel, 0, 1);
        grid.add(timeField, 1, 1);
        grid.add(dateLabel, 0, 2);
        grid.add(datePicker, 1, 2);
        grid.add(cancelButton, 0, 4);
        grid.add(okButton, 2, 4);
        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.show();

        okButton.setOnAction(event -> addEntry());
        cancelButton.setOnAction(event -> window.close());
    }



}
