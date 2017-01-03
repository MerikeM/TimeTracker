package Main;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Merike on 12-Nov-16.
 */
public class Main extends Application {

    static ProjectList projectList = new ProjectList();
    MainWindow mainWindow;
    static TimerView timerView;
    static EntryView entryView;
    static StatisticsView statisticsView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        timerView = new TimerView();
        entryView = new EntryView();
        statisticsView = new StatisticsView();
        mainWindow = new MainWindow();
        Database db = new Database();
        db.readData();
        db.closeConnection();
        mainWindow.open();
    }
}
