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

import java.util.Random;

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

    private final static String METEOR_BROWN_IMAGE = "file:src/main/resources/meteorBrown_med3.png";
    private final static String METEOR_GREY_IMAGE = "file:src/main/resources/meteorGrey_med1.png";

    private ImageView[] brownMeteors;
    private ImageView[] greyMeteors;
    Random randomPositionGenerator;


    public GameViewManager() {
        initializeStage();
        createKeyListeners();
        randomPositionGenerator = new Random();
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
        createShip(chosenShip);
        createGameElements();
        createGameLoop();


        this.gameStage.setResizable(false);
        this.gameStage.setTitle("SpaceRunner");
        this.gameStage.show();
    }

    private void createGameElements() {
        brownMeteors = new ImageView[3];
        for (ImageView brownMeteor : brownMeteors) {
            brownMeteor = new ImageView(METEOR_BROWN_IMAGE);
            setNewElementPosition(brownMeteor);
            gamePane.getChildren().add(brownMeteor);
        }
        greyMeteors = new ImageView[3];
        for (ImageView greyMeteor : greyMeteors) {
            greyMeteor = new ImageView(METEOR_GREY_IMAGE);
            setNewElementPosition(greyMeteor);
            gamePane.getChildren().add(greyMeteor);
        }
    }

    private void moveGameElements(){
        for (ImageView brownMeteor : brownMeteors) {
            brownMeteor.setLayoutY(brownMeteor.getLayoutY() + 7);
            brownMeteor.setRotate(brownMeteor.getRotate() + 4);
        }
        for (ImageView greyMeteor : greyMeteors) {
            greyMeteor.setLayoutY(greyMeteor.getLayoutY() + 7);
            greyMeteor.setRotate(greyMeteor.getRotate() + 4);
        }
    }

    private void reallocateGameElements(){
        for (ImageView brownMeteor : brownMeteors) {
            if (brownMeteor.getLayoutY() > 900) {
                setNewElementPosition(brownMeteor);
            }
        }
        for (ImageView greyMeteor : greyMeteors) {
            if (greyMeteor.getLayoutY() > 900) {
                setNewElementPosition(greyMeteor);
            }
        }
    }

    private void setNewElementPosition(ImageView meteor) {
        meteor.setLayoutX(randomPositionGenerator.nextInt(370));
        meteor.setLayoutY(-(randomPositionGenerator.nextInt(3200) + 600));
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
                moveGameElements();
                reallocateGameElements();
                moveShip();
            }
        };

        gameTimer.start();
    }

    private void moveShip() {
        if (isLeftKeyPressed && !isRightKeyPressed) {
            if (angle > -30) {
                angle -= 5;
            }
            ship.setRotate(angle);
            if (ship.getLayoutX() > -20) {
                ship.setLayoutX(ship.getLayoutX() - 3);
            }

        }
        if (isRightKeyPressed && !isLeftKeyPressed) {
            if (angle < 30) {
                angle += 5;
            }
            ship.setRotate(angle);
            if (ship.getLayoutX() < 522) {
                ship.setLayoutX(ship.getLayoutX() + 3);
            }
        }
        if (!isLeftKeyPressed && !isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            ship.setRotate(angle);
        }
        if (isLeftKeyPressed && isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            ship.setRotate(angle);
        }
    }

    private void createBackground() {
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for (int i = 0; i < 12; i++) {
            ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
            ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
            GridPane.setConstraints(backgroundImage1, i % 3, i / 3);
            GridPane.setConstraints(backgroundImage2, i % 3, i / 3);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }
        gridPane2.setLayoutY(-1023);

        gamePane.getChildren().addAll(gridPane1, gridPane2);

    }

    private void moveBackground() {
        gridPane1.setLayoutY(gridPane1.getLayoutY() + 1.5);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 1.5);

        if (gridPane1.getLayoutY() >= 1020) {
            gridPane1.setLayoutY(-1024);
        }
        if (gridPane2.getLayoutY() >= 1020) {
            gridPane2.setLayoutY(-1024);
        }

    }

}
