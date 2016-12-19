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
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.util.Date;

import static Main.Main.entryView;
import static Main.Main.mainWindow;

/**
 * Created by Merike on 12-Nov-16.
 */
public class TimerView {
    Timeline timeline;

    Button startEndButton;
    Stopwatch stopwatch;
    Label timeLabel;
    ComboBox<String> projectDropDown;

    Label totalTimeLabel;
    TextField newProjectTextField;
    Button addProjectButton;

    public TimerView(){
        startEndButton = new Button("Start");
        stopwatch = new Stopwatch();
        timeLabel = new Label("");
        projectDropDown = new ComboBox<>();
        projectDropDown.setMinWidth(120);
        for (Project project:Main.projectList.getProjectList()
             ) {
            String name = project.getName();
            addToDropDown(name);
        }

        totalTimeLabel = new Label(Main.projectList.getTotalTimesAsString());
        newProjectTextField = new TextField();
        newProjectTextField.setOnKeyPressed(event ->  {
                if (event.getCode().equals(KeyCode.ENTER))
                    addNewProject();
        });

        addProjectButton = new Button("Lisa uus projekt");
    }

    //avab taimeri vaate
    public void open (){
        VBox timerVBox = new VBox(20);
        timerVBox.setPadding(new Insets(10,10,10,10));
        timerVBox.getChildren().addAll(projectDropDown, startEndButton, timeLabel);
        mainWindow.pane.setCenter(timerVBox);

        VBox projectsVBox = new VBox(20);
        projectsVBox.setPadding(new Insets(10,10,10,10));
        projectsVBox.getChildren().addAll(totalTimeLabel, newProjectTextField, addProjectButton);
        mainWindow.pane.setRight(projectsVBox);

        startEndButton.setOnAction(event -> startEndButtonIsClicked());
        addProjectButton.setOnAction(event -> addNewProject());
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
            Project currentProject = Main.projectList.findProjectByName(currentProjectName);
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
        Project currentProject = Main.projectList.findProjectByName(currentProjectName);
        Entry currentEntry = new Entry(entryTime, new Date(), currentProjectName);
        currentProject.newEntry(currentEntry);
    }

    //uuendab stopperi näitu
    public void updateTimeLabel(){
        Time currentLength = stopwatch.getCurrentLength();
        String currentLengthString = currentLength.toString();
        timeLabel.setText(currentLengthString);
    }

    //uuendab projektidele kulutatud aja näitu
    public void updateTotalTimes(){
        totalTimeLabel.setText(Main.projectList.getTotalTimesAsString());
    }

    //lisab uue projekti. Projekti nimi pärineb vastavast TextField-ist
    public void addNewProject(){
        String projectName=newProjectTextField.getText();
        newProjectTextField.clear();

        for (int i = 0; i < Main.projectList.getProjectList().size(); i++) {
            Project p = Main.projectList.getProjectList().get(i);
            if (p.getName().equals(projectName)){
                AlertBox alertBox = new AlertBox();
                alertBox.display("Viga", "Sellise nimega projekt on olemas");
                return;
            }
        }

        Project newProject = new Project(projectName);
        Main.projectList.add(newProject);
        updateTotalTimes();
    }

    public void addToDropDown(String name){
        projectDropDown.getItems().add(name);
    }
}
