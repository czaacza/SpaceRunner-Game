package model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public abstract class SpaceRunnerLabel extends Label {
    protected final static String FONT_PATH = "src/main/resources/model/kenvector_future.ttf";
    protected final static String BACKGROUND_IMAGE = "file:src/main/resources/view/shipchooser/blue_button13.png";

    public SpaceRunnerLabel(String text) {
        setText(text);
        setWrapText(true);
        setAlignment(Pos.CENTER);
        setLabelFont();
    }

    abstract void setLabelFont();
}
