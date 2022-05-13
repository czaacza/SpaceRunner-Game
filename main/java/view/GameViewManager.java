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
import model.EndGameScene;
import model.SHIP;

public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;

    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 800;

    private int playerHealthPoints;
    private int points;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private int angle;
    private AnimationTimer gameTimer;

    private GridPane gridPane1;
    private GridPane gridPane2;
    private final static String BACKGROUND_IMAGE = "file:src/main/resources/view/menu/blue.png";

    GameElements gameElements;

    private final static int SHIP_RADIUS = 45;
    private final static int METEOR_RADIUS = 20;
    private static final int HEALTH_PILL_RADIUS = 12;
    private final static int STAR_RADIUS = 12;

    public GameViewManager() {
        initializeStage();
        createKeyListeners();
        gameElements = new GameElements(GAME_WIDTH, GAME_HEIGHT);
        gameStage.setX(2000);
        gameStage.setY(300);
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

    private void moveGameElements() {
        gameElements.getStar().setLayoutY(gameElements.getStar().getLayoutY() + 4);
        gameElements.getHealthPill().setLayoutY(gameElements.getHealthPill().getLayoutY() + 1.5);

        for (ImageView brownMeteor : gameElements.getBrownMeteors()) {
            brownMeteor.setLayoutY(brownMeteor.getLayoutY() + 3);
            brownMeteor.setRotate(brownMeteor.getRotate() + 4);
        }
        for (ImageView greyMeteor : gameElements.getGreyMeteors()) {
            greyMeteor.setLayoutY(greyMeteor.getLayoutY() + 3);
            greyMeteor.setRotate(greyMeteor.getRotate() + 4);
        }
    }

    public void createNewGame(Stage menuStage, SHIP chosenShip) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackground();
        createGameElements(chosenShip);
        createGameLoop();
        this.gameStage.setResizable(false);
        this.gameStage.setTitle("SpaceRunner");
        this.gameStage.show();
//        EndGameScene endGameScene = new EndGameScene(points, gameStage, menuStage);
//        endGameScene.moveSubScene();
//        gamePane.getChildren().add(endGameScene);
    }

    private void createGameElements(SHIP chosenShip) {
        playerHealthPoints = 3;

        gameElements.createShip(chosenShip);
        gameElements.createStarElement();
        gameElements.createMeteors();
        gameElements.createPointsLabel();
        gameElements.createPlayerHealthImages(chosenShip);
        gameElements.createHealthPill();

        gamePane.getChildren().addAll(
                gameElements.getShip(),
                gameElements.getStar(),
                gameElements.getPointsLabel(),
                gameElements.getHealthPill()
        );
        gamePane.getChildren().addAll(gameElements.getBrownMeteors());
        gamePane.getChildren().addAll(gameElements.getGreyMeteors());
        gamePane.getChildren().addAll(gameElements.getPlayerHealthImages());
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                moveBackground();
                moveGameElements();
                gameElements.reallocateGameElements();
                checkIfCollision();
                moveShip();
            }
        };
        gameTimer.start();
    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    private void moveShip() {
        if (isLeftKeyPressed && !isRightKeyPressed) {
            if (angle > -30) {
                angle -= 5;
            }
            gameElements.getShip().setRotate(angle);
            if (gameElements.getShip().getLayoutX() > -20) {
                gameElements.getShip().setLayoutX(gameElements.getShip().getLayoutX() - 3);
            }

        }
        if (isRightKeyPressed && !isLeftKeyPressed) {
            if (angle < 30) {
                angle += 5;
            }
            gameElements.getShip().setRotate(angle);
            if (gameElements.getShip().getLayoutX() < 522) {
                gameElements.getShip().setLayoutX(gameElements.getShip().getLayoutX() + 3);
            }
        }
        if (!isLeftKeyPressed && !isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            gameElements.getShip().setRotate(angle);
        }
        if (isLeftKeyPressed && isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            gameElements.getShip().setRotate(angle);
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

    private void checkIfCollision() {
        if (calculateDistance(gameElements.getShip().getLayoutX() + gameElements.getShip().getFitWidth() / 2, gameElements.getStar().getLayoutX() + 15.5, gameElements.getShip().getLayoutY() + gameElements.getShip().getFitHeight() / 2, gameElements.getStar().getLayoutY() + 15) < SHIP_RADIUS + STAR_RADIUS) {
            gameElements.setNewElementPosition(gameElements.getStar());
            points++;
            String textToSet = "POINTS : ";
            if (points < 10) {
                textToSet = textToSet + "00";
            }
            if (points >= 10 && points < 100) {
                textToSet = textToSet + "0";
            }
            textToSet = textToSet + points;
            gameElements.getPointsLabel().setText(textToSet);
        }

        if (calculateDistance(gameElements.getShip().getLayoutX() + gameElements.getShip().getFitWidth() / 2, gameElements.getHealthPill().getLayoutX() + 12.5, gameElements.getShip().getLayoutY() + gameElements.getShip().getFitHeight() / 2, gameElements.getHealthPill().getLayoutY() + 12.5) < SHIP_RADIUS + HEALTH_PILL_RADIUS) {
            addPlayerLife();
            gameElements.setNewElementPosition(gameElements.getHealthPill());
        }

        for (ImageView brownMeteor : gameElements.getBrownMeteors()) {
            if (calculateDistance(gameElements.getShip().getLayoutX() + gameElements.getShip().getFitWidth() / 2, brownMeteor.getLayoutX() + 22.5, gameElements.getShip().getLayoutY() + gameElements.getShip().getFitHeight() / 2, brownMeteor.getLayoutY() + 20) < SHIP_RADIUS + METEOR_RADIUS) {
                removePlayerLife();
                gameElements.setNewElementPosition(brownMeteor);
            }
        }
        for (ImageView greyMeteor : gameElements.getGreyMeteors()) {
            if (calculateDistance(gameElements.getShip().getLayoutX() + gameElements.getShip().getFitWidth() / 2, greyMeteor.getLayoutX() + 22.5, gameElements.getShip().getLayoutY() + gameElements.getShip().getFitHeight() / 2, greyMeteor.getLayoutY() + 20) < SHIP_RADIUS + METEOR_RADIUS) {
                removePlayerLife();
                gameElements.setNewElementPosition(greyMeteor);
            }
        }
    }

    private void addPlayerLife() {
        if(playerHealthPoints < 3){
            gamePane.getChildren().add(gameElements.getPlayerHealthImages()[playerHealthPoints]);
            playerHealthPoints++;
        }
    }

    private void removePlayerLife() {
        gamePane.getChildren().remove(gameElements.getPlayerHealthImages()[playerHealthPoints - 1]);
        playerHealthPoints--;
        if (playerHealthPoints <= 0) {
            EndGameScene endGameScene = new EndGameScene(points, gameStage, menuStage);
            gamePane.getChildren().add(endGameScene);
            endGameScene.moveSubScene();
            gameTimer.stop();

        }
    }

    private double calculateDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

}
