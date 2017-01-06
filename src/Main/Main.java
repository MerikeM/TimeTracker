package Main;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Merike on 12-Nov-16.
 */
public class Main extends Application {

    ProjectList projectList = new ProjectList();
    MainWindow mainWindow;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainWindow = new MainWindow(projectList);
        Database db = new Database(projectList);
        db.readData();
        db.closeConnection();
        mainWindow.open();
    }
}
