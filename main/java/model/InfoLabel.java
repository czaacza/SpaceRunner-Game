package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InfoLabel extends SpaceRunnerLabel {

    private final int PREF_HEIGHT = 49;
    private final int PREF_WIDTH = 380;
    private final int FONT_SIZE = 23;

    public InfoLabel(String text) {
        super(text);
        setPrefHeight(PREF_HEIGHT);
        setPrefWidth(PREF_WIDTH);
        setLabelBackground();
    }

    private void setLabelBackground() {
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, PREF_WIDTH, PREF_HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        setBackground(new Background(backgroundImage));
    }

    protected void setLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), FONT_SIZE));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", FONT_SIZE));
        }
    }

}
