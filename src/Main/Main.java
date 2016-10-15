package Main; /**
 * Created by Merike on 03-Oct-16.
 */

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.util.Duration;
import java.util.ArrayList;



public class Main extends Application{

    ArrayList<Project> allProjects = new ArrayList<Project>(); //sisaldab kõiki loodud projekte
    Timeline timeline;

    //TAIMERI VAADE

    //ülemise menüü elemendid
    Button timerButton = new Button("Taimer");
    Button entryButton = new Button("Lisa/muuda sissekandeid");
    Button statistikaButton = new Button("Statistika");

    //stopperi osa elemendid
    Button startEndButton;
    Stopwatch stopwatch = new Stopwatch();
    Label timeLabel = new Label("");
    ComboBox<String> projectDropDown = new ComboBox<>();

    //projektide osa elemendid
    Label totalTimeLabel = new Label(getTotalTimes(allProjects));
    TextField newProjectTextField = new TextField();
    Button addProjectButton = new Button("Lisa uus projekt");

    //SISSEKANNETE VAADE
    TableView<Entry> entryTabel = new TableView<Entry>();
    Button selfAddButton = new Button("Lisa");
    Button changeButton = new Button("Muuda");
    Button deleteButton = new Button("Kustuta");




    public static void main(String[] args){
        launch(args);
    }




    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("lihtne taimer");





        //stopperi osa
        projectDropDown.setMinWidth(120);


        startEndButton = new Button("Start");
            startEndButton.setOnAction(e -> {
                if (stopwatch.isRunning()==false){
                    startStopwatch();
                }else{
                    stopStopwatch();
                }

        });

        //projektide osa
        addProjectButton.setOnAction(e -> addNewProject());
        newProjectTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER))
                    addNewProject();
            }
        });


        //LAYOUT

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

        //sissekannete osa
        VBox rightLayoutEntries = new VBox();
        rightLayoutEntries.setPadding(new Insets(10,10,10,10));
        rightLayoutEntries.getChildren().addAll(selfAddButton, changeButton, deleteButton);
        StackPane tablePane = new StackPane();
        tablePane.getChildren().add(entryTabel);

        //taimer vaade
        BorderPane Layout = new BorderPane();
        Layout.setTop(upperLayout);
        Layout.setCenter(leftLayout);
        Layout.setRight(rightLayout);

        Scene scene = new Scene(Layout, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        //sissekannete vaade

        entryButton.setOnAction(e -> {
            Layout.setCenter(tablePane);
            Layout.setRight(rightLayoutEntries);
        });









    }

    // koostab sõne, mis sisaldab kõiki projekte ja nende koguaegu
    public String getTotalTimes(ArrayList<Project> list){
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            Project p = list.get(i);
            String s = p.toString() + ": " + p.getTotalTime() + "\n";
            result = result + s;
        }
        return result;
    }

    //Sisendiks projekte sisaldav ArrayList ja otsitava projekti nimi.
    //Väljastab otsitava nimega projekti.

    public Project findProjectByName(ArrayList<Project> list, String name){
        for (int i = 0; i < list.size(); i++) {
            Project p = list.get(i);
            if (p.toString().equals(name)){
                return p;
            }
        }
        System.out.println("Ei leidnud projekti nimega " + name);
        throw new IllegalArgumentException("sellist projekti ei ole");
    }

    //Lisab uue projekti. Projekti nimi pärineb vastavast TextField-ist
    public void addNewProject(){
        String projectName=newProjectTextField.getText();
        newProjectTextField.clear();

        for (int i = 0; i < allProjects.size(); i++) {
            Project p = allProjects.get(i);
            if (p.getName().equals(projectName)){
                AlertBox.display("Viga", "Sellise nimega projekt on olemas");
                return;
            }
        }

        Project newProject = new Project(projectName);
        projectDropDown.getItems().add(newProject.toString());
        allProjects.add(newProject);
        totalTimeLabel.setText(getTotalTimes(allProjects));
    }

    //Käivitab stopperi
    public void startStopwatch(){
        try {
            String currentProjectName = projectDropDown.getValue();
            Project currentProject = findProjectByName(allProjects, currentProjectName);
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
    public void stopStopwatch(){
        stopwatch.end();
        stopwatch.calcLength();
        Time entryTime = stopwatch.getLength();
        startEndButton.setText("Start");
        projectDropDown.setDisable(false);
        timeline.stop();

        String currentProjectName = projectDropDown.getValue();
        Project currentProject = findProjectByName(allProjects, currentProjectName);
        Entry currentEntry = new Entry(entryTime, new Date(13,10,2016));
        currentProject.newEntry(currentEntry);

        totalTimeLabel.setText(getTotalTimes(allProjects));

    }

    //uuendab stopperi näitu
    public void updateTimeLabel(){
        Time currentLength = stopwatch.getCurrentLength();
        String currentLengthString = currentLength.toString();
        timeLabel.setText(currentLengthString);
    }







}
