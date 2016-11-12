package Main;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Merike on 30-Oct-16.
 */
public class AddEntryWindow {


    public static void display(){
        Stage window = new Stage();
        window.setHeight(250);
        window.setWidth(400);

        Label projectLabel = new Label("Projekt: ");
        Label timeLabel = new Label("Aeg: ");
        Label dateLabel = new Label("Kuupäev: ");

        ComboBox<String> projectDropdown = new ComboBox<String>();
        int arrayLength = MainOld.allProjects.size();
        for(int i=0; i<arrayLength; i++){
            Project p = MainOld.allProjects.get(i);
            String projectName = p.toString();
            projectDropdown.getItems().add(projectName);
        }

        HBox timeField = new HBox();
        TextField hourField = new TextField();
        hourField.setPrefWidth(30);
        hourField.setPromptText("H");
        TextField minField = new TextField();
        minField.setPrefWidth(30);
        minField.setPromptText("M");
        TextField secField = new TextField();
        secField.setPrefWidth(30);
        secField.setPromptText("S");
        Label timeSeparator1 = new Label(" : ");
        Label timeSeparator2 = new Label(" : ");
        timeField.getChildren().addAll(hourField, timeSeparator1, minField, timeSeparator2, secField);

        DatePicker datePicker = new DatePicker();

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





    }



}
