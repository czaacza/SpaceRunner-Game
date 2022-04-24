package view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.SHIP;
import model.ShipPicker;
import model.SpaceRunnerSubScene;

import java.util.List;

public class ViewManager {
    public static final int HEIGHT = 768;
    public static final int WIDTH = 1024;

    private final AnchorPane mainPane;
    private final Scene mainScene;
    private final Stage mainStage;
    private final ButtonManager buttonManager;
    private final SubSceneManager subSceneManager;

    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;

    private List <ShipPicker> shipsList;
    private SHIP chosenShip;

    public ViewManager(){
        subSceneManager = new SubSceneManager(this);
        buttonManager = new ButtonManager(this, subSceneManager);
        mainStage = new Stage();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);

        mainStage.setX(2000);
        mainStage.setY(300);
        mainStage.setResizable(false);
        mainStage.setTitle("Space Runner");
        mainStage.setScene(mainScene);
        createBackground();

        subSceneManager.createSubScenes();
        buttonManager.createMainButtons();

        SpaceRunnerSubScene subScene = new SpaceRunnerSubScene();

        subScene.setLayoutX(200);
        subScene.setLayoutY(100);

    }

    private void createBackground(){
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Image image = new Image("file:src/main/resources/view/blue.png", 256,256, false, true);
        BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }


}
