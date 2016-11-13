package Main;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Merike on 12-Nov-16.
 */
public class Main extends Application {

    static ProjectList projectList = new ProjectList();

    @Override
    public void start(Stage primaryStage) throws Exception {
        new MainWindow();
    }
}
