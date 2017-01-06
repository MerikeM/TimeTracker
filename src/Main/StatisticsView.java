package Main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Created by Merike on 14-Dec-16.
 */
public class StatisticsView {
    ProjectList projectList;

    BorderPane statisticsArea = new BorderPane();

    Button projectsButton, progressButton, goalsButton;

    public StatisticsView(ProjectList pl){
        projectList = pl;

        projectsButton = new Button ("Projektid");
        progressButton = new Button("Progress");
        goalsButton = new Button ("Eesmärgid");
    }

    //tagastab Pane statistika vaatega
    public Pane open(){
        VBox statisticsMenu = new VBox(20);
        statisticsMenu.setPadding(new Insets(10,10,10,10));
        statisticsMenu.getChildren().addAll(projectsButton, progressButton, goalsButton);
        statisticsArea.setRight(statisticsMenu);
        StatisticsViewProjects statisticsViewProjects = new StatisticsViewProjects(projectList);
        StatisticsViewProgress statisticsViewProgress = new StatisticsViewProgress(projectList);
        StatisticsViewGoals statisticsViewGoals = new StatisticsViewGoals(projectList);
        projectsButton.setOnAction(event -> statisticsArea.setCenter(statisticsViewProjects.open()));
        progressButton.setOnAction(event -> statisticsArea.setCenter(statisticsViewProgress.open()));
        goalsButton.setOnAction(event -> statisticsArea.setCenter(statisticsViewGoals.open()));
        statisticsArea.setCenter(statisticsViewProjects.open());

        return statisticsArea;
    }
}