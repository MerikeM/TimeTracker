package Main; /**
 * Created by Merike on 03-Oct-16.
 */

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.*;

public class Main extends Application{

    Button startEndButton;
    Stopwatch stopwatch = new Stopwatch();


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("lihtne taimer");

        Label label = new Label("Aeg: " + stopwatch.getLength());




    startEndButton = new Button("Start");
        startEndButton.setOnAction(e -> {
            if (stopwatch.running==false){
                stopwatch.start();
                startEndButton.setText("Stopp");
            }else{
                stopwatch.end();
                stopwatch.getLength();
                label.setText("Aeg: " + stopwatch.length);
                startEndButton.setText("Start");
            }

        });



        VBox layout = new VBox(20);
        layout.getChildren().addAll(startEndButton, label);
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();




    }
}
