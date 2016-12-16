package Main;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static Main.Main.entryView;
import static Main.Main.statisticsView;
import static Main.Main.timerView;


/**
 * Created by Merike on 12-Nov-16.
 */
public class MainWindow {

    BorderPane pane = new BorderPane();
    private Scene scene = new Scene(pane, 600, 500);



    public MainWindow(){
        startStage();
        setUpperMenu();
        setClickEvent();
    }

    public void display(){
        timerView.open();
    }

    //nuppude funktsioonid
    private void setClickEvent() {
        timerView.startEndButton.setOnAction(event -> timerView.startEndButtonIsClicked());
        timerView.addProjectButton.setOnAction(event -> timerView.addNewProject());
        entryView.selfAddButton.setOnAction(event -> entryView.AddEntryManual());
        entryView.changeButton.setOnAction(event -> entryView.changeEntry());
        entryView.deleteButton.setOnAction(event -> entryView.deleteEntry());
    }

    //loob ülemise menüüriba
    private void setUpperMenu() {
        Button timerButton = new Button("Taimer");
        Button entryButton = new Button("Lisa/muuda sissekandeid");
        Button statisticsButton = new Button("Statistika");

        HBox upperMenu = new HBox(20);
        upperMenu.setPadding(new Insets(10,10,10,10));
        upperMenu.getChildren().addAll(timerButton, entryButton, statisticsButton);

        timerButton.setOnAction(event -> timerView.open());
        entryButton.setOnAction(event -> entryView.open());
        statisticsButton.setOnAction(event -> statisticsView.open());

        pane.setTop(upperMenu);
    }

    private void startStage(){
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
    }

}
