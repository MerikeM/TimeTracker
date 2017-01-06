package Main;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 * Created by Merike on 30-Oct-16.
 */
public class AddEntryWindow {
    ProjectList projectList;

    ComboBox<String> projectDropdown;
    DatePicker datePicker;
    TextField hourField, minField, secField;
    Stage window;
    Button okButton;

    public AddEntryWindow (ProjectList pl){
        projectList = pl;
    }

    public void addEntry(){
        String projectName;
        Project project;
        Date date = new Date();
        Time time;
        Entry entry;



        projectName = projectDropdown.getValue();
        try {
            project = projectList.findProjectByName(projectName);
        } catch (IllegalArgumentException e){
            AlertBox alertBox = new AlertBox();
            alertBox.display("Viga", "Vali mõni projekt");
            project = new Project("UserForgotToChooseTheProject");
        }

        if (datePicker.getValue() == null){
            AlertBox alertBox = new AlertBox();
            alertBox.display("Viga", "Vali kuupäev");
        } else {
            LocalDate localDate = datePicker.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            date = Date.from(instant);
        }

        time = findEnteredTime();
        if (time.toString().equals(new Time(0,0,0).toString())){
            AlertBox alertBox = new AlertBox();
            alertBox.display("Viga", "Sisesta aeg");
        }

        if (datePicker.getValue()!=null && !time.toString().equals(new Time(0,0,0).toString()) && !project.getName().equals(new Project("UserForgotToChooseTheProject").getName())) {
            entry = new Entry(time, date, projectName);
            project.newEntry(entry);

            window.close();
        }
    }

    //loob aja objekti sisestatud tundide, minutite ja sekundite põhjal
    private Time findEnteredTime(){
        int hours, minutes, seconds;
        Time time;

        if(hourField.getText().trim().equals("")){
            hours = 0;
        } else {
            try {
                hours = Integer.parseInt(hourField.getText().trim());
            } catch (NumberFormatException e) {
                AlertBox alertBox = new AlertBox();
                alertBox.display("Viga", "Viga aja sisestamisel. Lubatud ainult numbrid");
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
                AlertBox alertBox = new AlertBox();
                alertBox.display("Viga", "Viga aja sisestamisel. Lubatud ainult numbrid");
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
                AlertBox alertBox = new AlertBox();
                alertBox.display("Viga", "Viga aja sisestamisel. Lubatud ainult numbrid");
                time = new Time(0, 0, 0);
                return time;
            }
        }

        time = new Time(hours, minutes, seconds);
        return time;
    }

    //avab sissekannete lisamise akna
    public void setWindow(){

        window = new Stage();
        window.setHeight(250);
        window.setWidth(400);

        Label projectLabel = new Label("Projekt: ");
        Label timeLabel = new Label("Aeg: ");
        Label dateLabel = new Label("Kuupäev: ");

        //drop down menüü projekti valimiseks
        projectDropdown = new ComboBox<String>();
        int arrayLength = projectList.getProjectList().size();
        for(int i=0; i<arrayLength; i++){
            Project p = projectList.getProject(i);
            String projectName = p.getName();
            projectDropdown.getItems().add(projectName);
        }

        //väljad aja sisestamiseks
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

        //kuupäeva valimine
        datePicker = new DatePicker();
        String pattern = "dd.MM.yyyy";
        datePicker.setPromptText(pattern.toLowerCase());
        datePicker.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
        datePicker.setValue(LocalDate.now());

        //OK ja Tühista nupud
        okButton = new Button("OK");
        Button cancelButton = new Button("Tühista");
        HBox buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(cancelButton, okButton);
        buttonsHBox.setPadding(new Insets(20, 0, 0, 10));
        buttonsHBox.setSpacing(10);

        //elementide paigutus
        GridPane grid = new GridPane();
        grid.add(projectLabel, 0, 0);
        grid.add(projectDropdown, 1, 0);
        grid.add(timeLabel, 0, 1);
        grid.add(timeField, 1, 1);
        grid.add(dateLabel, 0, 2);
        grid.add(datePicker, 1, 2);
        grid.add(buttonsHBox, 0, 3);
        Scene scene = new Scene(grid);


        okButton.setOnAction(event -> addEntry());
        cancelButton.setOnAction(event -> window.close());

        window.setScene(scene);
    }

    public void blockParentAndShow(){
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();


    }







}
