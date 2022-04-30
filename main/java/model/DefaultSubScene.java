package model;


import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class DefaultSubScene extends SpaceRunnerSubScene{

    public DefaultSubScene() {
        super();
        this.moveSubScene();

        LogoLabel titleLabel = new LogoLabel("SPACE RUNNER");
        titleLabel.setLayoutX(45);
        titleLabel.setLayoutY(50);

        getPane().getChildren().add(titleLabel);

    }

    public void hideSubScene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        transition.setToX(-676);
    }


}
