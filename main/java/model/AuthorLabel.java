package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Paint;

public class AuthorLabel extends SpaceRunnerLabel{

    protected final String BACKGROUND_IMAGE = "file:src/main/resources/view/menu/grey_button04.png";

    private final int PREF_HEIGHT = 120;
    private final int PREF_WIDTH = 420;
    private final int FONT_SIZE = 23;

    public AuthorLabel(String text) {
        super(text);
        setTextFill(Paint.valueOf("#EEEEEE"));
        setPrefHeight(PREF_HEIGHT);
        setPrefWidth(PREF_WIDTH);
        setLabelFont(FONT_SIZE);
//        setLabelBackground();
    }


    private void setLabelBackground() {
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, PREF_WIDTH, PREF_HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        setBackground(new Background(backgroundImage));
    }


}
