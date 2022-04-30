package model;


import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class DefaultSubScene extends SpaceRunnerSubScene{

    public DefaultSubScene() {
        super();
        this.moveSubScene();

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(40);

        LogoLabel titleLabel = new LogoLabel("SPACE RUNNER");
        AuthorLabel authorLabel = new AuthorLabel("Author:\nMateusz Czarnecki\n(czaacza)");

        vbox.setLayoutX(45);
        vbox.setLayoutY(90);

        vbox.getChildren().add(titleLabel);
        vbox.getChildren().add(authorLabel);

        getPane().getChildren().add(vbox);

    }

    public void hideSubScene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        transition.setToX(-676);
    }


}
