package Main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import static Main.Main.mainWindow;


/**
 * Created by Merike on 14-Dec-16.
 */
public class StatisticsView {
    Button projectsButton, progressButton, goalsButton;

    public StatisticsView(){
        projectsButton = new Button ("Projektid");
        progressButton = new Button("Progress");
        goalsButton = new Button ("Eesmärgid");
    }

    public void open(){
        VBox statisticsMenu = new VBox(20);
        statisticsMenu.setPadding(new Insets(10,10,10,10));
        statisticsMenu.getChildren().addAll(projectsButton, progressButton, goalsButton);
        mainWindow.pane.setRight(statisticsMenu);
        StatisticsViewProjects statisticsViewProjects = new StatisticsViewProjects();
        statisticsViewProjects.open();
    }


}
