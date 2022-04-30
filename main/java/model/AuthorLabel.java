package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AuthorLabel extends SpaceRunnerLabel{

    protected final String BACKGROUND_IMAGE = "file:src/main/resources/view/grey_button04.png";

    private final int PREF_HEIGHT = 140;
    private final int PREF_WIDTH = 420;
    private final int FONT_SIZE = 23;

    public AuthorLabel(String text) {
        super(text);
        setPrefHeight(PREF_HEIGHT);
        setPrefWidth(PREF_WIDTH);
        setLabelBackground();
    }

    @Override
    void setLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), FONT_SIZE));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", FONT_SIZE));
        }
    }

    private void setLabelBackground() {
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, PREF_WIDTH, PREF_HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        setBackground(new Background(backgroundImage));
    }
}
