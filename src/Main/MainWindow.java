package Main;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Merike on 12-Nov-16.
 */
public class MainWindow {
    ProjectList projectList;

    Stage window;
    BorderPane pane = new BorderPane();
    Scene scene = new Scene(pane, 600, 500);

    public MainWindow(ProjectList pl){
        projectList = pl;
    }

    //avab mainWindow taimeri vaates
    public void open(){
        startStage();
        setUpperMenu();
        openTimerView();
    }

    //loob stage
    public void startStage(){
        window = new Stage();
        window.setScene(scene);
        window.show();
    }

    //loob ülemise menüüriba
    public void setUpperMenu() {
        Button timerButton = new Button("Taimer");
        Button entryButton = new Button("Lisa/muuda sissekandeid");
        Button statisticsButton = new Button("Statistika");

        HBox upperMenu = new HBox(20);
        upperMenu.setPadding(new Insets(10,10,10,10));
        upperMenu.getChildren().addAll(timerButton, entryButton, statisticsButton);

        timerButton.setOnAction(event -> openTimerView());
        entryButton.setOnAction(event -> openEntryView());
        statisticsButton.setOnAction(event -> openStatisticsView());

        pane.setTop(upperMenu);
    }

    //avab taimeri vaate
    public void openTimerView(){
        TimerView timerView = new TimerView(projectList);
        pane.setCenter(timerView.open());
    }

    //avab sissekannete vaate
    public void openEntryView(){
        EntryView entryView = new EntryView(projectList);
        pane.setCenter(entryView.open());
    }

    //avab statistika vaate
    public void openStatisticsView(){
        StatisticsView statisticsView = new StatisticsView(projectList);
        pane.setCenter(statisticsView.open());
    }
}