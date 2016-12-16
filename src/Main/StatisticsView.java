package Main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import static Main.Main.mainWindow;


/**
 * Created by Merike on 14-Dec-16.
 */
public class StatisticsView {
    Button projectsButton, progressButton, goalsButton;

    Label startLabel, endLabel;
    DatePicker startPicker, endPicker;
    Button submitDateButton;

    Button currentDay, currentWeek, currentMonth, currentYear;
    Button lastDay, lastWeek, lastMonth, lastYear;



    public StatisticsView(){
        projectsButton = new Button ("Projektid");
        progressButton = new Button("Progress");
        goalsButton = new Button ("Eesmärgid");

        startLabel = new Label("Algus: ");
        endLabel = new Label("Lõpp: ");
        submitDateButton = new Button("OK");
        startPicker = new DatePicker();
        endPicker = new DatePicker();

        currentDay = new Button("Praegune päev");
        currentWeek = new Button("Praegune nädal");
        currentMonth = new Button("Praegune kuu");
        currentYear = new Button("Praegune aasta");
        lastDay = new Button("Eelmine päev");
        lastWeek = new Button("Eelmine nädal");
        lastMonth = new Button("Eelmine kuu");
        lastYear = new Button("Eelmine aasta");
    }

    public void open(){
        VBox statisticsMenu = new VBox(20);
        statisticsMenu.setPadding(new Insets(10,10,10,10));
        statisticsMenu.getChildren().addAll(projectsButton, progressButton, goalsButton);
        mainWindow.pane.setRight(statisticsMenu);

        GridPane startEndChooser = new GridPane();
        startEndChooser.add(startLabel, 1, 2);
        startEndChooser.add(endLabel, 1, 3);
        startEndChooser.add(startPicker, 2, 2);
        startEndChooser.add(endPicker, 2, 3);
        startEndChooser.add(submitDateButton, 1, 4);
        startEndChooser.add(currentDay, 4, 1);
        startEndChooser.add(currentWeek, 4, 2);
        startEndChooser.add(currentMonth, 4, 3);
        startEndChooser.add(currentYear, 4, 4);
        startEndChooser.add(lastDay, 5, 1);
        startEndChooser.add(lastWeek, 5, 2);
        startEndChooser.add(lastMonth, 5, 3);
        startEndChooser.add(lastYear, 5, 4);

        BorderPane statisticsArea = new BorderPane();
        statisticsArea.setTop(startEndChooser);
        mainWindow.pane.setCenter(statisticsArea);
    }

    public void calculateTimes(){

    }
}
