package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

public class InfoLabel extends SpaceRunnerLabel {

    private final int PREF_HEIGHT = 49;
    private final int PREF_WIDTH = 380;
    private final int FONT_SIZE = 23;
    private final static String BACKGROUND_IMAGE = "file:src/main/resources/view/shipchooser/blue_button13.png";

    public InfoLabel(String text) {
        super(text);
        setLabelFont(FONT_SIZE);
        setPrefHeight(PREF_HEIGHT);
        setPrefWidth(PREF_WIDTH);
        setLabelBackground();
    }

    public InfoLabel(String text, boolean isBackground) {
        super(text);
        setLabelFont(FONT_SIZE);
        setPrefHeight(PREF_HEIGHT);
        setPrefWidth(PREF_WIDTH);
        if(isBackground){
            setLabelBackground();
        }
    }

    public void setLabelBackground() {
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, PREF_WIDTH, PREF_HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        setBackground(new Background(backgroundImage));
    }


}
