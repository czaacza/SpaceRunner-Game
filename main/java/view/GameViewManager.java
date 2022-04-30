package view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.SHIP;

public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 800;

    private Stage menuStage;
    private ImageView ship;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private int angle;
    private AnimationTimer gameTimer;

    private GridPane gridPane1;
    private GridPane gridPane2;
    private final static String BACKGROUND_IMAGE = "file:src/main/resources/model/blue.png";

    public GameViewManager() {
        initializeStage();
        createKeyListeners();
    }

    private void createKeyListeners() {

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.A) {
                    isLeftKeyPressed = true;
                } else if (keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.D) {
                    isRightKeyPressed = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.A) {
                    isLeftKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.D) {
                    isRightKeyPressed = false;
                }
            }
        });
    }

    public void createNewGame(Stage menuStage, SHIP chosenShip) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackground();
        createGameLoop();
        createShip(chosenShip);
        this.gameStage.setResizable(false);
        this.gameStage.setTitle("SpaceRunner");
        this.gameStage.show();
    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    private void createShip(SHIP chosenShip) {
        ship = new ImageView(chosenShip.getShipURL());
        ship.setLayoutX(0.5 * GAME_WIDTH - 49.5);
        ship.setLayoutY(GAME_HEIGHT - 90);
        gamePane.getChildren().add(ship);
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                moveBackground();
                moveShip();
            }
        };

        gameTimer.start();
    }

    private void moveShip() {
        if (isLeftKeyPressed && !isRightKeyPressed) {
            if(angle > -30){
                angle -=5;
            }
            ship.setRotate(angle);
            if(ship.getLayoutX() > -20){
                ship.setLayoutX(ship.getLayoutX()-3);
            }

        }
        if (isRightKeyPressed && !isLeftKeyPressed) {
            if(angle < 30){
                angle += 5;
            }
            ship.setRotate(angle);
            if(ship.getLayoutX() < 522){
                ship.setLayoutX(ship.getLayoutX()+3);
            }
        }
        if (!isLeftKeyPressed && !isRightKeyPressed) {
            if(angle < 0){
                angle +=5;
            } else if(angle > 0){
                angle -=5;
            }
            ship.setRotate(angle);
        }
        if (isLeftKeyPressed && isRightKeyPressed) {
            if(angle < 0){
                angle +=5;
            } else if(angle > 0){
                angle -=5;
            }
            ship.setRotate(angle);
        }
    }

    private void createBackground(){
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for(int i = 0; i< 12; i++){
            ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
            ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
            GridPane.setConstraints(backgroundImage1, i % 3, i/3);
            GridPane.setConstraints(backgroundImage2, i % 3, i/3);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }
        gridPane2.setLayoutY(-1023);

        gamePane.getChildren().addAll(gridPane1, gridPane2);

    }

    private void moveBackground(){
        gridPane1.setLayoutY(gridPane1.getLayoutY()+1.5);
        gridPane2.setLayoutY(gridPane2.getLayoutY()+1.5);

        if(gridPane1.getLayoutY() >= 1020){
            gridPane1.setLayoutY(-1024);
        }
        if(gridPane2.getLayoutY() >= 1020){
            gridPane2.setLayoutY(-1024);
        }

    }

}
