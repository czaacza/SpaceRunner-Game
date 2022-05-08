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
import model.SmallInfoLabel;

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
    private final static String BACKGROUND_IMAGE = "file:src/main/resources/view/menu/blue.png";

    private final static String METEOR_BROWN_IMAGE = "file:src/main/resources/model/game_models/meteorBrown_med3.png";
    private final static String METEOR_GREY_IMAGE = "file:src/main/resources/model/game_models/meteorGrey_med1.png";

    private ImageView[] brownMeteors;
    private ImageView[] greyMeteors;
    Random randomPositionGenerator;

    private ImageView star;
    private SmallInfoLabel pointsLabel;
    private ImageView[] playerLivesImages;
    private int playerLifePoints;
    private int points;
    private final static String GOLD_STAR_IMAGE = "file:src/main/resources/model/game_models/star_gold.png";

    private final static int STAR_RADIUS = 12;
    private final static int SHIP_RADIUS = 45;
    private final static int METEOR_RADIUS = 20;


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
        createGameElements(chosenShip);
        createGameLoop();

        this.gameStage.setResizable(false);
        this.gameStage.setTitle("SpaceRunner");
        this.gameStage.show();
    }

    private void createGameElements(SHIP chosenShip) {
        playerLifePoints = 3;

        createStarElement();
        createPointsLabel();
        createPlayerLivesImages(chosenShip);
        createMeteors();

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

    private void setNewElementPosition(ImageView element) {
        element.setLayoutX(randomPositionGenerator.nextInt(370));
        element.setLayoutY(-(randomPositionGenerator.nextInt(3200) + 600));
    }

    private void moveGameElements() {
        star.setLayoutY(star.getLayoutY() + 4);

        for (ImageView brownMeteor : this.brownMeteors) {
            brownMeteor.setLayoutY(brownMeteor.getLayoutY() + 3);
            brownMeteor.setRotate(brownMeteor.getRotate() + 4);
        }
        for (ImageView greyMeteor : this.greyMeteors) {
            greyMeteor.setLayoutY(greyMeteor.getLayoutY() + 3);
            greyMeteor.setRotate(greyMeteor.getRotate() + 4);
        }
    }

    private void reallocateGameElements() {

        if (star.getLayoutY() > 1200) {
            setNewElementPosition(star);
        }

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
        int shipFitWidth;
        int shipFitHeight;
        if (chosenShip == SHIP.INVADER) {
            shipFitWidth = 99;
            shipFitHeight = 75;
        } else if (chosenShip == SHIP.GUARDIAN) {
            shipFitWidth = 112;
            shipFitHeight = 75;
        } else if (chosenShip == SHIP.ROGUE) {
            shipFitWidth = 98;
            shipFitHeight = 75;
        } else {
            shipFitWidth = 91;
            shipFitHeight = 91;
        }
        ship.setFitWidth(shipFitWidth);
        ship.setFitHeight(shipFitHeight);
        gamePane.getChildren().add(ship);
        System.out.println(ship.getFitWidth() + " " + ship.getFitHeight());
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

    private void createStarElement() {
        star = new ImageView(GOLD_STAR_IMAGE);
        setNewElementPosition(star);
        gamePane.getChildren().add(star);
    }

    private void createPointsLabel() {
        pointsLabel = new SmallInfoLabel("POINTS: 000");
        pointsLabel.setLayoutX(450);
        pointsLabel.setLayoutY(20);
        gamePane.getChildren().add(pointsLabel);
    }

    private void createPlayerLivesImages(SHIP chosenShip) {
        playerLivesImages = new ImageView[3];
        for (int i = 0; i < playerLivesImages.length; i++) {
            playerLivesImages[i] = new ImageView(chosenShip.getLifeURL());
            if (chosenShip == SHIP.UFO) {
                playerLivesImages[i].setFitWidth(33);
                playerLivesImages[i].setFitHeight(33);
            }
            playerLivesImages[i].setLayoutY(80);
            playerLivesImages[i].setLayoutX(455 + (i * 50));
            gamePane.getChildren().add(playerLivesImages[i]);
        }
    }

    private void createMeteors() {
        brownMeteors = new ImageView[3];
        for (int i = 0; i < brownMeteors.length; i++) {
            brownMeteors[i] = new ImageView(METEOR_BROWN_IMAGE);
            setNewElementPosition(brownMeteors[i]);
            gamePane.getChildren().add(brownMeteors[i]);
        }

        greyMeteors = new ImageView[3];
        for (int i = 0; i < greyMeteors.length; i++) {
            greyMeteors[i] = new ImageView(METEOR_GREY_IMAGE);
            setNewElementPosition(greyMeteors[i]);
            gamePane.getChildren().add(greyMeteors[i]);
        }
    }

    private void checkIfCollision() {
        if (calculateDistance(ship.getLayoutX() + ship.getFitWidth() / 2, star.getLayoutX() + 15.5, ship.getLayoutY() + ship.getFitHeight() / 2, star.getLayoutY() + 15) < SHIP_RADIUS + STAR_RADIUS) {
            setNewElementPosition(star);
            points++;
            String textToSet = "POINTS : ";
            if (points < 10) {
                textToSet = textToSet + "00";
            }
            if (points >= 10 && points < 100) {
                textToSet = textToSet + "0";
            }
            textToSet = textToSet + points;
            pointsLabel.setText(textToSet);
        }

        for (ImageView brownMeteor : brownMeteors) {
            if (calculateDistance(ship.getLayoutX() + ship.getFitWidth() / 2, brownMeteor.getLayoutX() + 22.5, ship.getLayoutY() + ship.getFitHeight() / 2, brownMeteor.getLayoutY() + 20) < SHIP_RADIUS + METEOR_RADIUS) {
                removePlayerLife();
                setNewElementPosition(brownMeteor);
            }
        }
        for (ImageView greyMeteor : greyMeteors) {
            if (calculateDistance(ship.getLayoutX() + ship.getFitWidth() / 2, greyMeteor.getLayoutX() + 22.5, ship.getLayoutY() + ship.getFitHeight() / 2, greyMeteor.getLayoutY() + 20) < SHIP_RADIUS + METEOR_RADIUS) {
                removePlayerLife();
                setNewElementPosition(greyMeteor);
            }
        }
    }

    private void removePlayerLife() {
        gamePane.getChildren().remove(playerLivesImages[playerLifePoints - 1]);
        playerLifePoints--;
        if (playerLifePoints <= 0) {
            gameStage.close();
            gameTimer.stop();
            menuStage.show();
        }
    }

    private double calculateDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

}
