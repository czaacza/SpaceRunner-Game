package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage mainStage) throws IOException {
        ViewManager viewManager = new ViewManager();
        mainStage = viewManager.getMainStage();
        mainStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}