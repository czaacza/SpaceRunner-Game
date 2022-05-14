package view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.LogoLabel;
import model.SHIP;
import model.ShipPicker;

import java.util.List;

public class ViewManager {
    public static final int HEIGHT = 768;
    public static final int WIDTH = 1024;

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private ButtonManager buttonManager;
    private SubSceneManager subSceneManager;
    private LogoLabel logoLabel;

    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;

    private List<ShipPicker> shipsList;
    private SHIP chosenShip;

    public ViewManager() {
        mainStage = new Stage();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);

        subSceneManager = new SubSceneManager(this);
        buttonManager = new ButtonManager(this, subSceneManager);

        mainStage.setX(2000);
        mainStage.setY(300);
        mainStage.setResizable(false);
        mainStage.setTitle("Space Runner");
        mainStage.setScene(mainScene);
        createBackground();
        createLogoLabel();
        subSceneManager.createSubScenes();
        buttonManager.createMainButtons();

    }

    private void createBackground() {
        Image image = new Image("file:src/main/resources/view/blue.png", 256, 256, false, true);
        BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void createLogoLabel(){
        logoLabel = new LogoLabel("SPACE RUNNER", 48);
        logoLabel.setLayoutX(380);
        logoLabel.setLayoutY(50);
        mainPane.getChildren().add(logoLabel);
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

}
