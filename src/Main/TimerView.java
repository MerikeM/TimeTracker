package Main;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Merike on 12-Nov-16.
 */
public class TimerView {
    static Timeline timeline;

    static Button startEndButton;
    static Stopwatch stopwatch;
    static Label timeLabel;
    static ComboBox<String> projectDropDown;

    static Label totalTimeLabel;
    static TextField newProjectTextField;
    static Button addProjectButton;

    public TimerView(){
        startEndButton = new Button("Start");
        stopwatch = new Stopwatch();
        timeLabel = new Label("");
        projectDropDown = new ComboBox<>();
        projectDropDown.setMinWidth(120);

        totalTimeLabel = new Label(Main.projectList.getTotalTimesAsString());
        newProjectTextField = new TextField();
        addProjectButton = new Button("Lisa uus projekt");
    }

    public void open (){
        VBox timerVBox = new VBox(20);
        timerVBox.setPadding(new Insets(10,10,10,10));
        timerVBox.getChildren().addAll(projectDropDown, startEndButton, timeLabel);
        MainWindow.pane.setCenter(timerVBox);

        VBox projectsVBox = new VBox(20);
        projectsVBox.setPadding(new Insets(10,10,10,10));
        projectsVBox.getChildren().addAll(totalTimeLabel, newProjectTextField, addProjectButton);
        MainWindow.pane.setRight(projectsVBox);
    }

    public static void startEndButtonIsClicked() {
        if (stopwatch.isRunning() == false) {
            startStopwatch();
        } else {
            stopStopwatch();
        }
    }

    //Käivitab stopperi
    public static void startStopwatch(){
        try {
            String currentProjectName = projectDropDown.getValue();
            Project currentProject = findProjectByName(Main.projectList.getProjectList(), currentProjectName);
        } catch (IllegalArgumentException e){
            AlertBox.display("Viga", "Vali mõni projekt");
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

    //Lõpetab stopperi töö ja lisab selle aja valitud projektile
    public static void stopStopwatch(){

        stopwatch.end();
        stopwatch.calcLength();
        Time entryTime = stopwatch.getLength();
        startEndButton.setText("Start");
        projectDropDown.setDisable(false);
        timeline.stop();


        String currentProjectName = projectDropDown.getValue();
        Project currentProject = findProjectByName(Main.projectList.getProjectList(), currentProjectName);
        Entry currentEntry = new Entry(entryTime, new Date(), currentProjectName);
        currentProject.newEntry(currentEntry);
        EntryView.data.add(new TableData(currentProjectName, currentEntry.getDateString(), currentEntry.getTimeString(), currentEntry.getEntryID()));



        totalTimeLabel.setText(getTotalTimes(Main.projectList.getProjectList()));


    }

    //uuendab stopperi näitu
    public static void updateTimeLabel(){
        Time currentLength = stopwatch.getCurrentLength();
        String currentLengthString = currentLength.toString();
        timeLabel.setText(currentLengthString);
    }

    //Sisendiks projekte sisaldav ArrayList ja otsitava projekti nimi.
    //Väljastab otsitava nimega projekti.
    public static Project findProjectByName(ArrayList<Project> list, String name){
        for (int i = 0; i < list.size(); i++) {
            Project p = list.get(i);
            if (p.toString().equals(name)){
                return p;
            }
        }
        System.out.println("Ei leidnud projekti nimega " + name);
        throw new IllegalArgumentException("sellist projekti ei ole");
    }

    // koostab sõne, mis sisaldab kõiki projekte ja nende koguaegu
    public static String getTotalTimes(ArrayList<Project> list){
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            Project p = list.get(i);
            String s = p.toString() + ": " + p.getTotalTime() + "\n";
            result = result + s;
        }
        return result;
    }

    //Lisab uue projekti. Projekti nimi pärineb vastavast TextField-ist
    public static void addNewProject(){
        String projectName=newProjectTextField.getText();
        newProjectTextField.clear();

        for (int i = 0; i < Main.projectList.getProjectList().size(); i++) {
            Project p = Main.projectList.getProjectList().get(i);
            if (p.getName().equals(projectName)){
                AlertBox.display("Viga", "Sellise nimega projekt on olemas");
                return;
            }
        }

        Project newProject = new Project(projectName);
        projectDropDown.getItems().add(newProject.toString());
        Main.projectList.getProjectList().add(newProject);
        totalTimeLabel.setText(getTotalTimes(Main.projectList.getProjectList()));
    }


}
