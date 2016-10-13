package Main; /**
 * Created by Merike on 03-Oct-16.
 */

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.util.ArrayList;

public class Main extends Application{

    ArrayList<Project> allProjects = new ArrayList<Project>();

    Project projekt = new Project("projekt");


    //ülemine menüü
    Button timerButton = new Button("Taimer");
    Button entryButton = new Button("Lisa/muuda sissekandeid");
    Button statistikaButton = new Button("Statistika");

    //stopperi osa
    Button startEndButton;
    Stopwatch stopwatch = new Stopwatch();
    Label timeLabel;
    ComboBox<String> projectDropDown = new ComboBox<>();

    //projektide osa
    Label totalTimeLabel = new Label(getTotalTimes(allProjects));
    TextField newProjectTextField = new TextField();
    Button addProjectButton = new Button("Lisa uus projekt");




    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("lihtne taimer");
        //ülemine menüü


        //stopperi osa

        projectDropDown.setMinWidth(120);


        timeLabel = new Label("Aeg: " + stopwatch.getLength());

        startEndButton = new Button("Start");
            startEndButton.setOnAction(e -> {
                if (stopwatch.isRunning()==false){
                    startStopwatch();
                }else{
                    stopStopwatch();
                }

        });

        //projekti osa

        addProjectButton.setOnAction(e -> addNewProject());


        //ülemine menüü
        HBox upperLayout = new HBox(20);
        upperLayout.setPadding(new Insets(10,10,10,10));
        upperLayout.getChildren().addAll(timerButton, entryButton, statistikaButton);

        //stopperi osa
        VBox leftLayout = new VBox(20);
        leftLayout.setPadding(new Insets(10,10,10,10));
        leftLayout.getChildren().addAll(projectDropDown, startEndButton, timeLabel);

        //projetide osa
        VBox rightLayout = new VBox(20);
        rightLayout.setPadding(new Insets(10,10,10,10));
        rightLayout.getChildren().addAll(totalTimeLabel, newProjectTextField, addProjectButton);

        BorderPane layout = new BorderPane();
        layout.setTop(upperLayout);
        layout.setCenter(leftLayout);
        layout.setRight(rightLayout);

        Scene scene = new Scene(layout, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();




    }

    public String getTotalTimes(ArrayList<Project> list){
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            Project p = list.get(i);
            String s = p.toString() + ": " + p.getTotalTime() + "\n";
            result = result + s;
        }
        return result;
    }

    public Project findProjectByName(ArrayList<Project> list, String name){
        for (int i = 0; i < list.size(); i++) {
            Project p = list.get(i);
            if (p.toString().equals(name)){
                return p;
            }
        }
        System.out.println("Ei leidnud projekti nimega " + name);
        return new Project("nimetu");
    }

    public void addNewProject(){
        String projectName=newProjectTextField.getText();
        newProjectTextField.clear();
        Project newProject = new Project(projectName);
        projectDropDown.getItems().add(newProject.toString());
        allProjects.add(newProject);
        totalTimeLabel.setText(getTotalTimes(allProjects));
    }

    public void startStopwatch(){
        stopwatch.start();
        startEndButton.setText("Stopp");
    }

    public void stopStopwatch(){
        stopwatch.end();
        stopwatch.calcLength();
        Time entryTime = stopwatch.getLength();
        timeLabel.setText("Aeg: " + entryTime);
        startEndButton.setText("Start");

        String currentProjectName = projectDropDown.getValue();
        Project currentProject = findProjectByName(allProjects, currentProjectName);
        Entry currentEntry = new Entry(entryTime, new Date(13,10,2016));
        currentProject.newEntry(currentEntry);
        

        totalTimeLabel.setText(getTotalTimes(allProjects));

    }







}
