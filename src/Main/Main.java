package Main; /**
 * Created by Merike on 03-Oct-16.
 */

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class Main extends Application{

    //ülemine menüü
    Button timerButton = new Button("Taimer");
    Button entryButton = new Button("Lisa/muuda sissekandeid");
    Button statistikaButton = new Button("Statistika");

    //stopperi osa
    Button startEndButton;
    Stopwatch stopwatch = new Stopwatch();
    Label timeLabel;
    ChoiceBox<String> projectDropDown = new ChoiceBox<>();

    //projektide osa
    TextField newProjectTextField = new TextField();
    Button addProjectButton = new Button("Lisa uus projekt");




    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("lihtne taimer");

       timeLabel = new Label("Aeg: " + stopwatch.getLength());




        startEndButton = new Button("Start");
            startEndButton.setOnAction(e -> {
                if (stopwatch.isRunning()==false){
                    stopwatch.start();
                    startEndButton.setText("Stopp");
                }else{
                    stopwatch.end();
                    stopwatch.calcLength();
                    timeLabel.setText("Aeg: " + stopwatch.getLength());
                    startEndButton.setText("Start");
                }

        });

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
        rightLayout.getChildren().addAll(newProjectTextField, addProjectButton);

        BorderPane layout = new BorderPane();
        layout.setTop(upperLayout);
        layout.setCenter(leftLayout);
        layout.setRight(rightLayout);

        Scene scene = new Scene(layout, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();




    }
}
