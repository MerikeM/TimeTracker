package Main;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.util.Date;

/**
 * Created by Merike on 12-Nov-16.
 */
public class TimerView {
    ProjectList projectList;

    BorderPane timerArea = new BorderPane();

    Timeline timeline;

    Button startEndButton = new Button("Start");
    Stopwatch stopwatch = new Stopwatch();
    Label timeLabel = new Label("");
    ComboBox<String> projectDropDown = new ComboBox<>();

    Label totalTimeLabel;
    TextField newProjectTextField;
    Button addProjectButton;

    public TimerView(ProjectList pl){
        projectList = pl;

        projectDropDown.setMinWidth(120);
        for (Project project:projectList.getProjectList()
             ) {
            String name = project.getName();
            addToDropDown(name);
        }

        totalTimeLabel = new Label(projectList.getTotalTimesAsString());
        newProjectTextField = new TextField();
        newProjectTextField.setOnKeyPressed(event ->  {
                if (event.getCode().equals(KeyCode.ENTER))
                    addNewProject();
        });

        addProjectButton = new Button("Lisa uus projekt");
    }

    //tagastab Pane taimeri vaatega
    public Pane open (){
        VBox timerVBox = new VBox(20);
        timerVBox.setPadding(new Insets(10,10,10,10));
        timerVBox.getChildren().addAll(projectDropDown, startEndButton, timeLabel);
        timerArea.setCenter(timerVBox);

        VBox projectsVBox = new VBox(20);
        projectsVBox.setPadding(new Insets(10,10,10,10));
        projectsVBox.getChildren().addAll(totalTimeLabel, newProjectTextField, addProjectButton);
        timerArea.setRight(projectsVBox);

        startEndButton.setOnAction(event -> startEndButtonIsClicked());
        addProjectButton.setOnAction(event -> addNewProject());

        return timerArea;
    }

    //käivitab või peatab stopperi
    public void startEndButtonIsClicked() {
        if (stopwatch.isRunning() == false) {
            startStopwatch();
        } else {
            stopStopwatch();
        }
    }

    //käivitab stopperi
    public void startStopwatch(){
        try {
            String currentProjectName = projectDropDown.getValue();
        } catch (IllegalArgumentException e){
            AlertBox alertBox = new AlertBox();
            alertBox.display("Viga", "Vali mõni projekt");
            return;
        }
        stopwatch.start();
        startEndButton.setText("Stopp");
        projectDropDown.setDisable(true);
        updateTimeLabel();
        timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> updateTimeLabel()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    //lõpetab stopperi töö ja lisab selle aja valitud projektile
    public void stopStopwatch(){
        stopwatch.end();
        stopwatch.calcLength();
        Time entryTime = stopwatch.getLength();
        startEndButton.setText("Start");
        projectDropDown.setDisable(false);
        timeline.stop();

        String currentProjectName = projectDropDown.getValue();
        Project currentProject = projectList.findProjectByName(currentProjectName);
        Entry currentEntry = new Entry(entryTime, new Date(), currentProjectName);
        currentProject.newEntry(currentEntry);

        updateTotalTimes();
    }

    //uuendab stopperi näitu
    public void updateTimeLabel(){
        Time currentLength = stopwatch.getCurrentLength();
        String currentLengthString = currentLength.toString();
        timeLabel.setText(currentLengthString);
    }

    //uuendab projektidele kulutatud aja näitu
    public void updateTotalTimes(){
        totalTimeLabel.setText(projectList.getTotalTimesAsString());
    }

    //lisab uue projekti. Projekti nimi pärineb vastavast TextField-ist
    public void addNewProject(){
        String projectName=newProjectTextField.getText();
        newProjectTextField.clear();

        for (int i = 0; i < projectList.getProjectList().size(); i++) {
            Project p = projectList.getProjectList().get(i);
            if (p.getName().equals(projectName)){
                AlertBox alertBox = new AlertBox();
                alertBox.display("Viga", "Sellise nimega projekt on olemas");
                return;
            }
        }

        Project newProject = new Project(projectName);
        projectList.add(newProject);
        updateTotalTimes();
        addToDropDown(projectName);
    }

    //lisab projektide valiku rippmenüüse uue projekti nime
    public void addToDropDown(String name){
        projectDropDown.getItems().add(name);
    }
}