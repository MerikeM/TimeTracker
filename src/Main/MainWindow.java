package Main;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Merike on 12-Nov-16.
 */
public class MainWindow {
    ProjectList projectList;

    EntryView entryView;
    StatisticsView statisticsView;

    Stage window;
    BorderPane pane = new BorderPane();
    private Scene scene = new Scene(pane, 600, 500);



    public MainWindow(ProjectList pl){
        projectList = pl;

        startStage();
        setUpperMenu();
    }

    public void open(){
        TimerView timerView = new TimerView(projectList);
        pane.setCenter(timerView.open());
    }

    //loob ülemise menüüriba
    private void setUpperMenu() {
        Button timerButton = new Button("Taimer");
        Button entryButton = new Button("Lisa/muuda sissekandeid");
        Button statisticsButton = new Button("Statistika");

        HBox upperMenu = new HBox(20);
        upperMenu.setPadding(new Insets(10,10,10,10));
        upperMenu.getChildren().addAll(timerButton, entryButton, statisticsButton);

        timerButton.setOnAction(event -> pane.setCenter(openTimerView()));
        entryButton.setOnAction(event -> pane.setCenter(openEntryView()));
        statisticsButton.setOnAction(event -> pane.setCenter(openStatisticsView()));

        pane.setTop(upperMenu);
    }

    public Pane openTimerView(){
        TimerView timerView = new TimerView(projectList);
        return (timerView.open());
    }

    public Pane openEntryView(){
        EntryView entryView = new EntryView(projectList);
        return entryView.open();
    }

    public Pane openStatisticsView(){
        StatisticsView statisticsView = new StatisticsView(projectList);
        return statisticsView.open();
    }



    private void startStage(){
        window = new Stage();
        window.setScene(scene);
        window.show();
    }

}
