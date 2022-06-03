package view;

import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
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

import java.util.Arrays;
import java.util.Random;

import static javafx.util.Duration.seconds;

public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;

    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 800;

    private int playerHealthPoints;
    private int points;
    private double [] brownMeteorSpeed;
    private double [] greyMeteorSpeed;
    private final double starSpeed = 5.0;
    private final double healthPillSpeed = 1.5;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private int angle;
    private AnimationTimer gameTimer;
    private boolean wasJustHit = false;

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
        gameElements.getStar().setLayoutY(gameElements.getStar().getLayoutY() + starSpeed);
        gameElements.getHealthPill().setLayoutY(gameElements.getHealthPill().getLayoutY() + healthPillSpeed);

        for (int i = 0; i < gameElements.getBrownMeteors().length; i++) {
            gameElements.getBrownMeteors()[i].setLayoutY(gameElements.getBrownMeteors()[i].getLayoutY() + brownMeteorSpeed[i]);
            gameElements.getBrownMeteors()[i].setRotate(gameElements.getBrownMeteors()[i].getRotate() + 4);
        }
        for (int i = 0; i < gameElements.getGreyMeteors().length; i++) {
            gameElements.getGreyMeteors()[i].setLayoutY(gameElements.getGreyMeteors()[i].getLayoutY() + greyMeteorSpeed[i]);
            gameElements.getGreyMeteors()[i].setRotate(gameElements.getGreyMeteors()[i].getRotate() + 4);
        }
    }


    public void createNewGame(Stage menuStage, SHIP chosenShip) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        gameElements = new GameElements(GAME_WIDTH, GAME_HEIGHT, points);
        this.gameElements.setChosenShip(chosenShip);
        createBackground();
        createGameElements();
        createGameLoop();
        this.gameStage.setResizable(false);
        this.gameStage.setTitle("SpaceRunner");
        this.gameStage.show();
//        EndGameScene endGameScene = new EndGameScene(points, gameStage, menuStage);
//        endGameScene.moveSubScene();
//        gamePane.getChildren().add(endGameScene);
    }

    private void createGameElements() {
        playerHealthPoints = 3;

        gameElements.createShip();
        gameElements.createStarElement();
        gameElements.createMeteors();
        gameElements.createPointsLabel();
        gameElements.createPlayerHealthImages();
        gameElements.createHealthPill();
        gameElements.createDamageImages();
        brownMeteorSpeed = new double[gameElements.getBrownMeteors().length];
        greyMeteorSpeed = new double[gameElements.getGreyMeteors().length];
        Arrays.fill(brownMeteorSpeed, 3.0);
        Arrays.fill(brownMeteorSpeed, 3.0);

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
                reallocateGameElements();
                checkIfCollision();
                moveShip();
            }
        };
        gameTimer.start();
    }

    private void reallocateGameElements() {
        if (gameElements.getStar().getLayoutY() > 1200) {
            gameElements.setNewElementPosition(gameElements.getStar());
        }

        if(gameElements.getHealthPill().getLayoutY() > 1200){
            gameElements.setNewElementPosition(gameElements.getHealthPill());
        }

        for (int i = 0; i < gameElements.getBrownMeteors().length; i++) {
            if (gameElements.getBrownMeteors()[i].getLayoutY() > 900) {
                gameElements.setNewElementPosition(gameElements.getBrownMeteors()[i]);
                brownMeteorSpeed[i] = 3.0 + points * 0.5;
            }
        }

        for (int i = 0; i < gameElements.getGreyMeteors().length; i++) {
            if (gameElements.getGreyMeteors()[i].getLayoutY() > 900) {
                gameElements.setNewElementPosition(gameElements.getGreyMeteors()[i]);
                greyMeteorSpeed[i] = 3.0 + points * 0.5;
            }
        }
    }
    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    private void moveShip() {
        int shipSpeed = 3;
        if (gameElements.getChosenShip() == SHIP.INVADER) {
            shipSpeed = 4;
        }
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
            if (wasJustHit) {
                if (angle < 0) {
                    angle += 2;
                } else if (angle > 0) {
                    angle -= 2;
                } else {
                    wasJustHit = false;
                }
            } else {
                if (angle < 0) {
                    angle += 5;
                } else if (angle > 0) {
                    angle -= 5;
                }
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
        //star
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

        //healthPill
        if (calculateDistance(gameElements.getShip().getLayoutX() + gameElements.getShip().getFitWidth() / 2, gameElements.getHealthPill().getLayoutX() + 12.5, gameElements.getShip().getLayoutY() + gameElements.getShip().getFitHeight() / 2, gameElements.getHealthPill().getLayoutY() + 12.5) < SHIP_RADIUS + HEALTH_PILL_RADIUS) {
            addPlayerLife();
            gameElements.setNewElementPosition(gameElements.getHealthPill());
        }

        //meteors
        for (ImageView brownMeteor : gameElements.getBrownMeteors()) {
            if (calculateDistance(gameElements.getShip().getLayoutX() + gameElements.getShip().getFitWidth() / 2, brownMeteor.getLayoutX() + 22.5, gameElements.getShip().getLayoutY() + gameElements.getShip().getFitHeight() / 2, brownMeteor.getLayoutY() + 20) < SHIP_RADIUS + METEOR_RADIUS) {
                removePlayerLife();
                gameElements.setNewElementPosition(brownMeteor);
                generateDamageAnimation();
            }
        }
        for (ImageView greyMeteor : gameElements.getGreyMeteors()) {
            if (calculateDistance(gameElements.getShip().getLayoutX() + gameElements.getShip().getFitWidth() / 2, greyMeteor.getLayoutX() + 22.5, gameElements.getShip().getLayoutY() + gameElements.getShip().getFitHeight() / 2, greyMeteor.getLayoutY() + 20) < SHIP_RADIUS + METEOR_RADIUS) {
                removePlayerLife();
                gameElements.setNewElementPosition(greyMeteor);
                generateDamageAnimation();
            }
        }
    }

    private void generateDamageAnimation() {
        Random random = new Random();
        String damageImageUrl = gameElements.getDamageImageUrls().get(random.nextInt(2));

        ImageView damageImage = new ImageView(damageImageUrl);
        damageImage.setLayoutX(gameElements.getShip().getLayoutX());
        damageImage.setLayoutY(gameElements.getShip().getLayoutY() - 50);
        gamePane.getChildren().add(damageImage);

        ScaleTransition scaleTransition = new ScaleTransition(seconds(0.2), damageImage);
        RotateTransition rotateTransition = new RotateTransition(seconds(0.2), damageImage);


        scaleTransition.setByX(1.2);
        scaleTransition.setByY(1.2);
        rotateTransition.setByAngle(15);
        scaleTransition.setOnFinished(e -> damageImage.setVisible(false));

        scaleTransition.play();
        rotateTransition.play();
        wasJustHit = true;
        angle = new Random().nextInt(40) - 40;
    }

    private void addPlayerLife() {
        if (playerHealthPoints < 3) {
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
