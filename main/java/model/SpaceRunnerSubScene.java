package model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class SpaceRunnerSubScene extends SubScene {

    private final static String FONT_PATH = "src/main/resources/model/kenvector_future.ttf";
    private final static String BACKGROUND_IMAGE = "src/main/resources/model/blue_button10.png";
    protected static boolean isHidden = true;

    public SpaceRunnerSubScene() {
        super(new AnchorPane(), 600, 400);
        prefWidth(600);
        prefHeight(400);

        BackgroundImage backgroundImage = new BackgroundImage(new Image("file:" + BACKGROUND_IMAGE, 600, 400, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane actualRoot = (AnchorPane) this.getRoot();
        actualRoot.setBackground(new Background(backgroundImage));

        setLayoutX(1024);
        setLayoutY(180);

    }

    public void moveSubScene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if(isHidden){
            transition.setToX(-676);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = true;
        }
        System.out.println("isHidden: " + SpaceRunnerSubScene.getIsHidden());
        transition.play();
    }

    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }

    public static boolean getIsHidden() {
        return isHidden;
    }
}
