package model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class SpaceRunnerSubScene extends SubScene {

    private final static String FONT_PATH = "src/main/resources/model/kenvector_future.ttf";
    private final static String BACKGROUND_IMAGE = "src/main/resources/view/menu/blue_button10.png";
    protected static boolean isHidden = true;

    public SpaceRunnerSubScene() {
        super(new AnchorPane(), 600, 400);
        prefWidth(600);
        prefHeight(400);
        setSubSceneBackground(BACKGROUND_IMAGE);
        setLayoutX(1024);
        setLayoutY(180);
    }

    protected void setSubSceneBackground(String backgroundURL){
        BackgroundImage backgroundImage = new BackgroundImage(new Image("file:" + backgroundURL, 600, 400, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane actualRoot = (AnchorPane) this.getRoot();
        actualRoot.setBackground(new Background(backgroundImage));
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
        transition.play();
    }

    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }

    public static boolean getIsHidden() {
        return isHidden;
    }
}
