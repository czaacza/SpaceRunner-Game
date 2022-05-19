package view;

import javafx.scene.image.ImageView;
import model.SHIP;
import model.SmallInfoLabel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameElements {
    private int GAME_WIDTH;
    private int GAME_HEIGHT;

    private ImageView ship;
    private SHIP chosenShip;

    private ImageView[] brownMeteors;
    private ImageView[] greyMeteors;
    private final static String METEOR_BROWN_IMAGE = "file:src/main/resources/model/game_models/meteorBrown_med3.png";
    private final static String METEOR_GREY_IMAGE = "file:src/main/resources/model/game_models/meteorGrey_med1.png";


    private SmallInfoLabel pointsLabel;
    private ImageView[] playerHealthImages;

    private ImageView healthPill;
    private final static String HEALTH_PILL_IMAGE = "file:src/main/resources/model/game_models/pill_red.png";

    private ImageView star;
    private final static String GOLD_STAR_IMAGE = "file:src/main/resources/model/game_models/star_gold.png";

    private List<String> damageImageUrls;

    Random randomPositionGenerator;

    public GameElements(int GAME_WIDTH, int GAME_HEIGHT) {
        this.GAME_WIDTH = GAME_WIDTH;
        this.GAME_HEIGHT = GAME_HEIGHT;
        randomPositionGenerator = new Random();
    }

    public void createShip() {
        ship = new ImageView(chosenShip.getShipURL());
        ship.setLayoutX(0.5 * GAME_WIDTH - 49.5);
        if(chosenShip == SHIP.UFO){
            ship.setLayoutY(GAME_HEIGHT - 95);
        } else{
            ship.setLayoutY(GAME_HEIGHT - 90);
        }
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
    }

    public void createStarElement() {
        star = new ImageView(GOLD_STAR_IMAGE);
        setNewElementPosition(star);
    }

    public void createPointsLabel() {
        pointsLabel = new SmallInfoLabel("POINTS: 000");
        pointsLabel.setLayoutX(450);
        pointsLabel.setLayoutY(20);
    }

    public void createPlayerHealthImages() {
        playerHealthImages = new ImageView[3];
        for (int i = 0; i < playerHealthImages.length; i++) {
            playerHealthImages[i] = new ImageView(chosenShip.getLifeURL());
            if (chosenShip == SHIP.UFO) {
                playerHealthImages[i].setFitWidth(33);
                playerHealthImages[i].setFitHeight(33);
            }
            playerHealthImages[i].setLayoutY(80);
            playerHealthImages[i].setLayoutX(455 + (i * 50));
        }
    }

    public void createMeteors() {
        brownMeteors = new ImageView[3];
        for (int i = 0; i < brownMeteors.length; i++) {
            brownMeteors[i] = new ImageView(METEOR_BROWN_IMAGE);
            setNewElementPosition(brownMeteors[i]);
        }

        greyMeteors = new ImageView[3];
        for (int i = 0; i < greyMeteors.length; i++) {
            greyMeteors[i] = new ImageView(METEOR_GREY_IMAGE);
            setNewElementPosition(greyMeteors[i]);
        }
    }

    public void createHealthPill(){
        healthPill = new ImageView(HEALTH_PILL_IMAGE);
        healthPill.setFitWidth(25);
        healthPill.setFitHeight(25);
        setNewElementPosition(healthPill);
    }

    public void createDamageImages(){
        damageImageUrls = new ArrayList<>();
        damageImageUrls.add("file:src/main/resources/model/game_models/playerShip1_damage1.png");
        damageImageUrls.add("file:src/main/resources/model/game_models/playerShip2_damage1.png");
        damageImageUrls.add("file:src/main/resources/model/game_models/playerShip3_damage1.png");
    }

    public ImageView getShip() {
        return ship;
    }

    public ImageView[] getBrownMeteors() {
        return brownMeteors;
    }

    public ImageView[] getGreyMeteors() {
        return greyMeteors;
    }

    public SmallInfoLabel getPointsLabel() {
        return pointsLabel;
    }

    public ImageView[] getPlayerHealthImages() {
        return playerHealthImages;
    }

    public ImageView getHealthPill() {
        return healthPill;
    }

    public ImageView getStar() {
        return star;
    }

    public SHIP getChosenShip() {
        return chosenShip;
    }

    public void setChosenShip(SHIP chosenShip) {
        this.chosenShip = chosenShip;
    }

    public List<String> getDamageImageUrls() {
        return damageImageUrls;
    }

    public void setNewElementPosition(ImageView element) {
        element.setLayoutX(randomPositionGenerator.nextInt(370));
        element.setLayoutY(-(randomPositionGenerator.nextInt(3200) + 600));
    }

    public void reallocateGameElements() {
        if (star.getLayoutY() > 1200) {
            setNewElementPosition(star);
        }
        if(healthPill.getLayoutY() > 1200){
            setNewElementPosition(healthPill);
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

}
