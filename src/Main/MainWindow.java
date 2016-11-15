package Main;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Merike on 12-Nov-16.
 */
public class MainWindow {

    static BorderPane pane = new BorderPane();
    private Scene scene = new Scene(pane, 600, 500);

    TimerView timerView = new TimerView();
    EntryView entryView = new EntryView();

    public MainWindow(){
        startStage();
        setUpperMenu();
        timerView.open();
        setClickEvent();
    }

    private void setClickEvent() {
        timerView.startEndButton.setOnAction(event -> TimerView.startEndButtonIsClicked());
        timerView.addProjectButton.setOnAction(event -> TimerView.addNewProject());
        entryView.selfAddButton.setOnAction(event -> EntryView.AddEntryManual());
        entryView.deleteButton.setOnAction(event -> EntryView.DeleteEntry());
    }

    private void setUpperMenu() {
        Button timerButton = new Button("Taimer");
        Button entryButton = new Button("Lisa/muuda sissekandeid");
        Button statisticsButton = new Button("Statistika");

        HBox upperMenu = new HBox(20);
        upperMenu.setPadding(new Insets(10,10,10,10));
        upperMenu.getChildren().addAll(timerButton, entryButton, statisticsButton);

        timerButton.setOnAction(event -> timerView.open());
        entryButton.setOnAction(event -> entryView.open());

        pane.setTop(upperMenu);
    }

    private void startStage(){
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
    }

}
