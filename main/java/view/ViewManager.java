package view;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.SHIP;
import model.ShipPicker;
import model.SpaceRunnerButton;
import model.SpaceRunnerSubScene;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {
    public static final int HEIGHT = 768;
    public static final int WIDTH = 1024;

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private ButtonManager buttonManager;
    private SubSceneManager subSceneManager;

    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;

    private List <ShipPicker> shipsList;
    private SHIP chosenShip;

    public ViewManager(){
        subSceneManager = new SubSceneManager(this);
        buttonManager = new ButtonManager(this, subSceneManager);
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();

        mainStage.setX(2000);
        mainStage.setY(300);
        mainStage.setResizable(false);
        mainStage.setTitle("Space Runner");
        mainStage.setScene(mainScene);
        createBackground();

        subSceneManager.createSubScenes();
        buttonManager.createButtons();

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

    public HBox createShipsToChoose(){
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        shipsList = new ArrayList<ShipPicker>();
        for(SHIP ship : SHIP.values()){
            ShipPicker shipToPick = new ShipPicker(ship);
            shipsList.add(shipToPick);
            shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    for(ShipPicker ship : shipsList){
                        ship.setCircleChosen(false);
                    }
                    shipToPick.setCircleChosen(true);
                    chosenShip = shipToPick.getShip();
                }
            });
        }
        hBox.getChildren().addAll(shipsList);
        hBox.setLayoutX(300 - (110 * 2));
        hBox.setLayoutY(120);

        return hBox;
    }

    public SpaceRunnerButton createStartGameButton(){
        String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url(grey_button01.png);";
        String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url(grey_button00.png);";

        SpaceRunnerButton startGameButton = new SpaceRunnerButton("START", BUTTON_FREE_STYLE, BUTTON_PRESSED_STYLE);
        startGameButton.setLayoutX(200);
        startGameButton.setLayoutY(295);

        return startGameButton;
    }


    public Stage getMainStage() {
        return mainStage;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }


}
