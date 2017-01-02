package Main;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static Main.Main.mainWindow;
import static Main.Main.projectList;

/**
 * Created by Merike on 02-Jan-17.
 */
public class StatisticsViewGoals {
    BorderPane goalsArea;
    ProgressBar tenBar, hundredBar, thousandBar, tenThousandBar;
    Label tenLabel, hundredLabel, thousandLabel, tenThousandLabel;
    HBox projectBox;
    ChoiceBox<String> projectChooser;
    Button okButton;
    GridPane progressBars;

    public StatisticsViewGoals() {
        goalsArea = new BorderPane();
        projectChooser = new ChoiceBox<>();
        okButton = new Button("OK");
        projectBox = new HBox();

    }

    public void open(){
        for (Project p : projectList.projectList){
            projectChooser.getItems().addAll(p.getName());
        }

        projectBox.getChildren().addAll(projectChooser, okButton);
        goalsArea.setTop(projectBox);
        mainWindow.pane.setCenter(goalsArea);

        okButton.setOnAction(event -> calcStat());

    }

    public void calcStat(){
        progressBars = new GridPane();

        Project project = projectList.findProjectByName(projectChooser.getValue());
        Time totalTime = project.getTotalTime();
        double hours = totalTime.getTimeInHours();

        //10
        tenLabel = new Label();
        tenBar = new ProgressBar();
        int xTen = (int)hours/10;
        double leftTen = hours%10;
        String tenText = xTen*10 + " -> " + (xTen+1)*10 + ": ";
        tenLabel.setText(tenText);
        tenBar.setProgress(leftTen/10);

        tenBar.setPrefSize(300,30);

        progressBars.add(tenLabel, 0, 0);
        progressBars.add(tenBar, 1, 0);

        //100
        hundredLabel = new Label();
        hundredBar = new ProgressBar();
        int xHundred = (int)hours/100;
        double leftHundred = hours%100;
        String hundredText = xHundred * 100 + " -> " + (xHundred+1)*100 + ": ";
        hundredLabel.setText(hundredText);
        hundredBar.setProgress(leftHundred/100);

        hundredBar.setPrefSize(300,30);

        progressBars.add(hundredLabel, 0, 1);
        progressBars.add(hundredBar, 1, 1);

        //1000
        thousandLabel = new Label();
        thousandBar = new ProgressBar();
        int xThousand = (int)hours/1000;
        double leftThousand = hours%1000;
        String thousandText = xThousand * 1000 + " -> " + (xThousand+1)*1000 + ": ";
        thousandLabel.setText(thousandText);
        thousandBar.setProgress(leftThousand/1000);

        thousandBar.setPrefSize(300,30);

        progressBars.add(thousandLabel, 0, 2);
        progressBars.add(thousandBar, 1, 2);

        //10000
        tenThousandLabel = new Label();
        tenThousandBar = new ProgressBar();
        int xTenThousand = (int)hours/10000;
        double leftTenThousand = hours%10000;
        String tenThousandText = xTenThousand * 10000 + " -> " + (xTenThousand+1)*10000 + ": ";
        tenThousandLabel.setText(tenThousandText);
        tenThousandBar.setProgress(leftTenThousand/10000);

        tenThousandBar.setPrefSize(300,30);

        progressBars.add(tenThousandLabel, 0, 3);
        progressBars.add(tenThousandBar, 1, 3);

        goalsArea.setCenter(progressBars);
    }

}
