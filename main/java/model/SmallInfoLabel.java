package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

public class SmallInfoLabel extends SpaceRunnerLabel {

    private final String FONT_PATH = "src/main/resources/model/kenvector_future.ttf";
    private final String BACKGROUND_IMAGE = "file:src/main/resources/model/game_models/buttonBlue.png";

    private final int PREF_WIDTH = 145;
    private final int PREF_HEIGHT = 50;
    private final int FONT_SIZE = 15;


    public SmallInfoLabel(String text) {
        super(text);
        setLabelFont(FONT_SIZE);
        setPrefWidth(PREF_WIDTH);
        setPrefHeight(PREF_HEIGHT);
        setLabelBackground();
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 10, 10, 10));
    }

    private void setLabelBackground(){
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, PREF_WIDTH, PREF_HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(backgroundImage));
    }
}
